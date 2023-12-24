package com.yu.kafka.consumer;

import com.yu.kafka.constant.Constant;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

/**
 * https://kafka.apache.org/documentation/#consumerconfigs
 * @author yuhangbin
 * @date 2023/12/18
 **/
public class DefaultKafkaConsumer {

	public static KafkaConsumer<Integer,String> create() {
		Properties props = new Properties();
		// bootstrap server config is required for producer to connect to brokers
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.BOOTSTRAP_SERVERS);
		// key and value are just byte arrays, so we need to set appropriate serializers
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, Constant.GROUP_NAME);
		// client id is not required, but it's good to track the source of requests beyond just ip/port
		// by allowing a logical application name to be included in server-side request logging
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "client-" + UUID.randomUUID());
		// auto commit disable
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		return new KafkaConsumer<Integer, String>(props);
	}
}
