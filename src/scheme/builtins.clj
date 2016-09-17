(ns scheme.builtins
  (:require [scheme.env :refer [find-var-in-env expand-env generate-new-env]]
            [clojure.core.match :refer [match]]))

(defn self-eval? [x]
  (or (number? x)
      (symbol? x)
      (nil? x)
      (string? x)
      (instance? Boolean x)))

(defn self-eval [env x]
  (match [x]
         [num :guard number?] num
         [string :guard string?] string
         [sym :guard symbol?] (find-var-in-env env (keyword sym))
         [null :guard nil?] nil
         [bool :guard (partial instance? Boolean)] bool))

(defrecord Closure [params body env])
(defn eval-lambda [eval env params body]
  (->Closure params body env))

(defn eval-define [eval env var val]
  (match [var]
         [var :guard symbol?] (expand-env env (hash-map (keyword var)
                                                        (eval env val)))
         ))

(defn eval-if [eval env test then else]
  (if (eval env test) 
    (eval env then)
    (if (empty? else)
      nil
      (apply eval env else))))

(defn eval-application [eval env operator operands]
  (let [evaled-operator (eval env operator)
        evaled-operands (map (partial eval env) operands)]
    (match [evaled-operator]
           [closure :guard record?] (let [params (:params closure)
                                          body (:body closure)
                                          saved-env (:env closure)
                                          new-env (generate-new-env saved-env (zipmap (map keyword params)
                                                                                      evaled-operands))]
                                      (loop [body body]   ;; only return value of last expr
                                        (if (< (count body) 2)
                                          (eval new-env (first body))
                                          (do
                                            (eval new-env (first body))
                                            (recur (rest body))))))

           [raw-op] (apply evaled-operator
                           evaled-operands))))

