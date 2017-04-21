package webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Json;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
public class ChunYuYiShengProcessor extends BaseProcessor {

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
