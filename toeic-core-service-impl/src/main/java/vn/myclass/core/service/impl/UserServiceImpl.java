package vn.myclass.core.service.impl;

import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {
    public UserDTO isUserExist(UserDTO userDTO) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.findUserByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public UserDTO findRoleByUser(UserDTO userDTO) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.findUserByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }
}
