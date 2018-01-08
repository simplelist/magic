package spider;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

public class Ty6 {


//    Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
// Accept-Encoding:gzip, deflate
//Accept-Language:zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,ja;q=0.6
//Cache-Control:max-age=0
//Connection:keep-alive
//Cookie:__cfduid=dbacc1629ddedbf888679b6d2c36172d91515246507; UM_distinctid=160cbbae2af8c2-0f9b09e59f593-16356d57-1fa400-160cbbae2b0579; __guid=107703556.3429084842951894000.1515246511116.8162; CNZZDATA950900=cnzz_eid%3D1116551705-1515242548-http%253A%252F%252Fwww.t66y.com%252F%26ntime%3D1515242548; monitor_count=2
//Host:www.t66y.com
//Referer:http://www.t66y.com/index.php
//Upgrade-Insecure-Requests:1
//User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36


    public static String fetch() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("");
        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate");
        httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,ja;q=0.6");
        httpGet.addHeader("Cache-Control", "max-age=0");
        httpGet.addHeader("Connection", "keep-alive");
        httpGet.addHeader("Cookie", "__cfduid=dbacc1629ddedbf888679b6d2c36172d91515246507; UM_distinctid=160cbbae2af8c2-0f9b09e59f593-16356d57-1fa400-160cbbae2b0579; __guid=107703556.3429084842951894000.1515246511116.8162; CNZZDATA950900=cnzz_eid%3D1116551705-1515242548-http%253A%252F%252Fwww.t66y.com%252F%26ntime%3D1515242548; monitor_count=2");
        httpGet.addHeader("Host", "www.t66y.com");
        httpGet.addHeader("Referer", "http://www.t66y.com/index.php");
        httpGet.addHeader("Upgrade-Insecure-Requests", "1");
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
// Create a custom response handler
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        httpclient.execute(httpGet, responseHandler);
        return null;
    }

    public static String fetchHTML() {
        Document doc = null;
        try {
            doc = Jsoup.connect("").
                    header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8").
                    header("Accept-Encoding", "gzip, deflate").
                    header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,ja;q=0.6").
                    header("Cache-Control", "max-age=0").
                    header("Connection", "keep-alive").
                    header("Cookie", "__cfduid=dbacc1629ddedbf888679b6d2c36172d91515246507; UM_distinctid=160cbbae2af8c2-0f9b09e59f593-16356d57-1fa400-160cbbae2b0579; __guid=107703556.3429084842951894000.1515246511116.8162; CNZZDATA950900=cnzz_eid%3D1116551705-1515242548-http%253A%252F%252Fwww.t66y.com%252F%26ntime%3D1515242548; monitor_count=2").
                    header("Host", "www.t66y.com").
                    header("Referer", "http://www.t66y.com/index.php").
                    header("Upgrade-Insecure-Requests", "1").
                    header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36").
                    get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(doc);
        return null;
    }

    @Test
    public void runSpider() throws IOException {
        System.out.println(fetch());
    }

}
