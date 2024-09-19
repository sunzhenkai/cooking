'use strict';

const Controller = require('egg').Controller;

class HomeController extends Controller {
  async index() {
    const { ctx } = this;
    ctx.body = 'hi, egg';

    console.log(ctx.req.rawHeaders)
    console.log(ctx.request.headers)
    console.log('cookies test. ', ctx.cookies.get('foo'))
    ctx.request.headers.cookie = ''
    console.log('cookies test. ', ctx.cookies.get('foo'))
    // console.log('cookies test. ', ctx.cookies.set('foo', 'bar'))
    // ctx.cookies.set('foo', 'bar')
    // console.log('cookies test. ', ctx.cookies.get('foo'))
  }
}

module.exports = HomeController;
