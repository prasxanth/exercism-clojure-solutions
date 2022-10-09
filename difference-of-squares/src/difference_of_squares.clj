(ns difference-of-squares)

(defn sum-of-squares [n] 
  ;; Sn=n(2n+1)(n+1)/6
  (-> (reduce + [(* 2 n n n) (* 3 n n) n]) (/ 6)))

(defn sum-of-numbers [n]
  (-> (reduce + [(* n n) n]) (/ 2)))

(defn square-of-sum [n] 
  ;;(n(n+1)/2)^2
  (* (sum-of-numbers n) (sum-of-numbers n)))

(defn difference [n] 
  (- (square-of-sum n) (sum-of-squares n)))