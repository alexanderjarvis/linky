# Linky
####(yet another url shortener)

Linky is a quick and dirty URL shortener, written using [Play](http://playframework.org/) and [ReactiveMongo](http://reactivemongo.org).

## Why?

Because when you have a custom domain e.g. [http://pan.ax/]() and wish to use it to redirect URLs like [http://bit.ly]() or [http://goo.gl]() but don't think [$995/month](http://www.enterprise.bitly.com/) is a reasonable price for such a service.

Especially when it only takes a couple of hours to make a working prototype..

## There a *millions* of other URL shortener projects

Yes, but there's nothing quite like making your own and if something goes wrong, then you have only yourself to blame. Besides, I wanted to write it using my favourite technology stack and if I want to customise it further in the future then it will be much easier.

## Ok, cool so how do I get it running?

Don't ask silly questions.

## How does it generate the codes?

Currently it just generates a random Base62 ([a-zA-Z0-9]) string, 5 characters in length (916,132,832 possible combinations).

There are many subjective opinions on how best to implement the code generation of a URL shortener. It is possible to do a base conversion to an ordinary base-10 number to then perform a lookup in a database, but MongoDB isn't designed for auto-incrementing numerical indexes (although possible) and looking up the code should be pretty quick anyway.