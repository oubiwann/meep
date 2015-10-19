(ns meep.core
  (:require [seesaw.bind :as bind]
            [seesaw.core :as seesaw]
            [seesaw.scroll :as scroll]
            [twitter.callbacks]
            [twitter.callbacks.handlers]
            [twitter.api.restful]
            [meep.util])
  (:import [twitter.callbacks.protocols SyncSingleCallback])
  (:gen-class))

(defn top [target]
  (seesaw/action :name "(scroll! v :to :top)"
                 :handler (fn [e] (seesaw/scroll! target :to :top))))

(defn bottom [target]
  (seesaw/action :name "(scroll! v :to :bottom)"
                 :handler (fn [e] (seesaw/scroll! target :to :bottom))))

(defn point [target & [x y]]
  (seesaw/action :name (format "(scroll! v :to [:point %d %d]" x y)
                 :handler (fn [e] (seesaw/scroll! target :to [:point x y]))))

(defn rect [target & [x y w h]]
  (seesaw/action :name (format "(scroll! v :to [:rect %d %d %d %d]" x y w h)
                 :handler (fn [e] (seesaw/scroll! target :to [:rect x y w h]))))

(defn test-panel [target items]
  (seesaw/border-panel :center (seesaw/scrollable target)
                       :south (seesaw/grid-panel :columns 2
                                                 :items items)))
(defn general []
  (let [t (seesaw/text :multi-line? true
                       :text "Paste a lot of text here so there's scroll bars")]
    (test-panel t [(top t) (bottom t) (point t 500 500) (rect t 0 1500 50 50)])))

(defn test-op-int [target op-name]
  (let [arg (seesaw/text :columns 10)
        go-action (seesaw/action :name "Scroll!"
                                 :handler (fn [e]
                                            (seesaw/scroll! target :to [op-name (Integer/valueOf (seesaw/text arg))])
                                            #_(selection! target (Integer/valueOf (seesaw/text arg)))))
        go-button (seesaw/button :action go-action)]
    (bind/bind
     arg
     (bind/transform #(format "(scroll! v :to [%s %s])" op-name %))
     (bind/property go-button :text))
    (seesaw/text! arg "200")
    (seesaw/horizontal-panel :items [(name op-name) arg go-button])))

(defn test-op-int-int [target op-name]
  (let [arg0 (seesaw/text :columns 10)
        arg1 (seesaw/text :columns 10)
        go-action (seesaw/action :name "Scroll!"
                                 :handler (fn [e]
                                            (seesaw/scroll! target :to [op-name
                                                                        (Integer/valueOf (seesaw/text arg0))
                                                                        (Integer/valueOf (seesaw/text arg1))])))
        go-button (seesaw/button :action go-action)]
    (seesaw/listen #{arg0 arg1} :document
                   (fn [e]
                     (seesaw/text! go-button (format "(scroll! v :to [%s %s %s])" op-name (seesaw/text arg0) (seesaw/text arg1)))))
    (seesaw/text! [arg0 arg1] "20")
    (seesaw/horizontal-panel :items [arg0 arg1 go-button])))

(defn jlist []
  (let [jlist (seesaw/listbox :model (range 0 1000))]
    (test-panel jlist [(top jlist) (bottom jlist) (test-op-int jlist :row)])))

(defn jtable []
  (let [columns (map #(-> ( format "c%09d" %) keyword) (range 26))
        jtable (seesaw/table :auto-resize :off
                             :model [:columns columns
                                     :rows (repeat 500 (into {} (for [c columns] [c 100])))])]
    (test-panel
      jtable
      [(top jtable) (bottom jtable)
       (test-op-int jtable :row)
       (test-op-int jtable :column)
       (test-op-int-int jtable :cell)])))

(defn jtext[]
  (let [t (seesaw/text :multi-line? true
                       :text (apply str (interpose "\n" (range 0 1000))))]
    (test-panel t [(top t)
                   (bottom t)
                   (test-op-int t :line)
                   (test-op-int t :position)])))

(defn app-panel []
  (seesaw/tabbed-panel
   :tabs [{:title "general" :content (general)}
          {:title "listbox" :content (jlist)}
          {:title "table" :content (jtable)}
          {:title "text" :content (jtext)}]))

(defn -main [& args]
  (seesaw/invoke-later
   (-> (seesaw/frame :title "Meep"
                     :size [800 :by 400]
                     :content (app-panel)
                     :on-close :exit)
       seesaw/pack!
       seesaw/show!)))

