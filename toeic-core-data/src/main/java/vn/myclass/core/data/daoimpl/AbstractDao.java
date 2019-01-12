package vn.myclass.core.data.daoimpl;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import vn.myclass.core.common.constant.CoreConstant;
import vn.myclass.core.common.util.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private final Logger logger = Logger.getLogger(this.getClass());
    private Class<T> persistenceClass;

    public AbstractDao() {
        // generic < x , y > as array
        // set persistenceClass = T
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    private String getPersistenceClassName() {
        return this.persistenceClass.getSimpleName();
    }

    protected Class<T> getPersistenceClass() {
        return this.persistenceClass;
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public List<T> findAll() {
        List list;
        Session session = this.getSession();
        try {
            list = session.createCriteria(this.getPersistenceClass()).list();
        } finally {
            session.close();
        }

        return list;
    }

    public T update(T entity) {
        T result;
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = (T) session.merge(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            session.close();
        }
        return result;
    }

    public T save(T entity) throws HibernateException{
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            session.close();
        }
        return entity;
    }

    public T findById(ID id){
        T result = null;
        Session session = this.getSession();
        try {
            // note: the first parameter is class type, so we pass persistenceClass at this situation
            result = (T) session.get(this.getPersistenceClass(), id);
        } catch (HibernateException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }finally{
            session.close();
        }
        return result;
    }

    public Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List list;
        Long count;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());

//            set condition for query
            if (properties != null) {
                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                }
            }

//            set sort direction
            if (sortExpression != null && sortDirection != null) {
                Order order = sortDirection.equals(CoreConstant.SORT_ASC) ?
                        Order.asc(sortExpression) : Order.desc(sortExpression);
                cr.addOrder(order);
            }

//            set start position offset
            if (offset != null && offset >= 0) {
                cr.setFirstResult(offset);
            }

//            set limit row
            if (limit != null && limit > 0) {
                cr.setMaxResults(limit);
            }

            list = cr.list();

//            count total of items
            Criteria cr2 = session.createCriteria(this.getPersistenceClass());

//            set condition for query
            if (properties != null) {
                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    cr2.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                }
            }

//            set result is a num row of list
            cr2.setProjection(Projections.rowCount());
            count = (Long) cr2.uniqueResult();
        } finally {
            session.close();
        }
        return new Object[]{count, list};
    }

    @Override
    public Object[] findApproximateByProperties(Map<String, String> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List list;
        Long count;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());

//            set condition for query
            if (properties != null) {
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    cr.add(Restrictions.like(entry.getKey(), entry.getValue(), MatchMode.ANYWHERE));
                }
            }

//            set sort direction
            if (sortExpression != null && sortDirection != null) {
                Order order = sortDirection.equals(CoreConstant.SORT_ASC) ?
                        Order.asc(sortExpression) : Order.desc(sortExpression);
                cr.addOrder(order);
            }

//            set start position offset
            if (offset != null && offset >= 0) {
                cr.setFirstResult(offset);
            }

//            set limit row
            if (limit != null && limit > 0) {
                cr.setMaxResults(limit);
            }

            list = cr.list();

//            count total of items
            Criteria cr2 = session.createCriteria(this.getPersistenceClass());

//            set condition for query
            if (properties != null) {
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    cr2.add(Restrictions.like(entry.getKey(), entry.getValue(), MatchMode.ANYWHERE));
                }
            }

//            set result is a num row of list
            cr2.setProjection(Projections.rowCount());
            count = (Long) cr2.uniqueResult();
        } finally {
            session.close();
        }
        return new Object[]{count, list};
    }

    public Integer delete(List<ID> idList) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int countSuccess = 0;
        try {
            for (ID id : idList) {
                Object object = session.get(this.getPersistenceClass(), id);
                session.delete(object);
                countSuccess++;
            }
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            session.close();
        }
        return countSuccess;
    }

    @Override
    public T findUniqueEqual(String property, Object value) {
        T result;
        Session session = this.getSession();
        try {
            // note: the first parameter is class type, so we pass persistenceClass at this situation
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq(property, value));
            result = (T) cr.uniqueResult();
        } catch (HibernateException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }finally{
            session.close();
        }
        return result;
    }
}
