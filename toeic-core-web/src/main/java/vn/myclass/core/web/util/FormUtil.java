package vn.myclass.core.web.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil {
    public static <T> T populate(Class<T> clazz, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        T object = null;
        try {
            object = clazz.newInstance();
            BeanUtils.populate(object, request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw e;
        }
        return object;
    }
}
