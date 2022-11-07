package cn.com.goodlan.webvpn.annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 校验手机号码
 *
 * @author liukai
 */
public class MobileNumberConstraintValidator implements ConstraintValidator<MobileNumber, String> {

    private final static Pattern PATTERN = Pattern.compile("0?(13|14|15|18|19|16|17)[0-9]{9}");


    @Override
    public void initialize(MobileNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return PATTERN.matcher(value).matches();
    }

}
