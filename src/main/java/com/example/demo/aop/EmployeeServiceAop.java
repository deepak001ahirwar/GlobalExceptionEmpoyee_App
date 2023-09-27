package com.example.demo.aop;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class EmployeeServiceAop {

//	Logger logger =  LoggerFactory.getLogger(EmployeeControllerAop.class);
	Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceAop.class);

	// Advice

	@Before(value = "execution(public * com.example.demo.service.EmpoyeeServiceImp.*(..))")
	public void beforeAdviceforEmpServic(JoinPoint jpoint) {
		LOGGER.info(" Before Request goes to " + jpoint.getSignature() + " started at : " + new Date());
		LOGGER.debug(" request : " + jpoint.getArgs());
	}

	@After(value = "execution(public * com.example.demo.service.EmpoyeeServiceImp.*(..))")
	public void afterAdviceforEmpServic(JoinPoint jpoint) {
		LOGGER.info("After Responce goes to " + jpoint.getSignature() + " ended at : " + new Date());
	}

//	@After(value = "execution(public * com.example.demo.controller.EmpoyeeController.addEmpoyee(..))")
//	public void afterAdviceforEmpController2(JoinPoint jpoint) {
//		LOGGER.info("Responce goes to " + jpoint.getSignature()  + " ended at : " + new Date());
//	}

	@AfterThrowing(value = "execution(* com.example.demo.service.EmpoyeeServiceImp.*(..))", throwing = "ex")
	public void afterThrowAdviceforEmpServic(JoinPoint jpoint, Exception ex) {
		LOGGER.error(" AfterThrowing Responce goes to " + jpoint.getSignature() + " ended at : " + new Date());
		LOGGER.error(" request : " + jpoint.getArgs().toString());
		LOGGER.error(jpoint.getSignature() + " error MSG: " + ex.getMessage() + " ended at : " + new Date());

	}

	// @Around annotation we can pass in

//	@Around(value = "execution(public * com.example.demo.service.EmpoyeeServiceImp.addEmployee(..))")
//	public void aroundAdviceForEmpServic(ProceedingJoinPoint pjpoint) {
//		LOGGER.info(" @Around Responce goes to " + pjpoint.getSignature() + " Inside at : " + new Date());
//		try {
//			pjpoint.proceed();
//		} catch (Throwable e) {
//			LOGGER.error("@Around  Error Responce goes to   failed " + e.getMessage() + " at Date" + new Date());
//			//e.printStackTrace();
//		}
//
//	}

	@Around(value = "execution(public * com.example.demo.service.EmpoyeeServiceImp.*(..))")
	public Object AdvicelogRequestAndResponse(ProceedingJoinPoint pjpoint) {

		// fetch request and responce logs
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		HttpServletResponse responce = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getResponse();
		Object result = null;
		try {
//			String RequestBody = captureRequestBody(request);
			LOGGER.debug("Https Request Body: ");
			LOGGER.debug("Https Reques Method : " + request.getMethod());
			LOGGER.debug("Https Request URL : " + request.getRequestURI());
//			LOGGER.debug("Https Request Headers : " + request.getHeaderNames().toString());
//			LOGGER.debug(pjpoint.getSignature().getName() + "  RequestBody : " + request.getParameterMap().toString() + " ");
			
			request.getHeaderNames().asIterator().forEachRemaining(s->LOGGER.debug("Https Request Headers :"+ s));
			LOGGER.debug(pjpoint.getSignature().getName() + "  RequestBody : " );
			request.getParameterMap().entrySet().stream().forEach(e->LOGGER.debug( "  MapRequest : " +e));

			result = pjpoint.proceed();
//			String ResponceBody = captureResponseBody(responce);

			LOGGER.debug("Https responce Body: ");
			LOGGER.debug("Https responce status code : " + responce.getStatus());
			LOGGER.debug("Https responce Headers : " + responce.getHeaderNames());
			LOGGER.debug(pjpoint.getSignature().getName() + "  ResponceBody : " + result.toString() + " ");

		} catch (Throwable e) {
			// e.printStackTrace();
			LOGGER.error("Around  error failed " + pjpoint.getSignature().getName() + " result: " + result);
		}
		return result;
	}

	private String captureRequestBody(HttpServletRequest httprequest) throws IOException {
		BufferedReader reader = httprequest.getReader();
		return reader.lines().collect(Collectors.joining(System.lineSeparator()));
	}

	private String captureResponseBody(HttpServletResponse httpResponce) throws IOException {
		return httpResponce.getOutputStream().toString();
	}
}
