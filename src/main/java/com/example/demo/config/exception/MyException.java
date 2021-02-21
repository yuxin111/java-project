package com.example.demo.config.exception;

public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	protected int errorCode;
	/**
	 * 错误信息
	 */
	protected String errorMsg;

	public MyException() {
		super();
	}

	public MyException(IBaseErrorInfo errorInfoInterface) {
		super(errorInfoInterface.getResultMsg());
		this.errorCode = errorInfoInterface.getResultCode();
		this.errorMsg = errorInfoInterface.getResultMsg();
	}
	
	public MyException(IBaseErrorInfo errorInfoInterface, Throwable cause) {
		super(errorInfoInterface.getResultMsg(), cause);
		this.errorCode = errorInfoInterface.getResultCode();
		this.errorMsg = errorInfoInterface.getResultMsg();
	}
	
	public MyException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
	public MyException(int errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public MyException(int errorCode, String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getMessage() {
		return errorMsg;
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}