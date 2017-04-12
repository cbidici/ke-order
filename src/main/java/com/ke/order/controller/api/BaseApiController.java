package com.ke.order.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * BaseController class should be extended by all api controllers.
 * This class takes care of exception handling for api
 * @author cbidici
 * @since 0.0.1 (20141103)
 */
public class BaseApiController {

	private static final Logger logger = Logger.getLogger(BaseApiController.class);

	// TODO : Take care of internal exceptions
	// TODO : Take care of validation exceptions
	// TODO : Take care of security exceptions
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String exHandler(HttpServletRequest req, Exception e) throws Exception
	{
		logger.error("Exception Occurred :" + e);
		
		// TODO : Implement a message entity which is configurable from database and return as meaningful error messages.
		return e.getMessage();
	}
}
