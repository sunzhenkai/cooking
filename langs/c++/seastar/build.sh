#!/bin/bash
g++ -std=c++17 `pkg-config --cflags --libs /home/bovenson/Git/Other/seastar/build/release/seastar.pc` -o output/client src/client.cpp 
g++ -std=c++17 `pkg-config --cflags --libs /home/bovenson/Git/Other/seastar/build/release/seastar.pc` -o output/server src/server.cpp 