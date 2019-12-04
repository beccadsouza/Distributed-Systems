from random import randint
from time import time

coordinator_queue = [randint(1,11)]

coordinator = 0

print('Process {0} is the cooordinator'.format(coordinator))

pointer = 0

print('Coordinator ==> process {0} : GRANT'.format(coordinator_queue[0]))
req_time_elapsed = time_elapsed = int(time())


while len(coordinator_queue) > 0:
    #if(pointer) < len(coordinator_queue):
    process = randint(1,11)
    if process not in coordinator_queue and time() - req_time_elapsed > 0.5 and randint(0,1) % 2 == 0 :
        coordinator_queue.append(process)
        print('Process {0} ==> coordinator: REQUEST'.format(process), 'Queue: ', coordinator_queue)
        req_time_elapsed = time()

    
    #pointer += 1

    
    if  int(time()) - time_elapsed > 1 :#and time_elapsed % 2 == 0:
        coordinator_queue.pop(0)
        print('Process {0} ==> Coordinator: RELEASE'.format(coordinator_queue[0]), 'Queue: ', coordinator_queue)
        #pointer -= 1
        time_elapsed = int(time())
        if len(coordinator_queue) > 0:
            print('Coordinator ==> process {0} : GRANT'.format(coordinator_queue[0]))
