/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncergame.rest;
import jakarta.ws.rs.HttpMethod;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Defines a custom annotation for handling HTTP PATCH requests.
 */
@Target(ElementType.METHOD) // Allows this annotation to be used on methods.
@Retention(RetentionPolicy.RUNTIME) // Makes the annotation available at runtime.
@HttpMethod("PATCH") // Registers this annotation as an HTTP PATCH method.
public @interface PATCH {

}