# Description

文档计数。

# Files

## 定义顺序

- `scanner.hpp`
  - 定义 flexer 类

- `deriver.hpp`
- `main.cpp`
  - 解析器主程序，用于读取输入，并调用 driver 的解析方法

## 生成

```shell
$ bison -d -v parser.y
$ flex lexer.l
```

