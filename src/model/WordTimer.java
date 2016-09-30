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
 * @author Sam
 */
public class WordTimer {
    
    private final Timer     timer;
    private       TimerTask task;
    private final long      time = 3000;
    
    /**
     * 
     */
    public WordTimer() {
        timer = new Timer();
        task = new GameTask();
    }
    
    /**
     * Inner class to implement the required TimerTask functionality.
     */
    private class GameTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Time's up!");
            resetTimer(); //Terminate the timer thread
            getNewTargetWord();
            startTimer();
        }
    }
    
    /**
     * 
     * @todo
     * @return
     */
    public Word getNewTargetWord() {
        // TODO
        return null;
    }
    
    /**
     * @todo
     */
    protected void resetTimer() {
        timer.cancel();
        timer.purge();
    }
    
    /**
     * @todo
     */
    protected void startTimer() {
        timer.schedule(task, time);
    }
}
