package webmagic;

import com.xiaoleilu.hutool.util.CollectionUtil;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/4.
 */
public class QiuBaiPipeLine implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> result=new ArrayList();
        result=resultItems.get("content");
        if (CollectionUtil.isNotEmpty(result)){
            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}
