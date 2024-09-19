# coding: utf-8

import json

with open('t.json', 'r') as f:
    js = json.load(f)
    for k, v in js.items():
        print(k, v)
