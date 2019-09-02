package weixin;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;

import java.nio.charset.Charset;

/**
 * @author 胡说
 * @data 2019-07-30 15:57
 * TODO
 */
public class TestA {
    public static void main(String[] args) {
        CsvWriter writer = CsvUtil.getWriter(
                "/Users/kun/Documents/tmp/a.csv",
                Charset.defaultCharset()
                , true
        );

        writer.write(new String[]{"a", "b", "串号"});

    }
}
