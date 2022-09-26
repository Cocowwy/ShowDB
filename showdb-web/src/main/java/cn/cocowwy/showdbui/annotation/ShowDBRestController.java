package cn.cocowwy.showdbui.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * 自定义ShowDBRestController
 * 来防止context-path导致的路径不对
 * 导致模板引擎请求的地址有误
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface ShowDBRestController {
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};
}
