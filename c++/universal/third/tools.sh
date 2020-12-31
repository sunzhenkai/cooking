tool::os_type() {
    local os='unkown'
    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        os='linux'
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        os='darwin'
    fi

    echo ${os}
}

tool::get_cpu_num() {
    os="$(tool::os_type)"
    if [[ "$os" == "darwin" ]]; then
        echo "$(sysctl hw.logicalcpu | cut -d ' ' -f2)"
    else # TODO
        echo "unsupport os type $os"
    fi
}

# echo "$(tool::get_cpu_num)"
