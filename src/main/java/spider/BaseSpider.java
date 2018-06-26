package spider;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Created by simplelist on 2016/8/7.
 */
public abstract class BaseSpider {
    public String selector;
    public StringBuffer result=new StringBuffer();Elements element=null;
    public  String content=null;
    double startTime;
    public Document doc=null;
    public  static String baseUrl;

    public void writeStringToFile2(String filePath, String content, boolean append) {
        try {
            FileWriter fw = new FileWriter(filePath, append);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content.toString());
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void before(){
        startTime = System.currentTimeMillis();
    }

    @After
    public void after(){
        System.out.print(System.currentTimeMillis()-startTime);
    }


}
