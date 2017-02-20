package com.kafka;

public class Config {

	public final static String TOPIC = "TEST-TOPIC";
	public final static String bootstrap_servers = "localhost:9092";
	public final static String group_id = "jd-group";
	public final static String key_serializer = "org.apache.kafka.common.serialization.StringSerializer";
	public final static String value_serializer = "org.apache.kafka.common.serialization.StringSerializer";
	public final static String key_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
	public final static String value_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";

}