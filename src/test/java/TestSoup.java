import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by simplelist on 2016/8/7.
 * caoliu
 */
public class TestSoup extends BaseSpider{

    //125389.0
    //116306.0 类成员document
    //141586.0 throws IOExecption

    @Test
    public void s(){
        super.baseUrl="http://cl.oaiyr.com/read.php?tid=1863480&page=";
        super.selector="div[class='tpc_content']";
        double start=System.currentTimeMillis();
        try {
            for(int i=2;i<194;i++){
                parse(baseUrl+i);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        writeStringToFile2("a.txt",result);
        double end=System.currentTimeMillis();
        System.out.print(end-start);
    }

    protected String parse(String url) throws IOException {
        doc = Jsoup.connect(url).get();
        element= doc.select(selector);
            for(Element e:element){
                result.append(e.text());
            }
        return result.toString();
    }
}
