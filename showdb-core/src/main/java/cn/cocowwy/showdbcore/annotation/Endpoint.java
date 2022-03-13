package cn.cocowwy.showdbcore.annotation;

import cn.cocowwy.showdbcore.constants.EndpointEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Cocowwy
 * @create 2022-03-03-20:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface Endpoint {
    EndpointEnum value();
}
