package vn.myclass.core.data.daoimpl;

import org.hibernate.*;
import vn.myclass.core.common.constant.CoreConstant;
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

    public String getPersistenceClassName(){
        return this.persistenceClass.getSimpleName();
    }

    protected Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    public List<T> findAll() {
        List<T> list;
        Session session = this.getSession();
        try {
            StringBuilder hql = new StringBuilder("from ");
            hql.append(this.getPersistenceClassName());
            Query query = session.createQuery(hql.toString());
            list = query.list();
        }catch (HibernateException ex){
            throw ex;
        }finally {
            session.close();
        }

        return list;
    }

    public void update(T entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            throw ex;
        }finally {
            session.close();
        }
    }

    public void save(T entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            throw ex;
        }finally {
            session.close();
        }
    }

    public T findById(ID id) {
        T result;
        Session session = this.getSession();
        try {
            // note: the first parameter is class type, so we pass persistenceClass at this situation
            result = (T) session.get(this.persistenceClass, id);
            if (result == null){
                throw new ObjectNotFoundException("NOT FOUND " + id, this.getPersistenceClassName());
            }
        }catch (HibernateException ex){
            throw ex;
        }
        return result;
    }

    public List<T> findByProperty(String property, Object value, String sortExpression, String sortDirection) {
        List<T> list;
        Session session = this.getSession();
        try {
            StringBuilder hql = new StringBuilder("from ");
            hql.append(this.getPersistenceClassName());
            if (property != null && value != null) {
                hql.append(" where ").append(property).append(" = :value ");
            }
            if (sortExpression != null && sortDirection != null){
                hql.append("order by ").append(sortExpression);
                hql.append(" ").append(sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc");
            }

            Query query = session.createQuery(hql.toString());
            if (value != null) {
                query.setParameter("value", value);
            }
            list = query.list();
        }catch (HibernateException ex){
            throw ex;
        }
        return list;
    }

    public Integer delete(List<ID> ids) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int countSuccess = 0;
        try {
            for (ID id: ids) {
                Object object = session.get(this.persistenceClass, id);
                session.delete(object);
                countSuccess++;
            }
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            throw ex;
        }finally {
            session.close();
        }
        return countSuccess;
    }
}
