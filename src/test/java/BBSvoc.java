import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;

/**
 * 爬取塞班论坛二手物品交易信息
 * Created by simplelist on 2016/8/7.
 */
public class BBSvoc extends BaseSpider {

    @Test
    public void s() {
        super.baseUrl = "http://bbs.voc.com.cn/forum-50-";
        super.selector = ".tbfltd2>a[class='a1']";
        try {
            for (int i = 1; i < 20; i++) {
                parse(baseUrl + i + ".html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeStringToFile2("voc.html", result, false);

    }

    private static final String target = "http://bbs.voc.com.cn/";
    String realUrl;
    String name;
    String link;

    protected String parse(String url) throws IOException {
        System.out.println(url);
        doc = Jsoup.connect(url).get();
        element = doc.select(selector);
        for (Element e : element) {
            e.attr("href", target + e.attr("href"));
            realUrl = e.attr("href");
            name = e.text();
            link = "<a href=" + realUrl + " target='_blank'>" + name + "</a><br>";
            result.append(link);
        }
        return result.toString();
    }
}
