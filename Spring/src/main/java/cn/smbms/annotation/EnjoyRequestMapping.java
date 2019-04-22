package cn.smbms.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnjoyRequestMapping {
    String value() default "";   //表示在我们注解跟的字符串的值
}
