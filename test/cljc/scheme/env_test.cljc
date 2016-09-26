(ns scheme.env-test
  (:require #?(:clj [clojure.test :refer [deftest testing is]]
               :cljs [cljs.test :refer-macros [deftest testing is]])
            [scheme.env :refer [find-var-in-env the-global-env expand-env]]))

(deftest test-expand-env
  (testing "test expand-env ..."
    (expand-env the-global-env {:age 11})
    (is (= 11 (find-var-in-env the-global-env :age)))))
