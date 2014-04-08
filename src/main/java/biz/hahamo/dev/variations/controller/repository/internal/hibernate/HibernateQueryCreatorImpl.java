package biz.hahamo.dev.variations.controller.repository.internal.hibernate;

import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import biz.hahamo.dev.variations.controller.repository.PersistenceQuery;

@SuppressWarnings({"rawtypes", "unchecked"})
public class HibernateQueryCreatorImpl implements HibernateQueryCreator
{
    private Map<Class, HibernateQueryCreator> queryCreators;

    public void setQueryCreators(final Map<Class, HibernateQueryCreator> queryCreators)
    {
        this.queryCreators = queryCreators;
    }

    public Query createQuery(Session session, PersistenceQuery persistenceQuery)
    {
        for (Class persistenceQueryClass : queryCreators.keySet())
        {
            if (persistenceQueryClass.isInstance(persistenceQuery))
            {
                return queryCreators.get(persistenceQueryClass).createQuery(session, persistenceQuery);
            }
        }
        throw new RuntimeException("No QueryCreator for PersistenceQuery found: " + persistenceQuery);
    }

}
