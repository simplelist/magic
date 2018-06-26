package spider;

import cn.hutool.core.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Books extends BaseSpider {

    static String filePath = "/Users/kun/Documents/file/{}.txt";
    Map<String, String> resultMap = new HashMap<>();

    public static void main(String args[]) throws IOException {
        getList("https://www.xbookcn.net/book/zaishi/index.htm");
//        System.out.println(getContent("https://www.xbookcn.net/book/caihua/2.htm"));
    }

    protected static String getList(String url) {
        StringBuffer stringBuffer = new StringBuffer();
        Document list = null;
        try {
            list = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = list.select(".content a");
        try {

            for (Element e : elements) {
                stringBuffer.append(e.text() + "\n").append(getContent(url.replace("index.htm", e.attr("href"))));
//                Document content = Jsoup.connect(url.replace("index.htm", e.attr("href"))).get();
//                stringBuffer.append(content.select(".content p").text());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return stringBuffer.toString();
    }

    protected static String getContent(String url) throws IOException {
        Document content = Jsoup.connect(url).header("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,ja;q=0.6")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("cookie", "zh_choose=s; sc_is_visitor_unique=rx1659903.1526047360.0F5F908B0D174F7908196A51ECF8AA05.1.1.1.1.1.1.1.1.1; zh_choose=s; _ga=GA1.2.1191909582.1526046623; _gid=GA1.2.500572681.1527856514; _gat=1")
                .header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36")
                .get();
        System.out.println("发送请求" + url);
        return content.select(".content p").text();
    }

    @Test
    public void s() {
        super.baseUrl = "https://www.xbookcn.net/";
        super.selector = ".table-container center table td a";
        parse(baseUrl);
//        try {
//            for (int i = 1; i < 2; i++) {
//                parse(baseUrl + i + ".html");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(result.toString());
//        writeStringToFile2("a.html", result, false);

        int i = 1;
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println("正在处理:<" + entry.getValue() + ">" + i + "/" + resultMap.size());
            writeStringToFile2(StrUtil.format(filePath, entry.getValue()), getList(entry.getKey()), false);
            System.out.println("处理结束:<" + entry.getValue() + ">");
            i++;
        }
    }

    protected String parse(String url) {
//        System.out.println(url);
        try {
            doc = Jsoup.connect(url)
                    .header("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,ja;q=0.6")
                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("cookie", "zh_choose=s; sc_is_visitor_unique=rx1659903.1526047360.0F5F908B0D174F7908196A51ECF8AA05.1.1.1.1.1.1.1.1.1; zh_choose=s; _ga=GA1.2.1191909582.1526046623; _gid=GA1.2.500572681.1527856514; _gat=1")
                    .header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        element = doc.select(selector);
        for (Element e : element) {
//            System.err.println(e);
            resultMap.put(super.baseUrl + e.attr("href"), e.text());
            result.append(e).append("<br>");
        }
        return result.toString();
    }
}
