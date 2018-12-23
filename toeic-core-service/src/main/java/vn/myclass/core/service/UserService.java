package vn.myclass.core.service;

import vn.myclass.core.dto.UserDTO;

import java.util.Map;

public interface UserService {
    UserDTO findRoleByUser(UserDTO userDTO);
    Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer id);
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
}
