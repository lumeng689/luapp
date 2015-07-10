package org.luapp.practise.mqtt;

import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lum on 2015/7/1.
 */
public class HelloMqtt {

    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MQTT mqtt = new MQTT();
                    mqtt.setHost("localhost", 1883);

                    BlockingConnection connection = mqtt.blockingConnection();
                    connection.connect();
                    Topic[] topics = {new Topic("topic", QoS.AT_LEAST_ONCE)};
                    byte[] qoses = connection.subscribe(topics);

                    while (true) {
                        System.out.println("......");
                        TimeUnit.SECONDS.sleep(3);
                        Message message = connection.receive();
                        System.out.println("received topic: " + message.getTopic());
                        byte[] payload = message.getPayload();
                        System.out.println("received content: " + new String(payload, "utf-8"));
                        message.ack();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();

        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);

        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();

        int i = 0;
        while(true) {
            System.out.println("publish......");
            connection.publish("topic", ("hello" + i++).getBytes(), QoS.AT_LEAST_ONCE, false);
            TimeUnit.SECONDS.sleep(3);
        }

//        connection.disconnect();

//        Thread.currentThread().join();
    }
}
