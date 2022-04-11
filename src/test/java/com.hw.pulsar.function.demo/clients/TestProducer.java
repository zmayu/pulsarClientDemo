package com.hw.pulsar.function.demo.clients;

import com.google.protobuf.Descriptors;
import org.apache.pulsar.client.api.*;

public class TestProducer {
    public static void main(String[] args) {
        String brokerUrl = "pulsar://pl.yuzma.cn:6650";
        String topicName = "persistent://my-tenant/test-namespace/tp1";
        try {
            Descriptors descriptors;
            PulsarClient client = PulsarClient.builder().serviceUrl(brokerUrl).build();
            System.out.println(client.toString());

            Producer<String> producer = client.newProducer(Schema.STRING).topic(topicName).create();

            while (true) {
                try {
                    MessageId msgId = producer.newMessage().value("hello").send();
                    System.out.println(msgId);
                    Thread.sleep(500);

                    continue;

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
