# coding: utf-8
import json
import os
from abc import ABC

import psutil
import tornado.escape
import tornado.web


class JsonHandler(tornado.web.RequestHandler, ABC):
    def initialize(self):
        super(JsonHandler, self).initialize()
        # print('JsonHandler initialize')
        self.request.extract_arguments = {}
        if self.request.body:
            try:
                data = tornado.escape.json_decode(self.request.body)
            except json.decoder.JSONDecodeError:
                raise tornado.web.HTTPError(499, reason="Invalid Json structure")

            if not isinstance(data, dict):
                raise tornado.web.HTTPError(498, reason="The Json argument must be a dict")
            for key, value in data.items():
                self.request.arguments[key] = [value, ]
        # raise tornado.web.HTTPError(500)

    # def prepare(self):
        # print('JsonHandler prepare')


class BaseHandler(JsonHandler, ABC):
    __process__ = psutil.Process(os.getpid())

    def initialize(self):
        super(BaseHandler, self).initialize()
        # raise tornado.web.HTTPError(500)

    # def write(self, chunk) -> None:
    #     pass
