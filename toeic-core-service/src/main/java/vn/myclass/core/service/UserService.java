package vn.myclass.core.service;

import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.dto.UserImportDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO findByNameAndPassword(String name, String password);
    Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer id);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void validateImportUsers(List<UserImportDTO> userImportDTOS);
    void saveImportUsers(List<UserImportDTO> userImportDTOS);
}
