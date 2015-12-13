package client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import model.DVD;
import org.apache.commons.lang.SerializationUtils;
import utils.FileUtils;
import utils.MailUtils;

import java.io.IOException;

/**
 * Created by radud on 13/12/2015.
 */
public class StartClient {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String argv[]) throws IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, "#");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            DVD dvd = (DVD) SerializationUtils.deserialize(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();

            System.out.println(" [x] Received '" + routingKey + "':'" + dvd.toString() + "'");

            MailUtils.send("radu_dorin1993@yahoo.com", dvd);
            FileUtils.writeToFile(consumer.getConsumerTag() + ".txt", dvd);
        }
    }

}
