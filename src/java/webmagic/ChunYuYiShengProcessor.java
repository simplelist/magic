package webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
public class ChunYuYiShengProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");


    @Override
    public void process(Page page) {
        Json object = page.getJson();
        System.out.println(object);
        page.addTargetRequests(getUrl());
    }

    private List<String> getUrl() {
        List<String> urls = new LinkedList<>();
        for (int i = 2; i < 36; i++) {
            urls.add("http://www.chunyuyisheng.com/api/search/hospital/list/?page=" + i);
        }
        return urls;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        new Spider(new ChunYuYiShengProcessor()).addUrl("http://www.chunyuyisheng.com/api/search/hospital/list/?page=1").thread(1).run();
    }

    public class Hos {
        public Hos() {
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
