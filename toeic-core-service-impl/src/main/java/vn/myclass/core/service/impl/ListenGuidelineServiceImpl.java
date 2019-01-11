package vn.myclass.core.service.impl;

import vn.myclass.core.dao.ListenGuidelineDao;
import vn.myclass.core.daoimpl.ListenGuidelineDaoImpl;
import vn.myclass.core.dto.ListenGuidelineDTO;
import vn.myclass.core.persistence.entity.ListenGuidelineEntity;
import vn.myclass.core.service.ListenGuidelineService;
import vn.myclass.core.service.util.SingletonDaoUtil;
import vn.myclass.core.utils.ListenGuidelineBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {
    @Override
    public Object[] findListenGuidelineByProperty(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getListenGuidelineDaoInstance().findByProperties(properties, sortExpression, sortDirection, offset, limit);
        List<ListenGuidelineDTO> dtoList = new ArrayList<>();

//        convert entity list to dto
        for (ListenGuidelineEntity entity : (List<ListenGuidelineEntity>)objects[1]) {
            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(entity);
            dtoList.add(dto);
        }
        return new Object[]{objects[0], dtoList};
    }

    @Override
    public ListenGuidelineDTO findById(Integer listenGuideLineId) {
        ListenGuidelineEntity entity = SingletonDaoUtil.getListenGuidelineDaoInstance().findById(listenGuideLineId);
        return ListenGuidelineBeanUtil.entity2Dto(entity);
    }

    @Override
    public void saveListenGuideline(ListenGuidelineDTO listenGuidelineDTO) {
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        listenGuidelineDTO.setCreatedDate(createdDate);
        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dto2Entity(listenGuidelineDTO);
        SingletonDaoUtil.getListenGuidelineDaoInstance().save(entity);
    }
}
