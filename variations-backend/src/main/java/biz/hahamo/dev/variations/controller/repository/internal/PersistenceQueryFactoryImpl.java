package biz.hahamo.dev.variations.controller.repository.internal;

import java.util.Map;

import biz.hahamo.dev.variations.controller.repository.JpqlPersistenceQuery;
import biz.hahamo.dev.variations.controller.repository.NamedPersistenceQuery;
import biz.hahamo.dev.variations.controller.repository.PersistenceQuery;
import biz.hahamo.dev.variations.controller.repository.PersistenceQueryFactory;
import biz.hahamo.dev.variations.controller.repository.SqlPersistenceQuery;

public class PersistenceQueryFactoryImpl implements PersistenceQueryFactory
{

    public PersistenceQuery createQuery(String name, Object... keyValuePairs)
    {
        return new NamedPersistenceQuery(name, keyValuePairs);
    }

    public PersistenceQuery createQuery(String name, Map<String, Object> keyValueMap)
    {
        return new NamedPersistenceQuery(name, keyValueMap);
    }

    public PersistenceQuery createJpqlQuery(String name, String jpqlQuery, Object... keyValuePairs)
    {
        return new JpqlPersistenceQuery(name, jpqlQuery, keyValuePairs);
    }

    public PersistenceQuery createSqlQuery(String query, Object... keyValuePairs)
    {
        return new SqlPersistenceQuery(query, keyValuePairs);
    }

}
