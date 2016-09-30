/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

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
            this.getNextWord();   
        }
    }
    
    /**
     * @todo
     * @return
     */
    public void createWordBank() {
        //todo
    }
    
    /**
     * @todo
     * @return
     */
    public Word getNextWord() {
        Random newTarget = new Random();
        targetWord.addAttempt();
        targetWord = wordBank.get(newTarget.nextInt(wordBank.size()-1));
        return targetWord;
    }
    
    /**
     * @todo
     */
    public void gameOver() {
        // todo
    }
}
