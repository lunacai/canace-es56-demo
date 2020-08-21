package com.example.canaceesdemo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
@ConfigurationProperties(prefix = "es")
public class ElasticConfig {
    private Logger LOGGER = Logger.getLogger(String.valueOf(httpController.class));

    @Value("${es.host}")
    public String host;
    @Value("${es.port}")
    public String port;
    @Value("${es.scheme}")
    public String scheme;
    @Value("${es.pool}")
    public String poolSize;
    @Value("${es.cluster}")
    private String clusterName;

    @Bean
    public TransportClient transportClient() {
        // 9300是es的tcp服务端口
        TransportAddress node = null;
        try{
            node = new InetSocketTransportAddress(InetAddresses.forString(host),9300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置es节点的配置信息
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .build();
        // 实例化es的客户端对象
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }

}
