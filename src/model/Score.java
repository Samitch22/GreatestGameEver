/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player's score.
 * @author Mitchell
 * @author Ryan
 */
public class Score {
    
    private       int        currentScore;
    private       Integer    highScore;
    private final List<Word> foundWords;
    private final int        pointValue = 1000;
    private final int        baseMulti = 15;
    private final String     hsFile = "highscore.txt";
    private       File           file;
    private       BufferedReader reader;
    
    //private       BufferedWriter writer;
    private PrintWriter writer;
    private FileInputStream in;
    private FileOutputStream out;

    /**
     * Constructs a score holding current score and highscore.
     */
    public Score() {
        foundWords = new ArrayList<>();
        currentScore = 0;
        highScore = 0;
        //this.readHighscore(); // Read the high score. Save high score
    }
    
    /**
     * Calculates the points
     */
    private void calculatePoints() {
        currentScore = 0;
        for ( Word word : foundWords ) {
            currentScore += pointValue *( baseMulti - word.toString().length() );
        }
        if ( currentScore > highScore ) {
            //setHighScore(currentScore);
            highScore = currentScore;
        }
    }
    
    /**
     * Gets the current score achieved.
     * @return
     */
    public int getCurrentScore() {
        this.calculatePoints();
        return currentScore;
    }

    /**
     * Gets the current highscore.
     * @return
     */
    public int getHighScore() {
        this.calculatePoints();
        return highScore;
    }
    
    /**
     * Gets the file name.
     */
    private String getFileName() {
        return this.hsFile;
    }
    
    /**
     * Adds a word to the found word list.
     * @param w
     */
    public void addFoundWord(Word w) {
        foundWords.add(w);
    }

    /**
     * Sets the highscore;
     * @param highScore
     */
    public void setHighScore(int highScore) {
        if ( highScore > this.highScore )
        this.highScore = highScore;
    }
    
    /**
     * Gets the buffered reader.
     */
    private BufferedReader getReader() {
        try {
            reader = new BufferedReader(new FileReader(getFileName())); 
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file.");
        }
        return reader;
    }
    
    /**
     * Gets the buffered writer.
     */
    //private BufferedWriter getWriter() {
    private PrintWriter getWriter() {
        try {
            //writer = new BufferedWriter(new FileWriter(getFileName()));
            writer = new PrintWriter(new FileWriter(getFileName()));
        } catch (IOException ex) {
            System.out.println("Error opening the file.");
        }
        return writer;
    }
    
    /**
     * Loads the file, setting the high score.
     */
    private void readHighscore() {
        
        file = new File(hsFile);
        String line;
        Integer noHighscore = 0;

        try {
        // If the file exists, get it
            if ( Files.exists(Paths.get(file.toString()))) {
                getReader();
                line = reader.readLine();
                if ( line.trim() != null )
                    setHighScore(Integer.parseInt(line.trim()));
                else
                    line = "" + noHighscore.toString();
                reader.close();
            }
            else {
                file.createNewFile();
                getWriter();
                writer.write(noHighscore);
                writer.close();
            }
        } catch (IOException ex) {
                System.out.println("Problem creating the file.");
        }
    }
    
    /**
     * Saves the highscore.
     */
    public void saveHighscore() {
        getWriter();
        file = new File(hsFile);
//        try {
            writer.write(highScore.toString());
            writer.close();
//        } catch (IOException ex) {
//            System.out.println("Problem writing the file.");
//        }
    }
}
