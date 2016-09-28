/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * @todo
 * @author Mitchell
 */
public class WordFactory {

    /**
     * @param word
     * @todo
     * @return
     */
    public static Word makeWord(String word){
        Word newWord = new Word(word);
        if ( validateWord(newWord.toString()) == false )
            return null;
        return newWord;
    }
    
    /**
     * @todo
     */
    private void addToWordBank() {
        // TODO
    }
    
    /**
     * @todo
     */
    private void createWordList() {
        // TODO
    }
    
    /**
     * @todo
     */
    private static boolean validateWord(String word) {
        // TODO
        // Check word is not larger than size of board
        if ( word.length() > Board.getBoardSize() )
            return false;
        return true;
    }
}
