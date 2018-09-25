package vn.myclass.core.test;

import org.testng.annotations.Test;
import vn.myclass.core.dao.ListenGuidelineDao;
import vn.myclass.core.daoimpl.ListenGuidelineDaoImpl;
import vn.myclass.core.persistence.entity.ListenGuidelineEntity;

import java.util.List;

public class ListenGuidelineTest {
    @Test
    public void testFindByProperties(){
        ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();
        Integer offset = 2;
        Integer limit = 2;
        List<ListenGuidelineEntity> list = listenGuidelineDao.findByProperty(null, null, null, null, offset, limit);
    }
}
