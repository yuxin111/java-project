package com.example.demo.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/9/14
 **/
@Document(indexName = "index_article")
@Data
public class ArticleEntity {

    @Id
    @Field(type = FieldType.Long, store = true)
    private Long id;

    @Field(type = FieldType.Text, fielddata = true, store = true)
    private String title;

    @Field(type = FieldType.Text, fielddata = true, store = true)
    private String content;

    @Field(type = FieldType.Text, fielddata = true, store = true)
    private String author;
}
