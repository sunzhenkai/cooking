# coding: utf-8
from enum import Enum


class A(Enum):
    DA = 'hello'
    DB = 'world'
    _other = 'ot'

    def f(self):
        return self.DA

    @classmethod
    def fb(cls):
        return cls.DB


print(A.DA.value)
print(A.DB.value)
print(A.fb())
