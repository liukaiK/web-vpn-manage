package cn.com.goodlan.webvpn.annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 校验车牌号规则
 *
 * @author liukai
 */
public class LicensePlateNumberConstraintValidator implements ConstraintValidator<LicensePlateNumber, String> {

    private final static Pattern PATTERN = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");

    @Override
    public void initialize(LicensePlateNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return PATTERN.matcher(value).matches();
    }

}
