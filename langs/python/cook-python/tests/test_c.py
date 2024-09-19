#! coding: utf-8

from enum import Enum


class Color(Enum):
    RED = 'red'

print(Color.RED.value)
print(Color.RED.name)
