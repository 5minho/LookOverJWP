package spms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 3..
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value() default "";
}
