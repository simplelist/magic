package common;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;

/**
 * 减少逻辑判断的异常返回代码的重复编写
 */
public class ValidateUtil {

    public static final String NOT_FOUND = "{}不存在";


    /**
     * 该方法用在 controller 中,配合 hibernate-validator 使用,检查字段校验是否通过
     *
     * @param result
     */
    public static void paramValidate(BindingResult result) {
        if (result.hasErrors()) {
            Object target = result.getTarget();
            FieldError filedError = result.getFieldError();
            String filedName = filedError.getField();
            Field field = ReflectionUtils.findField(target.getClass(), filedName);
            if (field == null) {
                throwMessage(filedError.getDefaultMessage());
            }
            if (field.isAnnotationPresent(ApiModelProperty.class)) {
                ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                if (annotation != null) {
                    filedName = annotation.value();
                }
            }
            throw new CustomException("请检查参数[" + filedName + "]:" + filedError.getDefaultMessage());
        }
    }

    public static void isNull(Object... o) {
        for (Object oo : o) {
            if (oo == null) {
                throw new CustomException("对象不能为空");
            }
        }
    }

    public static void notFound(Object o, String message) {
        if (o == null) {
            throw new CustomException(404, StrUtil.format(NOT_FOUND, message));
        }
    }

    public static void isEmpty(Object o, String message) {
        if (Validator.isEmpty(o)) {
            throw new CustomException(message);
        }
    }

    /**
     * 满足条件抛出异常
     *
     * @param flag
     * @param message
     */
    public static void isTrue(boolean flag, String message) {
        if (flag) {
            throw new CustomException(message);
        }
    }

    /**
     * 不满足条件时抛出异常
     *
     * @param flag
     * @param message
     */
    public static void isFalse(boolean flag, String message) {
        isTrue(!flag, message);
    }


    public static void throwMessage(String message) {
        throw new CustomException(message);
    }


}
