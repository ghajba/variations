package biz.hahamo.dev.variations.controller.repository.internal;

import java.io.Serializable;
import java.util.List;

import javax.management.Query;

import org.hibernate.Hibernate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import biz.hahamo.dev.variations.controller.repository.GenericRepository;

public class GenericHibernateRepository extends HibernateDaoSupport implements GenericRepository
{

    public <T> T find(Class<T> clazz, Serializable primaryKey)
    {
        if (primaryKey == null)
        {
            throw new IllegalArgumentException("The primary key cannot be null");
        }
        
        return getHibernateTemplate().get(clazz, primaryKey);
    }

    public void initialize(Object proxy)
    {
        Hibernate.initialize(proxy);
    }

    public void save(Object entity)
    {
        // TODO Auto-generated method stub

    }

    public void remove(Object entity)
    {
        // TODO Auto-generated method stub

    }

    public void attach(Object entity)
    {
        getHibernateTemplate().saveOrUpdate(entity);

    }

    public void detach(Object entity)
    {
        getHibernateTemplate().evict(entity);
    }

    public void flush()
    {
        getHibernateTemplate().flush();
    }

    public <T> T findByQuery(Query query)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public <T> List<T> findListByQuery(Query query)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public int execute(Query query)
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
