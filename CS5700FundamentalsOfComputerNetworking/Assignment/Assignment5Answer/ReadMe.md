#### Notice: 

> 0. Virtual environment seting: python 3.6.5, pygame 2.0.0dev4. 
> 1. For the convience to debug and also for you to test the game, the sanke ONLY MOVES BY YOUR CONTROL. That means, you must press the direction keys on your keyboard to make your snake move.
If you want the snake moves by your control, and also by itself on the last input direction, you should check the server.py file and un-comment the 90 line code ```# self.continue_move()```. Then the snake will moves more like the normal snake game, but it may very hard to debug because the game will end very easy.
> 2. After the game end, the client may not be closed normally because of too fast key press or something, you should end the client and its daemon thread by force. And after the game end, you must end all the clients and server and their daemon threads, then start a brand new game to test another type of game result. For example, after test the "it is a draw" game result, you must end all clients and server and daemon threads, then start over step by step to test another game result.
> 3. Since we have no strict requirement about the robust(the workload will be too heavy), so in this project, it only support 2 players, run on the port number 8181, no check on user_name duplicate, no check on game_id existing etc.

### How to run
1. Start the server.py, it will init the status for the game.

2. Start the client.py, this will be the player1, argument should be ```create 123 master 8181```. After it's started successfully, there will be a pygame window with the following message on surface ```waiting for opponent```. You could change the game_id and user_name, but do not change the port number 8181.

3. Start the client.py, this will be the player2, argument should be ```join 123 killer 8181```. After it's started successfully, there will be another pygame window with the following message on surface ```game is about to startt```. In the meantime, the player1's window also shows this message. The message will last for about 1 second and the the game will start.

4. Use Up, Down, Left, Right on keyboard to control your snake to move and eat the red dot apple. You will lose the game if (1) your snake collide with the edge of the window, or (2) your snake collide with the tail/body of itself, or (3) your snake collide with the tail/body of your opponent’s snake. The game is considered a draw if two snakes bump heads.

5. During the game, 2 players move will appear on the both of their windows. When the game ends, the result will show on both of their windows.


