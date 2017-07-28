package com.tavant.mockdrill.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BaseException extends Exception implements MockBaseException{

	 private static final long serialVersionUID = 1L;
	  private ErrorDetails errorDetails;
	  
	  public BaseException(Throwable e)
	  {
	    super(e);
	  }
	  
	  public void setErrorDetails(ErrorDetails errorDetails) {
	    this.errorDetails = errorDetails;
	  }
	  
	  public ErrorDetails getErrorDetails() {
	    return errorDetails;
	  }
	  
	  public String printStackTrace(Throwable throwable) {
	    StringWriter stackTrace = new StringWriter();
	    if (throwable != null) {
	      throwable.printStackTrace(new PrintWriter(stackTrace));
	    }
	    return stackTrace.toString();
	  }
	  
	  public void handleException(BaseException baseException, String errorMessage) {
	    DefaultExceptionHandler mapDefaultExceptionHandler = new DefaultExceptionHandler();
	    if (baseException.getErrorDetails() == null) {
	      errorDetails = new ErrorDetails();
	    } else {
	      errorDetails = baseException.getErrorDetails();
	    }
	    
	 
	    if ((baseException instanceof MockResourceException)) {
	    	MockResourceException mapResourceException = new MockResourceException(baseException);
	      errorDetails.setErrorCode("MOCK200");
	      errorDetails.setBaseException(baseException);
	      errorDetails.setErrorMessage(errorMessage);
	      mapResourceException.setErrorDetails(errorDetails);
	      mapDefaultExceptionHandler.handleException(mapResourceException);

	    }
	    else if ((baseException instanceof MockInternalException)) {
	    	MockInternalException mapInternalException = new MockInternalException(baseException);
	      errorDetails.setErrorCode("MOCK300");
	      errorDetails.setBaseException(baseException);
	      errorDetails.setErrorMessage(errorMessage);
	      mapInternalException.setErrorDetails(errorDetails);
	      mapDefaultExceptionHandler.handleException(mapInternalException);
	    }
	    else if ((baseException instanceof MockIOException)) {
	    	MockIOException mapIOException = new MockIOException(baseException);
	      errorDetails.setErrorCode("MOCK400");
	      errorDetails.setBaseException(baseException);
	      errorDetails.setErrorMessage(errorMessage);
	      mapIOException.setErrorDetails(errorDetails);
	      mapDefaultExceptionHandler.handleException(mapIOException);
	    }
	  }
	}
