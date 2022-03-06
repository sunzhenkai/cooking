#!/bin/sh
gunicorn main:app -k uvicorn.workers.UvicornWorker -b 0.0.0.0:8000 -w 2 --threads 2