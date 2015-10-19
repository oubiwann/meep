(ns meep.auth
  (:require [clojure.edn :as edn]
            [twitter.oauth :as oauth]
            [me.raynes.fs :as fs]))

(def credfile (fs/expand-home "~/.meep/creds.clj"))

(defn load-creds []
  (edn/read-string (slurp credfile)))

(defn get-creds []
  (let [{consumer-key :consumer-key
         consumer-secret :consumer-secret
         access-token :access-token
         access-token-secret :access-token-secret} (load-creds)]
    (oauth/make-oauth-creds consumer-key
                            consumer-secret
                            access-token
                            access-token-secret)))

