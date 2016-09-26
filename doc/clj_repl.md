```shell
rlwrap java -jar target/scheme.clj-*-standalone.jar

Welcome to Scheme.clj
> 1
1
> "hello scheme.clj"
hello scheme.clj
> (+ 1 2)
3
> ((lambda (x y) (+ y x)) 1 2)
3
> (if (> 3 2) 3 2)
3
> (cons 1 2)
#scheme.env.pair{:x 1, :y 2}
> (list 1 2)
(1 2)
> (car (list 1 2))
1
> (cdr (list 1 2))
(2)
> (if (> 3 2) 3 2)
3
> (define (my-add x y) (+ y x))
nil
> (my-add 1 2)
3
> (let ((x 1) (y 2))  (my-add x y))
3
> (((lambda (x y) (+ x y)) 1) 2)   ;; currying
3
> exit
```

More supported functions can be found in [env.cljc](../src/cljc/scheme/env.cljc).
