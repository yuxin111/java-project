package com.example.demo.core.entity;

import lombok.Data;

import java.util.Map;

@Data
public class SingleInputTable {
    private String name;
    private String mobile;
    private String orgName;
    private String taskName;
    private String sceneName;
    private String callTimes;
    private String reachedEndOfFlow;
    private String hangUpByRejection;
    private String rounds;

    private Map<String,Object> params;
}
