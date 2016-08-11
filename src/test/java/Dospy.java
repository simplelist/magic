import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by simplelist on 2016/8/7.
 */
public class Dospy extends BaseSpider {

    @Test
    public void s(){
        super.baseUrl="http://bbs.dospy.com/forum-141-1.html";
        super.selector=".txtd>a:not(a[style])";
        try {
            parse(baseUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
       System.out.println(result.toString());
//        try {
//            for(int i=2;i<194;i++){
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        writeStringToFile2("a.txt",result);

    }

    protected String parse(String url) throws IOException {
        System.out.println(url);
        doc = Jsoup.connect(url).get();
        element= doc.select(selector);
            for(Element e:element){
                System.err.println(e);



                result.append(e.text());
            }
        return result.toString();
    }
}
