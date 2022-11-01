package cn.com.goodlan.webvpn.configuration;

import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.exception.DataValidException;
import cn.com.goodlan.webvpn.pojo.Result;
import cn.com.goodlan.webvpn.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * JSON增强 把json用Result封装起来
 *
 * @author liukai
 */
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(ResponseResultBodyAdvice.class);

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;


    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 防止重复包裹的问题出现

        if (body instanceof Boolean) {
            return body;
        }

        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }

    /**
     * Exception异常处理
     */
//    @ExceptionHandler(Exception.class)
//    public Result exception(Exception exception) {
//        log.error("exception", exception);
//        return Result.fail(500, ExceptionUtils.getStackTrace(exception));
//    }
//
//
//    /**
//     * 运行时异常
//     */
//    @ExceptionHandler(RuntimeException.class)
//    public Object runtimeException(RuntimeException exception, HttpServletRequest request, HttpServletResponse response) {
//        log.error("系统发生异常", exception);
//        if (isAjaxRequest(request)) {
//            return Result.fail(500, ExceptionUtils.getStackTrace(exception));
//        }
//        return new ModelAndView(SystemConstant.PAGE + "/error/500");
//    }
//
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        log.warn(e.getMessage(), e);
        return Result.fail(500, "不支持' " + e.getMethod() + "'请求");
    }


    @ExceptionHandler(BusinessException.class)
    public Object businessException(HttpServletRequest request, BusinessException e) {
        log.error(e.getMessage(), e);
        if (HttpUtil.isAjaxRequest(request)) {
            return Result.fail(500, e.getMessage());
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error/business");
            return modelAndView;
        }
    }


    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Result validatedBindException(BindException e) {
        log.warn(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Result.fail(500, message);
    }

    /**
     * 数据校验异常
     */
    @ExceptionHandler(DataValidException.class)
    public Result dataValidException(DataValidException e) {
        return Result.fail(400, e.getMessage());
    }

//    /**
//     * 自定义验证异常
//     */
//    @ExceptionHandler(BindException.class)
//    public Result validatedBindException(BindException e) {
//        String message = e.getAllErrors().get(0).getDefaultMessage();
//        return Result.fail(400, message);
//    }
//
//
}
