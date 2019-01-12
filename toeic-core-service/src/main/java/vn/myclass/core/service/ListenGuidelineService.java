package vn.myclass.core.service;

import org.hibernate.exception.ConstraintViolationException;
import vn.myclass.core.dto.ListenGuidelineDTO;

import java.util.Map;

public interface ListenGuidelineService {
    public Object[] findListenGuidelineByProperty(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ListenGuidelineDTO findById(Integer listenGuideLineId);
    void saveListenGuideline(ListenGuidelineDTO listenGuidelineDTO) throws ConstraintViolationException;
    ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO listenGuidelineDTO) throws ConstraintViolationException;
}
