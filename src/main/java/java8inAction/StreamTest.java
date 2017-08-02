package java8inAction;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.LongStream;
/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: java8inAction.StreamTest.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 2017/8/1 14:29
 * Description:
 * History:
 */
public class StreamTest extends TestFather {

	@Test public void test2() {
		sequentialSum(999991999);
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
	public static long sequentialSum(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}

	@Test public void test3() {
		iterativeSum(999991999);
	}
	public static long iterativeSum(long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		return result;
	}

	@Test public void test() throws IOException {
		String oneLine=processFile(b->b.readLine()+b.readLine());
		System.out.println(oneLine);
	}
	public static String processFile(BufferedReaderProcessor p) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(("/Users/kun/Desktop/a.txt")));
		return p.process(br);
	}


	@Test
	public void tes3t() {
	}
}
