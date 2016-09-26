(ns scheme.env)

(defrecord Pair [x y])

(defn- scheme-cons [x y]
  (if (or (nil? y) (list? y))
    (cons x y)
    (Pair. x y)))

(defn- scheme-car [pair-or-lst]
  (if (list? pair-or-lst)
    (first pair-or-lst)
    (:x pair-or-lst)))

(defn- scheme-cdr [pair-or-lst]
  (if (list? pair-or-lst)
    (rest pair-or-lst)
    (:y pair-or-lst)))

(def the-global-env 
  (atom {:> >
         :< <
         := =
         :+ +
         :- -
         :* *
         :/ /
         :not not
         :println println
         :str str
         :list list
         :null? empty?
         :cons scheme-cons
         :car scheme-car
         :cdr scheme-cdr}))

(defn find-var-in-env [env sym]
  (get @env sym :not-found))

(defn expand-env [origin-env added-env]
  (swap! origin-env merge added-env))

(defn generate-new-env [origin-env added-env]
  (atom (merge @origin-env added-env)))
