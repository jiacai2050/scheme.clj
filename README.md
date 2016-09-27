# scheme.clj [![Build Status](https://travis-ci.org/jiacai2050/scheme.clj.svg?branch=master)](https://travis-ci.org/jiacai2050/scheme.clj)

Yet another Scheme dialect written in Clojure and ClojureScript

## Usage

```
# First clone this repo, then run

# ClojureScript (Node.js-based REPL)
lein cljsbuild once node-repl && node resources/node/repl.js

# Clojure
lein uberjar && java -jar target/scheme.clj-*-standalone.jar
```

You can install `rlwrap` to support line editing, persistent history and completion.
```shell
# ubuntu
sudo apt-get install rlwrap
# centos
sudo yum install rlwrap
# Mac
brew install rlwrap  # for Homebrew
port install rlwrap  # for MacPorts
```
Now, you can try REPL like this:
```shell
rlwrap java -jar target/scheme.clj-*-standalone.jar
# or 
rlwrap node resources/node/repl.js

Welcome to Scheme.clj
> 1
1
> "hello scheme.clj"
hello scheme.clj
> (+ 1 2)
3
> ((lambda (x y) (+ y x)) 1 2)
3
> (if (> 3 2) 3 2)
3
> (cons 1 2)
#scheme.env.pair{:x 1, :y 2}
> (list 1 2)
(1 2)
> (car (list 1 2))
1
> (cdr (list 1 2))
(2)
> (if (> 3 2) 3 2)
3
> (define (my-add x y) (+ y x))
nil
> (my-add 1 2)
3
> (let ((x 1) (y 2))  (my-add x y))
3
> (((lambda (x y) (+ x y)) 1) 2)   ;; currying
3
> exit
```

More supported functions can be found in [env.cljc](src/cljc/scheme/env.cljc).

In case you want to try `scheme.clj` on browser, you can first try [rhino-based REPL](doc/rhino_repl.md).


## Test

```
# Clojure
lein test

# ClojureScript
lein doo node node-test once
```
## TODO

- [x] function currying (2016/09/18)
- [x] port to ClojureScript (2016/09/26)
- [ ] [tco](http://c2.com/cgi/wiki?ProperTailRecursion)
- [ ] a GUI to execute Scheme code

## ChangeLog

- `2016/09/17`, v0.1, first init
- `2016/09/26`, v0.2, port to ClojureScript
- `2016/09/27`, add Node.js-based REPL

## License

[MIT License](http://liujiacai.net/license/MIT.html?year=2016) © Jiacai Liu
