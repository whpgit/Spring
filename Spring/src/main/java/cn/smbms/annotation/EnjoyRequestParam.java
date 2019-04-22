package cn.smbms.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)  //变量
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnjoyRequestParam {
    String value() default "";
}
