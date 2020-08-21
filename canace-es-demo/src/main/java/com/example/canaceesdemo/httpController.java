package com.example.canaceesdemo;

import com.pamirs.pradar.Pradar;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/es56")
public class httpController {

    @Autowired
    BaseElasticService baseElasticService;
    private Logger log = Logger.getLogger(String.valueOf(httpController.class));

    @PostMapping(value = "/get")
    public String get(@RequestBody Usermodel usermodel){
        return baseElasticService.get(usermodel).toString();
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody Usermodel usermodel){
        String response = "2";
        try {
            if(usermodel.getIndex()==""){
                return "索引为空，不允许提交";
            }
            log.info("pradar>>>>>>>>>>>>>>>>>>>>"+ Pradar.isClusterTest());
            IndexResponse result = baseElasticService.add(usermodel);
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }


}
