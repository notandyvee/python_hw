import os
import re
#***********************START OF FUNCTION***********************************
def clean_list(the_list):
	#this function takes lists within a list and essentially relists it with just one main list
	holder = []
	for each_index in the_list:
		if re.search('^[a-zA-Z]', each_index):
			holder.append(each_index)
					
	#check to see which list to use to strip away any uneccessary symbols		
#	if string == 'c':
#		da_list = ['char', 'double', 'float', 'int', 'long', 'short', 'struct', 'include', 'define', 'if', 'while', 'for', 'else', 'NULL', 'EOF', 'FILE']
#		do_this = True
#	elif string == 'lisp':
#		#da_list = ['defun', 'if']
#		do_this = False
#	elif string == 'scala':
#		da_list = []
#	elif string == 'prolog':
#		da_list == []
#	elif string == 'python':
#		da_list == []
		
#	holder2 = []
#	test = False
#	for each_index in holder:
#		for sym in da_list:
#			if each_index == sym:
#				test = True
#		if test == False:
#			holder2.append(each_index)
#		test = False	
	
	#just removes duplicates
#	holder2 = set(holder2)
	holder = set(holder)
	return holder	
	
#***************************END OF FUNCTION**********************************





#*********************START FUNCTION*******************************************
def parse_c_file():
	#make sure the current working directory is where the C file is
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/a1')
	if os.getcwd() == '/Users/Zeroe/Documents/python_hw/csc344/a1':
		print("Successfully made it to the file that contains the c code")
	else:
		print("Failed to get to the page for some reason")	
			
	#now you can open the file and start reading the c file
	ch = ''
	list_holder = [];	
	with open('hw1.c') as f:
		while True:
			c = f.read(1)
			if not c:
				print("End of File")
				break
			if re.search("\w", c):
				ch = ch + c
			elif ch != '':
				list_holder.append(ch)
				ch = ''
	list_holder = clean_list(list_holder)
	
	#write the file symbols	
	with open('symbols', "w") as file_to_write:
		#add iteration, which should definitely be for loop
		for each_index in list_holder:
			print("[hw1.c, " + each_index + "]\n", file=file_to_write)		
#*************************END OF FUNCTION*************************************	

#**************************START OF FUNCTION*********************************	
def parse_lisp_file():
	#make sure the current working directory is where the C file is
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/a2')
	if os.getcwd() == '/Users/Zeroe/Documents/python_hw/csc344/a2':
		print("Successfully made it to the file that contains the lisp code")
	else:
		print("Failed to get to the page for some reason")
	
	#now you can open the file and start reading the c file	
	ch = ''
	list_holder = [];	
	with open('hw2.lisp') as f:
		while True:
			c = f.read(1)
			if not c:
				print("End of File")
				break
			if re.search("\w", c):
				ch = ch + c
			elif ch != '':
				list_holder.append(ch)
				ch = ''
	list_holder = clean_list(list_holder)
	
	#write the file symbols	
	with open('symbols', "w") as file_to_write:
		#add iteration, which should definitely be for loop
		for each_index in list_holder:
			print("[hw1.c, " + each_index + "]\n", file=file_to_write)
	
#******************************END OF FUNCTION*********************************	
	
def parse_scala_file():
	#make sure the current working directory is where the C file is
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/a3')
	if os.getcwd() == '/Users/Zeroe/Documents/python_hw/csc344/a3':
		print("Successfully made it to the file that contains the scala code")
	else:
		print("Failed to get to the page for some reason")

	#now you can open the file and start reading the c file	
