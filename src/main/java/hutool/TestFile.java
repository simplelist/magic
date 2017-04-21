package hutool;

import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.ImageUtil;
import org.junit.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
