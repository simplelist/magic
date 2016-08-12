import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by simplelist on 2016/8/7.
 * caoliu
 */
public class TestSoup extends BaseSpider{

    //125389.0
    //116306.0 类成员document
    //141586.0 throws IOExecption
    //72316.0 Mac
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
        System.out.print(System.currentTimeMillis()-start);
    }

    protected String parse(String url) throws IOException {
        System.err.println(url);
        doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36").post();
        element= doc.select(selector);
            for(Element e:element){
                result.append(e.text());
            }
        return result.toString();
    }
}
