package spider;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by simplelist on 2016/11/6.
 */
public class Qiubai {

    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";

    @Test
    public void test1() {
        System.out.println(english());
    }


    public static String joke() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.qiushibaike.com/history/").userAgent(userAgent).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements element = doc.select(".content>span");
        return element.first().text();
    }

    /**
     * jQuery1830619424725870273_1478415521893({"errno":0,"errmsg":"success","sid":2405,"title":"2016-11-06","content":"\"Hope is a good thing, maybe the best of things, and no good thing ever dies. \"","note":"\u5e0c\u671b\u662f\u7f8e\u597d\u7684\uff0c\u4e5f\u8bb8\u662f\u4eba\u95f4\u81f3\u5584\uff0c\u800c\u7f8e\u597d\u7684\u4e8b\u7269\u6c38\u4e0d\u6d88\u901d\u3002 ","translation":"\u8bcd\u9738\u5c0f\u7f16\uff1a\u5e0c\u671b\u2014\u2014\u5fc3\u4e2d\u6700\u771f\u5207\u7684\u5e7b\u60f3\u3001\u76fc\u671b\u3001\u671f\u671b\u3001\u613f\u671b\u3002\u671f\u671b\u8fbe\u5230\u7684\u67d0\u79cd\u76ee\u7684\u6216\u51fa\u73b0\u7684\u67d0\u79cd\u60c5\u51b5\u3002\u8fd9\u79cd\u671f\u76fc\u662f\u4eba\u95f4\u81f3\u5584\u4e5f\u53ef\u80fd\u662f\u6b32\u671b\uff0c\u65e0\u8bba\u5982\u4f55\u8fd8\u662f\u5e0c\u671b\u6bcf\u4e2a\u4eba\u7684\u5e0c\u671b\u90fd\u662f\u660e\u4eae\u7684\uff0c\u4e3a\u4e86\u7f8e\u597d\u8654\u8bda\u7977\u544a\u7684~\u5927\u5bb6\u4eca\u5929\u65e9\u7761\u660e\u5929\u5f00\u59cb\u65b0\u7684\u4e00\u5468\u4e86\u5466\uff01\r\n","picture":"http:\/\/cdn.iciba.com\/news\/word\/20161106.jpg","picture2":"http:\/\/cdn.iciba.com\/news\/word\/big_20161106b.jpg","picture3":"http:\/\/cdn.iciba.com\/news\/word\/xiaomi_20161106mi.jpg","caption":"\u8bcd\u9738\u6bcf\u65e5\u4e00\u53e5","tts":"http:\/\/news.iciba.com\/admin\/tts\/2016-11-06-day.mp3","tts_size":"79.49K","s_pv":0,"sp_pv":0,"love":1954,"s_content":"","s_link":"","period":0,"loveFlag":0,"tags":[{"tagid":"45","tagname":"\u56fe\u6587"}]})
     *
     * @return
     */
    public static String english() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://sentence.iciba.com/index.php?callback=jQuery1830619424725870273_1478415521893&c=dailysentence&m=getTodaySentence&_=" + new DateTime()).userAgent(userAgent).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = doc.body().text().substring(40);
        str = str.substring(0, str.length() - 1);
        System.out.println(str);
        StringBuffer result = new StringBuffer("英文:\n");
        try {
            JSONObject object = new JSONObject(str);
            result.append(object.getStr("content")).append("\n中文:\n").append(object.getStr("note"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


}
