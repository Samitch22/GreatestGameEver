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
    private Score      score;

    /**
     *
     */
    public WordBank(Score s) {
        wordBank = new ArrayList<>();
        score = s;
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
     * 
     * @return
     */
    public void foundWord() {
        int index = wordBank.indexOf(targetWord);
        score.addFoundWord(targetWord);
        wordBank.remove(index);
        if(wordBank.isEmpty()){
            this.gameOver();
        }
        else{
            this.getNewTargetWord();   
        }
    }
    
    /**
     * Returns the list of target words.
     * @return
     */
    public List<Word> getWordBank() {
        return wordBank;
    }
    
    /**
     * Sets a new target word.
     * @todo
     */
    private void setNextTargetWord() {
        Random newTarget = new Random();
        targetWord.addAttempt();
        targetWord = wordBank.get(newTarget.nextInt(wordBank.size()-1));
        this.setTargetWord(targetWord);
    }
    
    /**
     * Returns a new target word.
     * @return
     */
    public Word getNewTargetWord() {
        setNextTargetWord();
        return getTargetWord();
    }
    
    /**
     * @todo
     */
    public void gameOver() {
        score.calculatePoints();
    }
}
