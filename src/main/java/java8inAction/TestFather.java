package java8inAction;
import org.junit.After;
import org.junit.Before;
/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: java8inAction.TestFather.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 2017/8/1 14:32
 * Description:
 * History:
 */
public class TestFather {
	private long start;
	@Before
	public void before() {
		System.out.println(">>>>>>>>>>>>>>>>>开始执行<<<<<<<<<<<<<<<");
		start=System.currentTimeMillis();
	}
	@After
	public void after() {
		System.out.println(">>>>>>>>>>>>>耗时:"+(System.currentTimeMillis()-start)+"ms<<<<<<<<<<<");
	}
}
