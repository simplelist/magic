package webmagic;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
public class Tu extends BaseProcessor {

    @Override
    public void process(Page page) {
        Document doc=page.getHtml().getDocument();
        Elements elements=doc.select(".toplist_right ul li a img");
        String fileUrl;
        Element element;
        StringBuffer name;
        for (int i = 0; i < elements.size(); i++) {
            name=new StringBuffer("D:\\output\\");
            element=elements.get(i);
            fileUrl=element.attr("lazysrc");
            System.out.println(element.attr("alt")+":"+fileUrl);
            name.append(i);
            name.append(".");
            name.append(FileUtil.extName(fileUrl));
            HttpUtil.downloadFile(fileUrl,name.toString());
        }

    }
    public static void main(String [] args){
        Spider.create(new Tu()).addUrl("http://nanameitu.com/top/").thread(5).run();
    }
}
