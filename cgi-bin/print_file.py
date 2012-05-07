#! /usr/local/bin/python3

import os
import cgi
form_data = cgi.FieldStorage()

le_file = form_data['file'].value
os.chdir('/Users/Zeroe/Documents/python_hw/csc344/')

print('Content-type: text/html\n')
print('<html>')
print('<head><title>Homework 5</title></head>')
print('<body><p>')

with open(le_file) as f:
	for each_line in f:
		print(each_line+'<br />')

print('</p></body></html>')