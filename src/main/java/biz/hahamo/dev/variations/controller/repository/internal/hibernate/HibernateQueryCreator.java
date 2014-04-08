package biz.hahamo.dev.variations.controller.repository.internal.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import biz.hahamo.dev.variations.controller.repository.PersistenceQuery;

public interface HibernateQueryCreator<T extends PersistenceQuery>
{
    /**
     * @param session the session to create the query to
     * @param persistenceQuery the query to convert from generic persistence query to Hibernate Query
     * 
     * @return the converted query
     */
    Query createQuery(Session session, T persistenceQuery);

}
