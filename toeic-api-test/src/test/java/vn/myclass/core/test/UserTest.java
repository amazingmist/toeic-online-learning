package vn.myclass.core.test;

import org.testng.annotations.Test;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.persistence.entity.UserEntity;

public class UserTest {
//    @Test
    public void testIsUserExist(){
        String name = "thanhtai";
        String password = "12345";
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.isUserExist(name, password);
    }
}
