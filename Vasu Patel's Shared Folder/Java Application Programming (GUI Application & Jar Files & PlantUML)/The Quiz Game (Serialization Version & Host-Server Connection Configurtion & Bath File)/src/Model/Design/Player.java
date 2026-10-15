package Model.Design;

import java.io.Serializable;

/**
 * Represents a player in the game.
 * Stores personal details and score information.
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 
     * Unique ID of the player 
     */
    private int id;

    /** 
     * Name of the player 
     */
    private String name;

    /** 
     * Gender of the player 
     */
    private String gender;

    /** 
     * Nationality of the player
     */
    private String nationality;

    /** Current points in the ongoing game */
    private transient int currentPoint;

    /** 
     * Highest points achieved by the player 
     */
    private int historicPoint;

    /**
     * Constructs a Player with basic details.
     *
     * @param id unique player ID
     * @param name player name
     * @param gender player gender
     * @param nationality player nationality
     */
    public Player(int id, String name, String gender, String nationality) {
        this(id, name, gender, nationality, 0, 0);
    }

    /**
     * Constructs a Player with full details.
     *
     * @param id unique player ID
     * @param name player name
     * @param gender player gender
     * @param nationality player nationality
     * @param currentPoint current score of the player
     * @param historicPoint highest score achieved by the player
     */
    public Player(int id, String name, String gender, String nationality, int currentPoint, int historicPoint) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
        this.currentPoint = currentPoint;
        this.historicPoint = historicPoint;
    }

    /**
     * Gets the player ID.
     *
     * @return player ID
     */
    public int getId() { 
    	return id; 
    	}

    /**
     * Gets the player name.
     *
     * @return player name
     */
    public String getName() { 
    	return name; 
    	}

    /**
     * Gets the player gender.
     *
     * @return player gender
     */
    public String getGender() { 
    	return gender;
    	}

    /**
     * Gets the player nationality.
     *
     * @return player nationality
     */
    public String getNationality() {
    	return nationality; 
    	}

    /**
     * Gets the current score of the player.
     *
     * @return current points
     */
    public int getCurrentPoint() { 
    	return currentPoint; 
    	}

    /**
     * Gets the highest score achieved by the player.
     *
     * @return historic points
     */
    public int getHistoricPoint() {
    	return historicPoint;
    	}

    /**
     * Sets the player name.
     *
     * @param name new player name
     */
    public void setName(String name) { 
    	this.name = name;
    	}

    /**
     * Sets the player gender.
     *
     * @param gender new gender
     */
    public void setGender(String gender) {
    	this.gender = gender; 
    	}

    /**
     * Sets the player nationality.
     *
     * @param nationality new nationality
     */
    public void setNationality(String nationality) { 
    	this.nationality = nationality; 
    	}

    /**
     * Sets the current score of the player.
     *
     * @param currentPoint new current points
     */
    public void setCurrentPoint(int currentPoint) { 
    	this.currentPoint = currentPoint; 
    	}

    /**
     * Sets the highest score achieved by the player.
     *
     * @param historicPoint new historic points
     */
    public void setHistoricPoint(int historicPoint) { 
    	this.historicPoint = historicPoint; 
    	}
}