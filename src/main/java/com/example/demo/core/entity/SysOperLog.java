package com.example.demo.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * token表
 *
 * @author YuXin
 */
@Data
public class SysOperLog implements Serializable {

    private Long operLogId;

    /* 请求参数 */
    private String requestParams;

    /* 操作日志 */
    private String operLog;

    /* 操作状态（0失败，1成功） */
    private Integer status;

    /* 成功结果 */
    private String jsonResult;

    /* 错误信息 */
    private String errorMsg;

    /* 操作人 */
    private Long userId;

    /* 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String loginName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBeginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createEndTime;

}
