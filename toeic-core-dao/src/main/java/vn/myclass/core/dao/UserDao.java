package vn.myclass.core.dao;

import vn.myclass.core.data.dao.GenericDao;
import vn.myclass.core.persistence.entity.UserEntity;

import java.util.List;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    UserEntity findByNameAndPassword(String name, String password);
    List<UserEntity> findByNameInNameList(List<String> nameList);
}
