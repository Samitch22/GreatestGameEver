/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import controller.BoardController;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 * This class implements a timer to be used by the word search board.
 * @author Mitchell
 * @author Sam
 * @author Ryan
 */
public class WordTimer implements Serializable {
    
    private       Timer     timer;
    private       TimerTask task;
    private final long      timeMultiplier = 1000; // In seconds
    private final long      time = 21 * timeMultiplier; 
    private final long      endtime = 180 * timeMultiplier;
    private final long      jailTime = 5 * timeMultiplier;
    
    /**
     * Default constructor to create a new WordTimer.
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
     * Starts a timer to end the game after the time expires.
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

    /**
     * Starts a timer for putting a player in jail.
     * @param bc
     */
    public void startExplosionTimer(BoardController bc) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    bc.removeExplosion();
                });
            }
        } , jailTime );
    }
}
