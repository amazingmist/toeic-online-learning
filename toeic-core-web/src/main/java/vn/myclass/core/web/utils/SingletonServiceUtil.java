package vn.myclass.core.web.utils;

import vn.myclass.core.service.impl.ListenGuidelineServiceImpl;
import vn.myclass.core.service.impl.RoleServiceImpl;
import vn.myclass.core.service.impl.UserServiceImpl;

public class SingletonServiceUtil {
    private static UserServiceImpl userService = null;
    private static RoleServiceImpl roleService = null;
    private static ListenGuidelineServiceImpl listenGuidelineService = null;

    private SingletonServiceUtil() {
    }

    public static UserServiceImpl getUserServiceInstance() {
        if (userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public static RoleServiceImpl getRoleServiceInstance() {
        if (roleService == null){
            roleService = new RoleServiceImpl();
        }
        return roleService;
    }

    public static ListenGuidelineServiceImpl getListenGuidelineServiceInstance() {
        if (listenGuidelineService == null){
            listenGuidelineService = new ListenGuidelineServiceImpl();
        }
        return listenGuidelineService;
    }
}
