package com.tavant.mockdrill.exception;

public class ErrorDetails {

	private String errorCode;

	private String errorMessage;

	private Throwable baseException;

	private Object otherExceptionDetails;

	private String emailNotifyFlag;

	public ErrorDetails() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Throwable getBaseException() {
		return baseException;
	}

	public void setBaseException(Throwable baseException) {
		this.baseException = baseException;
	}

	public Object getOtherExceptionDetails() {
		return otherExceptionDetails;
	}

	public void setOtherExceptionDetails(Object otherExceptionDetails) {
		this.otherExceptionDetails = otherExceptionDetails;
	}

	public String getEmailNotifyFlag() {
		return emailNotifyFlag;
	}

	public void setEmailNotifyFlag(String emailNotifyFlag) {
		this.emailNotifyFlag = emailNotifyFlag;
	}
}
