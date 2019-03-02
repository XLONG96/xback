package com.xlong.back.zk;


import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class ProZKClient {
    private static final Logger logger = LoggerFactory.getLogger(ProZKClient.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private String url = "10.202.38.54:2181";

    private int timeout =10000;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ZooKeeper zk;

    public ProZKClient() {
        zk = connectServer();
        logger.info("Zookeeper is start up! Remote server is {}", url);
    }

    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            // ZooKeeper客户端与服务端的连接是异步的
            zk = new ZooKeeper(url, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            logger.error("{}", e);
        }
        return zk;
    }

    public List<String> getChildrenNode(String path) {
        try {
            return zk.getChildren(path, null);
        } catch (KeeperException | InterruptedException e) {
            logger.error("{}", e);
        }
        return null;
    }

    public String getData(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = zk.getData(path, false, null);
        } catch (KeeperException | InterruptedException e) {
            logger.error("{}", e);
        }
        return new String(bytes);
    }

    public Date getCreationTime(String path) {
        try {
            Stat stat = zk.exists(path, false);
            long timestap = stat.getCtime();
            return sdf.parse(sdf.format(timestap));
        } catch (Exception ex) {
            logger.error("{}", ex);
        }
        return null;
    }
}
