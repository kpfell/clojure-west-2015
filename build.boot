#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.1"

(set-env!
  :project      'clojure-west
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[[tailrecursion/boot.task   "2.2.4"]
                  [tailrecursion/hoplon      "5.10.25"]
                  [tailrecursion/javelin      "3.7.2"]
                  [quil/quil                 "2.2.5"]]
  :out-path     "resources/public"
  :src-paths    #{"src"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require
  '[tailrecursion.hoplon.boot    :refer :all]
  '[tailrecursion.boot.task.ring :refer [dev-server]])

(deftask development
  "Build clojure-west for development."
  []
  (comp (watch) (hoplon {:pretty-print true :prerender false}) (dev-server :port 9292)))

(deftask dev-debug
  "Build clojure-west for development with source maps."
  []
  (comp (watch) (hoplon {:pretty-print true
                         :prerender false
                         :source-map true}) (dev-server)))

(deftask production
  "Build clojure-west for production."
  []
  (hoplon {:optimizations :advanced}))
