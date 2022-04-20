package com.example.demo.core.service.impl;

import com.example.demo.core.entity.SingleInputTable;
import com.example.demo.core.entity.request.SingleInput;
import com.example.demo.core.entity.request.SingleInputOutput;
import com.example.demo.core.mapper.SingleInputTableMapper;
import com.example.demo.core.service.ISingleInputTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SingleInputTableServiceImpl implements ISingleInputTableService {

    @Autowired
    SingleInputTableMapper singleInputTableMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<SingleInputTable> selectSingleInutTableList(SingleInput singleInput) {
        SingleInputOutput singleInputOutput = new SingleInputOutput();
        singleInputOutput.setCalcExpress("rounds / call_times");
        singleInputOutput.setName("外呼率");

        SingleInputOutput singleInputOutput1 = new SingleInputOutput();
        singleInputOutput1.setCalcExpress("rounds / call_times / 2");
        singleInputOutput1.setName("外呼率1");

        SingleInputOutput singleInputOutput2 = new SingleInputOutput();
        singleInputOutput2.setCalcExpress("rounds / call_times / 3");
        singleInputOutput2.setName("外呼率2");

        List<SingleInputOutput> singleInputOutputList = new ArrayList<>();
        singleInputOutputList.add(singleInputOutput);
        singleInputOutputList.add(singleInputOutput1);
        singleInputOutputList.add(singleInputOutput2);

        singleInput.setSingleInputOutputList(singleInputOutputList);
        List<Map<String, Object>> mapList = singleInputTableMapper.selectSingleInutTableList(singleInput);

        List<SingleInputTable> singleInputTableList = new ArrayList<>();
        SingleInputTable singleInputTable;
        Map<String, Object> params;
        for (Map<String, Object> map : mapList) {
            singleInputTable = new SingleInputTable();
            params = new HashMap<>();

            singleInputTable = mapToBean(map, singleInputTable);
            singleInputTable.setParams(mapToMap(map, params));

            singleInputTableList.add(singleInputTable);
        }
        return singleInputTableList;
    }

    private <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    private Map<String, Object> mapToMap(Map<String, Object> map, Map<String, Object> params) {
        Set<String> mapKeySet = map.keySet();
        for (String key : mapKeySet) {
            if(key.startsWith("params")){
                params.put(key,map.get(key));
            }
        }
        return params;
    }
}
