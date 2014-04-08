package biz.hahamo.dev.variations.controller.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Abstract named query implementation
 * 
 * @author GHajba
 */
public class AbstractPersistenceQuery implements PersistenceQuery
{
    private final String queryName;
    private final Map<String, Object> keyValueMap;

    /**
     * Simple constructor
     * 
     * @param queryName the name of the query or a SQL / HQL query string
     * @param keyValueMap the key-value map
     */
    public AbstractPersistenceQuery(String queryName, Map<String, Object> keyValueMap)
    {
        if (queryName == null || queryName.trim().length() <= 0)
        {
            throw new IllegalArgumentException("name of PersistenceQuery not set");
        }
        this.queryName = queryName;

        if (keyValueMap == null)
        {
            this.keyValueMap = Collections.emptyMap();
        }
        else
        {
            this.keyValueMap = keyValueMap;
        }
    }

    /**
     * Constructor
     * @param queryName the name of the query or a SQL / HQL query string
     * @param keyValuePairs vararg representation of the key-value map, will be converted
     */
    public AbstractPersistenceQuery(final String queryName, final Object... keyValuePairs)
    {
        if (queryName == null || queryName.trim().length() <= 0)
        {
            throw new IllegalArgumentException("name of NamedQuery not set");
        }

        this.queryName = queryName;
        if (keyValuePairs == null || keyValuePairs.length <= 0)
        {
            keyValueMap = Collections.emptyMap();
        }
        else
        {
            if ((keyValuePairs.length % 2) != 0)
            {
                throw new IllegalArgumentException("number of keys and values is not equal: " + keyValuePairs.length);
            }

            keyValueMap = new HashMap<String, Object>(keyValuePairs.length / 2);

            int i = 0;

            while (i < keyValuePairs.length)
            {
                final Object key = keyValuePairs[i++];

                final Object data = keyValuePairs[i++];

                if (key == null)
                {
                    continue;
                }

                keyValueMap.put(key.toString(), data);
            }
        }
    }

    public Map<String, Object> getParameters()
    {
        return keyValueMap;
    }

    public String getQueryName()
    {
        return queryName;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this).append("queryName", queryName).append("keyValueMap", keyValueMap).toString();
    }

}
