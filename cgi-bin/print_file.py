#! /usr/local/bin/python3

import cgi
form_data = cgi.FieldStorage()

le_file = form_data['file'].value

print('Content-type: text/html\n')
print('<html>')
print('<head><title>Homework 5</title></head>')
print('<body>')

print('<p>'+le_file+'</p>')

print('</body></html>')