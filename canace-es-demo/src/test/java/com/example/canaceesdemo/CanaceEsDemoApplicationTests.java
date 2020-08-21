//package com.example.canaceesdemo;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.network.InetAddresses;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class CanaceEsDemoApplicationTests {
//
//    @Test
//    public void createIndex() throws Exception {
//        //1) 创建一个Settings对象，相当于配置信息，主要配置集群名称。
//        Settings settings = Settings.builder()
//                .put("cluster.name", "my-application")
//                .build();
//        //2) 创建一个客户端client对象
//        TransportClient client = new PreBuiltTransportClient(settings);
//        TransportAddress transportAddress =  new InetSocketTransportAddress(InetAddresses.forString("192.168.1.241"),Integer.valueOf("9300"));
//        client.addTransportAddresses(transportAddress);
//        //3) 使用client对象创建一个索引库
//        client.admin().indices().prepareCreate("index_hello")
//                //执行操作
//                .get();
//        //4) 关闭client对象
//        client.close();
//    }
//}
