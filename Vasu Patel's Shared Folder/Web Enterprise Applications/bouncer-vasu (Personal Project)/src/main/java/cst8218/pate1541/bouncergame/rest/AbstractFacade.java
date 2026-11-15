/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncergame.rest;
import jakarta.persistence.EntityManager;
import java.util.List;



/**
 * Provides common database operations for different entity types.
 *
 * @param <T> the type of entity managed by this class
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    
    
    /**
     * Creates the facade for the given entity class.
     *
     * @param entityClass the class of the entity
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    
    
     /**
     * Provides the EntityManager for database operations.
     *
     * @return the EntityManager
     */
    protected abstract EntityManager getEntityManager();

    
    
    /**
     * Saves a new entity in the database.
     *
     * @param entity the entity to save
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    
    
    /**
     * Updates an existing entity in the database.
     *
     * @param entity the entity to update
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    
    
    /**
     * Removes an entity from the database.
     *
     * @param entity the entity to remove
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    
    
     /**
     * Finds an entity using its primary key.
     *
     * @param id the primary key of the entity
     * @return the matching entity, or null if not found
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    
    
    /**
     * Retrieves all entities of this type from the database.
     *
     * @return a list of all entities
     */
    public List<T> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    
    
    /**
     * Retrieves entities within the selected range.
     *
     * @param range the starting and ending positions
     * @return a list of entities within the range
     */
    public List<T> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    
    
    /**
     * Counts the total number of entities in the database.
     *
     * @return the total number of entities
     */
    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
