#! /usr/local/bin/python3
#This file is used for testing to make sure output to browser is as expected

import cgi
form_data = cgi.FieldStorage()
#import cgitb
#cgitb.enable()
email = form_data['email'].value

print('Content-type: text/html\n')
print('<html> <head><title>Hello World</title></head><body>')

print('<p>This is the email info: ' + email)
print('</p></body>')
print('</html>')