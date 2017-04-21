package spider;

import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import strman.Strman;

import java.io.IOException;

/**
 * 爬取塞班论坛二手物品交易信息
 * Created by simplelist on 2016/8/7.
 */
public class BtHome {

    private static final String domainURI = "http://www.btbtt.co/";

    public static void main(String[] args) {
        System.out.println(searchPan("小鬼"));
    }

    public static String searchPan(String keyword) {
        String baseUrl = domainURI + "search-index-keyword-" + keyword + ".htm";
        try {
            return parse(baseUrl.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String parse(String url) throws IOException {
        Document doc = doc = Jsoup.connect(url).
                header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36").get();
        Elements element = doc.select("#threadlist>table .subject_link");
        StringBuilder result = new StringBuilder();
        for (Element e : element) {
            result.append(processURL(e));
        }
        return result.toString();
    }

    private static String processURL(Element content) {
        Elements ahref = content.select("a[href]");
        String url = domainURI + ahref.attr("href");
        String result = url + ahref.attr("title");
        return result + "<br>\n";
    }
}
