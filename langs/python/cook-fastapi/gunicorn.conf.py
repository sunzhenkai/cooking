# coding: utf-8

preload_app = True
debug = True


def on_starting(server):
    print('start')


def post_worker_init(worker):
    pass
    # from utils.log import set_work_id
    # WORK_ID = worker.age
    # set_work_id(WORK_ID)
    # globals()['WORK_ID'] = WORK_ID
