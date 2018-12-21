package vn.myclass.core.service.impl;

import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.utils.UserBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    public UserDTO isUserExist(UserDTO userDTO) {
        UserEntity entity = userDao.findUserByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public UserDTO findRoleByUser(UserDTO userDTO) {
        UserEntity entity = userDao.findUserByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] finded = userDao.findByProperties(properties, sortExpression, sortDirection, offset, limit);
        List<UserDTO> dtoList = new ArrayList<UserDTO>();

//        convert entity list to dto
        for (UserEntity entity : (List<UserEntity>) finded[1]) {
            UserDTO dto = UserBeanUtil.entity2Dto(entity);
            dtoList.add(dto);
        }

        return new Object[]{finded[0], dtoList};
    }

    public UserDTO findById(Integer id) {
        UserEntity entity = userDao.findById(id);
        return UserBeanUtil.entity2Dto(entity);
    }
}
