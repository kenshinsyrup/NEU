class Snake:
    # pos = (x, y), where x is the col offset, and y is the row offset.
    def __init__(self, pos, rows, cols):
        self.rows, self.cols = rows, cols
        self.body = [pos]
        self.dx, self.dy = 0, 0

    # Return 1 if move is okay, 0 is draw, -1 otherwise.
    def move(self, dire, apple, opponent):
        # 0 means UP, 1 means RIGHT, 2 means DOWN, 3 means LEFT
        print('move ', dire)
        if dire == 1:
            self.dx, self.dy = 1, 0
        elif dire == 3:
            self.dx, self.dy = -1, 0
        elif dire == 0:
            self.dx, self.dy = 0, -1
        elif dire == 2:
            self.dx, self.dy = 0, 1
        # else:
        #     return 1

        x, y = self.body[0]
        nx, ny = x + self.dx, y + self.dy
        print('nx: ', nx, ' ny: ', ny)

        #  You will lose the game if
        #  (1) your snake collide with the edge of the window,
        #  or (2) your snake collide with the tail/body of itself,
        #  or (3) your snake collide with the tail/body of your opponent’s snake.
        if nx == -1 or nx == 32 or ny == -1 or ny == 32:
            return -1
        if (nx, ny) in self.body:
            return -1
        if (nx, ny) in opponent.body:
            print('nx: ', nx, ' ny: ', ny, ' opponent body: ', opponent.body, ' opponent head: ', opponent.head())
            if (nx, ny) == opponent.head():
                return 0
            return -1

        self.body.insert(0, (nx, ny))
        # if not eat apple, move forward the whole body
        if not (nx, ny) == apple:
            self.body.pop()

        return 1

    # Return the head of the snake.
    def head(self):
        return self.body[0]
