/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncergame.presentation;
import cst8218.pate1541.bouncergame.business.BouncerFacade;
import cst8218.pate1541.bouncergame.entity.Bouncer;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;


/**
 * Connects the JSF pages to the Bouncer business layer.
 * It handles creating, viewing, editing, and deleting Bouncers.
 */
@Named("bouncerController") // Gives this controller a name for use in JSF pages.
@SessionScoped // Keeps this controller's data for the current user session.
public class BouncerController implements Serializable {

    private static final long serialVersionUID = 1L;

     // Injects the BouncerFacade to access database operations.
    @EJB
    private BouncerFacade bouncerFacade;

    private Bouncer current;
    private List<Bouncer> items;

    
    /**
     * Returns the Bouncer currently being created or edited.
     */
    public Bouncer getCurrent() {
        return current;
    }

    
    /**
     * Sets the Bouncer currently being managed by the pages.
     */
    public void setCurrent(Bouncer current) {
        this.current = current;
    }

    
    /**
     * Retrieves all Bouncers for display on the list page.
     */
    public List<Bouncer> getItems() {
        // Reload from the database each time the list is rendered so refreshes
        // show the latest state written by the simulation timer.
        items = bouncerFacade.findAll();
        return items;
    }

    
    /**
     * Saves the new Bouncer and returns to the list page.
     */
    /**
     * Prepares a new Bouncer with initial values for the create form.
     */
    public String prepareCreate() {
        current = new Bouncer();
        current.setX(0);
        current.setY(0);
        current.setySpeed(0);
        current.setSize(Bouncer.INITIAL_SIZE);
        return "Create";
    }
    
    
    public String create() {
        bouncerFacade.create(current);
        current = null;
        items = null;

        return "List?faces-redirect=true";
    }

    
    /**
     * Selects an existing Bouncer for editing.
     */
    public String prepareEdit(Bouncer bouncer) {
        current = bouncer;
        return "Edit";
    }

    
    /**
     * Saves changes to the selected Bouncer.
     */
    public String update() {
        bouncerFacade.edit(current);
        current = null;
        items = null;

        return "List?faces-redirect=true";
    }

    
    /**
     * Selects an existing Bouncer for viewing.
     */
    public String prepareView(Bouncer bouncer) {
        current = bouncer;
        return "View";
    }

    
    /**
     * Deletes the selected Bouncer.
     */
    public String delete(Bouncer bouncer) {
        bouncerFacade.remove(bouncer);
        items = null;

        return "List?faces-redirect=true";
    }

}
