package cn.bugstack.algorithms.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author huangnenghe
 * @date 2022-08-29 16:36
 */
public class KafkaTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.24.2.25:9092,172.18.1.155:9092");
        props.put("retries", 2);
        props.put("linger.ms", 1);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(
                props);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("hnhe", "11", "kafka集群 ->" + i);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    // TODO Auto-generated method stub
                }
            });
            try {
                Thread.sleep(1000);
                // producer.close();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}