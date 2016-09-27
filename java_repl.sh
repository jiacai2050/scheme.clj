#!/usr/bin/env bash

ENTRY="target/scheme.clj-*-standalone.jar"

cd $(dirname $0; pwd)

if ! ls $ENTRY &>/dev/null;then
  echo "Can't find an uberjar, packaging now ..."
  lein uberjar
fi

cmd="java -jar ${ENTRY}"
if which rlwrap &>/dev/null; then 
  rlwrap ${cmd}
else
  ${cmd}
fi
