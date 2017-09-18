package zookeeper;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: zookeeper.ZookeeperTest.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 16/09/2017 15:28
 * Description:
 * History:
 */
public class ZookeeperTest {

	@Test
	public void test() throws Exception {
		ZooKeeper zooKeeper=new ZooKeeper("localhost", 2181, new Watcher() {
			@Override public void process(WatchedEvent watchedEvent) {

			}
		});
		zooKeeper.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		System.out.println(new String(zooKeeper.getData("/testRootPath", false, null)));
	}
	static String path="/zookeeper";
	@Test
	public void test2() throws Exception {
		ZooKeeper zooKeeper=new ZooKeeper("localhost", 2181, new Watcher() {
			@Override public void process(WatchedEvent watchedEvent) {
					System.out.println("节点已创建");
			}
		});
		zooKeeper.create(path+"/test","abcTest".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

	}

	@Test
	public void testCurator() throws Exception {
		RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000,3);
		CuratorFramework client= CuratorFrameworkFactory.builder().connectString("localhost:2181").sessionTimeoutMs(50000).
				retryPolicy(retryPolicy).build();
		client.start();
		client.delete()
				.forPath("/test");

		byte[] bytes = client.getData().forPath("/test");
		System.out.println(new String(bytes));
	}

}
