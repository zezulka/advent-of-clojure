(ns advent-of-clojure.io
  (:require
    [clojure.string :as str]))

(defn get-lines [file]
  (str/split-lines (slurp file)))


(defn get-nonempty-lines [file]
  (remove str/blank? (get-lines file)))