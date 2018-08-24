package com.zz.mynetty.config.client.annotation;

import java.lang.annotation.*;

/**
 * @author zhangzuizui
 * @date 2018/8/24 10:47
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyListener {

    String name() default "zzz" ;
}
