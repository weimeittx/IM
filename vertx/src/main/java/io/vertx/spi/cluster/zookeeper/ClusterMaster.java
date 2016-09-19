package io.vertx.spi.cluster.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

public class ClusterMaster {
	{

		new LeaderSelector(null, "/sd/master", new LeaderSelectorListener() {

			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {
				
			}

			@Override
			public void takeLeadership(CuratorFramework client) throws Exception {
				System.out.println("获取到leader");
			}
		});
	}
}
