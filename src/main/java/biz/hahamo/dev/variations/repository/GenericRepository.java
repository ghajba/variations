package biz.hahamo.dev.variations.repository;

import java.io.Serializable;
import java.util.List;

import javax.management.Query;

/**
 * A simple generic repository for loading and saving the data.
 * 
 * The idea is that the repository is generic, and not bound to a specific type -- which could be a possibility too:
 * i.e. GenericRepository<T> -- here you could provide a Type and the repository would only query data with this Type.
 * 
 * The main goal is that in this case you could provide specific implementations (Hibernate, JPA, etc.) for this main, generic repository.
 * 
 * @author GHajba
 *
 */
public interface GenericRepository
{
    /**
     * @param clazz the class in the database
     * @param primaryKey the primary key of the database
     * 
     * @return the class found in the database, null if nothing with this primary key was found
     */
    <T> T find(Class<T> clazz, Serializable primaryKey);
    
    /**
     * Initializes possibly lazy-loaded entities (and collections).
     * @param proxy proxy for an entity
     */
    void initialize(Object proxy);

    /**
     * @param entity to be saved
     */
    void save(Object entity);

    /**
     * @param entity to remove
     */
    void remove(Object entity);

    /**
     * @param entity to attach to the session
     */
    void attach(Object entity);

    /**
     * @param entity to detach from the session
     */
    void detach(Object entity);

    /**
     * flushes all pending updates and deletes from the session to the database
     */
    void flush();

    /**
     * 
     * @param query
     * @return
     */
    <T> T findByQuery(Query query);

    /**
     * @param query 
     * @return
     */
    <T> List<T> findListByQuery(Query query);

    /**
     * Execute operation for mass-updates or mass-deletes
     * @param query the query to execute
     * @return the size of the changed datasets
     */
    int execute(Query query);

}
