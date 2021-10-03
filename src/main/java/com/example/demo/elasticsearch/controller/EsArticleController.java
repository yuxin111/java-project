package com.example.demo.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.annotation.AllowDevPass;
import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.page.PageDomain;
import com.example.demo.common.page.TableSupport;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.elasticsearch.dao.ArticleRepository;
import com.example.demo.elasticsearch.dto.ArticleDTO;
import com.example.demo.elasticsearch.entity.ArticleEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Qualifier("elasticsearchClient")
    @Autowired
    private RestHighLevelClient client;

    @MyLog("新增文章")
    @RequiresPermissions("article:operArticle:add")
    @PostMapping("/add")
    public ResultBody addArticle(@RequestBody ArticleEntity article) {
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateTime(LocalDateTime.now());
        articleRepository.save(article);
        return ResultBody.success("新增文章成功");
    }

    @MyLog("更新文章")
    @RequiresPermissions("article:operArticle:edit")
    @PostMapping("/update")
    public ResultBody updateArticle(@RequestBody ArticleEntity article) {
        article.setUpdateTime(LocalDateTime.now());
        articleRepository.save(article);
        return ResultBody.success("更新文章成功");
    }

    @AllowDevPass
    @GetMapping("/get/{articleId}")
    public ResultBody getUserList(@PathVariable("articleId") String articleId) {
        Optional<ArticleEntity> articleEntity = articleRepository.findById(articleId);
        return ResultBody.success(articleEntity.get());
    }

//    @AllowDevPass
//    @PostMapping("/findAll")
//    public ResultBody test() throws IOException {
//        SearchRequest searchRequest = new SearchRequest();
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        QueryBuilder queryBuilder = QueryBuilders.matchQuery("content", "2");
//
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("content")
//                // 设置前缀、后缀
//                .preTags("<i>")
//                .postTags("</i>")
//                .fragmentSize(800000)
//                .numOfFragments(0);
//
//        searchSourceBuilder.query(queryBuilder);
//        searchSourceBuilder.highlighter(highlightBuilder);
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        SearchHit[] hits = searchResponse.getHits().getHits();
//        List<ArticleEntity> articleEntities = new ArrayList<>();
//        for (SearchHit hit : hits) {
//            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//            HighlightField productName = highlightFields.get("content");
//            String newName = "";
//            if (productName != null) {
//                //获取该高亮字段的高亮信息
//                Text[] fragments = productName.getFragments();
//                //将前缀、关键词、后缀进行拼接
//                for (Text fragment : fragments) {
//                    newName += fragment;
//                }
//            }
//            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
//            //将高亮后的值替换掉旧值
//            sourceAsMap.put("content", newName);
//            System.out.println("content: " + newName);
//            String json = JSON.toJSONString(sourceAsMap);
//            ArticleEntity articleEntity = JSON.parseObject(json, ArticleEntity.class);
//            articleEntities.add(articleEntity);
//        }
//        return null;
//    }

    @AllowDevPass
    @PostMapping("/findAll")
    public ResultBody findAll(@RequestBody ArticleDTO articleDTO) {
        String text = articleDTO.getText();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        HighlightBuilder.Field highlightBuilder = new HighlightBuilder
                .Field("*")
                .preTags("<highlight class='word-highlight'>")
                .postTags("</highlight>")
                .fragmentSize(800000);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.hasText(text)) {
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("title", text));
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("content", text));
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("author", text));
        }

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(highlightBuilder)
                .withPageable(pageable)
                .build();
        SearchHits<ArticleEntity> hits = restTemplate.search(nativeSearchQuery, ArticleEntity.class);

        List<ArticleEntity> articleEntities = new ArrayList<>();
        for (SearchHit<ArticleEntity> hit : hits) {
            Map<String, List<String>> highlightFields = hit.getHighlightFields();
            List<String> titleHighlight = hit.getHighlightField("title");
            List<String> contentHighlight = hit.getHighlightField("content");
            List<String> authorHighlight = hit.getHighlightField("author");
            ArticleEntity articleEntity = hit.getContent();
            if (titleHighlight.size() != 0) {
                articleEntity.setTitle(titleHighlight.get(0));
            }
            if (contentHighlight.size() != 0) {
                articleEntity.setContent(contentHighlight.get(0));
            }
            if (authorHighlight.size() != 0) {
                articleEntity.setAuthor(authorHighlight.get(0));
            }
            articleEntities.add(articleEntity);
        }

        return getDataTable(articleEntities, (int) hits.getTotalHits());
    }

    @MyLog("删除文章")
    @RequiresPermissions("article:operArticle:delete")
    @GetMapping("/delete/{id}")
    public ResultBody deleteUser(@PathVariable String id) {
        articleRepository.deleteById(id);
        return ResultBody.success("删除文章成功");
    }

}
