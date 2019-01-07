package vn.myclass.core.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static SessionUtil sessionUtil = null;

    //    Applying Singleton Design Pattern
    public static SessionUtil getInstance() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }

    public static void putAttribute(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }

    public static Object getAttribute(HttpServletRequest req, String key){
        return req.getSession().getAttribute(key);
    }

    public static void removeAttribute(HttpServletRequest req, String key){
        req.getSession().removeAttribute(key);
    }
}
