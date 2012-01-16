(ns ring.middleware.session.memcached
  (:use ring.middleware.session.store)
  (:require [clj-xmemcached.core :as mem])
  (:import (net.rubyeye.xmemcached MemcachedClient) (java.util UUID)))


(deftype MemcachedStore [^MemcachedClient  cli]
  SessionStore
  (read-session [_ key]
    (try
      (or (mem/xget cli key) {})
      (catch Exception e {})))
  (write-session [_ key data]
    (let [key (or key (str (UUID/randomUUID)))]
      (mem/xset cli key data)
      key))
  (delete-session [_ key]
    (if-not (mem/xdelete cli key) key)))


(defn mem-store
  "Creates session storage backed by memcached with zero or more options(any order):

    :protocol  Protocol to talk with memcached,a string in \"text\" \"binary\" or \"kestrel\",default is text.

    :hash  Hash algorithm,a string in  \"consistent\", \"standard\" or \"phpmemcache\", default is standard hash.

    :pool  Connection pool size,default is 1

    :timeout  Operation timeout in milliseconds,default is five seconds.

    :name  A name to define a memcached client instance"
  [servers & opts]
  (let [cli (apply mem/xmemcached servers opts)]
    (MemcachedStore. cli)))
