/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import controller.BoardController;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 * A timer used to get a new target word for the word search.
 * @author Mitchell
 * @author Sam
 * @author Ryan
 */
public class WordTimer {
    
    private       Timer     timer;
    private       TimerTask task;
    private final long      timeMultiplier = 1000; // In seconds
    private final long      time = 20 * timeMultiplier; 
    private final long      endtime = 180 * timeMultiplier;
    
    /**
     * This class implements a timer to be used by the word search board.
     * 
     */
    public WordTimer() {}
    
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
    /**
     * 
     * @param bc
     */
    public void startEndTimer(BoardController bc) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run () {
                Platform.runLater(() -> {
                    bc.gameOver();
                    System.out.println("Time's up!");
                });
            }
        }, endtime);
    }

}
