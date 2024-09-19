# coding: utf-8
import asyncio
import signal
from concurrent.futures import ThreadPoolExecutor

import tornado.httpserver
import tornado.ioloop
import tornado.platform.asyncio

from engine.custome_application import RouterApplication, BaseApplication
# from handler.benchmark_handler import TornadoCoroutineHandler, NativeCoroutineHandler, NoCoroutineHandler, \
#     NativeCoroutineTaskHandler, NativeCoroutineTaskTruncationHandler
from handler.benchmark_handler import NoCoroutineHandler
from handler.biz_handler import BizHandler
from handler.debug import DebugHandler
from handler.heavy_task.block_main_ioloop_handler import BlockMainIOLoopHandler
from handler.heavy_task.thread_pool_handler import ThreadPoolHandler
from handler.heavy_task.thread_pool_timeout_cut_handler import CutThreadPoolTimeoutHandler
from handler.heavy_task.thread_pool_timeout_handler import ThreadPoolTimeoutHandler
from handler.heavy_task.thread_pool_timeout_native_handler import NativeThreadPoolTimeoutHandler
from handler.heavy_task.thread_pool_timeout_test_handler import TestThreadPoolTimeoutHandler
from handler.navite_handler import NativeHandler
from handler.timeout_handler import TimeoutHandler
from handler.truncation_handler import TruncationHandler


def create_app():
    url_map = [
        # (r'/api/benchmark/coroutine/tornado', TornadoCoroutineHandler, {}),
        # (r'/api/benchmark/coroutine/native', NativeCoroutineHandler, {}),
        # (r'/api/benchmark/coroutine/task', NativeCoroutineTaskHandler, {}),
        # (r'/api/benchmark/coroutine/truncation', NativeCoroutineTaskTruncationHandler, {}),
        (r'/api/benchmark/coroutine/none', NoCoroutineHandler, {}),
        (r'/api/cook/timeout', TimeoutHandler, {}),
        (r'/api/cook/heavy/block', BlockMainIOLoopHandler, {}),
        (r'/api/cook/heavy/pool', ThreadPoolHandler, {}),
        (r'/api/cook/heavy/timeout', ThreadPoolTimeoutHandler, {}),
        (r'/api/cook/heavy/native', NativeThreadPoolTimeoutHandler, {}),
        (r'/api/cook/heavy/cut', CutThreadPoolTimeoutHandler, {}),
        (r'/api/cook/heavy/test', TestThreadPoolTimeoutHandler, {}),
        (r'/api/cook/truncation', TruncationHandler, {}),
        (r'/api/cook/native', NativeHandler, {}),
        (r'/api/cook/biz', BizHandler, {}),
        (r'/api/cook/debug', DebugHandler, {}),
    ]

    asyncio.set_event_loop_policy(
        tornado.platform.asyncio.AnyThreadEventLoopPolicy())
    return RouterApplication(url_map)


def cb():
    pass
    # print('periodic cb')


def run_app(app: BaseApplication, port=9040):
    signal.signal(signal.SIGINT, app.signal_handler)
    signal.signal(signal.SIGTERM, app.signal_handler)
    signal.signal(signal.SIGQUIT, app.signal_handler)

    server = tornado.httpserver.HTTPServer(app)
    server.bind(port)
    # server.start(3) # multiple process
    server.start()
    tornado.ioloop.PeriodicCallback(app.try_exit, 300).start()
    tornado.ioloop.PeriodicCallback(cb, 1000).start()
    tornado.ioloop.IOLoop.current().set_default_executor(ThreadPoolExecutor(max_workers=4))
    tornado.ioloop.IOLoop.current().start()
