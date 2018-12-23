package vn.myclass.core.service.impl;

import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.service.utils.SingletonDaoUtil;
import vn.myclass.core.utils.UserBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    @Override
    public UserDTO findRoleByUser(UserDTO userDTO) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findUserByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] finded = SingletonDaoUtil.getUserDaoInstance().findByProperties(properties, sortExpression, sortDirection, offset, limit);
        List<UserDTO> dtoList = new ArrayList<UserDTO>();

//        convert entity list to dto
        for (UserEntity entity : (List<UserEntity>) finded[1]) {
            UserDTO dto = UserBeanUtil.entity2Dto(entity);
            dtoList.add(dto);
        }

        return new Object[]{finded[0], dtoList};
    }

    @Override
    public UserDTO findById(Integer id) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findById(id);
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        userDTO.setCreatedDate(createdDate);
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        SingletonDaoUtil.getUserDaoInstance().save(entity);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        entity = SingletonDaoUtil.getUserDaoInstance().update(entity);
        userDTO = UserBeanUtil.entity2Dto(entity);
        return userDTO;
    }
}
