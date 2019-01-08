package vn.myclass.core.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.data.daoimpl.AbstractDao;
import vn.myclass.core.persistence.entity.UserEntity;

import java.util.List;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {
    public UserEntity findByNameAndPassword(String name, String password) {
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

    @Override
    public List<UserEntity> findByNameInNameList(List<String> nameList) {
        List<UserEntity> userEntityList = null;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.in("name", nameList));
            userEntityList = cr.list();
        }catch (HibernateException e) {
            throw e;
        }finally {
            session.close();
        }
        return userEntityList;
    }
}
