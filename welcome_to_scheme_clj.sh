#!/usr/bin/env bash

JAR="target/scheme.clj-*-standalone.jar"

cd $(dirname $0; pwd)

if ! ls $JAR &>/dev/null;then
  echo "Can't find an uberjar, packaging now ..."
  lein uberjar
fi

if which rlwrap &>/dev/null; then 
  rlwrap java -jar $JAR
else
  java -jar $JAR
fi
