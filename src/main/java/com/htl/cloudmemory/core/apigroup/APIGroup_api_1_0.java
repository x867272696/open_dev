package com.htl.cloudmemory.core.apigroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于swagger分组,此分组为1.0版需求的分组
 * @author he.tianlong
 * @date 2018-8-15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APIGroup_api_1_0 {

}
