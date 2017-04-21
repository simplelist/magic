package rabbitMQ;

import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by jackshi on 16/8/30.
 */
public class Producer extends EndPoint{

    public Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }
    public void sendMessage(Serializable object) throws IOException{
        channel.basicPublish("",endPointName,null, SerializationUtils.serialize(object));
    }
}
