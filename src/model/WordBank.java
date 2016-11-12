/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class implements the concept of a word bank for the word search.
 * @author Mitchell
 * @author Ryan
 */
public class WordBank {
    
    private final List<Word>     wordBank;
    private final List<Word>     distractionBank;
    private final List<String>   lists;
    private final Score          score;
    private final WordFactory    factory;
    private final WordValidator  validator;
    private       BufferedReader wordReader = null;
    private       BufferedReader distractionReader = null;
    private       String         wordList;
    private       String         distractionList;
    private       int            numWords;
    private       int            numDistractions;
    private       boolean        gameover;

    /**
     * Constructs a word bank that contains a list of words from a word list.
     * @param s
     */
    public WordBank(Score s) {
        wordBank = new ArrayList<>();
        distractionBank = new ArrayList<>();
        lists = new ArrayList<>();
        factory = new WordFactory();
        validator = new WordValidator(this);
        score = s;
        setWordList();
        numWords = 0;
        numDistractions = 0;
        gameover = false;
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
        
        return validator.foundWord(w);
    }
    
    /**
     * Adds the words from the list to the word bank.
     * @throws IOException
     */
    public void createWordBank() throws IOException  {
        String line;
        Word newWord;
        getWordReader();
        
        while ( (line = wordReader.readLine()) != null ) {
            newWord = factory.makeWord(line);
            if ( newWord != null )
                wordBank.add(newWord);
        }
        this.numWords = this.calcWords();
    }
    
    /**
     * Adds the words from the list to the word bank.
     * @throws IOException
     */
    public void createDistractionBank() throws IOException  {
        String line;
        Word newWord;
        getDistractionReader();
        
        while ( (line = distractionReader.readLine()) != null ) {
            newWord = factory.makeWord(line);
            if ( newWord != null )
                distractionBank.add(newWord);
        }
        this.numDistractions = this.calcDistractions();
    }
    
    /**
     * Returns the list of target words.
     * @return
     */
    public List<Word> getWordBank() {
        return wordBank;
    }
    
    /**
     * Returns the list of distraction words.
     * @return
     */
    public List<Word> getDistractionBank() {
        return distractionBank;
    }
    
    /**
     * Calls the game over functionality.
     */
    public void gameOver() {
        this.setGameover(true);
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
        Word returnWord = null;
        Random newTarget = new Random();
        if ( ! wordBank.isEmpty() ) {
            returnWord = wordBank.get(newTarget.nextInt(wordBank.size()));
        }
        return returnWord;
    }
    
    /**
     * Calculates the number of words in the word bank.
     */
    private int calcWords() {
        return wordBank.size();
    }
    
    /**
     * Calculates the number of words in the distraction bank.
     */
    private int calcDistractions() {
        return distractionBank.size();
    }
    
    /**
     * Gets the number of words in the word bank.
     * @return
     */
    public int getNumWords() {
        return numWords;
    }
    
    /**
     * Gets the number of words in the distraction bank.
     * @return
     */
    public int getNumDistractions() {
        return numDistractions;
    }

    /**
     * Gets the score associated with the word.
     * @return
     */
    public Score getScore() {
        return score;
    }

    /**
     * Gets the name of the word list file.
     * @return
     */
    public String getWordList() {
        return wordList;
    }
    
    /**
     * Gets the name of the distraction list file.
     * @return
     */
    public String getDistractionList() {
        return distractionList;
    }

    /**
     * Sets a random word list as the list of words for the word and distraction banks.
     */
    private void setWordList() {
        String words;
        Random index = new Random();
        // Manually add any list of words 
        lists.add("gg.txt"); 
        lists.add("pizza.txt");
        lists.add("PeriodicTable.txt");
        words = lists.get(index.nextInt(lists.size()));
        this.wordList = "/files/wordLists/" + words;
        this.distractionList = "/files/distractionLists/" + words;
        System.out.println("Got the words!");
    }

    /**
     * Gets the buffered reader for file I/O for the word list.
     * @return 
     */
    private BufferedReader getWordReader() {

        wordReader = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(wordList) ));

        return wordReader;
    }
    
    /**
     * Gets the buffered reader for file I/O for the distraction list.
     * @return 
     */
    private BufferedReader getDistractionReader() {

        distractionReader = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(distractionList) ));

        return distractionReader;
    }
}
