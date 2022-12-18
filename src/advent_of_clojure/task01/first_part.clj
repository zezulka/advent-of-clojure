(ns advent-of-clojure.task01.first_part
  (:require [advent-of-clojure.io :as aoc-io]
            [clojure.string :as str]))

(defn sum-calories-by-elves [calories-by-elves]
  (map #(reduce + (map read-string %)) calories-by-elves))

(defn most-calories [category-list]
  (reduce max (sum-calories-by-elves category-list)))

(defn group-calories-by-empty-lines [cal-list]
  (filter #(not= [""] %) (partition-by str/blank? cal-list)))

(defn solution []
  (most-calories (group-calories-by-empty-lines (aoc-io/get-lines "src/advent_of_clojure/task01/input_01_1.txt"))))

;;(solution)