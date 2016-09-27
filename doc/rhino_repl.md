```shell
rlwrap lein trampoline cljsbuild repl-rhino

Running Rhino-based ClojureScript REPL.
To quit, type: :cljs/quit
cljs.user=> (require '[scheme.core :refer [eval]])
nil
cljs.user=> (eval "1")
1
cljs.user=> (eval "(((lambda (x y) (+ x y)) 1) 2)")
3
cljs.user=>
```
