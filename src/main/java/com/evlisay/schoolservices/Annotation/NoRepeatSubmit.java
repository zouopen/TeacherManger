package com.evlisay.schoolservices.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: EvilSay
 * @Date: 2019/7/14 21:08
 */
@Target(ElementType.METHOD) //注释在方法
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface NoRepeatSubmit {

}
