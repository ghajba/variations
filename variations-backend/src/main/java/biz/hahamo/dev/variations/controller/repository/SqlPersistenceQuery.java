package biz.hahamo.dev.variations.controller.repository;

import java.util.Collection;

import javax.persistence.SqlResultSetMapping;

import org.hibernate.type.Type;

/**
 * This query enables to send a plain SQL query to the repository.
 * 
 * @author GHajba
 */
public class SqlPersistenceQuery extends AbstractPersistenceQuery
{
    private final String sqlQuery;
    private final Collection<AliasToType> typeMapping;

    private final String sqlResultSetMapping;

    /**
     * This class enables to create a String alias for Hibernate Types
     * 
     * @author GHajba
     */
    public static class AliasToType
    {
        private final String alias;

        private final Type type;

        public static AliasToType create(final String alias, final Type type)
        {
            return new AliasToType(alias, type);
        }

        public String getAlias()
        {
            return alias;
        }

        public Type getType()
        {
            return type;
        }

        public AliasToType(final String alias, final Type type)
        {
            this.alias = alias;
            this.type = type;
        }
    }

    public SqlPersistenceQuery(String sqlQuery, Object... keyValuePairs)
    {
        this(sqlQuery, null, null, keyValuePairs);
    }
    
    public SqlPersistenceQuery(final String sqlQuery, final String sqlResultSetMapping, final Object... keyValuePairs)
    {
        this(sqlQuery, sqlResultSetMapping, null, keyValuePairs);
    }

    /**
     * Use this constructor only with Hibernate!
     * For JPA use {@link SqlResultSetMapping}
     * 
     * @param sqlQuery the SQL query
     * @param typeMapping the Hibernate type mapping
     * @param keyValuePairs the key-value pairs in the query
     */
    public SqlPersistenceQuery(final String sqlQuery, final Collection<AliasToType> typeMapping, final Object... keyValuePairs)
    {
        this(sqlQuery, null, typeMapping, keyValuePairs);
    }

    public String getSqlQuery()
    {
        return sqlQuery;
    }

    public Collection<AliasToType> getTypeMapping()
    {
        return typeMapping;
    }

    public String getSqlResultSetMapping()
    {
        return sqlResultSetMapping;
    }

    private SqlPersistenceQuery(final String sqlQuery, final String sqlResultSetMapping,
        final Collection<AliasToType> typeMapping, final Object... keyValuePairs)
    {
        super("sqlQuery", keyValuePairs);
        this.sqlQuery = sqlQuery;
        this.sqlResultSetMapping = sqlResultSetMapping;
        this.typeMapping = typeMapping;
    }

}
