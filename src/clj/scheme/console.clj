(ns scheme.console
  (:refer-clojure :exclude [eval])
  (:require [scheme.core :refer [eval]])
  (:import [jline.console ConsoleReader])
  (:gen-class))

(defn -main [& args]
  (println "Welcome to scheme.clj")
  (flush)
  (let [cr (ConsoleReader.)
        get-input #(.readLine cr "> ")]
    (loop [line (get-input)]
      (if (= "exit" line)
        (System/exit 0)
        (let [ret (eval line)]
          (println ret)
          (recur (get-input)))))))
