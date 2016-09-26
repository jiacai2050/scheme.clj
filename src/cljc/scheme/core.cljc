(ns scheme.core
  #?(:clj (:refer-clojure :exclude [read-string eval]))
  (:require [scheme.env :refer [the-global-env]]
            [scheme.builtins :as bis]
            #?@(:clj [[clojure.core.match :refer [match]]
                      [clojure.edn :refer [read-string]]]
                :cljs [[cljs.core.match :refer-macros [match]]
                       [cljs.reader :refer [read-string]]])))

(defn- eval-scheme [env expr]
  (match [expr]
         [x :guard bis/self-eval?] (bis/self-eval env x)
         [(['lambda ([& params] :seq) & body] :seq)] (bis/eval-lambda eval-scheme env
                                                                      params body)
         [(['define var & val] :seq)]  (bis/eval-define eval-scheme env
                                                        var val)
         [(['let ([& binding] :seq) & body] :seq)] (bis/eval-let eval-scheme env
                                                                 binding body)
         [(['if test then & else] :seq)]  (bis/eval-if eval-scheme env
                                                       test then else)
         [([operator & operands] :seq)] (bis/eval-application eval-scheme env
                                                          operator operands)))

(defn eval [raw-expr]
  (let [expr (read-string raw-expr)]
    (eval-scheme the-global-env expr)))
