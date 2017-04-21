package webmagic;

import com.xiaoleilu.hutool.io.FileUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
public class Tu extends BaseProcessor {
    @Override
    public void process(Page page) {
        System.out.println(page.getHtml());
    }
    public static void main(String [] args){
        Spider.create(new Tu()).addUrl("http://img4.cache.netease.com/photo/0001/2017-04-16/CI4IKQ5500AP0001.jpg").thread(5).run();
    }
}
