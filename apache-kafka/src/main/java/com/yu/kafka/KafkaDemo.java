package com.yu.kafka;

import com.yu.kafka.constant.Constant;
import com.yu.kafka.consumer.DefaultKafkaConsumer;
import com.yu.kafka.producer.DefaultKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author yuhangbin
 * @date 2023/12/24
 **/
public class KafkaDemo {

	public static void main(String[] args) {
		// create producer and consumer
		try (KafkaProducer<Integer,String> producer = DefaultKafkaProducer.create();
			KafkaConsumer<Integer,String> consumer = DefaultKafkaConsumer.create()){
			Future<RecordMetadata> future = producer.send(new ProducerRecord<>(Constant.TOPIC_NAME, 1, "1"));
			producer.flush();
			while (true) {
				consumer.subscribe(List.of(Constant.TOPIC_NAME));
				ConsumerRecords<Integer,String> consumerRecords = consumer.poll(Duration.ofMillis(500));
				if (consumerRecords.isEmpty()) {
					System.out.println("not message");
					break;
				}
				for (ConsumerRecord<Integer, String> record : consumerRecords) {
					System.out.println("Key: " + record.key() + ", Value: " + record.value());
					System.out.println("Partition: " + record.partition() + ", Offset:" + record.offset());
				}
				consumer.commitSync();
			}
		}catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
