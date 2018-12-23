package vn.myclass.core.service.impl;

import vn.myclass.core.dao.ListenGuidelineDao;
import vn.myclass.core.daoimpl.ListenGuidelineDaoImpl;
import vn.myclass.core.dto.ListenGuidelineDTO;
import vn.myclass.core.persistence.entity.ListenGuidelineEntity;
import vn.myclass.core.service.ListenGuidelineService;
import vn.myclass.core.utils.ListenGuidelineBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {
    ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();

    @Override
    public Object[] findListenGuidelineByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        /*Object[] finded = listenGuidelineDao.findByProperties(property, value, sortExpression, sortDirection, offset, limit);
        List<ListenGuidelineDTO> dtoList = new ArrayList<ListenGuidelineDTO>();

//        convert entity list to dto
        for (ListenGuidelineEntity entity : (List<ListenGuidelineEntity>)finded[1]) {
            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(entity);
            dtoList.add(dto);
        }
        return new Object[]{finded[0], dtoList};*/
        return null;
    }
}
