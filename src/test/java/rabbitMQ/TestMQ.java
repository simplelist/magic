package rabbitMQ;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by jackshi on 16/8/30.
 */
public class TestMQ {
    public TestMQ() throws IOException, TimeoutException {

        Producer producer=new Producer("queue");
        /*发送消息*/
        String mes="发送消息";
        for (int i=0;i<10;i++){
            producer.sendMessage(mes+i);
        }
        QueueConsumer consumer=new QueueConsumer("queue");
    }

    @Test
    public  void  tests(){
        try {
            new TestMQ();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
