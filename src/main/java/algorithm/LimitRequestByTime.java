package algorithm;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: algorithm.LimitRequestByTime.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 2017/7/17 18:00
 * Description:  对请求做限速控流
 * History:
 */
public class LimitRequestByTime {

	long limit=1000;

	Map<Long,AtomicLong> map= Collections.synchronizedMap(new LinkedHashMap<>(8));

	public boolean limitReq(){
		long currentTimeMillis=System.currentTimeMillis()/1000;
		map.putIfAbsent(currentTimeMillis,new AtomicLong(0));
		AtomicLong currentAtomicLong=map.get(currentTimeMillis);
		return !(currentAtomicLong.incrementAndGet()>=limit);
	}

	public static void main(String args[]){
		LimitRequestByTime limitRequestByTime=new LimitRequestByTime();

		// 统计所有的请求次数
		Map<Long, AtomicLong> totalMap = new ConcurrentHashMap<>();

		// 统计所有的请求次数
		Map<Long, AtomicLong> successTotalMap = new ConcurrentHashMap<>();

		for (int i = 0; i < 100000000; i++) {
			// 统计当前这一秒的请求数
			long currentTimeMillis = System.currentTimeMillis() / 1000;
			totalMap.putIfAbsent(currentTimeMillis, new AtomicLong(0));
			// 自增加1
			totalMap.get(currentTimeMillis).incrementAndGet();

			successTotalMap.putIfAbsent(currentTimeMillis, new AtomicLong(0));
			if (limitRequestByTime.limitReq()) {
				successTotalMap.get(currentTimeMillis).incrementAndGet();
			}
		}

		for (Map.Entry<Long, AtomicLong> total : totalMap.entrySet()) {
			Long totalKey = total.getKey();
			System.out.println(String
					.format("在%d这一秒一共发送了%d次请求，通过的请求数量为%d", totalKey, totalMap.get(totalKey).get(),
							successTotalMap.get(totalKey).get()));
		}

	}
}
