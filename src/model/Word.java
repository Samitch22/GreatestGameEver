/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.util.Objects;

/**
 * This class implements a word that is found in a word search.
 * @author Mitchell
 * @author Ryan
 */
public class Word implements Comparable<Word> {

    private final String name;
    //private       int    attempts;
    private final int    increment = 1;

    /**
     *
     * @param name
     */
    public Word(String name) {
        this.name = name;
        //this.attempts = 0;
    }
    
    /**
     * Increments the number of attempts made to solve This word.
     */
    /**public void addAttempt() {
        int currentAttempts = this.getAttempts();
        currentAttempts += increment;
        this.setAttempts(currentAttempts);
    }
    
    /**
     * Returns the name of the word.
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the number of attempts made to solve This word.
     * @return
     */
    /**public int getAttempts() {
        return attempts;
    }

    /**
     * Sets the number of attempts made to solve This word.
     * @param attempts
     */
    /**private void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Word o) {
        return this.getName().compareTo(o.getName());
    }
    
    @Override
    public String toString() {
        return "" + getName();
    }

}
