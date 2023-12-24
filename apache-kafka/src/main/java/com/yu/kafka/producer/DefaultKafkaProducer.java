package com.yu.kafka.producer;

import com.yu.kafka.constant.Constant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * https://kafka.apache.org/documentation/#producerconfigs
 * @author yuhangbin
 * @date 2023/12/18
 **/
public class DefaultKafkaProducer {

	public static KafkaProducer<Integer, String> create() {
		Properties props = new Properties();
		// bootstrap server config is required for producer to connect to brokers
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.BOOTSTRAP_SERVERS);
		// key and value are just byte arrays, so we need to set appropriate serializers
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new KafkaProducer<>(props);
	}

}
