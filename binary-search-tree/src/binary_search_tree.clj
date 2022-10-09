(ns binary-search-tree)

(def value :value)
(def left :left)
(def right :right)

(defn singleton [value]
  {:value value :left nil :right nil})

(defn insert [v {:keys [value left right] :as tree}]
  (cond
    (nil? tree) (singleton v)
    (> v value) (assoc tree :right (insert v right))
    :else (assoc tree :left (insert v left))))

;; https://eddmann.com/posts/binary-search-trees-in-clojure/
(defn to-list [{:keys [value left right] :as tree}]
  (when tree
    `(~@(to-list left) ~value ~@(to-list right))))

(defn from-list [xs]
  (reduce #(insert %2 %1) nil xs))
