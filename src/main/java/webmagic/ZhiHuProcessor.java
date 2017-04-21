package webmagic;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
public class ZhiHuProcessor extends BaseProcessor {

    @Override
    public void process(Page page) {
        Document document=page.getHtml().getDocument();
        System.out.println(document);
        Elements elements=document.select("img");
        for (Element element : elements) {
            System.out.println(element.attr("src"));
        }
    }

    public static void main(String[] args){
        Spider.create(new ZhiHuProcessor()).addUrl("https://www.zhihu.com/question/57862955").thread(5).run();
    }
}
