#! /usr/local/bin/python3
#file for the links to I am not usre really. I assume its the symbols that you create.

import os
import glob

def split_stuff(thing):
	(folder, le_file) = thing.split('/')
	return le_file
	
def print_list():
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/')
	num_of_hw = 4 # I'm using this to make it easier to show if the number of HW's increases, changing this single number makes it easier to add another folder
	el_files = []
	count = 1
	while count <= 4:
		globber = 'a'+str(count)+'/*'
		el_files.extend(glob.glob(globber))
		count = count + 1	
		
	return el_files
	

el_files = print_list()
html_string = ''
for each_file in el_files:
	html_string = html_string + '<input type=\"radio\" name=\"file\" value=\"'+each_file+'\" /> '+ split_stuff(each_file)+'<br />\n'

#************************WHATS PRINTED OUT***********************	
print('Content-type: text/html\n')
print('<html>')
print('<head><title>Homework 5</title></head>')
print('<body>')
print("<p>"+os.getcwd()+"</p>")
print("<h1>Pick one to go to file</h1>")
print('<form method=\"POST\" action=\"print_file.py\">')
	
print(html_string)
print('<input type=\"submit\" value=\"Choose this file\" />')

print('</form></body></html>')
