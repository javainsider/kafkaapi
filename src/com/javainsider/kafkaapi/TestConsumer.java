package com.javainsider.kafkaapi;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class TestConsumer {

	public static void main(String[] args) {

		//String topic = args[0].toString();
		String topic = "javainsider";
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		@SuppressWarnings("resource")
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

		consumer.subscribe(Arrays.asList(topic));
		System.out.println("Subscribed to topic " + topic);

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("Kafka offset = %d, Kafka topic Key = %s, Kafka Message: = %s\n", record.offset(), record.key(), record.value());
		}

	}

}
