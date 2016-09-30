/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @todo
 * @author Mitchell
 * @author Ryan
 */
public class WordBank {
    
    private List<Word> wordBank;
    private Word       targetWord;

    /**
     *
     */
    public WordBank() {
        wordBank = new ArrayList<>();
    }
    
    /**
     *
     * @return
     */
    public Word getTargetWord() {
        return targetWord;
    }

    /**
     *
     * @param targetWord
     */
    public void setTargetWord(Word targetWord) {
        this.targetWord = targetWord;
    }
    
    /**
     * @todo
     * @return
     */
    public int getRemainingWords() {
        return wordBank.size();
    }
    
    /**
     * @todo
     * @return
     */
    public void foundWord() {
        targetWord.KeepScore();
        int index = wordBank.indexOf(targetWord);
        wordBank.remove(index);
        if(wordBank.isEmpty()){
            this.gameOver();
        }
        else{
            this.getNewTargetWord();   
        }
    }
    
    /**
     * @todo
     * @return
     */
    public List<Word> getWordBank() {
        //todo
        return null;
    }
    
    /**
     * @todo
     */
    private void setNextTargetWord() {
        Random newTarget = new Random();
        targetWord.addAttempt();
        targetWord = wordBank.get(newTarget.nextInt(wordBank.size()-1));
        this.setTargetWord(targetWord);
    }
    
    public Word getNewTargetWord() {
        setNextTargetWord();
        return getTargetWord();
    }
    
    /**
     * @todo
     */
    public void gameOver() {
        // todo
    }
}
