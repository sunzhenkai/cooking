# coding: utf-8
from configparser import ConfigParser

cp = ConfigParser()
cp.read('sample.ini')

print(cp.sections())
print(cp.options('database'))
