package biz.hahamo.dev.variations.controller.repository.internal.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import biz.hahamo.dev.variations.controller.repository.JpqlPersistenceQuery;

public class JqplQueryCreator implements HibernateQueryCreator<JpqlPersistenceQuery>
{

    public Query createQuery(Session session, JpqlPersistenceQuery persistenceQuery)
    {
        return (Query) session.createQuery(persistenceQuery.getJpqlQuery()).setProperties(
            persistenceQuery.getParameters());
    }

}
