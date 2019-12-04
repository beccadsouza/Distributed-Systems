# import numpy as np

process_count = 5

timestamp_arr = [0]*process_count
request_arr = [0]*process_count
replied = [[0]*process_count]*process_count
deferred = [[0]*process_count]*process_count

def request(i, j, timestamp):
	if request_arr[j] != 0:
		if timestamp_arr[j]>timestamp:
			reply(i,j)
		else:
			deferred[j][i] = 1
	else:
		reply(i,j)


def reply(i,j):
	replied[i][j] = 1
	print("Process",i,"received reply from process",j)

def request_cs(i, timestamp):
	request_arr[i] = 1
	timestamp_arr[i] = timestamp
	for j in range(process_count):
		if j!=i:
			request(i, j, timestamp)

def enter_cs(i):
	cs = False
	for j in range(process_count):
		if j!=i and replied[i][j] == 0:
			cs = True

	if cs == False:
		print("Process",i,"is entering CS")
		release_cs(i)

def release_cs(i):
	print("Process",i,"is releasing CS")
	request_arr[i] = 0
	for j in range(process_count):
		if deferred[i][j] == 1:
			reply(j,i)
			deferred[i][j] = 0

request_arr[1] = 1
timestamp_arr[1] = 5
print("Process 1 is executing CS")
request_cs(2,10)
release_cs(1)
enter_cs(2)