package com.example.demo.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc elasticsearch配置
 * @Return
 * @Author YuXin
 * @Date 2021/9/13
 **/
@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {
    @Value("${es.address}")
    private String esAddress;
    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient(){
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(esAddress)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
