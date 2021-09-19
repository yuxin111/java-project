package com.example.demo.elasticsearch.controller;

import com.example.demo.core.entity.ResultBody;
import com.example.demo.elasticsearch.dao.ArticleRepository;
import com.example.demo.elasticsearch.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/9/14
 **/
@RestController
@RequestMapping("/article")
public class EsArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/addArticle")
    public ArticleEntity addArticle(@RequestBody ArticleEntity article){
        return articleRepository.save(article);
    }

    @GetMapping("/findArticle/{id}")
    public Optional<ArticleEntity> findArticle(@PathVariable Long id) {
        return articleRepository.findById(String.valueOf(id));
    }

    @PostMapping("/findAll")
    public ResultBody findAll() {
        Iterable<ArticleEntity> articleEntityIterable = articleRepository.findAll();
        List<ArticleEntity> articleEntities = new ArrayList<>();
        articleEntityIterable.forEach(e -> articleEntities.add(e));
        return ResultBody.success(articleEntities);
    }

    @GetMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(String.valueOf(id));
    }
}
