(ns advent-of-clojure.task02.task
  (:require
    [advent-of-clojure.io :as aoc-io]
    [advent-of-clojure.task02.shape :as shape]
    [clojure.string :as str]
    ))

(def ^:const points-for-win 6)
(def ^:const points-for-draw 3)
(def ^:const points-for-loss 0)

;; Clojure's case is REALLY bad at pattern matching ...
(defn assign-score-to-round [opponent me]
  (let [round (vec [opponent me])]
    (case round
      (
       ["SCISSORS" "ROCK"]
       ["ROCK" "PAPER"]
       ["PAPER" "SCISSORS"]
       ) points-for-win
      (
       ["ROCK" "SCISSORS"]
       ["PAPER" "ROCK"]
       ["SCISSORS" "PAPER"]
       ) points-for-loss
      (
       ["ROCK" "ROCK"]
       ["PAPER" "PAPER"]
       ["SCISSORS" "SCISSORS"]
       ) points-for-draw
      (throw (new Exception (str opponent " " me))))))

(defn assign-score-to-shape [shp]
  (case shp
    "ROCK" 1
    "PAPER" 2
    "SCISSORS" 3))

(defn split-str-using-whitespace [str]
  (str/split str #"\s+"))


(defn read-strategy-tokens []
  (map split-str-using-whitespace (aoc-io/get-nonempty-lines "src/advent_of_clojure/task02/input_02_1.txt")))

(defn parse-strategy-tokens [strategy]
  (map (fn [[opponent me]] (strategy opponent me)) (read-strategy-tokens)))

(defn evaluate-score [shapes]
  (reduce
    (fn [points-so-far [opponent me]] (+ points-so-far (assign-score-to-round opponent me) (assign-score-to-shape me)))
    0
    shapes))

(defn opponent-token-to-shape [tok]
  (case tok
    "A" shape/rock
    "B" shape/paper
    "C" shape/scissors))

(defn my-token-to-shape [tok]
  (case tok
    "X" shape/rock
    "Y" shape/paper
    "Z" shape/scissors))

(defn winning-token [tok]
  (case tok
    "A" shape/paper
    "B" shape/scissors
    "C" shape/rock))

(defn losing-token [tok]
  (case tok
    "A" shape/scissors
    "B" shape/rock
    "C" shape/paper))

(defn my-token-to-substrategy [opponent me]
  (case me
    "X" (losing-token opponent)
    "Y" (opponent-token-to-shape opponent)
    "Z" (winning-token opponent)))

(defn all-tokens-represent-shapes [opponent me]
  [(opponent-token-to-shape opponent) (my-token-to-shape me)])

(defn his-tokens-are-shapes-mine-substrategy [opponent me]
  [(opponent-token-to-shape opponent) (my-token-to-substrategy opponent me)])

;; first part
(evaluate-score (parse-strategy-tokens all-tokens-represent-shapes))
;; second part
(evaluate-score (parse-strategy-tokens his-tokens-are-shapes-mine-substrategy))