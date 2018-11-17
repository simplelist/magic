package spider;

import cn.hutool.core.collection.CollUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author kun
 * @DATETIME 2018/9/9 下午3:44
 * TODO
 */
public class EnglishName {

    @Test
    public void test() {
        Document doc = null;
        try {
            File in = new File("/Users/kun/Documents/workspace/self/magic/src/main/java/spider/eng-name.html");

            doc = Jsoup.parse(in, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        body > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(2) > td:nth-child(1) > p > span
        Elements elements = doc.select("body > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr:nth-child(3) > td > table > tbody > tr > td:nth-child(1) > p > span");
        System.out.println(elements.size());
        List<String> names = new LinkedList<>();
        for (Element element : elements) {
            names.add(element.text());
        }
        System.out.println(CollUtil.join(elements, ","));


    }

    @Test
    public void test金庸武侠小说角色() {
        Document doc = null;
        try {
            File in = new File("/Users/kun/Documents/workspace/self/magic/src/main/java/spider/role-name.html");

            doc = Jsoup.parse(in, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        #main > div.booklist > div:nth-child(2) > a:nth-child(2)
//        #main > div.booklist > div:nth-child(2)
        Elements elements = doc.select("#main > div.booklist > div>a>img");
        System.out.println(elements.size());
        List<String> names = new LinkedList<>();
        for (Element element : elements) {
            names.add(element.attr("alt"));
        }
        System.out.println(CollUtil.join(names, ","));


    }


}
