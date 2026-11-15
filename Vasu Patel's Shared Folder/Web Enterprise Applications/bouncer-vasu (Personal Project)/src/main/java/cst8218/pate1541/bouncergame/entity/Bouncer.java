/*
Student Name: Vasukumar Patel
Sutdent Number: 041153940
Assignment - 1 : Bouncer Game [Animation not implemented]
*/

package cst8218.pate1541.bouncergame.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;


/**
  Represents a Bouncer object and stores its position,
  speed, size, and movement behaviour.
 */
@Entity // Marks this class as a database entity.
public class Bouncer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.AUTO)  // Generates the ID automatically. 
    private Long id;

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 500;
    public static final int INITIAL_SIZE = 30;
    public static final int MAX_SPEED = 10;
    public static final int GRAVITY_ACCEL = 1;
    public static final int DECAY_RATE = 1;

    
    @Min(0) // Sets the minimum allowed value to 0.
    @Max(FRAME_WIDTH) // Sets the maximum allowed value to the frame width.
    private Integer x;

   
    @Min(0) // Sets the minimum allowed value to 0.
    @Max(FRAME_HEIGHT) // Sets the maximum allowed value to the frame height.
    private Integer y;

   
    @Min(-MAX_SPEED) // Sets the minimum allowed speed.
    @Max(MAX_SPEED) // Sets the maximum allowed speed.
    private Integer ySpeed;

  
    @Min(1) // Ensures the size is at least 1.
    private Integer size;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getX() { return x; }
    public void setX(Integer x) { this.x = x; }

    public Integer getY() { return y; }
    public void setY(Integer y) { this.y = y; }

    public Integer getySpeed() { return ySpeed; }
    public void setySpeed(Integer ySpeed) { this.ySpeed = ySpeed; }

    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }


/**
      Moves the Bouncer one time step using gravity.
      It changes direction when it reaches the top or bottom wall.
*/
    public void timeStep() {

        // Do nothing if the position or speed has not been provided.
        if (y == null || ySpeed == null) {
            return;
        }

        // Keep the Bouncer stationary if it has stopped at the bottom.
        if (y == FRAME_HEIGHT && ySpeed == 0) {
            return;
        }

        // Gravity increases the downward speed.
        ySpeed = ySpeed + GRAVITY_ACCEL;

        // Keep the speed within its allowed range.
        if (ySpeed > MAX_SPEED) {
            ySpeed = MAX_SPEED;
        }

        if (ySpeed < -MAX_SPEED) {
            ySpeed = -MAX_SPEED;
        }

        // Update the vertical position using the current speed.
        y = y + ySpeed;

        // Check whether the Bouncer has reached the bottom wall.
        if (y >= FRAME_HEIGHT) {
        
            y = FRAME_HEIGHT;

            // Reverse the direction and reduce the speed.
            if (ySpeed > 0) {
                ySpeed = -Math.max(0, ySpeed - DECAY_RATE);
            }
    
        }

        // Check whether the Bouncer has reached the top wall.
        else if (y <= 0) {
        y = 0;

            // Reverse the direction and reduce the speed.
            if (ySpeed < 0) {
                ySpeed = Math.max(0, -ySpeed - DECAY_RATE);
            }
        }
    
    }


    /**
    
        Calculates a hash code using the Bouncer's ID.
     
        @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    
    
    /** 
        Checks whether this Bouncer is equal to another object.
     
        @param object the object to compare
        @return true if the objects have matching IDs, otherwise false
     */
    @Override
    public boolean equals(Object object) {
        
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bouncer)) {
            return false;
        }
        
        Bouncer other = (Bouncer) object;
        
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        
        return true;
    
    }

    @Override
    public String toString() {
        return "cst8218.pate1541.bouncergame.entity.Bouncer[ id=" + id + " ]";
    }
    
}
