package queue;

import java.io.IOException;
import java.io.Serializable;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang.SerializationUtils;


/**
 * Created by radud on 13/12/2015.
 */
public class Producer {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void sendMessage(Serializable object) throws IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String routingKey = "#";
        String message = "message";

        channel.basicPublish(EXCHANGE_NAME, routingKey, null, SerializationUtils.serialize(object));
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        connection.close();
    }
}
