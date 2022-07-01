# coding: utf-8
import pydantic
from pydantic import BaseModel
import json


class Country(BaseModel):
    name: str


class Person(BaseModel):
    name: str
    age: int
    country: Country


if __name__ == '__main__':
    p = Person(name='wii', age=18, country=Country(name='china'))
    d = p.json()
    r = pydantic.parse_obj_as(Person, json.loads(d))
    print(r)
