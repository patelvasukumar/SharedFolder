/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncergame.game;
import cst8218.pate1541.bouncergame.business.BouncerFacade;
import cst8218.pate1541.bouncergame.entity.Bouncer;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.util.List;


/**
 * Updates the Bouncer simulation automatically.
 * The application starts this class when it is deployed.
 */
@Singleton  // Allows only one instance of this class in the application.
@Startup    // Starts this class automatically when the application starts.
public class AdvanceTime {

     // Injects the BouncerFacade to access Bouncer database operations.
    @EJB
    private BouncerFacade bouncerFacade;

     /**
     * Updates every Bouncer once per second and saves its new state.
     */
    @Schedule(
        hour = "*",
        minute = "*",
        second = "*",
        persistent = false
    ) // Runs this method every second without saving the timer schedule.
    
    public void simulateTimeStep() {

        // Retrieve all Bouncers through the facade.
        List<Bouncer> bouncers = bouncerFacade.findAll();

        // Advance each Bouncer by one time step.
        for (Bouncer bouncer : bouncers) {
            bouncer.timeStep();

            // Save the updated Bouncer through the facade.
            bouncerFacade.edit(bouncer);
        }
    
    }

}