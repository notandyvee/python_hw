import os

#***********************START OF FUNCTION***********************************
def re_list(the_list):
	#this function takes lists within a list and essentially relists it with just one main list
	holder = []
	for each_index in the_list:
		if isinstance(each_index, list):
			for inner in each_index:
				holder.append(inner)
		else:
			holder.append(each_index.strip())
	return holder
#***************************END OF FUNCTION**********************************


#***************************START OF FUNCTION*******************************
def create_file(final_list):
	keywords = ['char','double','float','int','long','short' ,'struct']
	with open('symbols', "w") as file_to_write:
		test = False
		#add iteration, which should definitely be for loop
				



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
	list_holder = [];
	scape_holder = []	
	with open('hw1.c') as f:
		for each_line in f:
			list_holder.append(each_line.split('('))
		list_holder = re_list(list_holder)
			
	#Now you can loop through the list and parse each element and put it into another list
	#re_list it, and repeat with the other dlimiters to split on
	for each_index in list_holder:
		scape_holder.append(each_index.split(')'))
	list_holder = re_list(scape_holder)
	scape_holder = []
	
	#for each_index in list_holder:
		#scape_holder.append(each_index.split(','))
	#list_holder = re_list(scape_holder)
	#scape_holder = []
	
	for each_index in list_holder:
		scape_holder.append(each_index.split('*'))
	list_holder = re_list(scape_holder)
	scape_holder = []
	
	for each_index in list_holder:
		scape_holder.append(each_index.split('{'))
	list_holder = re_list(scape_holder)
	scape_holder = []
	
	for each_index in list_holder:
		scape_holder.append(each_index.split('}'))
	list_holder = re_list(scape_holder)
	scape_holder = []
	
	#before you do anything with the spaces inbetween identifier and symbol strip any unecessary spaces
	for each_index in list_holder:
		scape_holder.append(each_index.strip())
	list_holder = scape_holder
	scape_holder = []
	
	#finally split by the spaces so there should be only single worlds next to each other
	for each_index in list_holder:
		scape_holder.append(each_index.split(' '))
	list_holder = re_list(scape_holder)
	scape_holder = []
	
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
