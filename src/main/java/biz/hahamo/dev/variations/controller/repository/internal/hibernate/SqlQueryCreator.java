package biz.hahamo.dev.variations.controller.repository.internal.hibernate;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import biz.hahamo.dev.variations.controller.repository.SqlPersistenceQuery;
import biz.hahamo.dev.variations.controller.repository.SqlPersistenceQuery.AliasToType;

public class SqlQueryCreator implements HibernateQueryCreator<SqlPersistenceQuery>
{

    public Query createQuery(Session session, SqlPersistenceQuery persistenceQuery)
    {
        SQLQuery query = session.createSQLQuery(persistenceQuery.getSqlQuery());

        query.setProperties(persistenceQuery.getParameters());

        if (persistenceQuery.getSqlResultSetMapping() != null)
        {
            query.setResultSetMapping(persistenceQuery.getSqlResultSetMapping());
        }
        else if (persistenceQuery.getTypeMapping() != null)
        {
            for (AliasToType mapping : persistenceQuery.getTypeMapping())
            {
                query.addScalar(mapping.getAlias(), mapping.getType());
            }
        }

        return query;
    }

}
