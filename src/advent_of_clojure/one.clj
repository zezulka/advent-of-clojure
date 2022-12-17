(ns advent-of-clojure.one)

(require
  ['clojure.string :as 'str])

(defn sum-calories-by-elves [calories-by-elves]
  (map #(reduce + (map read-string %)) calories-by-elves))

(defn most-calories [category-list]
  (reduce max (sum-calories-by-elves category-list)))

(defn group-calories-by-empty-lines [cal-list]
  (filter #(not= [""] %) (partition-by str/blank? cal-list)))

(defn get-lines [file]
  (str/split-lines (slurp file)))

(defn solution []
  (most-calories (group-calories-by-empty-lines (get-lines "src/advent_of_clojure/input_01.txt"))))

(solution)