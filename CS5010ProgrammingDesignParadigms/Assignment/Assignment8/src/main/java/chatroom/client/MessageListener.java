package chatroom.client;

import chatroom.message.Message;

import java.util.EventListener;
import java.util.Iterator;

/**
 * The listener interface for receiving events whenever message(s) are sent by an IM server to this
 * user. Classes interested in receiving these events should implement this interface and register
 * with an IMConnection instance's addMessageListener method.
 */
public interface MessageListener extends EventListener {

    /**
     * Invoked in registered listeners whenever one or more messages are received from an IM server.
     * The Iterator returned by this message will go over all of the messages received from the IM
     * server since the last time this event occurred. {@code it} may refer to a different instance of
     * {@code Iterator} each time this event is raised.
     *
     * @param it {@code Iterator} that will retrieve the 1 or more messages received since
     *           the last time the listener was notified. This {@code Iterator} cannot be used to
     *           retrieve messages that arrive after the original call to this method.
     */
    public void messagesReceived(Iterator<Message> it);
}
