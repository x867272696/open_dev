package com.htl.cloudmemory.core.validate;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 
 * @author he.tianlong
 * @since 20180913
 *
 */
public class ValidateUtils {

	private static Logger logger = LoggerFactory.getLogger(ValidateUtils.class);
    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final static ExecutableValidator validator = factory.getValidator().forExecutables();

	public static String validate(ProceedingJoinPoint pjp) {
		try {
			Object[] parameters = pjp.getArgs();
			if (parameters != null && parameters.length > 0) {
				Integer index = 0;
				//校验javabean
				for (Object parameter : parameters) {
					if (parameter instanceof BindingResult) {
						StringBuilder sBuilder = new StringBuilder();
						BindingResult result = (BindingResult) parameter;
						if (result.hasErrors()) {
							List<ObjectError> list = result.getAllErrors();
							for (ObjectError error : list) {
								logger.info("error info:" + error.getDefaultMessage());
								sBuilder.append(error.getDefaultMessage());
								sBuilder.append("/");
							}
							if (sBuilder.length() > 0 && sBuilder.lastIndexOf("/") == sBuilder.length() - 1) {
								sBuilder.deleteCharAt(sBuilder.length() - 1);
							}
							if (sBuilder.length() > 0) {
								return new String(sBuilder.toString());
							}
						}
					}

					index++;
				}
				
		        //校验单个参数
		        Object target = pjp.getThis();
		        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, parameters);
		        if (!validResult.isEmpty()) {
		            return new String(validResult.iterator().next().getMessage());
		        }
		        return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


    private static <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }
}
