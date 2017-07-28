package com.tavant.mockdrill.exception;

import org.apache.log4j.Logger;

import com.tavant.mockdrill.logger.MockLogger;

public class DefaultExceptionHandler {

		  private static final Logger LOGGER_LOG = Logger.getLogger(DefaultExceptionHandler.class.getName());
		  
		  public DefaultExceptionHandler() {}
		  
		  public void handleException(MockBaseException mapBaseException) { if (LOGGER_LOG.isDebugEnabled()) {
		      ErrorDetails errorDetails = mapBaseException.getErrorDetails();
		      MockLogger.log(" *********  Entering : handleException() :  Logging Error Details ******** ");
		      MockLogger.error(" Error Code :" + errorDetails.getErrorCode());
		      MockLogger.error(" Error Message :" + errorDetails.getErrorMessage());
		      MockLogger.error(" Printing Exception StackTrace : " + mapBaseException.printStackTrace(errorDetails.getBaseException()));
		      MockLogger.log(" *********  Exiting : handleException() :   Logging Error Details ******** ");
		    }
		  }
		}