package vn.myclass.core.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import vn.myclass.core.dao.RoleDao;
import vn.myclass.core.data.daoimpl.AbstractDao;
import vn.myclass.core.persistence.entity.RoleEntity;

import java.util.List;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {

    @Override
    public List<RoleEntity> findByNameInNameList(List<String> nameList) {
        List<RoleEntity> roleEntityList = null;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.in("name", nameList));
            roleEntityList = cr.list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
        return roleEntityList;
    }
}