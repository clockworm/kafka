package com.kafka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KProducer {
	private static AtomicInteger msgNo = new AtomicInteger(1);

	public static class ProducerThread implements Runnable {
		Producer<String, String> producer = KafkaUtil.getProducer();
		String topic;

		public ProducerThread(String topic) {
			this.topic = topic;
		}

		@Override
		public void run() {
			while (true) {
				int no = msgNo.getAndIncrement();
				String data = new String("hello kafka message " + no);
				String key = String.valueOf(no);
				ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, data);
				producer.send(record, new Callback() {
					@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						
					}
				});
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService producerPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			producerPool.execute(new ProducerThread(KafkaUtil.topic));
		}
		producerPool.shutdown();

	}
}