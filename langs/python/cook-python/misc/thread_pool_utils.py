# coding: utf-8

"""
python 在 Executor 中提交 Future 之后，在 Future 运行时无法取消，只能 shutdown 掉 executor
创建 ThreadPoolExecutor 消耗较多计算资源
对于需要超时截断的场景，大量浪费资源
"""