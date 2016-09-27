(defproject scheme.clj "0.2"
  :description "Yet another scheme dialect"
  :url "https://github.com/jiacai2050/scheme.clj"
  :license {:name "MIT"
            :url "http://liujiacai.net/license/MIT.html?year=2016"}
  :aot [scheme.repl]
  :main scheme.repl
  :source-paths ["src/clj" "src/cljc"]
  :test-paths ["test/cljc"]
  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-doo "0.1.7"]]
  :cljsbuild {:builds {:dev {:source-paths ["src/cljc"]
                             :compiler {:optimizations :whitespace
                                        :pretty-print true
                                        :output-to "resources/js/scheme-clj-dev.js"
                                        :output-dir "resources/js"}}
                       :node-repl {:source-paths ["src/cljc" "src/cljs"]
                                   :compiler {:main scheme.repl
                                              :output-to "resources/node/repl.js"
                                              :output-dir "resources/node"
                                              :target :nodejs
                                              :optimizations :none}}
                       :node-test {:source-paths ["src/cljc" "test/cljc" "test/cljs"]
                                   :compiler {:main scheme.test-runner
                                              :output-to "resources/node-test/test.js"
                                              :output-dir "resources/node-test"
                                              :target :nodejs}}}}
  :clean-targets ^{:protect false} [:target-path "resources/js" "resources/node" "resources/node-test"]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229" :scope "provided"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [jline "2.12"]])
