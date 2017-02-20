package com.kafka;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class KConsumer{
    private final String topic;
    private final Consumer<String, String> consumer=KafkaUtil.getConsumer();

    public KConsumer(String topic) {
        this.topic = topic;
    }

    public void consume() {
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Consumer  record  offset=" + record.offset() + "  key=" + record.key() + " value="
                        + record.value());
            }
        }
    }
    public void close(){
        consumer.close();
    }
    public static void main(String[] args) {
        KConsumer consumer=new KConsumer(KafkaUtil.topic);
        consumer.consume();
        consumer.close();
    }
}