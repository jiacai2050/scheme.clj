#!/usr/bin/env bash

if which rlwrap &>/dev/null; then
  rlwrap lein trampoline cljsbuild repl-rhino
else
  lein trampoline cljsbuild repl-rhino
fi

