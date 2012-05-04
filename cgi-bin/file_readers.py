import os
import re
#***********************START OF FUNCTION***********************************
def clean_list(the_list, string):
	#this function takes lists within a list and essentially relists it with just one main list
	holder = []
	for each_index in the_list:
		if re.search('^[a-zA-Z]', each_index):
			holder.append(each_index)
			
	#check to see which list to use to strip away any uneccessary symbols		
	if string == 'c':
		da_list = ['char', 'double', 'float', 'int', 'long', 'short', 'struct', 'include', 'define', 'if', 'while', 'for', 'NULL', 'EOF', 'FILE']
	elif string == 'lisp':
		da_list = []
	elif string == 'scala':
		da_list = []
	elif string == 'prolog':
		da_list == []
	elif string == 'python':
		da_list == []
		
	holder2 = []
	test = False
	for each_index in holder:
		for sym in da_list:
			if each_index == sym:
				test = True
		if test == False:
			holder2.append(each_index)
		test = False	
	
	holder2 = set(holder2)
				
	return holder2	
	
#***************************END OF FUNCTION**********************************


#***************************START OF FUNCTION*******************************
def create_file(final_list):
	with open('symbols', "w") as file_to_write:
		test = False
		#add iteration, which should definitely be for loop
		for each_index in final_list:
			print("[hw1.c, " + each_index + "]\n", file=file_to_write)
				

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
	list_holder = clean_list(list_holder, 'c')
	#return list_holder	
	create_file(list_holder)		
#*************************END OF FUNCTION*************************************	
	
def parse_lisp_file():
	#make sure the current working directory is where the C file is
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/a2')
	if os.getcwd() == '/Users/Zeroe/Documents/python_hw/csc344/a2':
		print("Successfully made it to the file that contains the lisp code")
	else:
		print("Failed to get to the page for some reason")
	
	#now you can open the file and start reading the c file	
	
	
	
	
	
	
	
	
	
def parse_scala_file():
	#make sure the current working directory is where the C file is
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/a3')
	if os.getcwd() == '/Users/Zeroe/Documents/python_hw/csc344/a3':
		print("Successfully made it to the file that contains the scala code")
	else:
		print("Failed to get to the page for some reason")

	#now you can open the file and start reading the c file	
