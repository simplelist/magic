package learn;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kun on 2017/4/19.
 */
public class TestIOStream {
    public static void main(String [] args) {
        TestIOStream t=new TestIOStream();
        System.out.println("输入字符:");
        try {
            t.copy(System.in,System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copy(InputStream is, OutputStream os) throws IOException{
        byte[] buf=new byte[4096];
        int len=is.read(buf);
        while (len!=-1){
            os.write(buf,0,len);
            len=is.read(buf);
        }
    }
}
