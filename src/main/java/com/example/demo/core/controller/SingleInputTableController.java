package com.example.demo.core.controller;

import com.example.demo.common.annotation.AllowDevPass;
import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SingleInputTable;
import com.example.demo.core.entity.request.SingleInput;
import com.example.demo.core.service.ISingleInputTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/singleInputTable")
public class SingleInputTableController extends BaseController {

    @Autowired
    ISingleInputTableService singleInputTableService;

    @MyLog("单一输入框表格查询")
    @AllowDevPass
    @PostMapping("/list")
    public ResultBody getSingleInputTableList(@RequestBody SingleInput singleInput){
        startPage();
        List<SingleInputTable> singleInputTableList = singleInputTableService.selectSingleInutTableList(singleInput);
        return getDataTable(singleInputTableList);
    }

}
