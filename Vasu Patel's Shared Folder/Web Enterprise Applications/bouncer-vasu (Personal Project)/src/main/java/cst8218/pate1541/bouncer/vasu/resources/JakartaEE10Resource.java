/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncer.vasu.resources;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


/**
 * Provides a REST resource to check if the application is running.
 */
@Path("jakartaee10") // // Sets the URL path for this REST resource.
public class JakartaEE10Resource {
    
    /**
     * Responds to GET requests with a ping message.
     *
     * @return an HTTP response containing the ping message.
     */
    @GET // Allows this method to handle HTTP GET requests.
    public Response ping(){
        return Response.ok("ping Jakarta EE").build();
    }

}
