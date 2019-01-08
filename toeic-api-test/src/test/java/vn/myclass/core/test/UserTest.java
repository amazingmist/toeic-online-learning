package vn.myclass.core.test;

import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.web.util.SingletonServiceUtil;

public class UserTest {
//    @Test
    public void testIsUserExist(){
//        String name = "thanhtai";
//        String password = "12345";
//        UserDao userDao = new UserDaoImpl();
//        UserEntity entity = userDao.findByNameAndPassword(name, password);
//        System.out.println(entity.getName());
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(1);
        UserDTO userDTO = new UserDTO();
        userDTO.setName("33");
        userDTO.setFullName("33");
        userDTO.setRoleDTO(roleDTO);
        userDTO.setPassword("33");

        SingletonServiceUtil.getUserServiceInstance().saveUser(userDTO);
    }
}
