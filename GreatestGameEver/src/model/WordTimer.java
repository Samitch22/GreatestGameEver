/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.util.Timer;

/**
 * @todo
 * @author Mitchell
 */
public class WordTimer {
    
    private Timer timer;
    private final long time = 30;
    
    /**
     * @todo
     * @return
     */
    public Word getNewTargetWord() {
        // TODO
        return null;
    }
    
    /**
     * @todo
     * @return
     */
    public long getTimeRemaining() {
        // TODO
        return -1;
    }
    
    /**
     * @todo
     */
    private void resetTimer() {
        timer.cancel();
        startTimer();
    }
    
    /**
     * @todo
     */
    private void startTimer() {
        // TODO
        
    }
}
