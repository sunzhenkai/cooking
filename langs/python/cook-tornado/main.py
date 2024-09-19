# coding: utf-8

import engine


def run():
    app = engine.create_app()
    engine.run_app(app)


if __name__ == '__main__':
    run()
