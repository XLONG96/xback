package com.xlong.back.zk;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ZKClient {
    private static final Logger logger = LoggerFactory.getLogger(ZKClient.class);
/*
    @Value("${zookeeper.url}")
    private String url;

    @Value("${zookeeper.timeout}")
    private int timeout; //ms
*/
    private String url = "10.202.38.54:2181";

    private int timeout =10000;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ZkClient zk;

    public ZKClient() {
        logger.info("url: {}, timeout: {}", url, timeout);
        zk = new ZkClient(new ZkConnection(url), timeout);
        zk.setZkSerializer(new SerializableSerializer());

        logger.info("Zookeeper is start up! Remote server is {}", url);
    }

    public List<String> getChildrenNode(String path) {
        if (zk.exists(path)) {
            return zk.getChildren(path);
        }
        return null;
    }

    public String getData(String path) {
        if (zk.exists(path)) {
            return zk.readData(path);
        }
        return null;
    }

    public Date getCreationTime(String path) {
        try {
            if (zk.exists(path)) {
                long timestap = zk.getCreationTime(path);
                return sdf.parse(sdf.format(timestap));
            }
        } catch (Exception ex) {
            logger.error("{}", ex);
        }
        return null;
    }

    public void ZKDemo() throws Exception {
        /*
        zkc.createEphemeral("/temp");
        zkc.createPersistent("/super/c1", true);
        Thread.sleep(10000);
        zkc.delete("/temp");
        zkc.deleteRecursive("/super");

        //2. 设置path和data 并且读取子节点和每个节点的内容
        zkc.createPersistent("/super", "1234");
        zkc.createPersistent("/super/c1", "c1内容");
        zkc.createPersistent("/super/c2", "c2内容");
        List<String> list = zkc.getChildren("/super");
        for(String p : list){
            System.out.println(p);
            String rp = "/super/" + p;
            String data = zkc.readData(rp);
            System.out.println("节点为：" + rp + "，内容为: " + data);
        }

        //3. 更新和判断节点是否存在
        zkc.writeData("/super/c1", "新内容");
        System.out.println(zkc.readData("/super/c1").toString());
        System.out.println(zkc.exists("/super/c1"));

        //4.递归删除/super内容
        zkc.deleteRecursive("/super");*/
    }
}
