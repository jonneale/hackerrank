(ns largest-rectangle)

(defn filter-adjacent-heights-greater-than
  [building-height list-of-heights]
  (take-while #(>= % building-height) list-of-heights))

(defn building-heights
  [heights building-index]
  (if (= building-index (count heights))
    [building-index]
    (let [building-height      (nth heights building-index)
          previous-buildings   (map (partial nth heights) (range (dec building-index) -1 -1))
          subsequent-buildings (map (partial nth heights) (range building-index (count heights)))]
      (mapcat (partial filter-adjacent-heights-greater-than building-height) [previous-buildings subsequent-buildings]))))

(defn max-contiguous-area
  [heights]
  (loop [index 0 max-height 0]
    (if (= index (count heights))
      max-height
      (let [building-height (nth heights index)]
        (if (> max-height (* building-height (count heights)))
          (recur (inc index) max-height)
          (let [buildings (building-heights heights index)]
            (recur (inc index) (max max-height (* building-height (count buildings))))))))))


;; (read-line)
;; (println (max-contiguous-area (vec (map #(Integer/parseInt %) (clojure.string/split (read-line) #" ")))))

