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
    elif [[ "$os" == "linux" ]]; then 
        echo "$(grep ^cpu\\scores /proc/cpuinfo | uniq |  awk '{print $4}')"
    else
        echo "unsupport os type $os"
    fi
}

# echo "$(tool::get_cpu_num)"
