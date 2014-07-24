package biz.hahamo.dev.variations.controller.repository;

import java.util.Map;

/**
 * With this class you can pass JPQL queries to the repository. This queries could be dynamically generated. 
 * @author GHajba
 *
 */
public class JpqlPersistenceQuery extends AbstractPersistenceQuery
{
    private final String jpqlQuery;

    /**
     * @param queryName the name of the query
     * @param jpqlQuery the query itself
     * @param keyValueMap the key-value map of the parameters
     */
    public JpqlPersistenceQuery(String queryName, String jpqlQuery, Map<String, Object> keyValueMap)
    {
        super(queryName, keyValueMap);
        this.jpqlQuery = jpqlQuery;
    }
    
    public JpqlPersistenceQuery(String queryName, String jpqlQuery, Object... keyValuePairs)
    {
        super(queryName, keyValuePairs);
        this.jpqlQuery = jpqlQuery;
    }

    public String getJpqlQuery()
    {
        return jpqlQuery;
    }

}
