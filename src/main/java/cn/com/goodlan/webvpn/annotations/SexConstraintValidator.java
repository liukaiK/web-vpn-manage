package cn.com.goodlan.webvpn.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验手机号码
 *
 * @author liukai
 */
public class SexConstraintValidator implements ConstraintValidator<Sex, Character> {

    @Override
    public void initialize(Sex constraintAnnotation) {

    }

    @Override
    public boolean isValid(Character value, ConstraintValidatorContext context) {
        return '男' == value || '女' == value;
    }

}
