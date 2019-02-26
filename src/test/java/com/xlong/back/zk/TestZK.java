package com.xlong.back.zk;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.junit.Test;

import java.util.List;

public class TestZK {

    static final String CONNECT_ADDR = "120.77.246.48:2181";

    static final int SESSION_OUTTIME = 10000;//ms

    @Test
    public void ZKStartUP() throws Exception {
        ZkClient zkc = new ZkClient(new ZkConnection(CONNECT_ADDR), SESSION_OUTTIME);
        //1. create and delete方法

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
        zkc.deleteRecursive("/super");
    }
}
