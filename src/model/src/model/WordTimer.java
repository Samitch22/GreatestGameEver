/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import controller.BoardController;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A timer used to get a new target word for the word search.
 * @author Mitchell
 * @author Sam
 */
public class WordTimer {
    
    private       Timer     timer;
    private       TimerTask task;
    private final long      timeMultiplier = 1000;
    private final long      time = 20 * timeMultiplier; // In seconds
    private final Board     board;
    
    /**
     * This class implements a timer to be used by the word search board.
     * @param board
     */
    public WordTimer(Board board) { // IF multiple timers are needed, use task as parameter instead of board.
        this.board = board;
    }
    
    /**
     * Cancels the timer.
     */
    public void cancelTimer() {
        timer.cancel();
    }
    
    /**
     * Schedules a task to be completed at the given time.
     */
    public void startTimer() {
        timer = new Timer();
        task = new BoardController();
        timer.scheduleAtFixedRate(task, time, time);
    }
}