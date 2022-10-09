(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [deck]
  (let [[first-card%] deck]
  first-card%))

(defn second-card
  "Returns the second card from deck."
  [deck]
    (let [[_ second-card%] deck]
  second-card%))

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [deck]
  (let [[first-card% second-card% & remaining-cards% :as all] deck]
  (conj remaining-cards% first-card% second-card%)))

(defn discard-top-card
  "Returns a vector containing the first card and
   a vector of the remaining cards in the deck."
  [deck]
    (let [[first-card% & remaining-cards% :as all] deck]
  [first-card% remaining-cards%]))

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [deck]
  (let [[first-card% & remaining-cards% :as all] deck]
   (->> (conj remaining-cards% face-cards first-card%)
    flatten
    vec
    (remove nil?))))
