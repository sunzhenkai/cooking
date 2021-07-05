# coding: utf-8
import asyncio
import signal

import tornado.httpserver
import tornado.ioloop
import tornado.platform.asyncio

from engine.custome_application import RouterApplication, BaseApplication
# from handler.benchmark_handler import TornadoCoroutineHandler, NativeCoroutineHandler, NoCoroutineHandler, \
#     NativeCoroutineTaskHandler, NativeCoroutineTaskTruncationHandler
from handler.benchmark_handler import NoCoroutineHandler
from handler.timeout_handler import TimeoutHandler


def create_app():
    url_map = [
        # (r'/api/benchmark/coroutine/tornado', TornadoCoroutineHandler, {}),
        # (r'/api/benchmark/coroutine/native', NativeCoroutineHandler, {}),
        # (r'/api/benchmark/coroutine/task', NativeCoroutineTaskHandler, {}),
        # (r'/api/benchmark/coroutine/truncation', NativeCoroutineTaskTruncationHandler, {}),
        (r'/api/benchmark/coroutine/none', NoCoroutineHandler, {}),
        (r'/api/cook/timeout', TimeoutHandler, {}),
    ]

    asyncio.set_event_loop_policy(
        tornado.platform.asyncio.AnyThreadEventLoopPolicy())
    return RouterApplication(url_map)


def run_app(app: BaseApplication, port=9040):
    signal.signal(signal.SIGINT, app.signal_handler)
    signal.signal(signal.SIGTERM, app.signal_handler)
    signal.signal(signal.SIGQUIT, app.signal_handler)

    server = tornado.httpserver.HTTPServer(app)
    server.bind(port)
    server.start()
    tornado.ioloop.PeriodicCallback(app.try_exit, 300).start()
    tornado.ioloop.IOLoop.current().start()
