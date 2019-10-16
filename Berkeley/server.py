from random import *
from socket import *
HOST = '127.0.0.1'
s1 = socket(AF_INET, SOCK_STREAM)
s1.bind((HOST, 65000))
s1.listen()
conn1, addr1 = s1.accept()
print(addr1, 'connected to Server')
c1 = conn1.recv(1024)
print('Time at ', addr1, 'is', c1.decode('UTF-8'))
s2 = socket(AF_INET, SOCK_STREAM)
s2.bind((HOST, 65001))
s2.listen()
conn2, addr2 = s2.accept()
print(addr2, 'connected to Server')
c2 = conn2.recv(1024)
print('Time at ', addr2, 'is', c2.decode('UTF-8'))
while True:
    mins = int(60*random())%60
    hrs = 3 #int(24*random())%24
    print('Time at ', HOST, 'is', '{0}:{1}'.format(hrs, mins))
    c1_hrs, c1_mins = map(int, c1.decode('UTF-8').split(':'))
    c2_hrs, c2_mins = map(int, c2.decode('UTF-8').split(':'))
    time = (c1_hrs + c1_mins/60 + c2_hrs + c2_hrs/60 + hrs + mins/60)/3
    new_time = bytes('{0}:{1}'.format(int(time), int((time-int(time))*60)), 'UTF-8')
    print('All clocks to be synchronized to', new_time)
    conn1.sendall(new_time)
    conn2.sendall(new_time)
    # break
    c1 = conn1.recv(1024)
    c2 = conn2.recv(1024)