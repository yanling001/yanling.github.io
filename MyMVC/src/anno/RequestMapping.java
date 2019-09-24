package anno;


import java.lang.annotation.*;

@Target(ElementType.METHOD)//要求注解只能注解在方法上
@Retention(RetentionPolicy.RUNTIME)//设置RequestMapping的生命周期为运行时
public @interface RequestMapping {
    String value();
}
