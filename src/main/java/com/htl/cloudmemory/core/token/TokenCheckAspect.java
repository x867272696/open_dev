package com.htl.cloudmemory.core.token;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class TokenCheckAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 拦截器具体实现
	 * 
	 * @param pjp
	 * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
	 * @throws Throwable
	 */
	@Around("execution(* com.htl.cloudmemory.controller..*.*(..))")
	public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {

		logger.info("{}", "-----------------the token check  start...");

		Object result = null;

		logger.info("{}", "-----------------the token check  end...");

		try {
			if (result == null) {
				// 如果是已知异常,则直接向前端返回
				try {
					result = pjp.proceed();
				}catch(Exception e){
					return e;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return e;
		}
		return result;
	}

}
