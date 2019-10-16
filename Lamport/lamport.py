process_deposit = [_*6 for _ in range(20)]
process_display = [_*3 for _ in range(20)]
process_withdraw = [_*8 for _ in range(20)]
counter = 7
print('*'*100)
print('Welcome to Reserved Bank of Rebecca'.center(100))
while True:
	counter += 1
	print('*'*100)
	print('Logical Clock values : ')
	print('Deposit Process', process_deposit)
	print('Display Process', process_display)
	print('Withdraw Process', process_withdraw)
	print('*'*100)
	print('Press 1 to deposit money in account')
	print('Press 2 to withdraw money from account')
	print('Press 3 to exit the application')

	option = int(input())

	if option == 3:
		print('*'*100)
		print('Thank you for banking with us today'.center(100))
		print('*'*100)
		break

	print('Enter account ID')
	account_id = input()
	print('Enter amount')
	amount = input()

	if option == 1:
		print('Deposit amount {0} in account {1} at time {2}'.format(amount, account_id, process_deposit[counter]))

		sender_timestamp = process_deposit[counter] + 1
		print('Time at display (receiving) process is {0}'.format(process_display[counter+1]))
		receiver_timestamp = max([sender_timestamp, process_display[counter+1]]) + 1

		if process_display[counter+2] <= receiver_timestamp:

			diff = receiver_timestamp - process_display[counter+1]

			for i in range(counter+2, len(process_display)):
				process_display[i] += diff
		process_display[counter+1] = receiver_timestamp
		print('Amount {0} has been deposited into account {1} at time {2}'.format(amount, account_id, receiver_timestamp))

	if option == 2:
		print('Withdraw amount {0} in account {1} at time {2}'.format(amount, account_id, process_withdraw[counter]))

		sender_timestamp = process_withdraw[counter] + 1
		print('Time at display (receiving) process is {0}'.format(process_display[counter+1]))
		receiver_timestamp = max([sender_timestamp, process_display[counter+1]]) + 1

		if process_display[counter+2] <= receiver_timestamp:

			diff = receiver_timestamp - process_display[counter+1]
			process_display[counter+1] = receiver_timestamp

			for i in range(counter+2, len(process_display)):
				process_display[i] += diff
		process_display[counter+1] = receiver_timestamp
		print('Amount {0} has been withdrawn from account {1} at time {2}'.format(amount, account_id, receiver_timestamp))





