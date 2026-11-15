/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncergame.rest;
import cst8218.pate1541.bouncergame.business.BouncerFacade;
import cst8218.pate1541.bouncergame.entity.Bouncer;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import jakarta.validation.Valid;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.Response.Status;

/**
 * Provides REST endpoints to retrieve Bouncer information.
 * Database operations are handled by BouncerFacade.
 */
@Stateless
@Path("bouncers")
public class BouncerFacadeREST {

    
    // Injects the facade used for database operations.
    @EJB
    private BouncerFacade bouncerFacade;

    
    
    
    /**
     * Returns all Bouncers stored in the database.
     *
     * @return HTTP 200 with the list of Bouncers
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(bouncerFacade.findAll()).build();
    }

    
    
    
    /**
     * Returns the Bouncer identified by the supplied ID.
     *
     * @param id the ID of the requested Bouncer
     * @return HTTP 200 with the Bouncer, or HTTP 404 if absent
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        Bouncer bouncer = bouncerFacade.find(id);

        if (bouncer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(bouncer).build();
    }

    
    
    
    
    /**
     * Returns the number of Bouncers stored in the database.
     *
     * @return HTTP 200 with the count as plain text
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {
        
        return Response.ok(String.valueOf(bouncerFacade.count()),MediaType.TEXT_PLAIN).build();
    
    }
    
    
    

    
    
    /**
 * Creates a Bouncer when the request does not provide an ID.
 *
 * @param entity the Bouncer supplied in the request body
 * @param uriInfo information used to create the new resource URL
 * @return HTTP 201 with the created Bouncer, or HTTP 400 for an invalid request
 */
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response create(@Valid Bouncer entity, @Context UriInfo uriInfo) {

    // The request body must contain a Bouncer.
    if (entity == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("A post must contain body").build();
    }

    // The client must not supply an ID when creating a Bouncer.
    if (entity.getId() != null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("A post method does not need id").build();
    }

    // Set defaults for properties omitted from the request.
    if (entity.getX() == null) {
        entity.setX(0);
    }

    if (entity.getY() == null) {
        entity.setY(0);
    }

    if (entity.getySpeed() == null) {
        entity.setySpeed(0);
    }

    if (entity.getSize() == null) {
        entity.setSize(Bouncer.INITIAL_SIZE);
    }

    // Save the new Bouncer through the business layer.
    bouncerFacade.create(entity);

    // Build the URL of the newly created Bouncer.
    URI location = uriInfo.getAbsolutePathBuilder().path(entity.getId().toString()).build();

    // Return HTTP 201 with the created Bouncer and its URL.
    return Response.created(location).entity(entity).build();

}
    



/**
 * Updates only the non-null properties supplied in the request.
 *
 * @param id the ID of the Bouncer to update
 * @param updates the Bouncer containing the requested changes
 * @return HTTP 200 with the updated Bouncer, or an appropriate error
 */
@PATCH
@Path("{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response patch(@PathParam("id") Long id,
                      @Valid Bouncer updates) {

    // Reject an empty request body.
    if (updates == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("an empty body").build();
    }

    // Find the existing Bouncer.
    Bouncer existing = bouncerFacade.find(id);

    // Return 404 if the requested Bouncer does not exist.
    if (existing == null) {
        return Response.status(Response.Status.NOT_FOUND).entity("404: bouncer not found").build();
    }

    // Reject a supplied ID if it does not match the URL.
    if (updates.getId() != null
            && !updates.getId().equals(id)) {
        return Response.status(Response.Status.BAD_REQUEST).entity("ID conflict").build();
    }

    // Change only the properties supplied in the request.
    if (updates.getX() != null) {
        existing.setX(updates.getX());
    }

    if (updates.getY() != null) {
        existing.setY(updates.getY());
    }

    if (updates.getySpeed() != null) {
        existing.setySpeed(updates.getySpeed());
    }

    if (updates.getSize() != null) {
        existing.setSize(updates.getSize());
    }

    // Save the updated Bouncer through the facade.
    bouncerFacade.edit(existing);

    // Return the updated Bouncer.
    return Response.ok(existing).build();
}
       



/*
put error while completeing replacing
*/
@PUT
public Response editAll(){

    return Response.status(Status.METHOD_NOT_ALLOWED).entity("PUT on root resource is not allowed.").build();
    
}


/**
 * Completely replaces an existing Bouncer with the supplied values.
 *
 * @param id the ID of the Bouncer to replace
 * @param replacement the new Bouncer information
 * @return HTTP 200 with the replaced Bouncer, or an appropriate error
 */
@PUT
@Path("{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response put(@PathParam("id") Long id, Bouncer replacement) {

    // Reject an empty request body.
    if (replacement == null) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Reject an empty body").build();
    }

    // Find the existing Bouncer.
    Bouncer existing = bouncerFacade.find(id);

    // Return 404 if the Bouncer does not exist.
    if (existing == null) {
        return Response.status(Response.Status.NOT_FOUND).entity("404 : Bouncer not found").build();
    }

    // Reject an ID that conflicts with the URL.
    if (replacement.getId() != null && !replacement.getId().equals(id)) {
        return Response.status(Response.Status.BAD_REQUEST).entity("ID conflict").build();
    }

    // Replace every property, using defaults when values are missing.
    existing.setX(replacement.getX() == null ? 0 : replacement.getX());

    existing.setY(replacement.getY() == null ? 0 : replacement.getY());

    existing.setySpeed(replacement.getySpeed() == null ? 0 : replacement.getySpeed());

    existing.setSize(replacement.getSize() == null ? Bouncer.INITIAL_SIZE : replacement.getSize());

    // Save the complete replacement through the facade.
    bouncerFacade.edit(existing);

    // Return the replaced Bouncer.
    return Response.ok(existing).build();
}



/**
 * Deletes the Bouncer identified by the supplied ID.
 *
 * @param id the ID of the Bouncer to delete
 * @return HTTP 204 if deleted, or HTTP 404 if not found
 */
@DELETE
@Path("{id}")
public Response delete(@PathParam("id") Long id) {

    // Find the Bouncer to delete.
    Bouncer bouncer = bouncerFacade.find(id);

    // Return 404 if the Bouncer does not exist.
    if (bouncer == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Delete the Bouncer through the business layer.
    bouncerFacade.remove(bouncer);

    // Return 204 to indicate success without a response body.
    return Response.noContent().build();

}
    






    
}