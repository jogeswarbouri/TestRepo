package com.tavant.mockdrill.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MockLogger {
	 private static final Logger LOGGER_LOG = Logger.getLogger(MockLogger.class.getName());
	  private static boolean logStatus = false;
	  
	  static {
	    try { PropertyConfigurator.configure("resources\\marriotLogger.properties");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    if (LOGGER_LOG.isDebugEnabled())
	      logStatus = true;
	  }
	  
	  public MockLogger() {}
	  
	  public static boolean isDebugEnabled() {
	    return logStatus;
	  }
	  
	  public static void entry(String methodName, String className) {
	    if (LOGGER_LOG.isDebugEnabled()) {
	      LOGGER_LOG.info("Entering " + methodName + " Method" + " of " + className);
	    }
	  }
	  
	  public static void exit(String methodName, String className) {
	    if (LOGGER_LOG.isDebugEnabled()) {
	      LOGGER_LOG.info("Exiting " + methodName + " Method" + " of " + className);
	    }
	  }
	  
	  public static void log(String message) {
	    if (LOGGER_LOG.isInfoEnabled()) {
	      LOGGER_LOG.info(message);
	    }
	  }
	  
	  public static void log(String message, String message1, String message2) {
	    if ((LOGGER_LOG.isInfoEnabled()) && (LOGGER_LOG.isEnabledFor(Level.INFO))) {
	      LOGGER_LOG.info(message + " " + message1 + " " + message2);
	    }
	  }
	  
	  public static void debug(String message) {
	    if (LOGGER_LOG.isDebugEnabled()) {
	      LOGGER_LOG.debug(message);
	    }
	  }
	  
	  public static void error(String message) {
	    LOGGER_LOG.error(message);
	  }
	  
	  public static void error(String message, Throwable t) {
	    LOGGER_LOG.error(message, t);
	  }
	  
	  public static String getMethodName() {
	    return Thread.currentThread().getStackTrace()[2].getMethodName();
	  }
	  
	  public static String getClassName()
	  {
	    return Thread.currentThread().getStackTrace()[2].getClassName().substring(Thread.currentThread().getStackTrace()[2].getClassName().lastIndexOf('.') + 1);
	  }
	  
	  public static void setMessage(String str, String str2) {
	    try {
	      File file = new File("resources\\mockLogger.properties");
	      FileOutputStream fos = new FileOutputStream(file, false);
	      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	      bw.write(str);
	      bw.newLine();
	      bw.write(str2);
	      bw.newLine();
	      bw.flush();
	      bw.close();
	    }
	    catch (Exception localException) {}
	  }
	}
