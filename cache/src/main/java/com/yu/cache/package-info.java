/**
 * One fundamental difference between a cache and a Map is that a cache evicts stored items.
 * Google Guava
 * 	Eviction Policy: LRU
 *
 * Caffeine Cache (https://github.com/ben-manes/caffeine)
 * 	Eviction Policy: Window Tiny LRU
 * 	- http://highscalability.com/blog/2016/1/25/design-of-a-modern-cache.html
 */
package com.yu.cache;