package com.jeremy.sample.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class LoggingErrorHandler implements ErrorHandler
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingErrorHandler.class);
	
	public void handleError(Throwable t)
	{
		LOGGER.error("Error processing message.", t);
		
	}
	
}
