package com.example.demo.elasticsearch.controller;

import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.page.PageDomain;
import com.example.demo.common.page.TableSupport;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysUser;
import com.example.demo.elasticsearch.dao.ArticleRepository;
import com.example.demo.elasticsearch.entity.ArticleEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/9/14
 **/
@RestController
@RequestMapping("/article")
public class EsArticleController extends BaseController {

    @Autowired
    private ArticleRepository articleRepository;

    @MyLog("新增文章")
    @PostMapping("/add")
    public ResultBody addArticle(@RequestBody ArticleEntity article){
        article.setUpdateTime(LocalDateTime.now());
        articleRepository.save(article);
        return ResultBody.success("新增文章成功");
    }

    @MyLog("更新文章")
    @PostMapping("/update")
    public ResultBody updateArticle(@RequestBody ArticleEntity article){
        articleRepository.save(article);
        return ResultBody.success("更新文章成功");
    }

    @GetMapping("/get/{articleId}")
    public ResultBody getUserList(@PathVariable("articleId") String articleId){
        Optional<ArticleEntity> articleEntity = articleRepository.findById(articleId);
        return ResultBody.success(articleEntity.get());
    }

    @PostMapping("/findAll")
    public ResultBody findAll() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Pageable pageable = PageRequest.of(pageNum - 1,pageSize);
        Page<ArticleEntity> articleEntities = articleRepository.findAll(pageable);
        return getDataTable(articleEntities);
    }

    @GetMapping("/delete/{id}")
    public void deleteArticle(@PathVariable String id) {
        articleRepository.deleteById(id);
    }
}
