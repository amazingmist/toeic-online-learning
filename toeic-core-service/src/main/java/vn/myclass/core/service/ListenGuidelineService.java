package vn.myclass.core.service;

import vn.myclass.core.dto.ListenGuidelineDTO;

import java.util.List;
import java.util.Map;

public interface ListenGuidelineService {
    public Object[] findListenGuidelineByProperty(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
