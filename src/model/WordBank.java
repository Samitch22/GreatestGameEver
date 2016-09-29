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
 */
public class WordBank {
    
    private List<Word> wordBank;
    private Word       targetWord;

    public Word gettargetWord(){
        return targetWord;
    }
    
    public void settargetWord(Word target){
        targetWord = target;
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
