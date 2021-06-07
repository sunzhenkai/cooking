# coding: utf-8
import signal

import tornado.httpserver
import tornado.ioloop

from engine.custome_application import RouterApplication, BaseApplication
from handler.benchmark_handler import TornadoCoroutineHandler, NativeCoroutineHandler, NoCoroutineHandler, \
    NativeCoroutineTaskHandler, NativeCoroutineTaskTruncationHandler


def create_app():
    url_map = [
        (r'/api/benchmark/coroutine/tornado', TornadoCoroutineHandler, {}),
        (r'/api/benchmark/coroutine/native', NativeCoroutineHandler, {}),
        (r'/api/benchmark/coroutine/task', NativeCoroutineTaskHandler, {}),
        (r'/api/benchmark/coroutine/truncation', NativeCoroutineTaskTruncationHandler, {}),
        (r'/api/benchmark/coroutine/none', NoCoroutineHandler, {}),
    ]

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
