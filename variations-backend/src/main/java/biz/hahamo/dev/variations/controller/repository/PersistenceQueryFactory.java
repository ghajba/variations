package biz.hahamo.dev.variations.controller.repository;

import java.util.Map;

public interface PersistenceQueryFactory
{
    PersistenceQuery createQuery(String name, final Object... keyValuePairs);
    PersistenceQuery createQuery(String name, final Map<String, Object> keyValueMap);
    PersistenceQuery createJpqlQuery(String name, String jpqlQuery, final Object... keyValuePairs);
    PersistenceQuery createSqlQuery(String query, final Object... keyValuePairs);

}
