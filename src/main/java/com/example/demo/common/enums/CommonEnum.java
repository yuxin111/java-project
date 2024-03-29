package com.example.demo.common.enums;

import com.example.demo.config.exception.IBaseErrorInfo;

public enum CommonEnum implements IBaseErrorInfo {
	// 数据操作错误定义
	SUCCESS(200, "成功!"),
	BODY_NOT_MATCH(400,"请求的数据格式不符!"),
	SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配!"),
	NOT_FOUND(404, "未找到该资源!"),
	INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
	SERVER_BUSY(503,"服务器正忙，请稍后再试!"),
	DEV_NOT_ALLOW_PASS(46001,"当前为演示环境，无法操作！")
	;

	/** 错误码 */
	private final int resultCode;

	/** 错误描述 */
	private final String resultMsg;

	CommonEnum(int resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	@Override
	public int getResultCode() {
		return resultCode;
	}

	@Override
	public String getResultMsg() {
		return resultMsg;
	}

}