## How to run

In this assignemnt, we stop all the routers manually, the ```stop``` method isn't called because it's a little complext to decide when we get the stable forwarding table we want and that part is not important to our routering algo.

All the screenshots are in the ```screenshots.zip```. And In the ```Assignemnt6 Answer.pdf```, you could see the explaination about screenshots.

If there're too many logs in console, you may want to use some command to store all the output logs into a file before start routers to avoid losing earliest logs.

## Small Network

#### Case 1-1

1. Start all routers
```
python start_router.py config/small-1
python start_router.py config/small-2
python start_router.py config/small-3
```

2. Wait and check the logs on the console, when they are stable, ie, not chage for a while, typically whthin 5 seconds, check the log for the forwarding tables.

3. Stop all the routers manually. Check the log in console.

### Case 1-2

1. Start all routers
```
python start_router.py config/small-1
python start_router.py config/small-2
python start_router.py config/small-3
```

2. Wait and check the logs on the console, when they are stable, ie, not chage for a while, typically whthin 5 seconds.

3. Change the file ```config/small-1```, in line 2, ```2,4``` to ```2,60```. 

4. Wait and check the logs on the console, when they are stable, ie, not chage for a while, typically whthin 5 seconds.

5. Change the file ```config/small-1```, in line 2, ```2,60``` to ```2,4```. 

6. Wait and check the logs on the console, when they are stable, ie, not chage for a while, typically whthin 5 seconds.

7. Stop all the routers manually. Check the log in console.

## Large Network

1. Start all routers
```
python start_router.py config/large-1
python start_router.py config/large-2
python start_router.py config/large-3
python start_router.py config/large-4
python start_router.py config/large-5
python start_router.py config/large-6
python start_router.py config/large-7
python start_router.py config/large-8
python start_router.py config/large-9
python start_router.py config/large-10
```

2. Wait and check the logs on the console, when they are stable, ie, not chage for a while, typically whthin 5 seconds, check the log for the forwarding tables.

3. Stop all the routers manually. Check the log in console.