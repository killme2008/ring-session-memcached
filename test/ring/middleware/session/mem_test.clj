(ns ring.middleware.session.mem-test
  (:use [clojure.test])
  (:use [clj-xmemcached.core])
  (:use [ring.middleware.session.store])
  (:use [ring.middleware.session.memcached] :reload))

(def test-servers "localhost:11211")

(use-fixtures :each (fn [f]
                      (xflush (xmemcached test-servers))
                      (f)))

(deftest mem-session-read-not-exist
  (let [store (mem-store test-servers)]
    (is (read-session store "non-existant")
        {})))


(deftest mem-session-create
  (let [store (mem-store test-servers)
        sess-key (write-session store nil {:foo "bar"})]
    (is (not (nil? sess-key)))
    (is (= (read-session store sess-key)
           {:foo "bar"}))))


(deftest mem-session-update
  (let [store (mem-store test-servers)
        sess-key (write-session store nil {:foo "bar"})
        sess-key* (write-session store sess-key {:bar "baz"})]
    (is (= sess-key sess-key*))
    (is (= (read-session store sess-key)
           {:bar "baz"}))))


(deftest mem-session-delete
  (let [store (mem-store test-servers)
        sess-key (write-session store nil {:foo "bar"})]
    (is (nil? (delete-session store sess-key)))
    (is (= (read-session store sess-key)
           {}))))





