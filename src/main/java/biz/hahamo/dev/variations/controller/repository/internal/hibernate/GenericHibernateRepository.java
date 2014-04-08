package biz.hahamo.dev.variations.controller.repository.internal.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import biz.hahamo.dev.variations.controller.repository.GenericRepository;
import biz.hahamo.dev.variations.controller.repository.PersistenceQuery;

@SuppressWarnings("rawtypes")
public class GenericHibernateRepository extends HibernateDaoSupport implements GenericRepository
{
    private final HibernateQueryCreator hibernateQueryCreator;
    
    public GenericHibernateRepository(HibernateQueryCreator hibernateQueryCreator)
    {
        this.hibernateQueryCreator = hibernateQueryCreator;
    }

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
        getHibernateTemplate().saveOrUpdate(entity);
    }

    public void remove(Object entity)
    {
        getHibernateTemplate().delete(entity);
    }

    public void attach(Object entity)
    {
        save(entity);
    }

    public void detach(Object entity)
    {
        getHibernateTemplate().evict(entity);
    }

    public void flush()
    {
        getHibernateTemplate().flush();
    }

    public <T> T findByQuery(final PersistenceQuery query)
    {
        return getHibernateTemplate().execute(new HibernateCallback<T>()
        {
            @SuppressWarnings("unchecked")
            public T doInHibernate(final Session session)
            {
                final Query hibernateQuery = getHibernateQuery(query, session);
                return (T) hibernateQuery.uniqueResult();
            }
        });
    }

    public <T> List<T> findListByQuery(final PersistenceQuery query)
    {
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>()
        {
            @SuppressWarnings("unchecked")
            public List<T> doInHibernate(final Session session)
            {
                final Query hibernateQuery = getHibernateQuery(query, session);

                return hibernateQuery.list();
            }
        });
    }

    public int execute(final PersistenceQuery query)
    {
        return (Integer) getHibernateTemplate().execute(new HibernateCallback<Integer>()
        {
            public Integer doInHibernate(final Session session)
            {
                final Query hibernateQuery = getHibernateQuery(query, session);
                return hibernateQuery.executeUpdate();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private Query getHibernateQuery(PersistenceQuery persistenceQuery, Session session)
    {
        return hibernateQueryCreator.createQuery(session, persistenceQuery);
    }

}
