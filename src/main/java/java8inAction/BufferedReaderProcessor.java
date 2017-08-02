package java8inAction;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: java8inAction.BufferedReaderProcessor.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 2017/8/1 16:28
 * Description:
 * History:
 */
public interface BufferedReaderProcessor {
	String process(BufferedReader b) throws IOException;
}
