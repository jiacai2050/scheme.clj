#!/usr/bin/env bash

ENTRY="resources/node/repl.js"

cd $(dirname $0; pwd)

if ! ls $ENTRY &>/dev/null;then
  echo "Can't find an ${ENTRY}, packaging now ..."
  lein cljsbuild once node-repl
fi

cmd="node ${ENTRY}"
if which rlwrap &>/dev/null; then 
  rlwrap ${cmd}
else
  ${cmd}
fi
