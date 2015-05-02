(ns airlab.test-runner
  (:require
   [cljs.test :refer-macros [run-tests]]
   [airlab.core-test]))

(enable-console-print!)

(defn runner []
  (if (cljs.test/successful?
       (run-tests
        'airlab.core-test))
    0
    1))
