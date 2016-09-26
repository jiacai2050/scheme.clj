(ns scheme.test-runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [scheme.core-test]
            [scheme.env-test]))

(doo-tests 'scheme.core-test
           'scheme.env-test)
