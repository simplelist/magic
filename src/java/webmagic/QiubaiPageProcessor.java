package webmagic;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public class QiubaiPageProcessor implements PageProcessor {
    private Site site=Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    public void process(Page page) {
        System.out.println(page.getUrl());
        Elements elements=page.getHtml().getDocument().select(".content>span");
        List<String> contents=new LinkedList<>();
        if (elements.size()>0){
            for (Element element : elements) {
                contents.add(element.text());
            }
            page.putField("content",contents);
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

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new QiubaiPageProcessor()).addUrl("http://www.qiushibaike.com/").thread(5).addPipeline(new QiuBaiPipeLine()).run();
    }
}
