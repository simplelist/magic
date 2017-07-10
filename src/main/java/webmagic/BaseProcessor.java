package webmagic;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
public abstract class BaseProcessor implements PageProcessor {

    private Site site=Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    @Override
    public Site getSite() {
        return site;
    }
}
