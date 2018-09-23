package vn.myclass.core.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.data.daoimpl.AbstractDao;
import vn.myclass.core.persistence.entity.RoleEntity;
import vn.myclass.core.persistence.entity.UserEntity;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    public UserEntity isUserExist(String name, String password) {
        UserEntity entity;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("name", name));
            cr.add(Restrictions.eq("password", password));
            entity = (UserEntity) cr.uniqueResult();
        }catch (HibernateException e) {
            throw e;
        }finally {
            session.close();
        }
        return entity;
    }

    public RoleEntity findRoleByUser(String name, String password) {
        RoleEntity entity;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("name", name));
            cr.add(Restrictions.eq("password", password));
            entity = ((UserEntity) cr.uniqueResult()).getRoleEntity();
        }catch (HibernateException e) {
            throw e;
        }finally {
            session.close();
        }
        return entity;
    }
}
