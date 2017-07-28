package com.tavant.mockdrill.exception;

public interface MockBaseException {
	
	public abstract void setErrorDetails(ErrorDetails paramErrorDetails);
	  
	  public abstract ErrorDetails getErrorDetails();
	  
	  public abstract String toString();
	  
	  public abstract String printStackTrace(Throwable paramThrowable);

}
