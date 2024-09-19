#!/bin/bash
script_dir="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
export CLASSPATH=$script_dir/jar/lucene-analyzers-common-8.5.2.jar:$CLASSPATH
export CLASSPATH=$script_dir/jar/lucene-core-8.5.2.jar:$CLASSPATH
export CLASSPATH=$script_dir/jar/lucene-demo-8.5.2.jar:$CLASSPATH
export CLASSPATH=$script_dir/jar/lucene-queryparser-8.5.2.jar:$CLASSPATH

# generate index
java org.apache.lucene.demo.IndexFiles -docs ~/Git/notes/Coding 

