package com.jeremy.sample.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Jeremy Yang on 21/12/2015.
 */
@Transactional
public abstract class AbstractHibernateDao<T> implements Dao<T>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHibernateDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession()
    {
//        if (session == null) {
//            session = sessionFactory.withOptions().interceptor(new AuditInterceptor())
//                    .openSession();
//            session.setFlushMode(FlushMode.AUTO);
//            return session;
//        }
        return sessionFactory.getCurrentSession();
    }

    /**
     * Creates the criteria.
     *
     * @return the criteria
     */
    abstract protected Criteria createCriteria();


    public T getById(Long id)
    {
        Criteria criteria = this.createCriteria();
        criteria.add(Restrictions.eq("id", id));

        return (T) criteria.uniqueResult();
    }

    public T save(T entity)
    {
        return (T) saveEntity(entity);
    }

    /**
     * Save entity.
     *
     * @param entity the entity
     * @return the object
     */
    protected Object saveEntity(Object entity)
    {
        LOGGER.debug(entity.toString());

        Session session = getSession();

        entity = session.merge(entity);

        LOGGER.debug(entity.toString());

        return entity;
    }

    public void delete(T entity)
    {
        deleteEntity(entity);
    }

    /**
     * Delete entity.
     *
     * @param entity the entity
     */
    protected void deleteEntity(Object entity)
    {
        Session session = getSession();
        session.delete(entity);

    }
}
