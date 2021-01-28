# mac
```shell
$ brew install automake autoconf libtool pkg-config gnu-getopt coreutils
```
- `libtool pkg-config` for thrift
- `gnu-getopt coreutils` for brpc

**setup**
```shell
echo 'export PATH="/usr/local/opt/gnu-getopt/bin:$PATH"' >> ~/.zshrc # ~/.bash_profile
source ~/.zshrc
```

# ubuntu
```shell
$ sudo apt install cmake automake pkg-config libtool flex
``` 
- `flex` for thriftç