package cn.bugstack.algorithms.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author huangnenghe
 * @date 2022-08-29 16:48
 */
public class Consume {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Properties props = new Properties();
        //设置brokerServer(kafka)ip地址
        props.put("bootstrap.servers", "172.24.2.25:9092,172.18.1.155:9092");
        //设置consumer group name
        props.put("group.id","mygroup");
        props.put("enable.auto.commit", "false");
        //props.put("auto.offset.reset", "earliest");
        //设置心跳时间
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String ,String> consumer = new KafkaConsumer<String ,String>(props);
        consumer.subscribe(Arrays.asList("hnhe"));
        final int minBatchSize = 5;  //批量提交数量
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("consumer message values is "+record.value()+" and the offset is "+ record.offset());
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                System.out.println("now commit offset"+buffer.size());
                consumer.commitSync();
                buffer.clear();
            }
        }
    }
}
