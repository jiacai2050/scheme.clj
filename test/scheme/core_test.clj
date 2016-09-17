(ns scheme.core-test
  (:require [clojure.test :refer :all]
            [scheme.core :refer [eval-scheme]]
            [scheme.env :refer [the-global-env ->pair]]))

(deftest eval-test
  (testing "eval simple numbers and symbols"
    (are [expected expr] (= expected (eval-scheme the-global-env expr))
      1 '1
      :not-found 'x))

  (testing "eval literal"
    (is (true? (eval-scheme the-global-env 'true)))
    (is (false? (eval-scheme the-global-env 'false)))
    (is (nil? (eval-scheme the-global-env 'nil))))

  (testing "eval logical"
    (are [expr] (true? (eval-scheme the-global-env expr))
      '(> 3 2)
      '(not false)))

  (testing "eval if"
    (is (= 3 (eval-scheme the-global-env '(if (> 3 2)
                                            3
                                            2)))))

  (testing "eval let"
    (is (= 6 (eval-scheme the-global-env '(let ((x (+ 1 1)) (y (+ 2 2)))
                                            (+ y x))))))

  (testing "eval define"
    (eval-scheme the-global-env '(define (my-add x y) (+ x y)))
    (is (= 2 (eval-scheme the-global-env '(my-add 1 1)))))

  (testing "eval pair/list"
    (are [pair-or-lst expr] (= pair-or-lst (eval-scheme the-global-env expr))
      (->pair 2 3) '(cons 2 3)
      2 '(car (cons 2 3))
      3 '(cdr (cons 2 3))
      '(1) '(cons 1 nil)
      '() '(list)
      '(1 2 3) '(list 1 2 3)
      '(2 3) '(cdr (list 1 2 3))
      true '(null? (list))))

  (testing "eval lambda ..."
    (is (=  3 (eval-scheme the-global-env '((lambda (x y) 
                                                    (println (str "x = " x ", y = " y))
                                                    (+ x y)) 
                                            1
                                            2))))))
