package spider;

import com.xiaoleilu.hutool.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import strman.Strman;

import java.io.IOException;
import java.util.*;

/**
 * 爬取好大夫的医院信息
 * Created by simplelist on 2016/8/7.
 */
public class HaoDaiFu  {

    private List<String>  provinces=new ArrayList<>();
    @Test
    public void s() {
        String baseUrl="http://www.haodf.com/yiyuan/guangdong/list.htm";
        Document document=null;
        try {
            document=Jsoup.connect(baseUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements city=document.select("#el_tree_1000000 .kstl a");
        for (Element element : city) {
            provinces.add(element.attr("href"));
        }
        try {
            List<Hospital> result=  getByProvince(provinces);
            for (Hospital hospital : result) {
                System.out.println(hospital);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Hospital> getByProvince(List<String> provinces) throws IOException {
            Document document=null;
        List<Hospital> result=new LinkedList<>();
        for (String province : provinces) {
            document=Jsoup.connect(province).get();
            String provStr=document.getElementsByTag("title").text();
            provStr= StrUtil.subPre(provStr,provStr.length()-8);
            Elements elements=document.select(".ct");
            for (Element element : elements) {

                Elements areas=element.getElementsByClass("m_title_green");
                for (Element area : areas) {
                    String city=area.text();
                    Element hospital=area.nextElementSibling();

                    for (Element hos : hospital.getElementsByClass("m_ctt_green")) {
                       Elements names=hos.select("ul li");
                        for (Element name : names) {
                            result.add(new Hospital(provStr,city,name.text()));
                        }
                    }
                }
            }
        }
        return result;
    }

}
