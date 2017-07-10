package excel;

import com.github.abel533.easyxls.EasyXls;
import com.github.abel533.easyxls.bean.ExcelConfig;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by kun on 2017/4/12.
 */
public class TestExcel {

    @Test
    public void test() {
        try {
            //创建一个配置
            ExcelConfig config = new ExcelConfig.Builder(DimCity.class)
                    .sheetNum(0)
                    .startRow(1)
                    .key("id")
                    .addColumn("id","province", "city", "pinyin").build();
            File file=new File("/Users/kun/Desktop/2.xls");
            List list = EasyXls.xls2List(config, file);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(((DimCity) list.get(i)).getCity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
