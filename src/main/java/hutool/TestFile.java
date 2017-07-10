package hutool;

import com.xiaoleilu.hutool.http.HttpUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
public class TestFile {

    private String src="http://img4.cache.netease.com/photo/0001/2017-04-16/CI4IKQ5500AP0001.jpg";
    @Test
    public void testWriteFile(){
        HttpUtil.downloadFile(src,"d:/a.jpg");
    }
}
