/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.IOException;
import java.util.Random;

/**
 * This class implements the concept of a board. It is an m X m matrix of 
 * characters,containing the concept of words placed inside.
 * @author Mitchell
 * @author Sam
 * @author Ryan
 */
public final class Board { 
    
    private static final int          boardSize = 14;
    private        final int          rSize;
    private        final int          cSize;
    private        final Object[][]   board;
    private        final WordTimer    timer;
    private        final WordBank     wordBank;
    private        final Player       player;
    private        final Object[][][] wordKeys;
    
    /**
     *
     * @param p
     * @throws java.io.IOException
     */
    public Board(Player p) throws IOException {
        this.rSize    = Board.getBoardSize();
        this.cSize    = Board.getBoardSize();
        this.timer    = new WordTimer();
        this.player   = p;
        this.wordBank = new WordBank(p.getScore());
        this.board    = new Character[getrSize()][getcSize()];
        this.wordKeys = new Object[getrSize()][getcSize()][wordBank.getNumWords()];
        createBoard();
    }
    
    /**
     * @throws java.io.IOException
     * @todo
     */
    public void createBoard() throws IOException {
        loadWordBank();
        int num = 0;
        
        // get first word in list
        // getRandomPoint
        // need to check that it fits and that there is not another letter in the slot
        // getRandomBackward
        // getRandomDirection --> direction still needs to have a switch statement somewhere
        // add the Word (String) as single CHARACTERS to the grid
        // when all of the words are added, check the entire matrix
        // --> if the point is null, add a randomLetter
        
        for ( int r = 0; r < Board.boardSize; r++ ) {
            for ( int c = 0; c < Board.boardSize; c++ )
            {
                board[r][c] = randomLetter();
            }
        }
        
        //Iterating through all the points in the list
        for ( int w = 0; w < this.wordBank.getWordBank().size(); w++ ) {
            
            //Grabs next word from the list
            Word currentWord = this.wordBank.getWordBank().get(w);
            
            //Checks if word will be backwords or not
            boolean isBackward = this.randomBackward();
            
            //Picks a starting direction to choose
            int direction = this.randomDirection();
            
            //Determines if a word will fit or not
            boolean wordFits = false;
            
            //Picks a random point on the board
            int r = this.randomPoint(); 
            int c = this.randomPoint();
            
            while(wordFits){
                //Picks a direction
                switch (direction) {
                    case 0: //Horizontal
                        if(currentWord.getName().length() + r < rSize){
                            wordFits = true;
                        }
                        else {
                            direction = (direction + 1) % 4;
                        }
                    case 1: //Vertical
                        if(currentWord.getName().length() + c < cSize){
                            wordFits = true;
                        }
                        else {
                            direction = (direction + 1) % 4;
                        }
                    case 2: //Main Diagonal
                        if(currentWord.getName().length()/2 + r < rSize && currentWord.getName().length()/2 + c < cSize){
                            wordFits = true;
                        }
                        else {
                            direction = (direction + 1) % 4;
                        }
                    case 3: //Secondary Diagonal
                        if(currentWord.getName().length()/2 + r < rSize && c - currentWord.getName().length()/2 > 0){
                            wordFits = true;
                        }
                        else {
                            direction = (direction + 1) % 4;
                        }
                    default:
                        break;
                }
            }
        }
    }
    
    /**
     * Starts the game.
     */
    public void startGame() {
        //while ( wordBank.isGameover() != true ){
            //timer.startTimer();
        //}
    }
    
    /**
     * Creates the word bank to be used for the game.
     * @throws java.io.IOException
     */
    private void loadWordBank() throws IOException {
        this.wordBank.createWordBank();
    }

    /**
     * Returns the board.
     * @return
     */
    public Object[][] getBoard() {
        return board;
    }

    /**
     * Gets the size of the board.
     * @return
     */
    public static int getBoardSize() {
        return boardSize;
    }

    /**
     * Returns the number of rows on the board.
     * @return
     */
    public int getrSize() {
        return rSize;
    }
    
    /**
     * Returns the number of columns on the board.
     * @return
     */
    public int getcSize() {
        return cSize;
    }

    /**
     * Gets the current target word.
     * @return
     */
    public Word getTargetWord() {
        return wordBank.getTargetWord();
    }
    
    /**
     * Gets the next target word from the word bank.
     * @return
     */
    public Word getNextTargetWord() {
        return wordBank.getNewTargetWord();
    }

    /**
     * Returns the number of words in the word bank.
     * @return
     */
    public int getNumWords() {
        return wordBank.getNumWords();
    }
    
    /**
     * Gets the words and their keys.
     * @return
     */
    public Object[][][] getWordKeys() {
        return wordKeys;
    }
    
    /**
     * Gets a random point in the grid.
     * @todo
     */
    private int randomPoint() {
        Random rand = new Random();
        int point = rand.nextInt(this.cSize);
        return point;
    }
    
    /**
     * Returns a randomly selected alphabetic letter.
     * @return 
     */
    private char randomLetter() {
        Random letter = new Random();
        int numLetters = 26;
        int letterpos = letter.nextInt(numLetters);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char rletter = alpha.charAt(letterpos);
        return rletter;
    }
    
    /**
     * Returns if the word will be stored backward in the grid.
     * @return 
     */
    private boolean randomBackward() {
        Random backward = new Random();
        boolean isbackward = backward.nextBoolean();
        return isbackward;
    }
    
    /**
     * Returns the direction the word will be stored in the grid.
     *  *** Use a switch statement for each case in creation
     */
    private int randomDirection() {
        Random direction = new Random();
        int numDirections = 4;
        int rdirection = direction.nextInt(numDirections);
        return rdirection;
    }
    
    /**
    * Returns the word in reverse
    */
    private String reverseWord(String reverse) { 
        char[] letters = reverse.toCharArray();
        int start = 0;
        int finish = letters.length-1;
        char tempVar;
        
        while(finish > start){
            tempVar = letters[start];
            letters[start]=letters[finish];
            letters[finish] = tempVar;
            start++;
            finish--;
        }
        
        return new String(letters);
    }
    
    /**
     * Places a word onto the board
     * @param direction 
     */
    private void inputWord(String word, int direction, int row, int col) {
        switch (direction) {
            case 0: //Horizontal
                for(int r = 0; r < word.length(); ++r) {
                    board[row+r][col] = word.charAt(r);
                }
            case 1: //Vertical
                for(int c = 0; c < word.length(); ++c) {
                    board[row][col+c] = word.charAt(c);
                }
            case 2: //Main Diagonal
                for(int d = 0; d < word.length(); ++d) {
                    board[row+d][col+d] = word.charAt(d);
                }
            case 3: //Secondary Diagonal
                for(int d = 0; d < word.length(); ++d) {
                    board[row+d][col-d] = word.charAt(d);
                }
            default:
                break;
        }
    }
}
