(ns advent-of-clojure.task01.second_part
  (:require [clojure.string :as str]))

(defn sum-calories-by-elves [calories-by-elves]
  (map #(reduce + (map read-string %)) calories-by-elves))

(defn calories [category-list]
  (sum-calories-by-elves category-list))

(defn top-n-calories [n, category-list]
  (take n (sort > (calories category-list))))

(defn group-calories-by-empty-lines [cal-list]
  (filter #(not= [""] %) (partition-by str/blank? cal-list)))

(defn get-lines [file]
  (str/split-lines (slurp file)))

(defn solution []
  (top-n-calories 3 (group-calories-by-empty-lines (get-lines "src/advent_of_clojure/task01/input_01_1.txt"))))

;;(reduce + (solution))