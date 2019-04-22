package cn.smbms.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)  //成员变量
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnjoyAutowired {
    String value() default "";
}
