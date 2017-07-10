package webmagic;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * Created by kun on 2017/5/22.
 */
public class W3CSchool extends BaseProcessor {
	private static String baseUrl="http://www.runoob.com/";
	private static boolean first=true;

	@Override public void process(Page page) {
		Document document=page.getHtml().getDocument();
		Elements elements=document.select("#leftcolumn>a");
		if (first){
			first=false;
			Set<String> urls=new LinkedHashSet<>();
			for (Element element : elements) {
				urls.add(baseUrl+element.attr("href"));
			}
			page.addTargetRequests(new ArrayList<>(urls));
		}
		page.putField("body",document.select("#content").text());
	}
	public static void main(String[] args){
		Spider.create(new W3CSchool()).addUrl("http://www.runoob.com/css3/css3-tutorial.html").thread(1).addPipeline(new W3CSchoolPipeLine()).run();
	}
}
