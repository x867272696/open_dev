package com.htl.cloudmemory.core.token;

import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htl.cloudmemory.core.exception.SystemInfo;
import com.htl.cloudmemory.core.log.LogMark;
import com.htl.cloudmemory.core.log.LogVO;
import com.htl.cloudmemory.core.validate.ValidateUtils;
import com.htl.cloudmemory.core.vo.Result;

@Aspect
@Component
@Order(1)
public class TokenCheckAspect {

	@Around("execution(* com.htl.cloudmemory..*.*(..))")
	public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {

		Object result = null;

		try {
			if (result == null) {

				// 进行参数校验
				String validateR = null;
				if ((validateR = ValidateUtils.validate(pjp)) != null) {

					HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
							.getResponse();

					resp.setCharacterEncoding("utf-8");
					resp.setContentType("application/json; charset=utf-8");
					PrintWriter writer = resp.getWriter();

					writer.write(JSONObject.toJSONString(new Result("-1", validateR, null),
							SerializerFeature.WriteMapNullValue));
					writer.close();
					return null;
				}

				// 如果是已知异常,则直接向前端返回
				try {
					result = pjp.proceed();
				} catch (Exception e) {
					if (e instanceof SystemInfo) {
						SystemInfo si = (SystemInfo) e;

						HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder
								.getRequestAttributes()).getResponse();

						resp.setCharacterEncoding("utf-8");
						resp.setContentType("application/json; charset=utf-8");
						PrintWriter writer = resp.getWriter();

						writer.write(JSONObject.toJSONString(new Result(si.getCode(), si.getMessage(), null),
								SerializerFeature.WriteMapNullValue));
						writer.close();

						return null;
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return e;
		}

		Signature s = pjp.getSignature();
		MethodSignature ms = (MethodSignature) s;
		Method m = ms.getMethod();
		System.out.println(m.getDeclaringClass().getName());
		System.out.println("name: " + m.getName());

		Annotation att2 = m.getAnnotation(LogMark.class);
		if (att2 != null) {
			if (LogVO.log != null) {
				LoggerFactory.getLogger(m.getDeclaringClass()).info(LogVO.log.get());
			}
		}

		return result;
	}

}
