# scheme.clj

Yet another Scheme dialect written in Clojure

## Usage

```
;; first clone this repo, then

lein uberjar
java -jar target/scheme.clj-*-standalone.jar
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
Then, run like this
```shell
rlwrap java -jar target/scheme.clj-*-standalone.jar

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

More supported functions can be found in [env.clj](src/scheme/env.clj).


## TODO

- [x] function currying (2016/09/18)
- [ ] port to ClojureScript
- [ ] [tco](http://c2.com/cgi/wiki?ProperTailRecursion)
- [ ] a GUI to execute Scheme code

## ChangeLog

- `2016/09/17`, v0.1, first init

## License

[MIT License](http://liujiacai.net/license/MIT.html?year=2016) © Jiacai Liu
