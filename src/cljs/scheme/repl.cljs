(ns scheme.repl
  (:require [cljs.nodejs :as nodejs]
            [scheme.core :refer [eval]]))

(nodejs/enable-util-print!)
(println "Welcome to scheme.clj")

(defn -main [& args]
  (let [readline (nodejs/require "readline")
        rl (.createInterface readline 
                             (.-stdin nodejs/process)
                             (.-stdout nodejs/process))]
    (.prompt rl)
    (.on rl "line" (fn [line]
                     (if (= "exit" line)
                       (.exit nodejs/process 0)
                       (let [ret (eval line)]
                         (println ret)
                         (.prompt rl)))))))

(set! *main-cli-fn* -main)
