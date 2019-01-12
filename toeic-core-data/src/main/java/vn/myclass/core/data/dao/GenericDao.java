package vn.myclass.core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();
    T update(T entity);
    T save(T entity);
    T findById(ID id);
    Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Object[] findApproximateByProperties(Map<String, String> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Integer delete(List<ID> idList);
    T findUniqueEqual(String property, Object value);
}
