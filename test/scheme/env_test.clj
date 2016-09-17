(ns scheme.env-test
  (:require [clojure.test :refer :all]
            [scheme.env :refer [find-var-in-env the-global-env expand-env]]))

(deftest test-expand-env
  (testing "test expand-env ..."
    (expand-env the-global-env {:age 11})
    (is (= 11 (find-var-in-env the-global-env :age)))))
