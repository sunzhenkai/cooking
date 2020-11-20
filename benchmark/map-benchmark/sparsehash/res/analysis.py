#!/bin/python3

"""
@file analysis.py
@author bovenson
@date 2018-11-29 18:55:15
@desc -
"""

import os
import pathlib
import re

size_list = [4, 8, 16, 256]
map_list = ['SPARSE_HASH_MAP', 'DENSE_HASH_MAP', 'STANDARD HASH_MAP', 'STANDARD MAP', 'NARK MAP']
item_list = ['map_grow', 'map_predict/grow', 'map_replace', 'map_fetch_random', 'map_fetch_sequential', 
    'map_fetch_empty', 'map_remove', 'map_toggle', 'map_iterate']
res = [[[[] for _ in item_list] for _ in map_list] for _ in size_list]

def extract_data(data):
    _res = re.findall(r'(\d+\.\d+) ns', data)
    # print(len(_res))
    _cnt = 0
    for _i in range(len(size_list)):
        for _j in range(len(map_list)):
            for _k in range(len(item_list)):
                res[_i][_j][_k].append(float(_res[_cnt]))
                _cnt += 1


file_list = []
log_file_path = './log'
for item in os.listdir(log_file_path):
    item = os.path.join(log_file_path, item)
    if os.path.isfile(item):
        with open(item) as f:
            extract_data(f.read())

# print(res[0][0][0])
# print(res[-1][-1][-2])

# average
for _i in range(len(size_list)):
    for _j in range(len(map_list)):
        for _k in range(len(item_list)):
            _cur = res[_i][_j][_k]
            res[_i][_j][_k] = (sum(_cur) - min(_cur) - max(_cur)) / (len(_cur) - 2)

# print(res[0][0][0])
# print(res[-1][-1][-2])

def print_all():
    with open('all.txt', 'w+') as f:
        for _i in range(len(size_list)):
            for _k in range(len(item_list)):
                f.write('\n' + '+' * 20 + str(size_list[_i]) + ' byte ' + ' && ' + str(item_list[_k]) + '+' * 20)
                for _j in range(len(map_list)):
                    f.write(repr(map_list[_j]).rjust(20) + ': ' + '{0:.1f}'.format(res[_i][_j][_k]))
    

# print oper
def print_oper(oper):
    _k = item_list.index(oper)
    _file_name = os.path.join('.', 'analysis', (oper + '.txt').replace('/', ''))
    with open(_file_name, 'w+') as f:
        for _i in range(len(size_list)):
            f.write('\n' + '+' * 20 + str(size_list[_i]) + ' byte ' + ' && ' + str(item_list[_k]) + '+' * 20 + '\n')
            for _j in range(len(map_list)):
                f.write(repr(map_list[_j]).rjust(20) + ': ' + '{0:.1f}'.format(res[_i][_j][_k]) + '\n')



for _oper in item_list:
    print_oper(_oper)
