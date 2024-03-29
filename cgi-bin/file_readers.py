import os
import re
import glob

#***********************START OF FUNCTION***********************************
def clean_list(the_list):
	#this function takes lists within a list and essentially relists it with just one main list
	holder = []
	for each_index in the_list:
		if re.search('^[a-zA-Z]', each_index):
			holder.append(each_index)
					

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
	
	#now you can open the file and start reading the lisp file	
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
			print("[hw2.lisp, " + each_index + "]\n", file=file_to_write)
	
#******************************END OF FUNCTION*********************************	

#*****************************START OF FUNCTION*******************************	
def parse_scala_file():
	#make sure the current working directory is where the C file is
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/a3')
	if os.getcwd() == '/Users/Zeroe/Documents/python_hw/csc344/a3':
		print("Successfully made it to the file that contains the scala code")
	else:
		print("Failed to get to the page for some reason")

	#now you can open the file and start reading the scala file
	ch = ''
	list_holder = [];	
	with open('Driver.scala') as f:
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
			print("[Driver.scala, " + each_index + "]\n", file=file_to_write)	
#*******************************END OF FUNCTION*********************************

#********************************START OF FUNCTION**************************
def parse_prolog_file():
	#make sure the current working directory is where the C file is
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/a4')
	if os.getcwd() == '/Users/Zeroe/Documents/python_hw/csc344/a4':
		print("Successfully made it to the file that contains the scala code")
	else:
		print("Failed to get to the page for some reason")
		
	#now you can open the file and start reading the prolog file
	ch = ''
	list_holder = [];	
	with open('hw4.pl') as f:
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
			print("[hw4.pl, " + each_index + "]\n", file=file_to_write)
#*******************************END OF FUNCTION*************************************			

#********************************START OF FUNCTION**********************************

def parse_python_file():
	#make sure the current working directory is where the C file is
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/a5')
	if os.getcwd() == '/Users/Zeroe/Documents/python_hw/csc344/a5':
		print("Successfully made it to the file that contains the scala code")
	else:
		print("Failed to get to the page for some reason")
		
	#now you can open the file and start reading the python file
	file_list = []
	final_list_holder = []
	file_list.extend(glob.glob('*.py'))
	
	for each_file in file_list:
		ch = ''
		list_holder = []	
		with open(each_file) as f:
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
			final_list_holder.extend(clean_list(list_holder))
			print(final_list_holder)

	#write the file symbols	
	with open('symbols', "w") as file_to_write:
		#add iteration, which should definitely be for loop
		for each_index in final_list_holder:
			print("[hw5.py, " + each_index + "]\n", file=file_to_write)
			
#*******************************END OF FUNCTION**************************************