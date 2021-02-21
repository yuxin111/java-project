package com.example.demo.config.exception;

public interface IBaseErrorInfo {
    /** 错误码*/
	 int getResultCode();
	
	/** 错误描述*/
	 String getResultMsg();
}