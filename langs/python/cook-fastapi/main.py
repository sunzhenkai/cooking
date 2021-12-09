# coding: utf-8


from typing import Optional

from fastapi import FastAPI

from registry_v3 import start
from registry_v2 import start as start_v2

app = FastAPI()
start()

print("run fast api")


@app.get("/")
def read_root():
    start_v2()
    return {"Hello": "World"}


@app.get("/items/{item_id}")
def read_item(item_id: int, q: Optional[str] = None):
    return {"item_id": item_id, "q": q}
