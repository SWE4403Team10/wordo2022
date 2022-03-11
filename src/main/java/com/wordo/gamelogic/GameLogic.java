package com.wordo.gamelogic;

public class GameLogic {

    private Difficulty difficulty;
    private String correctWord = "hello";
    private int numGuesses = 0;
    private static final GameLogic instance = new GameLogic();

    private final Difficulty easyDifficulty = new EasyDifficulty();
    private final Difficulty normalDifficulty = new NormalDifficulty();
    private final Difficulty hardDifficulty = new HardDifficulty();


    // GameLogic Constructor:
    private GameLogic(){}


    // Returns the instance of GameLogic
    public static GameLogic getInstance(){
        return instance;
    }


    // Compares the user guess with the correct word
    public int[] checkGuess(String guess){
        int[] result = new int[difficulty.getWordLength()];
        char[] correctWordCopy = correctWord.toCharArray();
        char[] guessArray = guess.toCharArray();
        boolean found = false;


        //check if guess meets word length difficulty requirements
        if(guess.length() == difficulty.getWordLength()){

            for(int i = 0; i < difficulty.getWordLength(); i++){
                for (int j = 0; j < difficulty.getWordLength(); j++) {
                    if(guessArray[i] == correctWordCopy[j]){ // If correct letter
                        if(i == j){ // If correct spot
                            result[i] = 2;
                            correctWordCopy[j] = '*';
                            break;
                        }
                        else{ // Correct letter && wrong spot; check to see if there is duplicate letter in the guess and see if it would be in the correct spot
                            for(int k = i; k < guess.length(); k++){
                                if(guessArray[k] == guessArray[i]){ //letter @ k (guess) == letter @ i (guess)
                                    if(k == j){ // position of letter k (guess) == position of letter j (correct)
                                        result[i] = 0;
                                        found = true;
                                        break;
                                    }
                                }
                            }
                            if(!found){ // no duplicate found in the correct position
                                result[i] = 1;
                            }
                            break;
                        }
                    }
                }
            }

        }
        else{
            return result;
        }
        numGuesses++;
        return result;
    }


    // Gets the current number of guesses
    public boolean getNumGuesses(){
        if(numGuesses == 6){
            numGuesses = 0;
            return false;
        }
        return true;
    }


    // Selects a random word from the data file to use as the correct word
    public void generateCorrectWord(){
    }


    // Allows the difficulty to be changed
    public boolean setDifficulty(String difficultyIn){
        if(difficultyIn.equalsIgnoreCase("easy")){
            difficulty = easyDifficulty;
        }
        else if(difficultyIn.equalsIgnoreCase("normal")){
            difficulty = normalDifficulty;
        }
        else if(difficultyIn.equalsIgnoreCase("hard")){
            difficulty = hardDifficulty;
        }
        else{
            return false;
        }
        return true;
    }
}
