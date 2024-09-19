# coding: utf-8
from abc import ABC

import tornado.gen
import tornado.web
from tornado.httputil import HTTPServerRequest, HTTPConnection

from handler.base_handler import JsonHandler


class FakeHTTPConnection(HTTPConnection, ABC):
    def set_close_callback(self, *args, **kwargs):
        pass


class BizHandler(tornado.web.RequestHandler, ABC):

    @tornado.gen.coroutine
    def post(self):
        request = HTTPServerRequest(
            uri=self.request.uri,
            body=self.request.body,
            host=self.request.host,
            connection=FakeHTTPConnection()
        )
        h = JsonHandler(self.application, request)
        self.write(h.request.arguments)
