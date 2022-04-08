#!/bin/sh
gunicorn "main:app(app.ini)" -k uvicorn.workers.UvicornWorker -b 0.0.0.0:8000 -w 2 --threads 2 --log-config log.ini
