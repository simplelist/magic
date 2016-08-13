import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Callable;

/**
 * Created by simplelist on 2016/8/7.
 * caoliu
 */
public class TestSoup extends BaseSpider {
    Map<Integer, String> urlsMap = new Hashtable<Integer, String>();
    Map<Integer, String> resultMap = new Hashtable<Integer, String>();
    int threadSize=5;
    //125389.0
    //116306.0 类成员document
    //141586.0 throws IOExecption
    //72316.0 Mac
    @Test
    public void s() {
        super.baseUrl = "http://cl.oaiyr.com/read.php?tid=1863480&page=";
        super.selector = "div[class='tpc_content']";
        double start = System.currentTimeMillis();
        try {
            for (int i = 2; i < 194; i++) {
                urlsMap.put(i, baseUrl + i);
            }
            System.out.println(urlsMap.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= threadSize; i++) {
            ParseThread parseThread1 = new ParseThread(i);
            Thread t1 = new Thread(parseThread1, "线程"+i+"正在运行:");
            t1.start();
        }
//        System.out.println(urlsMap);
//        System.out.print(System.currentTimeMillis() - start);
        System.out.println(resultMap);
    }


    class ParseThread implements Runnable {

        private int urlIndex;

        public ParseThread(int urlIndex) {
            this.urlIndex = urlIndex;
        }

        public void run() {
            try {
                int perThreadSize = urlsMap.size() / threadSize;
                for (int currentPage = 1; currentPage <= perThreadSize && urlsMap.size() > 0; currentPage++) {
                    synchronized (urlsMap) {
                        int urlCount = currentPage * urlIndex;
                        if (urlsMap.containsKey(urlCount)) {
                            System.out.println(Thread.currentThread().getName() + currentPage + ">" + urlIndex + "=" + urlCount);
                            resultMap.put(urlCount, parse(urlsMap.get(urlCount)));
                            urlsMap.remove(urlCount);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        protected String parse(String url) throws IOException {
//            System.err.println(url);
//            result=new StringBuffer();
//            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36").post();
//            element = doc.select(selector);
//            for (Element e : element) {
//                String content = e.text();
//                if (content.length() > 1000) {
//                    result.append(content);
//                }
//            }
            return "处理过的url"+url;
        }
    }
}
