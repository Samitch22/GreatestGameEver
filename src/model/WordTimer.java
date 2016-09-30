/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @todo
 * @author Mitchell
 */
public class WordTimer {
    
    private Timer timer;
    private TimerTask task;
    private final long time = 3000;
    
    /**
     * 
     */
    public WordTimer() {
        timer = new Timer();
        task = new Board();
    }
    
    /**
     * @todo
     * @return
     */
    public long getTimeRemaining() {
        // TODO
        // I don't believe this will be possible
        return -1;
    }
    
    /**
     * @todo
     */
    protected void resetTimer() {
        timer.cancel();
        timer.purge();
        startTimer();
    }
    
    /**
     * @todo
     */
    private void startTimer() {
        // TODO
        timer.schedule(task, time);
    }
}
