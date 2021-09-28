package com.example.demo.elasticsearch.dao;

import com.example.demo.elasticsearch.entity.ArticleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/9/14
 **/
@Repository
public interface ArticleRepository extends ElasticsearchRepository<ArticleEntity,String> {
//    Page<ArticleEntity> findByTitleAndContent(Pageable)
}
