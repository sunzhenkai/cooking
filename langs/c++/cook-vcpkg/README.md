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
mkdir build && cd build
cmake -DCMAKE_TOOLCHAIN_FILE=/root/.local/vcpkg/scripts/buildsystems/vcpkg.cmake ..
make -j 5

# 运行
./main
```