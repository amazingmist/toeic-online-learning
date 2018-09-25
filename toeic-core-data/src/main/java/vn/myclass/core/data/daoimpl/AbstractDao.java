package vn.myclass.core.data.daoimpl;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
    public Class getPersistenceClass(){
        return this.persistenceClass;
    }

    protected Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    public List<T> findAll() {
        List<T> list;
        Session session = this.getSession();
        try {
            list = session.createCriteria(this.getPersistenceClass()).list();
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
            result = (T) session.get(this.getPersistenceClass(), id);
            if (result == null){
                throw new ObjectNotFoundException("NOT FOUND " + id, this.getPersistenceClassName());
            }
        }catch (HibernateException ex){
            throw ex;
        }
        return result;
    }

    public List<T> findByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<T> list;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            if (property != null && value != null) {
                cr.add(Restrictions.eq(property, value));
            }
            if (sortExpression != null && sortDirection != null){
                Order order = sortDirection.equals(CoreConstant.SORT_ASC) ?
                        Order.asc(sortExpression) : Order.desc(sortExpression);
                cr.addOrder(order);
            }

//            set start position offset
            if (offset != null && offset >= 0){
                cr.setFirstResult(offset);
            }
//            set limit row
            if (limit != null && limit > 0){
                cr.setMaxResults(limit);
            }

            list = cr.list();
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
                Object object = session.get(this.getPersistenceClass(), id);
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
