package com.example.demo.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/9/14
 **/
@Document(indexName = "articles")
@Data
public class ArticleEntity {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ngram")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ngram")
    private String content;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
