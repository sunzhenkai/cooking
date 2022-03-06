# coding: utf-8
import os
from os.path import dirname
import pathlib
from typing import List

current_dir = pathlib.Path(__file__)
print(current_dir)
print(current_dir.absolute())
print(current_dir.parent.absolute())
print(current_dir.parent.parent.absolute())
print(current_dir.parent.parent.parent.absolute())
print(current_dir.parent.parent.parent.parent.absolute())
print(current_dir.parent.parent.parent.parent.parent.absolute())
print(current_dir.parent.parent.parent.parent.parent.parent.absolute())

print(os.path.abspath(current_dir.absolute()))
print(os.path.abspath(str(current_dir) + "/.."))

print('------')
current_dir = os.path.abspath(pathlib.Path(__file__).absolute())
print(dirname(current_dir))
print(dirname(dirname(current_dir)))
print(dirname(dirname(dirname(current_dir))))
print(dirname(dirname(dirname(dirname(current_dir)))))


class A:
    def __init__(self, li: List[str]):
        self.li = li


a = A([])
print(a.li)
