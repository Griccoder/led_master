package com.car.led.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.car.led.util.E3Result;

@ControllerAdvice
public class ApplicationExceptionHandler {
	public static final String ERROR_VIEW = "error";

	private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
		logger.error(e.getMessage(),e);
		if (isAjax(reqest)) {
			return E3Result.build(500, e.getMessage());
		} else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", e);
			mav.addObject("url", reqest.getRequestURL());
			mav.setViewName(ERROR_VIEW);
			return mav;
		}
	}

	/**
	 *
	 * @Description: 判断是否是ajax请求
	 */
	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
	}
}
