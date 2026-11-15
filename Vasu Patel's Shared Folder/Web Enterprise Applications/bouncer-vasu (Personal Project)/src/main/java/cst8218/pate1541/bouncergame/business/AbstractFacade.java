/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncergame.business;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;



/**
 * Provides common database operations for different entity types.
 * Other facade classes can reuse these methods.
 *
 * @param <T> the type of entity managed by this facade
 */
public abstract class AbstractFacade<T> {

    
    private Class<T> entityClass;

    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    
    protected abstract EntityManager getEntityManager();

    
    /** Saves a new entity in the database. */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    
    /** Updates an existing entity in the database. */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    
    /** Removes an entity from the database. */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
     
    
    /** Finds an entity using its primary key. */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
  
    
    /** Retrieves all entities of this type. */
    public List<T> findAll() {
        
        TypedQuery<T> query = getEntityManager().createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e",entityClass);

        return query.getResultList();
    
    }

    
    /** Retrieves a selected range of entities. */
    public List<T> findRange(int[] range) {
        
        TypedQuery<T> query = getEntityManager().createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e",entityClass);

        query.setFirstResult(range[0]);
        query.setMaxResults(range[1] - range[0] + 1);

        return query.getResultList();
    
    }

   
    /** Returns the number of entities stored in the database. */
    public int count() {
        
        TypedQuery<Long> query = getEntityManager().createQuery(
                "SELECT COUNT(e) FROM "
                + entityClass.getSimpleName() + " e",
                Long.class);

        return query.getSingleResult().intValue();
    
    }

}