/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * This class represents a player with a score.
 * @author Mitchell
 * @author Ryan
 */
public class Player {

    private final Score score;
    
    /**
     * Constructs a player with a score.
     */
    public Player() {
        score = new Score();
    }

    /**
     * Gets the player's score.
     * @return 
     */
    public Score getScore() {
        return score;
    }
    
}
