package com.xlong.back.zk;

import org.junit.Test;

import java.util.List;

public class TestProZK {

    private static final String ZK_PROVIDER_ROOT = "/provider";

    @Test
    public void ZKStartup() {
        ProZKClient client = new ProZKClient();
        List<String> nodes = client.getChildrenNode(ZK_PROVIDER_ROOT);
        for (String n : nodes) {
            System.out.println(n);
            String data = client.getData(ZK_PROVIDER_ROOT + "/" + n);
            System.out.println(data);
            System.out.println(client.getCreationTime(ZK_PROVIDER_ROOT + "/" + n));
        }
    }
}
