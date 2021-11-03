package com.example.demo.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.annotation.AllowDevPass;
import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.page.PageDomain;
import com.example.demo.common.page.TableSupport;
import com.example.demo.common.utils.ImageUtils;
import com.example.demo.common.utils.ServletUtils;
import com.example.demo.config.FileConfig;
import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.elasticsearch.dao.ArticleRepository;
import com.example.demo.elasticsearch.dto.ArticleDTO;
import com.example.demo.elasticsearch.entity.ArticleEntity;
import com.sun.imageio.plugins.common.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/9/14
 **/
@Slf4j
@RestController
@RequestMapping("/article")
public class EsArticleController extends BaseController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private FileConfig fileConfig;

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
                .preTags("<hl>")
                .postTags("</hl>")
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

    @MyLog("上传图片")
    @PostMapping("/upload/img")
    public ResultBody imgUpload(@RequestParam("files") MultipartFile files) {

        String originalFilename = files.getOriginalFilename();
        if (!StringUtils.hasText(originalFilename)) {
            throw new MyException("图片不能为空");
        }

        String extensionName = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (!fileConfig.getExtensions().contains(extensionName)) {
            throw new MyException("后缀名不符合规范，请检查后重试");
        }

        File pathFile = new File(fileConfig.getLocation());
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        File serverFile = new File(pathFile, uuid + "_" + files.getOriginalFilename());
        try {
            files.transferTo(serverFile);
            ImageUtils.compressImg(serverFile,0.2);
        } catch (IOException e) {
            log.error("==上传图片异常==");
            e.printStackTrace();
        }
        String imgPath = "/article/img/get/" + serverFile.getName();
        return ResultBody.success(imgPath);
    }

    @AllowDevPass
    @GetMapping("/img/get/{imgName}")
    public void imgGet(@PathVariable String imgName) {
        File imgFile = new File(fileConfig.getLocation() + File.separator + imgName);
        if (!imgFile.exists()) {
            log.error("图片不存在");
        }

        String extensionName = imgName.substring(imgName.lastIndexOf("."));
        if (!fileConfig.getExtensions().contains(extensionName)) {
            log.error("后缀名不符合规范，请检查后重试");
        }

        FileInputStream inputStream = null;
        OutputStream outputStream = null;
        HttpServletResponse response = ServletUtils.getResponse();
        try {
            response.setContentType("image/" + extensionName.substring(1));
            inputStream = new FileInputStream(imgFile);
            outputStream = ServletUtils.getResponse().getOutputStream();
            int len = 0;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            log.error("图片不存在");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("图片获取io异常");
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                log.error("图片获取流关闭异常");
                e.printStackTrace();
            }
        }
    }


    @MyLog("删除文章")
    @RequiresPermissions("article:operArticle:delete")
    @GetMapping("/delete/{id}")
    public ResultBody deleteUser(@PathVariable String id) {
        articleRepository.deleteById(id);
        return ResultBody.success("删除文章成功");
    }

}
