package biz.hahamo.dev.variations.controller.repository;

import java.util.Map;

/**
 * Class to use named queries -- for Hibernate. The names are looked up in the orm.xml files
 * 
 * @author GHajba
 *
 */
public class NamedPersistenceQuery extends AbstractPersistenceQuery
{

    public NamedPersistenceQuery(String queryName, Map<String, Object> keyValueMap)
    {
        super(queryName, keyValueMap);
    }
    
    public NamedPersistenceQuery(String queryName, Object... keyValuePairs)
    {
        super(queryName, keyValuePairs);
    }

}
