# Description

定义一种语言，有如下功能。

- 数据类型定义

# Files

```shell
scanner.l		# flex source for c++ lexical scanner
scanner.cc  # generated from scanner.l by flex
scanner.h   # definex lexer class example::Scanner
parser.y    # bison parser grammar
parser.cc   # generated from parser.y by bison
parser.h    # generated from parser.y by bison
driver.h    # defines the example::Driver class, which put together lexer and parser
driver.cc   # implements for deriver.h
```

# Reference

- https://github.com/bingmann/flex-bison-cpp-example

- https://github.com/jonathan-beard/simple_wc_example