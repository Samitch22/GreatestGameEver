/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.Serializable;

/**
 * @todo
 * @author Mitchell
 */
public class WordFactory implements Serializable {

    /**
     * Method to create a new word, based on the factory functionality.
     * @param word
     * @return
     */
    public Word makeWord(String word){
        Word newWord = new Word(word.toUpperCase());
        if ( validateWord(newWord.toString()) == false )
            return null;
        return newWord;
    }
    
    /**
     * Validates that the word will fit on the board.
     * @todo
     */
    private boolean validateWord(String word) {
        // Check word is not larger than size of board
        int minSize = 4;
        
        if ( word.length() > Board.getBoardSize() )
            return false;
        if ( word.length() < minSize )
            return false;
        
        return true;
    }
}
