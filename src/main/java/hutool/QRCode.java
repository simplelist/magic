package hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import org.junit.Test;

/**
 * @Author kun
 * @DATETIME 2018/6/26 下午4:11
 * TODO
 */
public class QRCode {
    @Test
    public void codeTest() {
        QrCodeUtil.generate("http://www.baidu.com", 300, 300, FileUtil.file("/Users/kun/Downloads/leech310.png"));
    }
}
