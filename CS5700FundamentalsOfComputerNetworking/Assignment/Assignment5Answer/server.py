import datetime
import random
import socket
import struct
import threading
import time

import snake
import utils

ROWS = 32
COLS = 32


class Server:
    def __init__(self):
        # socket connection
        server_address = ('localhost', 8181)
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        s.bind(server_address)
        self.socket = s

        # seq_num for game_status_msg
        self.seq_num = 0

        # game result and winner
        self.result = 0
        self.winner_nick_name = ''

        # game information
        # only support 2 players now
        self.game_id = ''
        self.user1 = ''
        self.user2 = ''
        self.snake1 = ''
        self.snake2 = ''
        self.address1 = ''
        self.address2 = ''
        # move to RIGHT by default if continue_move is active
        self.snake1_to = 2
        self.snake2_to = 2

        # timer for heart beat interval to update game status to players
        self.timer = datetime.datetime.now()

        # check game is starting or not
        self.game_start = False

        # game board
        self.rows = ROWS
        self.cols = COLS

        # Initialize snake status map
        self.snake1_status_map = []
        self.snake2_status_map = []
        for i in range(32):
            self.snake1_status_map.append(0)
            self.snake2_status_map.append(0)
        # Initialize first snake
        (snake1_row, snake1_col) = self.choose_random_pos()
        self.snake1_status_map[snake1_row] = 1 << snake1_col
        print('snake1 init: ', ' row: ', snake1_row, ' col: ', snake1_col, self.snake1_status_map[snake1_row])
        self.snake1 = snake.Snake((snake1_row, snake1_col), self.rows, self.cols)
        # Initialize second snake
        (snake2_row, snake2_col) = self.choose_random_pos()
        while (snake2_row, snake2_col) in self.snake1.body:
            (snake2_row, snake2_col) = self.choose_random_pos()
        self.snake2_status_map[snake2_row] = 1 << snake2_col
        print('snake2 init: ', ' row: ', snake2_row, ' col: ', snake2_col, self.snake2_status_map[snake2_row])
        self.snake2 = snake.Snake((snake2_row, snake2_col), self.rows, self.cols)

        # Initialize apple. Make sure it's not the same as snake start position.
        self.apple = self.choose_random_pos()
        while self.apple in self.snake1.body or self.apple in self.snake2.body:
            self.apple = self.choose_random_pos()

    def choose_random_pos(self):
        x = random.randrange(self.cols)
        y = random.randrange(self.rows)
        return x, y

    # periodically send game status to clients
    def heart_beat(self):
        print('after game start, multicast game status periodically...')
        while True:
            # print('time elapse: ', (datetime.datetime.now() - self.timer).microseconds)
            if self.game_start and (datetime.datetime.now() - self.timer).microseconds / 1000 >= 50:  # every 50 ms
                # this makes the two snakes continue moving on its direction,
                # for review, could comment it to only control snakes by hand
                # self.continue_move()

                self.multicast_game_status()

                # update timer
                self.timer = datetime.datetime.now()

    def multicast_game_status(self):
        # update board status, send status back
        game_status_msg = struct.pack('!B', 7)  # 1 byte for type
        game_status_msg += struct.pack('!B', self.seq_num)  # 1 byte for sequence nuber
        if self.seq_num == 0:
            self.seq_num = 1
        else:
            self.seq_num = 0
        game_status_msg += struct.pack('!B', self.apple[0])  # 1 byte for row # of apple
        game_status_msg += struct.pack('!B', self.apple[1])  # 1 byte for col # of apple

        # for every snake, 1 bit for a col, 32 bits represents a whole row, totally 32 row,
        # so totally 1024 bits ie 128 byte
        for snake1_status in self.snake1_status_map:
            game_status_msg += struct.pack('!I', snake1_status)
        for snake2_status in self.snake2_status_map:
            game_status_msg += struct.pack('!I', snake2_status)

        # send to two players
        if self.address1 != '':
            self.socket.sendto(game_status_msg, self.address1)
        if self.address2 != '':
            self.socket.sendto(game_status_msg, self.address2)

    # make the snake move on its direction automatically
    def continue_move(self):
        snake1_move_status = self.snake1.move(self.snake1_to, self.apple, self.snake2)
        if snake1_move_status == -1:
            self.result = 1
            self.winner_nick_name = self.user2
        if self.snake1.head() == self.apple:
            while self.apple in self.snake1.body or self.apple in self.snake2.body:
                self.apple = self.choose_random_pos()

        snake2_move_status = self.snake2.move(self.snake2_to, self.apple, self.snake1)
        if snake2_move_status == -1:
            self.result = 1
            self.winner_nick_name = self.user1
        if self.snake2.head() == self.apple:
            while self.apple in self.snake1.body or self.apple in self.snake2.body:
                self.apple = self.choose_random_pos()

        if snake1_move_status == 1 and snake2_move_status == 1:
            move_status = 1
        elif snake1_move_status == 0 and snake2_move_status == 0:
            move_status = 0
        else:
            move_status = -1

        self.process_game_status(move_status)

    # process msg from client and send response msg to them
    def process_msg(self, msg, client_address):
        conn = self.socket
        exprs = utils.parse_msg(msg)
        msg_type = exprs[0]
        game_id = exprs[1]
        nick_name = exprs[2]

        # handle write to client
        if msg_type == 1:  # create new game
            self.game_id = game_id
            self.user1 = nick_name
            self.address1 = client_address

            # wait for opponent
            wait_opponent_msg = struct.pack('!B', 4)
            conn.sendto(wait_opponent_msg, self.address1)

        elif msg_type == 2:  # join existing game
            if game_id == self.game_id:
                self.user2 = nick_name
                self.address2 = client_address

                # wait for game start
                wait_start_msg = struct.pack('!B', 5)
                conn.sendto(wait_start_msg, self.address1)
                conn.sendto(wait_start_msg, self.address2)

                # after 1 second, game start
                time.sleep(1)
                self.game_start = True

        elif msg_type == 3:
            dire = exprs[3]
            # move
            if nick_name == self.user1:
                self.snake1_to = dire
                move_status = self.snake1.move(dire, self.apple, self.snake2)
                if move_status == -1:
                    self.result = 1
                    self.winner_nick_name = self.user2
                if self.snake1.head() == self.apple:
                    while self.apple in self.snake1.body or self.apple in self.snake2.body:
                        self.apple = self.choose_random_pos()
            else:
                move_status = self.snake2.move(dire, self.apple, self.snake1)
                self.snake2_to = dire
                if move_status == -1:
                    self.result = 1
                    self.winner_nick_name = self.user1
                if self.snake2.head() == self.apple:
                    while self.apple in self.snake1.body or self.apple in self.snake2.body:
                        self.apple = self.choose_random_pos()

            print('move_status: ', move_status)
            self.process_game_status(move_status)

    def process_game_status(self, move_status):
        conn = self.socket
        # game over
        if move_status != 1:
            game_over_msg = struct.pack('!B', 6)
            game_over_msg += struct.pack('!B', self.result)
            if self.result != 0:
                game_over_msg += struct.pack('!B', len(self.winner_nick_name))
                game_over_msg += self.winner_nick_name.encode('utf-8')

            print('Server send game_over_msg: ', game_over_msg)
            # send to two players
            conn.sendto(game_over_msg, self.address1)
            conn.sendto(game_over_msg, self.address2)

            self.game_start = False

        else:
            # get snake status
            print('current snake1 has body: ', self.snake1.body)
            # get status map from snakes body, first clear
            self.snake1_status_map = []
            for i in range(32):
                self.snake1_status_map.append(0)
            # snake_status_map = self.game_info[game_id][nick_name]['status']
            for b in self.snake1.body:
                row = b[0]
                col = b[1]
                # use | to keep all the positions of 1, ie the body of snake
                self.snake1_status_map[row] = self.snake1_status_map[row] | (1 << col)

            if self.snake2 != '':
                self.snake2_status_map = []
                for i in range(32):
                    self.snake2_status_map.append(0)
                for b in self.snake2.body:
                    row = b[0]
                    col = b[1]
                    self.snake2_status_map[row] = self.snake2_status_map[row] | (1 << col)

            self.multicast_game_status()

    # listen data from clients and response with process_msg method
    def listen_clients(self):
        conn = self.socket
        while True:
            print('waiting for client msg...')
            msg, client_address = conn.recvfrom(1024)
            print('server recv: ', msg, ' client_address: ', client_address)
            self.process_msg(msg, client_address)


if __name__ == "__main__":
    server = Server()
    print('Server started. Waiting for connection...')

    # updating game status to players in background thread every 50 ms
    threading.Thread(target=server.heart_beat).start()

    # listen player actions
    server.listen_clients()
