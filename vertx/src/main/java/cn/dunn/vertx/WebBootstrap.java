package cn.dunn.vertx;

import cn.dunn.front.impl.ScriptImpl;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface WebBootstrap {
    String URL = "url";
    String NAMESPACE = "namespace";
    String EVENT_BUS_PATH = "eventBus";

    static void start() {
        Config config = ConfigFactory.load();
        String url = config.getString(URL);
        String namespace = config.getString(NAMESPACE);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 1);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(url).namespace(namespace).sessionTimeoutMs(100).connectionTimeoutMs(100)
                .retryPolicy(retryPolicy).build();
        curatorFramework.start();
        ClusterManager mgr = new ZookeeperClusterManager(curatorFramework);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                String eventBusPath = config.getString(EVENT_BUS_PATH);
                Vertx vertx = res.result();
            } else {

            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
