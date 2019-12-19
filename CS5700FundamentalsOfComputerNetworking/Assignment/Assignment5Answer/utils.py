import struct


# parse the communication message
def parse_msg(msg):
    exprs = []

    start = 0
    (msg_type,) = struct.unpack('!B', msg[start: start + 1])
    start += 1
    exprs.append(msg_type)
    print('msg type: ', msg_type)

    if msg_type == 1 or msg_type == 2 or msg_type == 3:
        (id_len,) = struct.unpack('!B', msg[start: start + 1])
        start += 1
        (name_len,) = struct.unpack('!B', msg[start: start + 1])
        start += 1

        game_id = (msg[start: start + id_len]).decode('utf-8')
        start += id_len
        print('game id: ', game_id)
        exprs.append(game_id)

        nick_name = (msg[start: start + name_len]).decode('utf-8')
        print('nick name: ', nick_name)
        start += name_len
        exprs.append(nick_name)

        if msg_type == 1 or msg_type == 2:
            (port_number,) = struct.unpack('!h', msg[start: start + 2])
            print('port_number: ', port_number)
            exprs.append(port_number)
        else:
            (direction,) = struct.unpack('!B', msg[start: start + 1])
            print('direction: ', direction)
            exprs.append(direction)

    elif msg_type == 4 or msg_type == 5:
        # return exprs
        print(msg_type)

    elif msg_type == 6:  # game over
        (msg_result,) = struct.unpack('!B', msg[start: start + 1])
        start += 1
        exprs.append(msg_result)

        if msg_result == 1:
            (name_len,) = struct.unpack('!B', msg[start: start + 1])
            start += 1

            winner_nick_name = (msg[start: start + name_len]).decode('utf-8')
            exprs.append(winner_nick_name)

    elif msg_type == 7:  # game status update
        seq_num = struct.unpack('!B', msg[start: start + 1])
        start += 1
        exprs.append(seq_num)

        apple_row_num = struct.unpack('!B', msg[start: start + 1])
        start += 1
        exprs.append(apple_row_num)

        apple_col_num = struct.unpack('!B', msg[start: start + 1])
        start += 1
        exprs.append(apple_col_num)

        snake1_status_map_info = msg[start: start + 128]
        start += 128
        print('row_status_map_info: ', snake1_status_map_info)
        # snake1_status_map = struct.unpack('!i', snake1_status_map_info)
        snake1_status_map = []
        for i in range(0, 128, 4):
            snake1_status_map.append(struct.unpack('!I', snake1_status_map_info[i:i + 4])[0])
        exprs.append(snake1_status_map)

        snake2_status_map_info = msg[start: start + 128]
        snake2_status_map = []
        for i in range(0, 128, 4):
            snake2_status_map.append(struct.unpack('!I', snake2_status_map_info[i:i + 4])[0])
        exprs.append(snake2_status_map)

    print('parsed msg: ', exprs)
    return exprs
