package com.example.canaceesdemo;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

import com.pamirs.pradar.Pradar;

@Slf4j
@Component
public class BaseElasticService {
    @Autowired
    private TransportClient client;

    public GetResponse get(Usermodel usermodel) {
        GetResponse result = client.prepareGet(usermodel.getIndex(), usermodel.getIndexname(), usermodel.getId()).execute().actionGet();
        if (!result.isExists()) {
            return null;
        }
        return result;
    }


    public IndexResponse add(Usermodel usermodel){
        try {
            XContentBuilder contentBuilder =
                    XContentFactory.jsonBuilder()
                            .startObject()
                            .field("id", usermodel.getId())
                            .field("content", usermodel.getContent())
                            .field("usename", usermodel.getIndexname())
                            .endObject();
            System.out.println("pradar>>>>>>>>>>"+Pradar.isClusterTest());
            IndexResponse result = client.prepareIndex("canace", "String").setSource(contentBuilder).execute().actionGet();
            return result;
        } catch (IOException e) {
            System.out.println("error>>>>>>>>>>"+e);
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     * @param idxName   索引名称
//     * @param idxSQL    索引描述
//     */
//    public void createIndex(String idxName,String idxSQL){
//        try {
//            if (!this.indexExist(idxName)) {
//                log.error(" idxName={} 已经存在,idxSql={}",idxName,idxSQL);
//                return;
//            }
//            CreateIndexRequest request = new CreateIndexRequest(idxName);
//            buildSetting(request);
//            request.mapping(idxSQL, XContentType.JSON);
//            CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
//            if (!res.isAcknowledged()) {
//                throw new RuntimeException("初始化失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//    }
//
//    /** 断某个index是否存在
//     * @param idxName index名
//     */
//    public boolean indexExist(String idxName) throws Exception {
//        GetIndexRequest request = new GetIndexRequest(idxName);
//        request.local(false);
//        request.humanReadable(true);
//        request.includeDefaults(false);
//        request.indicesOptions(IndicesOptions.lenientExpandOpen());
//        System.out.println(restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT));
//        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
//    }
//    /** 设置分片
//     * @param request
//     */
//    public void buildSetting(CreateIndexRequest request){
//        request.settings(Settings.builder().put("index.number_of_shards",3)
//                .put("index.number_of_replicas",2));
//    }
//
//    /**
//     * @param idxName index
//     * @param builder   查询参数
//     * @param c 结果类对象
//     */
//    public <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> c) {
//        SearchRequest request = new SearchRequest(idxName);
//        request.source(builder);
//        try {
//            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
//            SearchHit[] hits = response.getHits().getHits();
//            List<T> res = new ArrayList<>(hits.length);
//            for (SearchHit hit : hits) {
//                res.add(JSON.parseObject(hit.getSourceAsString(), c));
//            }
//            return res;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    /**
//     * @param idxName index
//     * @param entity    对象
//     */
//    public void insertOrUpdateOne(String idxName, ElasticEntity entity) {
//        IndexRequest request = new IndexRequest(idxName);
//        log.info("Data : id={},entity={}", entity.getId(), JSON.toJSONString(entity.getData()));
//        request.id(entity.getId());
//        request.source(entity.getData(), XContentType.JSON);
//        try {
////            log.info("pradar>>>>>>>>>>>>>>>>>>>>"+ Pradar.isClusterTest());
//            restHighLevelClient.index(request, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

}
