import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by simplelist on 2016/8/13.
 */
public class BBSvocImage extends BaseSpider {
    private static final String downloadpath = "O:\\download\\";
    private static final String target = "http://bbs.voc.com.cn/";
    private String folder;
    private String fileName;

    @Test
    public void image() {
        super.selector = "img[onmouseover='if(this.width>screen.width*0.7) this.width=screen.width*0.7;']";
        try {
            File input = new File("voc.html");
            Document docment = Jsoup.parse(input, "UTF-8", "http://www.oschina.net/");
            Elements elements = docment.select("a");
            for (Element el : elements) {

                parseImage(el.attr("href"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseImage(String startUrl) throws IOException {
        System.out.println(startUrl);
        doc = Jsoup.connect(startUrl).get();
        element = doc.select(selector);
        Element el;
        System.out.println(element.size());
        if (element.size() == 0) return;
        for (int i = 0; i < element.size(); i++) {
            String title = doc.title();
            folder = doc.getElementsByTag("h1").text();
            el = element.get(i);
            String src = el.attr("src");
            System.out.println(src);
//            download(src);
            fileName = i + src.substring(src.lastIndexOf("."));
            System.out.println(fileName);
        }

    }

    private void download(String src) throws FileNotFoundException {
        makeDirs(downloadpath + folder);
        writeImage(downloadpath + folder + "/" + fileName, src);
    }

    public void makeDirs(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void writeImage(String fileName, String src) throws FileNotFoundException {
        File file = new File(fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        try {
            outputStream.write(readInputStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] readInputStream(String src) throws Exception {
        //new一个URL对象
        URL url = new URL(src);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
