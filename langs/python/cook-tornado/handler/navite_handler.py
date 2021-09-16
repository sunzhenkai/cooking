# coding: utf-8
from abc import ABC

import tornado.gen
import tornado.web

from handler.base_handler import JsonHandler


class NativeHandler(tornado.web.RequestHandler, ABC):

    @tornado.gen.coroutine
    def post(self):
        h = JsonHandler(self.application, self.request)
        self.write(h.request.arguments)
