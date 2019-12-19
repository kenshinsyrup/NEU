### For SW protocol

#### MSG

1. Start receiver side: ```python3 msg_receiver.py sw```.

2. Start sender side: ```python3 msg_sender sw```.

3. The terminal on receiver side should show the msg.

#### File

1. Start receiver side: ```python3 msg_receiver.py sw cat.jpg```.

2. Start sender side: ```python3 msg_sender sw grumpy_cat.jpg```.

3. After sender side done, use ```Ctrl + C``` to close receiver side.

4. The cat.jpg should be show in the project directory and same as the grumpy_cat.jpg.

### For GBN protocol

#### MSG

1. Start receiver side: ```python3 msg_receiver.py gbn```.

2. Start sender side: ```python3 msg_sender gbn```.

3. Please be patient to wait, after some time, the terminal on receiver side should show the msg.

#### File

1. Start receiver side: ```python3 msg_receiver.py gbn cat.jpg```.

2. Start sender side: ```python3 msg_sender gbn grumpy_cat.jpg```.

3. After sender side done, use ```Ctrl + C``` to close receiver side, if the sender side stop sending pkts but still running, use ```Ctrl + C``` close it first.

4. The cat.jpg should be show in the project directory and same as the grumpy_cat.jpg.
