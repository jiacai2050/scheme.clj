(ns scheme.console
  (:require [scheme.core :refer [eval-scheme]]
            [scheme.env :refer [the-global-env]]
            [clojure.edn :as edn])
  (:import [jline.console ConsoleReader])
  (:gen-class))


(defn get-input [cr]
  (.readLine cr "> "))

(defn -main [& args]
  (println "Welcome to scheme.clj")
  (flush)
  
  (let [cr (ConsoleReader.)]
    (loop [line (get-input cr)]
      (if (= "exit" line)
        (System/exit 0)
        (let [ret (eval-scheme the-global-env (edn/read-string line))]
          (println ret)
          (recur (get-input cr)))
        ))
    ))


