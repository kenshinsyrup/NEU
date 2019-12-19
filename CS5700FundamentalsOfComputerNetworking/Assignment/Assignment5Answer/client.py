import socket
import struct
import sys
import threading

import pygame

import utils

pygame.init()

WHITE = 255, 255, 255
BLACK = 0, 0, 0
RED = 255, 0, 0
GREEN = 0, 255, 0
BLUE = 0, 0, 255
PINK = 255, 20, 147
ORANGE = 255, 165, 0

SNAKE_SIZE = 20
ROWS = 32
COLS = 32


class Client:
    def __init__(self, game_id, nick_name, port_number):
        # socket connection
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        # server information
        self.server_name = 'localhost'
        self.server_port = port_number
        self.server_address = ('localhost', port_number)

        # game
        self.game_id = game_id
        self.nick_name = nick_name
        self.game_start = False

        # Initialize game board surface
        self.rows = ROWS
        self.cols = COLS
        self.surface = pygame.display.set_mode((self.cols * SNAKE_SIZE, self.rows * SNAKE_SIZE))
        pygame.display.set_caption('Snake Game')
        self.surface.fill(WHITE)
        pygame.display.flip()

    # normal render: the game board with apple and two snakes
    def draw_board(self, exprs):
        print('draw board...')
        (seq_num,) = exprs[1]
        (apple_row_num,) = exprs[2]
        (apple_col_num,) = exprs[3]
        snake1_status_map = exprs[4]
        snake2_status_map = exprs[5]
        print('client receive apple_row_num: ', apple_row_num)
        print('client receive apple_col_num: ', apple_col_num)
        print('client receive snake1_status_map: ', snake1_status_map, ' len: ', len(snake1_status_map))
        print('client receive snake2_status_map: ', snake2_status_map, ' len: ', len(snake2_status_map))

        # render: in status_map, every int num represents the row number in board,
        # every bit in num represents the col number in board
        snake1_body = []
        snake2_body = []
        for i in range(ROWS):
            snake1_status_i = snake1_status_map[i]
            snake2_status_i = snake2_status_map[i]
            j = 0
            while snake1_status_i > 0:
                if snake1_status_i & 1 == 1:
                    snake1_body.append((i, j))
                # check next bit
                snake1_status_i = snake1_status_i >> 1
                j += 1

            k = 0
            while snake2_status_i > 0:
                if snake2_status_i & 1 == 1:
                    snake2_body.append((i, k))
                # check next bit
                snake2_status_i = snake2_status_i >> 1
                k += 1

        self.render((apple_row_num, apple_col_num), snake1_body, snake2_body)

    # render game board with apple and two snakes
    def render(self, apple, snake1_body, snake2_body):
        self.surface.fill(WHITE)

        self.draw_rect(apple, RED)

        for pos in snake1_body:
            self.draw_rect(pos, GREEN)
        for pos in snake2_body:
            self.draw_rect(pos, ORANGE)

        pygame.display.flip()

    # draw a SNAKE_SIZE square rect with given position and color
    def draw_rect(self, pos, color):
        row, col = pos
        pygame.draw.rect(self.surface, color,
                         (row * SNAKE_SIZE + 1, col * SNAKE_SIZE + 1, SNAKE_SIZE - 2, SNAKE_SIZE - 2))

    # waiting for opponent render
    def draw_waiting(self):
        self.surface.fill(WHITE)
        assert pygame.font.get_init()
        font = pygame.font.Font(None, 60)
        text = font.render("waiting for opponent", True, BLUE)
        text_rect = text.get_rect()
        text_x = self.surface.get_width() / 2 - text_rect.width / 2
        text_y = self.surface.get_height() / 2 - text_rect.height / 2
        self.surface.blit(text, [text_x, text_y])
        pygame.display.flip()

    # starting warning render
    def draw_starting(self):
        self.surface.fill(WHITE)
        assert pygame.font.get_init()
        font = pygame.font.Font(None, 60)
        text = font.render("game is about to start", True, BLUE)
        text_rect = text.get_rect()
        text_x = self.surface.get_width() / 2 - text_rect.width / 2
        text_y = self.surface.get_height() / 2 - text_rect.height / 2
        self.surface.blit(text, [text_x, text_y])
        pygame.display.flip()

    # game over render: if winner is '' then is a draw
    def draw_game_over(self, winner):
        assert pygame.font.get_init()
        font = pygame.font.Font(None, 60)
        if winner != '':
            text = font.render(winner + ' is winner', True, BLUE)
        else:
            text = font.render("It is a draw", True, BLUE)
        text_rect = text.get_rect()
        text_x = self.surface.get_width() / 2 - text_rect.width / 2
        text_y = self.surface.get_height() / 2 - text_rect.height / 2
        self.surface.blit(text, [text_x, text_y])
        pygame.display.flip()

    def connect_to_server(self, action):
        msg = b''
        if action == 'create':
            # do create
            msg += struct.pack('!B', 1)  # 1 byte for type

        elif action == 'join':
            # do join
            msg += struct.pack('!B', 2)  # 1 byte for type

        else:
            self.socket.close()

        msg += struct.pack('!B', len(self.game_id))  # 1 byte for game_id length
        msg += struct.pack('!B', len(self.nick_name))  # 1 byte for player nick_name length
        msg += self.game_id.encode('utf-8')  # many bytes for player game_id
        msg += self.nick_name.encode('utf-8')  # many bytes for player nick_name
        msg += struct.pack('!h', self.server_port)  # 2 bytes for port number

        print('action msg to server: ', msg)
        self.socket.sendto(msg, self.server_address)

    # player take a move its snake: # 'UP', 'RIGHT', 'DOWN', 'LEFT'
    def take_move(self, dire):
        move_msg = struct.pack('!B', 3)  # 1 byte for type
        move_msg += struct.pack('!B', len(self.game_id))  # 1 byte for game_id length
        move_msg += struct.pack('!B', len(self.nick_name))  # 1 byte for player nick_name length
        move_msg += self.game_id.encode('utf-8')  # many bytes for player game_id
        move_msg += self.nick_name.encode('utf-8')  # many bytes for player nick_name
        if dire == 'UP':
            direction = 0
        elif dire == 'RIGHT':
            direction = 1
        elif dire == 'DOWN':
            direction = 2
        else:
            direction = 3
        move_msg += struct.pack('!B', direction)  # 1 byte for direction

        print('move msg to server: ', move_msg)
        self.socket.sendto(move_msg, self.server_address)

        print('read back game status...')
        game_status_msg, addr = self.socket.recvfrom(1024)

        print('client receive game_status_msg: ', game_status_msg)

        exprs = utils.parse_msg(game_status_msg)
        print('client receive exprs: ', exprs)

        game_status_msg_type = exprs[0]
        if game_status_msg_type == 6:
            # game over
            print('game over')
            result = exprs[1]
            if result == 0:
                winner = ''
            else:
                winner = exprs[2]
            self.draw_game_over(winner)

        elif game_status_msg_type == 7:
            # draw board
            self.draw_board(exprs)

    def listen_server(self):
        while True:
            print('client is listening server..., game start: ', self.game_start)

            game_status_msg, addr = self.socket.recvfrom(1024)
            print('client receive game_status_msg: ', game_status_msg)

            exprs = utils.parse_msg(game_status_msg)
            game_status_msg_type = exprs[0]
            action_res_msg_type = exprs[0]
            if action_res_msg_type == 4:
                # wait for opponent, draw waiting
                print('wait for opponent')
                self.draw_waiting()

            elif action_res_msg_type == 5:
                # wait for start, draw starting and set start true
                print('wait for start')
                self.game_start = True
                self.draw_starting()

            elif game_status_msg_type == 6:
                # game over
                print('game over')
                result = exprs[1]
                if result == 0:
                    winner = ''
                else:
                    winner = exprs[2]
                self.draw_game_over(winner)

                self.socket.close()
                break

            elif game_status_msg_type == 7:
                self.draw_board(exprs)

    def listen_keyboard(self):
        clock = pygame.time.Clock()
        FPS = 10  # frames-per-second
        while True:
            if self.game_start:
                clock.tick(FPS)
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                        pygame.quit()

                # if get a move from keyboard
                move = ''
                keys = pygame.key.get_pressed()
                if keys[pygame.K_RIGHT]:
                    move = 'RIGHT'
                elif keys[pygame.K_LEFT]:
                    move = 'LEFT'
                elif keys[pygame.K_UP]:
                    move = 'UP'
                elif keys[pygame.K_DOWN]:
                    move = 'DOWN'

                if move == 'RIGHT' or move == 'LEFT' or move == 'UP' or move == 'DOWN':
                    self.take_move(move)


if __name__ == "__main__":
    # read parameter from command line
    action = sys.argv[1]
    game_id = sys.argv[2]
    nick_name = sys.argv[3]
    port_number = int(sys.argv[4])

    # init Client and connect to server
    client = Client(game_id, nick_name, port_number)
    client.connect_to_server(action)

    # listen multicast message from server in background thread
    threading.Thread(target=client.listen_server).start()

    # monitor user keyboard control
    client.listen_keyboard()
