package com.javainsider.kafkaapi;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TestProducer {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		@SuppressWarnings("rawtypes")
		KafkaProducer producer = new KafkaProducer(props);
		for (int key = 0; key < 50; key++) {
			producer.send(new ProducerRecord<String, String>("javainsider", Integer.toString(key), "My test message for key:"+key));
			System.out.println("Kafka topic Key=" + key +", Kafka Message:"+"My test message for key:"+key);
		}

		producer.close();
	}

}
