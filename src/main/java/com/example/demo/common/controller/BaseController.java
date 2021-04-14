package com.example.demo.common.controller;

import com.example.demo.common.page.PageDomain;
import com.example.demo.common.page.TableDataInfo;
import com.example.demo.common.page.TableSupport;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.common.enums.CommonEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * web层通用数据处理
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(com.example.demo.common.controller.BaseController.class);

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected ResultBody getDataTable(List<?> list)
    {
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setData(list);
        tableDataInfo.setTotal(new PageInfo(list).getTotal());

        ResultBody resultBody = new ResultBody();
        resultBody.setCode(CommonEnum.SUCCESS.getResultCode());
        resultBody.setMessage("查询成功");
        resultBody.setResult(tableDataInfo);
        return resultBody;
    }

}
