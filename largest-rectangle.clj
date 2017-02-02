(ns largest-rectangle)

(defn filter-adjacent-heights-greater-than
  [list-of-heights building-height]
  (take-while #(>= % building-height) list-of-heights))

(defn building-heights
  [heights building-index]
  (if (= building-index (count heights))
    [building-index]
    (let [building-height (nth heights building-index)
          heights-before (reverse (take building-index heights))
          heights-after  (drop building-index heights)
          taller-adjacent-buildings (concat (filter-adjacent-heights-greater-than heights-before building-height)
                                            (filter-adjacent-heights-greater-than heights-after building-height))]
      taller-adjacent-buildings)))

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
;; (println (max-contiguous-area (map #(Integer/parseInt %) (clojure.string/split (read-line) #" "))))
