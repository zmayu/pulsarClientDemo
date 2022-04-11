package com.hw.pulsar.function.demo.clients;

import com.hw.pulsar.function.demo.benchmark.DataStructure;
import com.hw.pulsar.function.demo.benchmark.DataStructureSerde;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;


public class TestConsumer {

    public static void main(String[] args) {

        try {
            String brokerUrl = "pulsar://pl.yuzma.cn:6650";
            // 租户  命名空间  topic
            String topicName = "persistent://my-tenant/test-namespace/tp2";
            String subName = "my-scr";
            PulsarClient client = PulsarClient.builder().serviceUrl(brokerUrl).build();
            System.out.println(client.toString());


            Consumer<byte[]> consumer = client.newConsumer().topic(topicName).
                    subscriptionName(subName).subscribe();

            while (true) {
                try {
                    Message<byte[]> msg = consumer.receive();
                    System.out.println(new String(msg.getValue()));
               /*    DataStructure dataStructure = DataStructureSerde.deserialize(msg.getValue());
                    consumer.acknowledge(msg);
                    System.out.println("message info (sentence: " + dataStructure.getSentence()
                            + ", startTime: " + dataStructure.getStartTime()
                            + ", endTime: " + dataStructure.getEndTime()
                            + ", msgLength: " + dataStructure.getMsgLength());*/

                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            client.close();

        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }
}
