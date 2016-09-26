(defproject scheme.clj "0.2"
  :description "Yet another scheme dialect"
  :url "https://github.com/jiacai2050/scheme.clj"
  :license {:name "MIT"
            :url "http://liujiacai.net/license/MIT.html?year=2016"}
  :aot [scheme.console]
  :main scheme.console
  :source-paths ["src/clj" "src/cljc"]
  :test-paths ["test/cljc"]
  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-doo "0.1.7"]]
  :cljsbuild {:builds {:dev {:source-paths ["src/cljc"]
                             :compiler {:optimizations :whitespace
                                        :pretty-print true
                                        :output-to "target/scheme-clj-dev.js"}}
                       :prod {:source-paths ["src/cljc"]
                              :compiler {:optimizations :advanced
                                         :pretty-print false
                                         :output-to "target/scheme-clj.js"}}
                       :node-test {:source-paths ["src/cljc" "test/cljc" "test/cljs"]
                                   :compiler {:main scheme.test-runner
                                              :output-to "target/test.js"
                                              :target :nodejs}}}}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229" :scope "provided"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [jline "2.12"]])
