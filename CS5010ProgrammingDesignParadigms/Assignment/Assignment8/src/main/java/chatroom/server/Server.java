package chatroom.server;

import chatroom.message.Message;
import chatroom.util.ChatLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


/**
 * A network server that communicates with IM clients that connect to it. This
 * version of the server spawns a new thread to handle each client that connects
 * to it. At this point, messages are broadcast to all of the other clients. It
 * does not send a response when the user has gone off-line.
 */
public abstract class Server {

    /**
     * Don't do anything unless the server is ready.
     */
    private static boolean isReady = false;

    /**
     * Collection of threads that are currently being used.
     */
    public static ConcurrentLinkedQueue<ClientRunnable> active;

    /** All of the static initialization occurs in this "method" */
    static {
        // Create the new queue of active threads.
        active = new ConcurrentLinkedQueue<>();
    }

    /**
     * Broadcast a given message to all the other IM clients currently on the
     * system. This message _will_ be sent to the client who originally sent it.
     *
     * @param message Message that the client sent.
     */
    public static void broadcastMessage(Message message) {
        System.out.println("broadcastMessage: " + message);
        // Loop through all of our active threads
        for (ClientRunnable tt : active) {
            System.out.println("tt: " + tt.getName() + " init: " + tt.isInitialized());
            // Do not send the message to any clients that are not ready to receive it.
            if (tt.isInitialized()) {
                tt.enqueueMessage(message);
            }
        }
    }

    /**
     * Send a direct message to a client.
     * @param message is given message.
     */
    public static void directMessage(Message message) {
        System.out.println("directMessage: " + message);
        // Loop through all of our active threads
        for (ClientRunnable tt : active) {
            System.out.println("tt: " + tt.getName() + " init: " + tt.isInitialized());
            // Do not send the message to any clients that are not ready to receive it.
            if (tt.isInitialized() && tt.getName() != null && tt.getName().equals(message.getRecipient())) {
                tt.enqueueMessage(message);
            }
        }
    }

    /**
     * Remove the given IM client from the list of active threads.
     *
     * @param dead Thread which had been handling all the I/O for a client who has
     *             since quit.
     */
    public static void removeClient(ClientRunnable dead) {
        // Test and see if the thread was in our list of active clients so that we
        // can remove it.
        if (!active.remove(dead)) {
            ChatLogger.info("Could not find a thread that I tried to remove!\n");
        }
    }

    /**
     * Terminates the server.
     */
    public static void stopServer() {
        isReady = false;
    }

    /**
     * Start up the threaded talk server. This class accepts incoming connections on
     * a specific port specified on the command-line. Whenever it receives a new
     * connection, it will spawn a thread to perform all of the I/O with that
     * client. This class relies on the server not receiving too many requests -- it
     * does not include any code to limit the number of extant threads.
     *
     * @param args String arguments to the server from the command line. At present
     *             the only legal (and required) argument is the port on which this
     *             server should list.
     * @throws IOException Exception thrown if the server cannot connect to the port
     *                     to which it is supposed to listen.
     */
    public static void main(String[] args) {
        // Connect to the socket on the appropriate port to which this server connects.
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.configureBlocking(false);
            serverSocket.socket().bind(new InetSocketAddress(ServerConstants.PORT));
            // Create the Selector with which our channel is registered.
            Selector selector = SelectorProvider.provider().openSelector();
            // Register to receive any incoming connection messages.
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            // Create our pool of threads on which we will execute.
            ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(ServerConstants.THREAD_POOL_SIZE);

            System.out.println("ChatRoom is open on localhost:" + ServerConstants.PORT);

            // If we get this far than the server is initialized correctly
            isReady = true;
            // Now listen on this port as long as the server is ready
            while (isReady) {
                // Check if we have a valid incoming request, but limit the time we may wait.
                while (selector.select(ServerConstants.DELAY_IN_MS) != 0) {
                    // Get the list of keys that have arrived since our last check
                    Set<SelectionKey> acceptKeys = selector.selectedKeys();
                    // Now iterate through all of the keys
                    Iterator<SelectionKey> it = acceptKeys.iterator();
                    while (it.hasNext()) {
                        // Get the next key; it had better be from a new incoming connection
                        SelectionKey key = it.next();
                        it.remove();
                        // Assert certain things I really hope is true
                        assert key.isAcceptable();
                        assert key.channel() == serverSocket;
                        // Create new thread to handle client for which we just received request.
                        createClientThread(serverSocket, threadPool);
                    }
                }
            }
        } catch (IOException ex) {
            ChatLogger.error("Fatal error: " + ex.getMessage());
            throw new IllegalStateException(ex.getMessage());
        }
    }

    /**
     * Create a new thread to handle the client for which a request is received.
     *
     * @param serverSocket The channel to use.
     * @param threadPool   The thread pool to add client to.
     */
    private static void createClientThread(ServerSocketChannel serverSocket, ScheduledExecutorService threadPool) {
        try {
            // Accept the connection and create a new thread to handle this client.
            SocketChannel socket = serverSocket.accept();
            // Make sure we have a connection to work with.
            if (socket != null) {
                NetworkConnection connection = new NetworkConnection(socket);
                ClientRunnable tt = new ClientRunnable(connection);

                // Add the thread to the queue of active threads
                active.add(tt);

                // Have the client executed by our pool of threads.
                ScheduledFuture<?> clientFuture = threadPool.scheduleAtFixedRate(tt, ServerConstants.CLIENT_CHECK_DELAY,
                        ServerConstants.CLIENT_CHECK_DELAY, TimeUnit.MILLISECONDS);
                tt.setFuture(clientFuture);
            }
        } catch (AssertionError ae) {
            ChatLogger.error("Caught Assertion: " + ae.toString());
        } catch (IOException e) {
            ChatLogger.error("Caught Exception: " + e.toString());
        }
    }

    /**
     * Check given name is valid.
     * @param name is given name.
     * @return true if valid.
     */
    public static String checkUserNameValid(String name) {
        if (name == null) {
            return "Name could not be null.";
        }

        for (char ch : name.toCharArray()) {
            if (!Character.isDigit(ch) && !Character.isAlphabetic(ch)) {
                return "Only digit or alphabet is allowed!";
            }
        }

        if (active != null && active.size() != 0) {
            for (ClientRunnable tt : active) {
                if (tt.getName() != null && tt.getName().equals(name)) {
                    return "Such name is chosen by another user.";
                }
            }
        }

        return "";
    }

    /**
     * Current all connected users number.
     * @return number.
     */
    public static int currentActiveNum() {
        return active.size();
    }

    /**
     * Check given user exist in current connected users.
     * @param name is given user name.
     * @return true if exist.
     */
    public static boolean checkUseExist(String name) {
        if (name == null) {
            return false;
        }

        if (active != null && active.size() != 0) {
            for (ClientRunnable tt : active) {
                if (tt.getName() != null && tt.getName().equals(name)) {
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * Get the current connected user list except given user.
     * @param name is given user name.
     * @return list.
     */
    public static List<String> getConnectedUserList(String name) {
        List<String> users = new ArrayList<>();
        if (active != null && active.size() != 0) {
            System.out.println("active total: " + active.size());
            for (ClientRunnable tt : active) {
                if (tt.isInitialized() && tt.getName() != null && !tt.getName().equals(name)) {
                    users.add(tt.getName());
                }
            }
        }

        return users;
    }
}