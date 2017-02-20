package com.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

public class KafkaUtil {
	public final static String topic = "Kaffka_demo2";
	public final static String bootstrap_servers = "localhost:9092,localhost:9093,localhost:9094";

	public final static String group_id = "group_demo2";
	public final static String key_serializer = "org.apache.kafka.common.serialization.StringSerializer";
	public final static String value_serializer = "org.apache.kafka.common.serialization.StringSerializer";
	public final static String key_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
	public final static String value_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";

	private static KafkaProducer<String, String> kp;
	private static KafkaConsumer<String, String> kc;

	public static KafkaProducer<String, String> getProducer() {
		if (kp == null) {
			Properties props = new Properties();
			props.put("bootstrap.servers", bootstrap_servers);
			props.put("acks", "all");
			props.put("client.id", "demo2_producer");
			props.put("retries", 1);
			props.put("batch.size", 16384);
			props.put("key.serializer", key_serializer);
			props.put("value.serializer", value_serializer);
			kp = new KafkaProducer<String, String>(props);
		}
		return kp;
	}

	public static KafkaConsumer<String, String> getConsumer() {
		if (kc == null) {
			Properties props = new Properties();

			props.put("bootstrap.servers", bootstrap_servers);
			props.put("group.id", group_id);
			props.put("client.id", "demo2_consumer");
			props.put("heartbeat.interval.ms", "200");
			props.put("enable.auto.commit", "true");
			props.put("auto.commit.interval.ms", "1000");
			props.put("session.timeout.ms", "30000");
			props.put("key.deserializer", key_deserializer);
			props.put("value.deserializer", value_deserializer);
			kc = new KafkaConsumer<String, String>(props);
		}

		return kc;
	}
}