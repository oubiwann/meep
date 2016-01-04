(defmodule meep.auth
  (export all))

(defun credfile ()
  "~/.meep/creds.clj")

(defun load-creds ()
  'noop)

(defn get-creds []
  ;; XXX call out to oauth2 library or HTTP client library to authenticate
  ;; against remote service
  'noop)

