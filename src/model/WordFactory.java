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
 * 
 * @author Mitchell
 */
public class WordFactory {

    private       BufferedReader wordReader = null;
    private       BufferedReader distractionReader = null;
    private final List<Word>     wordBank;
    private final List<Word>     distractionBank;
    private final List<String>   lists;
    private       String         wordList;
    private       String         distractionList;
    private       int            numWords;
    private       int            numDistractions;
    private static WordFactory   factory;
    
    public WordFactory() {
        wordBank = new ArrayList<>();
        distractionBank = new ArrayList<>();
        lists = new ArrayList<>();
        numWords = 0;
        numDistractions = 0;
    }
    
    public static WordFactory makeFactory() {
        factory = new WordFactory();
        return factory;
    }

    public static WordFactory getFactory() {
        return factory;
    }
    
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
     * 
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
    
    /**
     * Adds the words from the list to the word bank.
     * @throws IOException
     */
    public void createWordBank() throws IOException  {
        String line;
        Word newWord;
        getWordReader();
        
        while ( (line = wordReader.readLine()) != null ) {
            newWord = this.makeWord(line);
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
            newWord = this.makeWord(line);
            if ( newWord != null )
                distractionBank.add(newWord);
        }
        this.numDistractions = this.calcDistractions();
    }
    
    /**
     * Sets a random word list as the list of words for the word and distraction banks.
     */
    public void setWordList() {
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
     * Gets the number of distraction words in the list.
     * @return
     */
    public int getNumDistractions() {
        return numDistractions;
    }

    public List<Word> getWordBank() {
        return wordBank;
    }

    public List<Word> getDistractionBank() {
        return distractionBank;
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
