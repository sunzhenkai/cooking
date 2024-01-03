# 安装 vcpkg
```shell
INSTALL_DIR="$HOME/.local"
mkdir "$INSTALL_DIR"
cd "$INSTALL_DIR"
git clone https://github.com/Microsoft/vcpkg.git
bash "vcpkg/bootstrap-vcpkg.sh"
```

# 编译
```shell
cmake 
```