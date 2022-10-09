(ns bob)

;; Solution based on https://exercism.org/tracks/clojure/exercises/bob/solutions/arkadiusz-fratczak

(require '[clojure.string :as str])

(defn- yell? [sentence]
 (= sentence (str/upper-case sentence)))

(defn- question? [sentence]
 (-> sentence (str/ends-with? "?")))

(defn- letters? [sentence]
 (re-seq #"[A-Z]+|[a-z]+" sentence))

(defn- is? [sentence conditions]
  (every? #(% sentence) conditions))

(defn response-for 
  "Provide Bob's responses to various sentences"
  [sentence] 
  (let [sententia (str/trim sentence)]
    (cond
      (is? sententia [letters? yell? question?]) "Calm down, I know what I'm doing!"
      (is? sententia [letters? yell?]) "Whoa, chill out!"
      (is? sententia [question?]) "Sure."
      (is? sententia [str/blank?]) "Fine. Be that way!"
      :else "Whatever.")))
