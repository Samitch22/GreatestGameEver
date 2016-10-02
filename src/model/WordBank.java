/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @todo
 * @author Mitchell
 * @author Ryan
 */
public class WordBank {
    
    private final List<Word>     wordBank;
    private final Score          score;
    private final int            numWords;
    private final List<String>   lists;
    private final WordFactory    factory;
    private final WordValidator  validator;
    private       BufferedReader reader = null;
    private       Word           targetWord;
    private       String         wordList;
    private       boolean        gameover;

    /**
     * Constructs a word bank that contains a list of words from a word list.
     * @param s
     */
    public WordBank(Score s) {
        wordBank = new ArrayList<>();
        lists = new ArrayList<>();
        factory = new WordFactory();
        validator = new WordValidator(this);
        setWordList();
        numWords = calcWords();
        score = s;
        gameover = false;
    }
    
    /**
     * Gets the target word.
     * @return
     */
    public Word getTargetWord() {
        return targetWord;
    }

    /**
     * Sets the target word.
     * @param targetWord
     */
    public void setTargetWord(Word targetWord) {
        this.targetWord = targetWord;
    }
    
    /**
     * Gets the remaining number of words.
     * @return
     */
    public int getRemainingWords() {
        return wordBank.size();
    }
    
    /**
     * This method implements the logic for after a word is found.
     * @param w
     * @return 
     */
    public boolean foundWord(Word w) {
//        boolean isFound = false;
//        if ( this.getTargetWord().equals(w) ) {
//            int index = wordBank.indexOf(targetWord);
//            score.addFoundWord(targetWord);
//            wordBank.remove(index);
//            if(wordBank.isEmpty()){
//                this.gameOver();
//                isFound = true;
//            }
//            else{
//                this.getNewTargetWord();   
//                isFound = true;
//            }
//        }
//        return isFound;
        
        return validator.foundWord(w);
    }
    
    /**
     * Adds the words from the list to the word bank.
     * @throws IOException
     */
    public void createWordBank() throws IOException {
        String line;
        Word newWord;
        getReader();
        
        while ( (line = reader.readLine()) != null ) {
            newWord = factory.makeWord(line);
            if ( newWord != null )
                wordBank.add(newWord);
        }
        this.setTargetWord(this.getRandomWord());
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
        targetWord.addAttempt();
        this.setTargetWord(this.getRandomWord());
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
        this.setGameover(true);
        score.calculatePoints();
    }

    public Score getScore() {
        return score;
    }

    /**
     * Returns if the game is over or not.
     * @return
     */
    public boolean isGameover() {
        return gameover;
    }

    /**
     * Sets if the game is over.
     * @param gameover
     */
    private void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    /**
     * Gets a random word from the list.
     */
    private Word getRandomWord() {
        Random newTarget = new Random();
        Word returnWord = wordBank.get(newTarget.nextInt(wordBank.size()));
        return returnWord;
    }
    
    /**
     * Calculates the number of words in the word bank.
     */
    private int calcWords() {
        return wordBank.size();
    }
    
    /**
     * Gets the number of words in the word bank.
     * @return
     */
    public int getNumWords() {
        return numWords;
    }

    /**
     * Gets the name of the word list file.
     * @return
     */
    public String getWordList() {
        return wordList;
    }

    /**
     * Sets a random word list as the list of words for the word bank.
     */
    private void setWordList() {
        String words;
        Random index = new Random();
        // Manually add any list of words
        lists.add("src/files/wordLists/gg.txt"); 
        lists.add("src/files/wordLists/pizza.txt");
        words = lists.get(index.nextInt(lists.size()));
        this.wordList = words;
        System.out.println("Got the words!");
    }

    /**
     * Gets the buffered reader for file I/O.
     * @return 
     */
    private BufferedReader getReader() {
        try {
            reader = new BufferedReader(new FileReader(getWordList())); 
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file.");
            System.exit(0);
        }
        return reader;
    }
}
