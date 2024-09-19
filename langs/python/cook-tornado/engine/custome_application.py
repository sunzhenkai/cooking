# coding: utf-8
import tornado.web
import tornado.ioloop


class BaseApplication(tornado.web.Application):
    is_closing = False

    def signal_handler(self, signum, frame):
        self.is_closing = True

    def try_exit(self):
        if self.is_closing:
            tornado.ioloop.IOLoop.current().stop()
            print('exit application success')


class RouterApplication(BaseApplication):
    def route(self, uri, params=None):
        def register(handler):
            assert uri[0] == '/', 'invalid uri %s, should startswith /' % uri
            self.add_handlers(".*$", [(uri, handler, params)])
            print("bind uri %s with handler %s done" % (uri, handler))
            return handler

        return register
