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
```

More supported functions can be found in [env.clj](src/scheme/env.clj).


## TODO

- [ ] function currying
- [ ] a GUI to execute Scheme code

## License

[MIT License](http://liujiacai.net/license/MIT.html?year=2016) © Jiacai Liu