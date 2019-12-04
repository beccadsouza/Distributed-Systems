#discard output section before running
import numpy as np

delay = np.array([5,10,15,10,15,15])
election_count = np.zeros(6)
process_count = 6

print('Process Response Time Chart: ',delay)

threshold = int(input('Pls Enter the Threshold(Less than or equal to basis): '))

def send_message(sender, rcvr, msg):
	print (str(sender) +" => "+str(rcvr) + ' ' + msg )

def election(i):
	election_count[i]+=1

def coordinator(i):
	for j in range(i):
		send_message(i+1,j+1,"Coordinator")
	print ("\nProcess",i+1,"is the coordinator")

def bully(i):
	for j in range(i,process_count):
		for k in range(j,process_count):
			if(delay[k]<=threshold and delay[j]<=threshold and j!=k):
				send_message(j+1,k+1,"Election")
				election(k)
	max_index = 0
	print ("\nElection results: ",election_count,"\n")
	for j in range(0,process_count):
		if(election_count[j]>election_count[max_index]):
			max_index = j

	if i == max_index:
		coordinator(i)
	else:
		for j in range(i,max_index):
			if(delay[j]<=10):
				send_message(max_index+1,j+1,"OK")
		coordinator(max_index)



i = int(input("Sending process: ")) - 1
j = int(input("Receiving process: ")) - 1

if (delay[j]>10):
	print("Transmission failed.")
	print("Bully algorithm started.\n")
	bully(i)