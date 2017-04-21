package learn;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by kun on 2017/4/19.
 */
public class TestFile {
    @Test
    public void test() {
        File f1=new File("/Users/kun/Desktop/in3.txt");
        try {
            System.out.println(f1.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        f1=new File("/Users/kun/Desktop/");
        File[] files=f1.listRoots();
        for (File file : files) {
            System.out.println(file);
            if (file.length()>0){
                String[] filenames=file.list();
                for (String filename : filenames) {
                    System.out.println(filename);
                }
            }
        }
    }
}
