#! /usr/local/bin/python3

import cgi
import os
import zipfile
import glob

#imorts related to emailing the zip
import smtplib
import zipfile
import tempfile
from email import encoders
from email.message import Message
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart

form_data = cgi.FieldStorage()
le_file = form_data['email'].value


def send_file_zipped(the_file, recipients, sender='avaldez31890@gmail.com'):
    myzip = open(the_file, 'rb')
        

    # Create the message
    themsg = MIMEMultipart()
    themsg['Subject'] = 'File %s' % the_file
    themsg['To'] = ''.join(recipients)
    themsg['From'] = sender
    themsg.preamble = 'I am not using a MIME-aware mail reader.\n'
    msg = MIMEBase('application', 'zip')
    msg.set_payload(myzip.read())
    encoders.encode_base64(msg)
    msg.add_header('Content-Disposition', 'attachment', filename=the_file)
    themsg.attach(msg)
    themsg = themsg.as_string()

    # send the message
    smtp = smtplib.SMTP_SSL("smtp.gmail.com", 465, "res-dhcp-129-3-136-245")
    smtp.login("INSERT_REAL_EMAIL_HERE", "INSERT_REAL_PASS_HERE")
    smtp.sendmail(sender, recipients, themsg)
    smtp.quit()

#******************************************************************************************************************

def split_stuff(thing):
	(folder, le_file) = thing.split('/')
	return [folder, le_file]

def isset():
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344')
	le_file = glob.glob('hw5.zip')
	if not le_file:
		return False
	else:
		return True

def return_files():		
	#Zip file here
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344')
	el_files = []
	count = 1
	while count <= 4:
		globber = 'a'+str(count)+'/*.*'
		el_files.extend(glob.glob(globber))
		count = count + 1
	return el_files


el_files = return_files()
myzip =  zipfile.ZipFile('hw5.zip', 'w', zipfile.ZIP_DEFLATED)
for each_file in el_files:
	le_list = split_stuff(each_file)
	os.chdir('/Users/Zeroe/Documents/python_hw/csc344/'+le_list[0])
	myzip.write(le_list[1])
myzip.close()
		
		

print('Content-type: text/html\n')
print('<html>')
print('<head><title>Homework 5</title></head>')
print('<body>')

if isset() == True:
	
	print('<p>'+os.getcwd()+'</p>')
	

	
	send_file_zipped('hw5.zip', le_file)
	print('<h1>Successfully sent email</h1>')
	
	
	
else:
	print('<h1>File Failed To Create File For Some Reason!</h1>')

print('</body></html>')