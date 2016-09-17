(defproject scheme.clj "0.1"
  :description "Yet another scheme dialect"
  :url "https://github.com/jiacai2050/scheme.clj"
  :license {:name "MIT"
            :url "http://liujiacai.net/license/MIT.html?year=2016"}
  :aot :all
  :main scheme.console
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [jline "2.12"]])
