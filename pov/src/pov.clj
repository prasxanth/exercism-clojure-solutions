(ns pov)

;; Adopted from ideas in
;; https://porkostomus.gitlab.io/posts-output/2018-08-08-Tree-reparenting/

(defn filter-nodes [leaf tree]
  (->> (tree-seq next rest tree)
       (filter #(some (set [leaf]) (flatten %)))
       not-empty))

(defn of [new-root tree]
  (some->> (filter-nodes new-root tree)
           (reduce #(conj %2 (vec (remove #{%2} %1))))))

(defn path-from-to [from to tree]
  (some->> (of from tree)
           (filter-nodes to)
           (mapv first)))
