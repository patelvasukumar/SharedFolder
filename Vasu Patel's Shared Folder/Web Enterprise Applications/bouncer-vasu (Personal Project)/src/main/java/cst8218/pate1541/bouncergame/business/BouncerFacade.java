/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncergame.business;
import cst8218.pate1541.bouncergame.entity.Bouncer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


/**
 * Manages database operations for Bouncer entities.
 * It reuses the common methods provided by AbstractFacade.
 */
@Stateless  // Marks this class as a stateless session bean.
public class BouncerFacade extends AbstractFacade<Bouncer> {

    // Injects the EntityManager to handle database operations.
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    
    public BouncerFacade() {
        super(Bouncer.class);
    }

     /**
     * Returns the EntityManager used for database operations.
     *
     * @return the EntityManager for Bouncer entities
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}