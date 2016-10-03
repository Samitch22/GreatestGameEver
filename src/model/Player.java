/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * @todo
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
