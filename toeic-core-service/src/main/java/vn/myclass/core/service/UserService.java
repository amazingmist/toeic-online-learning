package vn.myclass.core.service;

import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.dto.UserImportDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO findRoleByUser(UserDTO userDTO);
    Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer id);
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    void validateImportUsers(List<UserImportDTO> userImportDTOS);
}
