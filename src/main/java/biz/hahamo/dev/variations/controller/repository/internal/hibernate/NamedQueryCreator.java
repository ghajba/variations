package biz.hahamo.dev.variations.controller.repository.internal.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import biz.hahamo.dev.variations.controller.repository.NamedPersistenceQuery;

public class NamedQueryCreator implements HibernateQueryCreator<NamedPersistenceQuery>
{
    public Query createQuery(Session session, NamedPersistenceQuery persistenceQuery)
    {
        return session.getNamedQuery(
            persistenceQuery.getQueryName()).setProperties(persistenceQuery.getParameters());
    }

}
