package vn.myclass.core.data.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
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
        List<T> list;
        Transaction transaction = null;

        try {
            Session session = this.getSession();
            transaction = session.beginTransaction();

            /*//Change to hql query if possible
            StringBuilder hql = new StringBuilder("from role");
            hql.append(this.getPersistenceClass());
            Query query = session.createQuery(hql.toString());*/

            SQLQuery query = session.createSQLQuery("SELECT * FROM role");
            list = query.list();
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            throw ex;
        }

        return list;
    }

    public T update(T entity) {
        T result = null;
        Transaction transaction = null;
        try {
            Session session = this.getSession();
            transaction = session.beginTransaction();
            result = (T) session.merge(entity);
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            throw ex;
        }
        return result;
    }
}
