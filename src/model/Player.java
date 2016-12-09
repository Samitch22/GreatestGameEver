/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class represents a player with a score.
 * @author Mitchell
 * @author Ryan
 */
public class Player implements Serializable {

    private Score score ;
    
    /**
     * Constructs a player with a score.
     * @throws java.io.IOException
     */
    public Player() throws IOException {
        score = new Score();
    }

    /**
     * Gets the player's score.
     * @return 
     */
    public Score getScore() {
//        return score;
        return null;
   }
//
    public void setScore(Score score) {
        this.score = score;
    }
    
}
