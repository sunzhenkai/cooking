# coding: utf-8
import threading
import time

import consul
from fastapi import FastAPI
from jinja2 import Environment, PackageLoader, select_autoescape

from handler.api import router as api_router

# from registry_v3 import start

app = FastAPI()
# start()

print("run fast api")

# jinja
env = Environment(
    loader=PackageLoader("fastapi", ""),
    autoescape=select_autoescape()
)


@app.on_event("startup")
def register():
    print('register')


@app.on_event("shutdown")
def deregister():
    print('deregister', flush=True)
    print('deregister', flush=True)
    time.sleep(3)


def reg():
    # c = consul.Consul('192.168.6.110', '8500')
    c = consul.Consul('127.0.0.1', '8500')
    c.agent.service.register(
        name='cook-fa',
        service_id='127.0.0.1:8000',
        address='127.0.0.1',
        port=8000,
        tags=[],
        # check=consul.Check().tcp('192.168.6.53', 8000, "5s", "30s", "30s"),
        check=consul.Check().ttl('1s')
    )

    def ttl_pass():
        while True:
            print(c.agent.checks().keys())
            c.agent.check.ttl_pass('service:127.0.0.1:8000')
            time.sleep(0.5)
            print('check')

    threading.Thread(target=ttl_pass, daemon=True).start()


app.include_router(api_router)
