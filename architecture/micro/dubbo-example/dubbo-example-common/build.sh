#!/bin/bash
export PATH=/usr/local/Cellar/thrift/0.13.0/bin:$PATH
thrift -version
mvn clean install