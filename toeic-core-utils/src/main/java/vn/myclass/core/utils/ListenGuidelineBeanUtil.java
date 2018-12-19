package vn.myclass.core.utils;

import vn.myclass.core.dto.ListenGuidelineDTO;
import vn.myclass.core.persistence.entity.ListenGuidelineEntity;

public class ListenGuidelineBeanUtil {
    public static ListenGuidelineDTO entity2Dto(ListenGuidelineEntity entity){
        ListenGuidelineDTO dto = new ListenGuidelineDTO();
        dto.setListenGuideLineId(entity.getListenGuideLineId());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }

    public static ListenGuidelineEntity dto2Entity(ListenGuidelineDTO dto){
        ListenGuidelineEntity entity = new ListenGuidelineEntity();
        entity.setListenGuideLineId(dto.getListenGuideLineId());
        entity.setTitle(dto.getTitle());
        entity.setImage(dto.getImage());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }
}
