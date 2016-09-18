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
                                                        (eval env (first val))))
         [([fn-name & params] :seq)] (let [old-env (atom @env)] ;; make a copy of origin env to avoid circular reference
                                       (expand-env env (hash-map (keyword fn-name)
                                                                 (->Closure params val old-env)))))
  nil)

(defn- eval-seqs [eval env seqs]
  "Eval a sequences of expr and return last expr as value"
  (loop [seqs seqs]
    (if (< (count seqs) 2)
      (eval env (first seqs))
      (do
        (eval env (first seqs))
        (recur (rest seqs))))))

(defn eval-let [eval env binding body]
  (let [expanded-env (reduce (fn [new-env [var val]]
                               (merge new-env (hash-map (keyword var)
                                                        (eval env val))))
                             {}
                             binding)
        new-env (generate-new-env env expanded-env)]
    (eval-seqs eval new-env body)))

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
           [closure :guard (partial instance? Closure)]
           (let [params (:params closure)
                 body (:body closure)
                 saved-env (:env closure)
                 new-env (generate-new-env saved-env (zipmap (map keyword params)
                                                             evaled-operands))]
             (let [num-param (count params)
                   num-operand (count operands)]
               (if (zero? (- num-param num-operand))
                 (eval-seqs eval new-env body)
                 (->Closure (drop num-operand params)
                            body
                            new-env))))
           [raw-op] (apply raw-op evaled-operands))))

