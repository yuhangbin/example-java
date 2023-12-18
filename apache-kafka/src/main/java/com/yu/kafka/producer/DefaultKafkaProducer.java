package com.yu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author yuhangbin
 * @date 2023/12/18
 **/
public class DefaultKafkaProducer<K,V> {

	public Properties buildConfig() {
		return new Properties();
	}

	public KafkaProducer<K,V> create() {
		return new KafkaProducer<K,V>(buildConfig());
	}

	public Future<RecordMetadata> doAsync(KafkaProducer<K,V> producer, String topicName, K key, V value) {

		return null;
	}
}
