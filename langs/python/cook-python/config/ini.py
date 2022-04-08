# coding: utf-8
import json
from configparser import ConfigParser

from pydantic import BaseModel


class B(BaseModel):
    b: int = 0

    def __call__(self, *args, **kwargs):
        print('call')


tb = B()
tb()

cp = ConfigParser()
cp.read('sample.ini')

print(cp.sections())
print(cp.options('database'))
print(cp.items('database'))

with open('sample.json', 'r') as f:
    cfg = json.load(f)
    print(cfg.items())
    for k, c in cfg.items():
        print(k, c)
        b = B.parse_obj(c)
        print(b.b)
        print(b.dict())

d = {(1, 2): 1}
print(d[(1, 2)])
