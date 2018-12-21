package vn.myclass.core.service;

import vn.myclass.core.dto.UserDTO;

import java.util.Map;

public interface UserService {
    UserDTO isUserExist(UserDTO userDTO);
    UserDTO findRoleByUser(UserDTO userDTO);
    Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer id);
}
