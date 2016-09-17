(ns scheme.core
  (:require [clojure.core.match :refer [match]]
            [scheme.builtins :refer :all]
            [scheme.env :refer [the-global-env]]))

(defn eval-scheme [env expr]
  (match [expr]
         [x :guard self-eval?] (self-eval env x)
         [(['lambda ([& params] :seq) & body] :seq)] (eval-lambda eval-scheme env
                                                              params body)

         [(['define var val] :seq)] (eval-define eval-scheme env
                                                   var val)
         [(['if test then & else] :seq)]  (eval-if eval-scheme env
                                                   test then else)
         [([operator & operands] :seq)] (eval-application eval-scheme env
                                                          operator
                                                          operands)
))
