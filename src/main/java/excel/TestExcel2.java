package excel;

import com.icexls.IceExcel;
import com.icexls.IceExcelConfig;
import com.icexls.NumberType;
import com.icexls.ParserType;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
public class TestExcel2 {
        @Test
        public void export() {
            String[][] data = { { "aaa", "167" }, { "bbb", "2343" }, { "ccc", "120.362" }, { "ddd", "32434" } };
            IceExcel iceExcel = new IceExcel("D:/xls-test.xls");
            // IceExcel iceExcel=new
            // IceExcel("C:/Users/Administrator/Desktop/xls-test.xls","test-data");
            // IceExcelConfig.setSheet(iceExcel,"hello");
            IceExcelConfig.setNumberType(iceExcel, NumberType.STRING);
            IceExcelConfig.setParserType(iceExcel, ParserType.POI);
            iceExcel.setData(data);
            System.out.println("export complete");
        }

         @Test
        public void importx() {
            IceExcel iceExcel = new IceExcel("D:/xls-test.xls");
            IceExcelConfig.setParserType(iceExcel, ParserType.POI);
            String[][] data = iceExcel.getData();
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    System.out.print(data[i][j] + "\t  ");
                }
                System.out.println();
            }
        }
    }
