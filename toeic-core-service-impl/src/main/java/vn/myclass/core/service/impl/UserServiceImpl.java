package vn.myclass.core.service.impl;

import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.dto.UserImportDTO;
import vn.myclass.core.persistence.entity.RoleEntity;
import vn.myclass.core.persistence.entity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.service.util.SingletonDaoUtil;
import vn.myclass.core.utils.UserBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    @Override
    public UserDTO findRoleByUser(UserDTO userDTO) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] finded = SingletonDaoUtil.getUserDaoInstance().findByProperties(properties, sortExpression, sortDirection, offset, limit);
        List<UserDTO> dtoList = new ArrayList<>();

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
        UserDTO oldUserDTO = this.findById(userDTO.getUserId());
//        set new data to old dto
        oldUserDTO.setFullName(userDTO.getFullName());
        oldUserDTO.setPassword(userDTO.getPassword());
        oldUserDTO.setRoleDTO(userDTO.getRoleDTO());

        UserEntity entity = UserBeanUtil.dto2Entity(oldUserDTO);
        entity = SingletonDaoUtil.getUserDaoInstance().update(entity);
        oldUserDTO = UserBeanUtil.entity2Dto(entity);
        return oldUserDTO;
    }

    @Override
    public void validateImportUsers(List<UserImportDTO> userImportDTOS) {
        List<String> nameList = new ArrayList<>();
        List<String> roleList = new ArrayList<>();

//        put all names and roles in userImportDTOS to each list
        userImportDTOS.forEach(userImportDTO -> {
            if (userImportDTO.isValid()) {
                nameList.add(userImportDTO.getName());

                if (!roleList.contains(userImportDTO.getRoleName())) {
                    roleList.add(userImportDTO.getRoleName());
                }
            }
        });

//        these map will contains all of the names and roles retrieve from database
        Map<String, UserEntity> userEntityMap = new HashMap<>();
        Map<String, RoleEntity> roleEntityMap = new HashMap<>();

//        Put all of the names retrieved from db to map
        if (nameList.size() > 0) {
            List<UserEntity> userEntityList = SingletonDaoUtil.getUserDaoInstance().findByNameInNameList(nameList);
            userEntityList.forEach(userEntity -> {
                userEntityMap.put(userEntity.getName().toLowerCase(), userEntity);
            });
        }

//        Put all of the roles retrieved from db to map
        if (roleList.size() > 0) {
            List<RoleEntity> roleEntityList = SingletonDaoUtil.getRoleDaoInstance().findByNameInNameList(roleList);
            roleEntityList.forEach(roleEntity -> {
                roleEntityMap.put(roleEntity.getName().toLowerCase(), roleEntity);
            });
        }

//        Iteration over the list to validate it
        userImportDTOS.forEach(userImportDTO -> {
//            validate if the dto still is valid
            if (userImportDTO.isValid()) {
                String msg = userImportDTO.getError();
                if ((userEntityMap.containsKey(userImportDTO.getName().toLowerCase()))) {
                    if (!msg.equals("")) {
                        msg += "<br/>";
                    }
                    msg += "Tên đăng nhập đã tồn tại trong hệ thống";
                }

                if (!roleEntityMap.containsKey(userImportDTO.getRoleName().toLowerCase())) {
                    if (!msg.equals("")) {
                        msg += "<br/>";
                    }
                    msg += "Vai trò không tồn tại trong hệ thống";
                }

                if (!msg.equals("")) {
                    userImportDTO.setValid(false);
                    userImportDTO.setError(msg);
                }
            }
        });

    }

    @Override
    public void saveImportUsers(List<UserImportDTO> userImportDTOS) {
        userImportDTOS.forEach(userImportDTO -> {
            if (userImportDTO.isValid()) {
                UserEntity userEntity = new UserEntity();

                userEntity.setName(userImportDTO.getName());
                userEntity.setPassword(userImportDTO.getPassword());
                userEntity.setFullName(userImportDTO.getFullName());
                userEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));

                RoleEntity roleEntity = SingletonDaoUtil.getRoleDaoInstance().findUniqueEqual("name", userImportDTO.getRoleName());
                userEntity.setRoleEntity(roleEntity);

                SingletonDaoUtil.getUserDaoInstance().save(userEntity);
            }
        });
    }
}
