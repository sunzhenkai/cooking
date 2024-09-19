# coding: utf-8
import os
from time import sleep
from typing import Optional

from fastapi import APIRouter

from src.go import go
from utils.log import log
from gunicorn.glogging import Logger

router = APIRouter()


@router.get("/")
def read_root():
    # start_v2()
    sleep(1)
    go()
    log.info("hello world, {}", str(os.getpid()))
    return {"Hello": "World", "work": globals()['WORK_ID']}


@router.get("/items/{item_id}")
def read_item(item_id: int, q: Optional[str] = None):
    return {"item_id": item_id, "q": q}
