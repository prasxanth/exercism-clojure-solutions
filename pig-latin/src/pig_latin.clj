(ns pig-latin
  (:require [clojure.string :as str]
            [clojure.pprint :as pp]))

(defn make-rule
 ([f regex word]
  (when-let [matches (->> word (re-matches (re-pattern regex)) rest seq)]
      (pp/cl-format false "~{~a~^~}ay" (f matches))))
 ([regex word]
  (make-rule #(concat (rest %) [(first %)]) regex word)))

(def rule-1 (partial make-rule identity "^(a|e|i|o|u|xr|yt)(.*)"))
(def rule-2 (partial make-rule "^(.*qu)(.*)"))
(def rule-3 (partial make-rule "(.*?)(a|e|i|o|u|xr|yt)(.*)"))
(def rule-4 (partial make-rule "(.*)(y.*)"))

(defn translate-word [word]
  ((some-fn rule-1 rule-2 rule-3 rule-4 identity) word))

(defn translate [sentence]
  (->> sentence
       (#(str/split % #" "))
       (map translate-word)
       (str/join " ")))
