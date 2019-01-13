package vn.myclass.core.service;

import org.hibernate.exception.ConstraintViolationException;
import vn.myclass.core.dto.ListenGuidelineDTO;

import java.util.List;
import java.util.Map;

public interface ListenGuidelineService {
    public Object[] findApproximateByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ListenGuidelineDTO findById(Integer listenGuideLineId);
    void save(ListenGuidelineDTO listenGuidelineDTO) throws ConstraintViolationException;
    ListenGuidelineDTO update(ListenGuidelineDTO listenGuidelineDTO) throws ConstraintViolationException;
    Integer delete(List<Integer> idList);
}
