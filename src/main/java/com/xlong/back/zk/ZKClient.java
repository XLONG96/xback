package com.xlong.back.zk;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ZKClient {
    private static final Logger logger = LoggerFactory.getLogger(ZKClient.class);

    private static final String ZK_URL = "120.77.246.48:2181";

    private static final int SESSION_TIMEOUT = 10000;//ms

    private ZkClient zk;

    public ZKClient() {
        zk = new ZkClient(new ZkConnection(ZK_URL), SESSION_TIMEOUT);
    }

    public void getNode() {

    }

    public void ZKStartUP() throws Exception {
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
