package cn.com.goodlan.webvpn.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liukai
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IPConstraintValidator.class)
public @interface IP {

    String message() default "IP格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
