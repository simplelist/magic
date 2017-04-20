package learn;

import org.junit.Test;

import java.io.*;

/**
 * Created by kun on 2017/4/19.
 */
public class TestFileIOStream {
    @Test
    public void test() {
        File inFile=new File("/Users/kun/Desktop/in.txt");
        File outFile=new File("out.txt");
        FileInputStream inStream=null;
        FileOutputStream outStream=null;
        try {
            inStream=new FileInputStream(inFile);
            outStream=new FileOutputStream(outFile);
            int c;
            while ((c=inStream.read())!=-1){
                outStream.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inStream!=null){
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outStream!=null){
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
