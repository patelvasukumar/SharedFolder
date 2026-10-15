package Model.Exception;

/**
 * Custom exception class for handling game-related errors.
 */
public class GameException extends Exception {
    
	/** 
	 * Serialization ID 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a GameException with a specific error message.
     *
     * @param message error message describing the exception
     */
	public GameException(String message) {
        super(message);
    }

	/**
     * Throws a GameException with the given message.
     *
     * @param message error message
     * @throws GameException always thrown when this method is called
     */
    public static void raise(String message) throws GameException {
        throw new GameException(message);
    }
    
}