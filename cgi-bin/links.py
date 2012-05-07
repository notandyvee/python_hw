#! /usr/local/bin/python3
#file for the links to I am not usre really. I assume its the symbols that you create.

import os
import glob

def split_stuff(thing):
	(folder, le_file) = thing.split('/')
	return le_file
	
def print_list():
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/')
	num_of_hw = 5 # I'm using this to make it easier to show if the number of HW's increases, changing this single number makes it easier to add another folder
	el_files = []
	count = 1
	while count <= num_of_hw:
		globber = 'a'+str(count)+'/*'
		el_files.extend(glob.glob(globber))
		count = count + 1	
		
	return el_files
	

el_files = print_list()
html_string = ''
for each_file in el_files:
	if split_stuff(each_file) != 'symbols':
		html_string = html_string + '<input type=\"radio\" name=\"file\" value=\"'+each_file+'\" /> <span>'+ split_stuff(each_file)+'</span><br />\n'
	else:
		html_string = html_string + '<input type=\"radio\" name=\"file\" value=\"'+each_file+'\" /> <span class=\"boldy\">'+ split_stuff(each_file)+'</span><br />\n'

#************************WHATS PRINTED OUT***********************	
print('Content-type: text/html\n')
print('<html>')
print('<head><title>Homework 5</title><style>body{background-color: gray; color: black;} h1{text-align: center;} .boldy {font-weight: bold;}</style></head>')
print('<body>')
print("<h1>Pick one to go to file (The \'Symbols\' files belong to any preceding code with extensions)</h1>")
print('<form method=\"POST\" action=\"print_file.py\">')
	
print(html_string)
print('<input type=\"submit\" value=\"Choose this file\" />')

print('</form></body></html>')
