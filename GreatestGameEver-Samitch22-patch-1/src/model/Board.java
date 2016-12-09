/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * This class implements the concept of a board. It is an m X m matrix of 
 * characters,containing the concept of words placed inside.
 * @author Mitchell
 * @author Sam
 * @author Ryan
 */
public final class Board implements Serializable { 
    
    private static final int          boardSize = 14;
    private        final int          rSize;
    private        final int          cSize;
    private        final Object[][]   board;
    private        final WordTimer    spriteTimer;
    private        final WordBank     wordBank;
    private              Player       player;
    private        final Object[][][] wordKeys;
    private        final Object[]     targetKeys;
    private              int          targetKey;
    
    /**
     * This class implements the board that holds a word search puzzle.
     * @param p
     * @throws java.io.IOException
     */
    public Board(Player p) throws IOException {
        this.rSize      = Board.getBoardSize();
        this.cSize      = Board.getBoardSize();
        this.spriteTimer = new WordTimer();
        this.player     = p;
        this.wordBank   = new WordBank(p.getScore());
        this.board      = new Character[getrSize()][getcSize()];
        loadWordBank();
        this.wordKeys   = new Character[getrSize()][getcSize()][wordBank.getNumWords()];
        this.targetKeys = new Word[wordBank.getNumWords()];
        createBoard();
    }
    
    public Board(Board b) throws IOException {
        this (b.getPlayer());
    }
    
    /**
     * This method creates the board with words hidden in the puzzle.
     * @throws java.io.IOException
     */
    private void createBoard() throws IOException {

        //Iterating through all the words in the list
        for ( int w = 0; w < this.getNumWords(); w++ ) {
            
            //Grabs next word from the list
            Word currentWord = this.wordBank.getWordBank().get(w);
            
            //Checks if word will be backwords or not
            boolean isBackward = this.randomBackward();
            
            //Picks a starting direction to choose
            int direction = this.randomDirection();
            
            //Determines if a word will fit or not
            boolean wordFits = false;
            
            //Picks a random point on the board
            int tempRow = this.randomPoint(); 
            int tempCol = this.randomPoint();
            targetKey = w;
            
            while( ! wordFits ){
                //Picks a direction
                switch (direction) {
                    case 0: // Vertical
                        if(currentWord.getName().length() + tempRow < rSize && this.checkWordSpace(currentWord.getName(), direction, tempRow, tempCol) == true ){
                            wordFits = true;
                            break;
                        }
                        else {
                            direction = this.incrementDirection(direction);
                        }
                    case 1: // Horizontal
                        if(currentWord.getName().length() + tempCol < cSize && this.checkWordSpace(currentWord.getName(), direction, tempRow, tempCol) == true ){
                            wordFits = true;
                            break;
                        }
                        else {
                            direction = this.incrementDirection(direction);
                        }
                    case 2: //Main Diagonal
                        if(currentWord.getName().length() + tempRow < rSize && currentWord.getName().length() + tempCol < cSize && this.checkWordSpace(currentWord.getName(), direction, tempRow, tempCol) == true ) {
                            wordFits = true;
                            break;
                        }
                        else {
                            direction = this.incrementDirection(direction);
                        }
                    case 3: //Secondary Diagonal
                        if(currentWord.getName().length() + tempRow < rSize && tempCol - currentWord.getName().length() > 0 && this.checkWordSpace(currentWord.getName(), direction, tempRow, tempCol) == true ) {
                            wordFits = true;
                            break;
                        }
                        else {
                            direction = this.incrementDirection(direction);
                        }
                    default:
                        tempRow = this.randomPoint(); 
                        tempCol = this.randomPoint();
                        direction = this.randomDirection();
                        break;
                }
            }
            this.inputWord(currentWord, direction, isBackward, tempRow, tempCol, false);
        }
        
        //Iterating through all the words in the list
        for ( int w = 0; w < this.getNumDistractions(); w++ ) {

            //Grabs next word from the list
            Word currentWord = this.wordBank.getDistractionBank().get(w);
            
            //Checks if word will be backwords or not
            boolean isBackward = this.randomBackward();
            
            //Picks a starting direction to choose
            int direction = 0;
            
            //Determines if a word will fit or not
            boolean wordFits = false;
            
            //Picks a random point on the board
            int tempRow = this.randomPoint(); 
            int tempCol = this.randomPoint();
            
            int tries = 0;
            
            while( ! wordFits ){
                //Picks a direction
                switch (direction) {
                    case 0: // Vertical
                        if(currentWord.getName().length() + tempRow < rSize && this.checkWordSpace(currentWord.getName(), direction, tempRow, tempCol) == true ){
                            wordFits = true;
                            this.inputWord(currentWord, direction, isBackward, tempRow, tempCol, true);
                            break;
                        }
                        else {
                            direction = this.incrementDirection(direction);
                        }
                    case 1: // Horizontal
                        if(currentWord.getName().length() + tempCol < cSize && this.checkWordSpace(currentWord.getName(), direction, tempRow, tempCol) == true ){
                            wordFits = true;
                            this.inputWord(currentWord, direction, isBackward, tempRow, tempCol, true);
                            break;
                        }
                        else {
                            direction = this.incrementDirection(direction);
                        }
                    case 2: //Main Diagonal
                        if(currentWord.getName().length() + tempRow < rSize && currentWord.getName().length() + tempCol < cSize && this.checkWordSpace(currentWord.getName(), direction, tempRow, tempCol) == true ) {
                            wordFits = true;
                            this.inputWord(currentWord, direction, isBackward, tempRow, tempCol, true);
                            break;
                        }
                        else {
                            direction = this.incrementDirection(direction);
                        }
                    case 3: //Secondary Diagonal
                        if(currentWord.getName().length() + tempRow < rSize && tempCol - currentWord.getName().length() > 0 && this.checkWordSpace(currentWord.getName(), direction, tempRow, tempCol) == true ) {
                            wordFits = true;
                            this.inputWord(currentWord, direction, isBackward, tempRow, tempCol, true);
                            break;
                        }
                        else {
                            direction = this.incrementDirection(direction);
                        }
                    default:
                        tempRow = this.randomPoint(); 
                        tempCol = this.randomPoint();
                        direction = 0;
                        tries++;
                        if ( tries == 14 )
                            wordFits = true; // breaks out of loop but does not add the distraction word to the board
                        break;
                }
            }
        }
        
        // Puts random letters on the board.
        for ( int r = 0; r < Board.boardSize; r++ ) {
            for ( int c = 0; c < Board.boardSize; c++ )
            {
                if ( board[r][c] == null ) {
                    board[r][c] = randomLetter();
                }
            }
        }
    }
    
    /**
     * Starts the game.
     */
    public void startGame() {
        spriteTimer.startTimer();
    }
    
    /**
     * Creates the word bank to be used for the game.
     * @throws java.io.IOException
     */
    private void loadWordBank() throws IOException {
        this.wordBank.createWordBank();
        this.wordBank.createDistractionBank();
    }

    /**
     * Checks if a word was found.
     * @param word
     * @return 
     */
    public boolean foundWord(Word word) {
        return this.wordBank.foundWord(word);
    }
    
    /**
     * Returns the board.
     * @return
     */
    public Object[][] getBoard() {
        return board;
    }

    public Board getBoardObject() {
        return this;
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
     * Gets the word timer.
     * @return
     */
    public WordTimer getSpriteTimer() {
        return spriteTimer;
    }
      
    /**
     * Returns the number of words in the word bank.
     * @return
     */
    public int getNumWords() {
        return wordBank.getNumWords();
    }
    
    /**
     * Returns the number of words in the distraction bank.
     * @return
     */
    public int getNumDistractions() {
        return wordBank.getNumDistractions();
    }

    /**
     * Sets the player for the board.
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
        setWordBankScore(player); // Coupled
    }

    /**
     * Sets this Word Bank's score associated with this player.
     * @param player 
     */
    private void setWordBankScore(Player player) {
        this.wordBank.setScore(player.getScore());
    }
    
    /**
     * Gets the player.
     * @return
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Gets the words and their keys.
     * @return
     */
    public Object[][][] getWordKeys() {
        return wordKeys;
    }

    /**
     * Gets the array of target keys.
     * @return
     */
    public Object[] getTargetKeys() {
        return targetKeys;
    }

    /**
     * Returns if the game is over.
     * @return
     */
    public boolean isGameover() {
        return this.wordBank.isGameover();
    }

    /**
     * Gets the word bank object.
     * @return
     */
    public WordBank getWordBank() {
        return wordBank;
    }
    
    /**
     * Gets a random point in the grid.
     * @todo
     */
    private int randomPoint() {
        Random rand = new Random();
        int point = rand.nextInt(Board.getBoardSize());
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
            finish--;
            start++;
        }
        
        return new String(letters);
    }
    
    /**
     * Places a word onto the board
     * @param direction 
     */
    private void inputWord(Word w, int direction, boolean isBackward, int row, int col, boolean isDistraction) {
        String word = w.toString();
        int first = 0;
        int last = word.length()-1;
        
        if ( isBackward == true ) {
            word = this.reverseWord(word);
        }
        
        switch (direction) {
            case 0: //Horizontal
                for(int r = 0; r < word.length(); r++) {
                    board[row+r][col] = word.charAt(r);
                    if ( ! isDistraction ) {
                        if ( r == first ) {
                            wordKeys[row+r][col][targetKey] = word.charAt(r);
                        }
                        if ( r == last ) {
                            wordKeys[row+r][col][targetKey] = word.charAt(r);
                        }
                    }
                }
                if ( ! isDistraction )
                    targetKeys[targetKey] = w;
                break;
            case 1: //Vertical
                for(int c = 0; c < word.length(); c++) {
                    board[row][col+c] = word.charAt(c);
                    if ( ! isDistraction ) {
                        if ( c == first ) {
                            wordKeys[row][col+c][targetKey] = word.charAt(c);
                        }
                        if ( c == last ) {
                            wordKeys[row][col+c][targetKey] = word.charAt(c);
                        }
                    }
                }
                if ( ! isDistraction )
                    targetKeys[targetKey] = w;
                break;
            case 2: //Main Diagonal
                for(int d = 0; d < word.length(); d++) {
                    board[row+d][col+d] = word.charAt(d);
                    if ( ! isDistraction ) {
                        if ( d == first ) {
                            wordKeys[row+d][col+d][targetKey] = word.charAt(d);
                        }
                        if ( d == last ) {
                            wordKeys[row+d][col+d][targetKey] = word.charAt(d);
                        }
                    }
                }
                if ( ! isDistraction )
                    targetKeys[targetKey] = w;
                break;
            case 3: //Secondary Diagonal
                for(int d = 0; d < word.length(); d++) {
                    board[row+d][col-d] = word.charAt(d);
                    if ( ! isDistraction ) {
                        if ( d == first ) {
                            wordKeys[row+d][col-d][targetKey] = word.charAt(d);
                        }
                        if ( d == last ) {
                            wordKeys[row+d][col-d][targetKey] = word.charAt(d);
                        }
                    }
                }
                if ( ! isDistraction )
                    targetKeys[targetKey] = w;
                break;
            default:
                break;
        }
    }
    
    /**
     * Checks that there is enough room on the board to fit the word.
     * @param word
     * @param direction
     * @param row
     * @param col
     * @return 
     */
    private boolean checkWordSpace(String word, int direction, int row, int col) {
        boolean wordSpace = true; 
        
        switch (direction) {
            case 0: // Vertical
                for(int r = 0; r < word.length(); ++r) {
                    if( board[row+r][col] != null) {
                        wordSpace = false;
                    }
                }
                break;
            case 1: // Horizontal
                for(int c = 0; c < word.length(); ++c) {
                    if( board[row][col+c] != null) {
                        wordSpace = false;
                    }
                }
                break;
            case 2: //Main Diagonal
                for(int d = 0; d < word.length(); ++d) {
                    if( board[row+d][col+d] != null) {
                        wordSpace = false;
                    }
                }
                break;
            case 3: //Secondary Diagonal
                for(int d = 0; d < word.length(); ++d) {
                    if (board[row+d][col-d] != null) {
                        wordSpace = false;
                    }
                }
                break;
            default:
                break;
        }
        
        return wordSpace;
    }
    
    
    /**
     * Increments the direction of the word.
     * @param direction
     * @return 
     */
    private int incrementDirection(int direction) {
        return (direction + 1) % 4;
    }
}
