package webmagic;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public class QiubaiPageProcessor extends BaseProcessor {
    public void process(Page page) {
        System.out.println(page.getUrl());
        Elements elements = page.getHtml().getDocument().select(".content>span");
        List<String> contents = new LinkedList<>();
        if (elements.size() > 0) {
            for (Element element : elements) {
                contents.add(element.text());
            }
            page.putField("content", contents);
        }

//        Elements urls=page.getHtml().getDocument().select(".pagination li a");
//        if (elements.size()>0){
//            int current=0;
//            for (Element url : urls) {
//                System.out.println(current);
//                    page.addTargetRequest(url.attr("href"));
//                current++;
//            }
//        }
    }

    public static void main(String[] args) {
        Spider.create(new QiubaiPageProcessor()).addUrl("http://www.qiushibaike.com/").thread(5).addPipeline(new QiuBaiPipeLine()).run();
    }
}
