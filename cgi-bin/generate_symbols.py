#! /usr/local/bin/python3

import file_readers

#ggenerate symbols files for each one
file_readers.parse_c_file()

file_readers.parse_lisp_file()

file_readers.parse_scala_file()

file_readers.parse_prolog_file()

file_readers.parse_python_file()

print('Content-type: text/html\n')
print('<html>')
print('<head><title>Homework 5</title><style>body{background-color: gray; color: black;}h1{text-align: center;}</style></head>')
print('<body>')

print('<h2>Successfully Generated Symbols</h2>')
print('<p><a href=\"../index.html\">Go Back to HomePage</a></p>')

print('</body></html>')