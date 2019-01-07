package com.joke.yu.corelib.IOC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @author luyanjun
 * @email lu.yj@sand.com.cn
 * @description
 */

//注解的目标类型： METHOD(方法) FIELD(字段)
@Target(ElementType.FIELD)
//保留的范围 默认为CLASS   SOURCE: 只在源码中可用  CLASS:在源码和字节码中可用
//RUNTIME: 在源码,字节码,运行时均可用
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewById {
    int value();
}
