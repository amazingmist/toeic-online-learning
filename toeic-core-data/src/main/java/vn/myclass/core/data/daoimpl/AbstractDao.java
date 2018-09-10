package vn.myclass.core.data.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private Class<T> persistenceClass;

    public AbstractDao() {
        // generic < x , y > as array
        // set persistenceClass = T
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClass(){
        return this.persistenceClass.getSimpleName();
    }

    protected Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    public List<T> findAll() {
        // TODO: change list = null when declare
        List<T> list = new ArrayList<T>();
        Transaction transaction = null;

        try {
            Session session = this.getSession();
            transaction = session.beginTransaction();

            StringBuilder hql = new StringBuilder("from ");
            hql.append(this.getPersistenceClass());

            Query query = session.createQuery(hql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            throw ex;
        }

        return list;
    }
}
