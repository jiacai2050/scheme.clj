# scheme.clj

Yet another Scheme dialect written in Clojure and ClojureScript

## Usage

```
# First clone this repo, then run

# ClojureScript
lein trampoline cljsbuild repl-rhino  

# Clojure
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

Concrete REPL demos can be found here:

- [Clojure](doc/clj_repl.md)
- [ClojureScript](doc/cljs_rhino.md)


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

## License

[MIT License](http://liujiacai.net/license/MIT.html?year=2016) © Jiacai Liu
