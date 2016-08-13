import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;

/**
 * 爬取塞班论坛二手物品交易信息
 * Created by simplelist on 2016/8/7.
 */
public class Dospy extends BaseSpider {

    @Test
    public void s() {
        super.baseUrl = "http://bbs.dospy.com/forum-141-";
        super.selector = ".txtd>a:not(a[style])";
        try {
            for (int i = 1; i < 20; i++) {
                parse(baseUrl + i + ".html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
        writeStringToFile2("a.html", result, false);

    }

    protected String parse(String url) throws IOException {
        System.out.println(url);
        doc = Jsoup.connect(url).get();
        element = doc.select(selector);
        for (Element e : element) {
            System.err.println(e);
            result.append(e).append("<br>");
        }
        return result.toString();
    }
}
