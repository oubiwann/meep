(ns meep.core
  (:require [seesaw.core :as seesaw]
            [twitter.callbacks]
            [twitter.callbacks.handlers]
            [twitter.api.restful]
            [meep.util]
            [meep.widgets :as widgets])
  (:import [twitter.callbacks.protocols SyncSingleCallback])
  (:gen-class))

(defn app-panel []
  (seesaw/tabbed-panel
   :tabs [{:title "general" :content (widgets/general)}
          {:title "listbox" :content (widgets/jlist)}
          {:title "table" :content (widgets/jtable)}
          {:title "text" :content (widgets/jtext)}]))

(defn -main [& args]
  (seesaw/invoke-later
   (-> (seesaw/frame :title "Meep"
                     :size [800 :by 400]
                     :content (app-panel)
                     :on-close :exit)
       seesaw/pack!
       seesaw/show!)))

