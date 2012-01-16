# ring-session-memcached
  This permits you to store your ring session in memcached.

## Usage

In your project.clj:

    [ring-session-memcached "0.0.1"]

Wrap your routes:
	(use 'ring.middleware.session.memcached)
    (-> routes
        (wrap-session {:store (mem-store "host1:11211 host2:11211")}))

The mem-store fn provides a few options to give it:

	  :name  Memcached client's name
	  :protocol  Protocol to talk with memcached,a string value in text,binary or kestrel,default is text protocol.
	  :hash  Hash algorithm,a string value in consistent or standard,default is standard hash.
	  :timeout Operation timeout in milliseconds,default is five seconds.
	  :pool  Connection pool size,default is one,and it is suitable for most applications.

For example:

	(mem-store "lcoalhost:11211" :timeout 3000 :hash consistent)


## License

Copyright (C) 2012 Dennis Zhuang

Distributed under the Eclipse Public License, the same as Clojure.



