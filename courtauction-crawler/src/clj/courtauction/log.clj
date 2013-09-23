(ns courtauction.log
  (:use [courtauction.util]) 
  (:require [clojure.tools [logging :as log]])  
  )

(defmacro log-message [& args]
  `(log/info (str ~@args)))

(defmacro log-error [e & args]
  `(log/log :error ~e (str ~@args)))

(defmacro log-debug [& args]
  `(log/debug (str ~@args)))

(defmacro log-warn-error [e & args]
  `(log/warn (str ~@args) ~e))

(defmacro log-warn [& args]
  `(log/warn (str ~@args)))

(defn log-capture! [& args]
  (apply log/log-capture! args))

(defn log-stream [& args]
  (apply log/log-stream args))

(defn configure-logback [path]
  (def url (get-url path))
  (def lc (org.slf4j.LoggerFactory/getILoggerFactory))
  (def configurator (ch.qos.logback.classic.joran.JoranConfigurator.))
  (.reset lc) 
  (.setContext configurator lc)
  (.doConfigure configurator url)  
  
  (log-message "logback xml from classpath loaded...")    
  )