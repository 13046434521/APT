package com.jtl.apt_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者:jtl
 * 日期:Created in 2022/12/28 14:54
 * 描述:
 * 更改:
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ARouter {
    String path();

    String group () default "";
}
