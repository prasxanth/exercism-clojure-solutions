(ns grains)
 
;; Of course we could use Math/pow but where’s the fun in that ;)

(def doubler (memoize #(* 2N %)))

(defn square [s]
 (-> (iterate doubler 1) (nth (dec s))))

(defn total []
  (dec (square 65)))
