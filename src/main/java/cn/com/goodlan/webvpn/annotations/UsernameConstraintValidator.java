package cn.com.goodlan.webvpn.annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 校验账号规则
 *
 * @author liukai
 */
public class UsernameConstraintValidator implements ConstraintValidator<Username, String> {

    private final static Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]{4,16}$");

    @Override
    public void initialize(Username constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return PATTERN.matcher(value).matches();
    }

}
