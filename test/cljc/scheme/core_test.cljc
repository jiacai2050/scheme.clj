(ns scheme.core-test
  (:refer-clojure :exclude [eval])
  (:require #?(:clj [clojure.test :refer [deftest testing are is]]
               :cljs [cljs.test :refer-macros [deftest testing are is]])
            [scheme.core :refer [eval]])
  #?(:clj (:import [scheme.env Pair])
     :cljs (:use [scheme.env :only [Pair]])))

(deftest eval-test
  (testing "eval simple numbers and symbols"
    (are [expected expr] (= expected (eval expr))
      1 "1"
      :not-found "x"))

  (testing "eval literal"
    (is (true? (eval "true")))
    (is (false? (eval "false")))
    (is (nil? (eval "nil"))))

  (testing "eval logical"
    (are [expr] (true? (eval expr))
      "(> 3 2)"
      "(not false)"))

  (testing "eval if"
    (is (= 3 (eval "(if (> 3 2)
                      3
                      2)"))))

  (testing "eval let"
    (is (= 6 (eval "(let ((x (+ 1 1)) (y (+ 2 2)))
                      (+ y x))"))))

  (testing "eval define"
    (eval "(define (my-add x y) (+ x y))")
    (is (= 2 (eval "(my-add 1 1)"))))

  (testing "eval pair/list"
    (are [pair-or-lst expr] (= pair-or-lst (eval expr))
      (Pair. 2 3) "(cons 2 3)"
      2 "(car (cons 2 3))"
      3 "(cdr (cons 2 3))"
      '(1) "(cons 1 nil)"
      '() "(list)"
      '(1 2 3) "(list 1 2 3)"
      '(2 3) "(cdr (list 1 2 3))"
      true "(null? (list))"))

  (testing "eval lambda ..."
    (is (=  3 (eval "((lambda (x y) 
                              (println (str \"x = \" x \", y = \" y))
                              (+ x y)) 
                      1
                      2)"))))
  (testing "eval lambda currying ..."
    (is (= 3 (eval "(((lambda (x y)
                              (+ x y))
                      1)
                     2)")))))
