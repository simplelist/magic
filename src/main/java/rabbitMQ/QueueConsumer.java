package rabbitMQ;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by jackshi on 16/8/30.
 */
public class QueueConsumer extends EndPoint implements Consumer {
    public QueueConsumer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
        channel.basicConsume(endPointName,true,this);
    }

    public void handleConsumeOk(String s) {
        System.out.println("Consumer "+s+" registered");
    }

    public void handleCancelOk(String s) {

    }

    public void handleCancel(String s) throws IOException {

    }

    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    public void handleRecoverOk(String s) {

    }

    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        String message=new String(bytes,"utf-8");
        System.out.println(message);
    }

}
