package simu.tech.log;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    //要执行的操作类型
    public String OperationType() default  "";

    //要执行的模块
    public String Serve()default "";
}
