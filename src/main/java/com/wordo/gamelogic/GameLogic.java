package com.wordo.gamelogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {

    private Difficulty difficulty;
    private String correctWord = "";
    private String guess = "";
    public int[] result;
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
        this.guess = guess;
        int[] result = new int[difficulty.getWordLength()];
        this.result = result;
        char[] correctWordArray = correctWord.toCharArray();
        char[] guessArray = guess.toCharArray();
        boolean found = false;

        //check if guess meets word length difficulty requirements
        if(guess.length() == difficulty.getWordLength()){

            for(int i = 0; i < difficulty.getWordLength(); i++){ //iterates through the inputted guess
                for (int j = 0; j < difficulty.getWordLength(); j++) { //iterates through the correct word
                    if(guessArray[i] == correctWordArray[j]){ // If a letter match is found
                        if(i == j){ // If the letter is in the correct spot
                            result[i] = 2; //updates result to show letter is correct, in the correct spot
                            break;
                        }
                        else{ // Correct letter && wrong spot; check to see if there is duplicate letter in the guess and see if it would be in the correct spot
                            for(int k = i; k < guess.length(); k++){ //iterates through the rest of the guess
                                if(guessArray[k] == guessArray[i]){ //letter @ k (guess) == letter @ i (guess)
                                    if(k == j){ // position of letter k (guess) == position of letter j (correct)
                                        found = true;
                                        break;
                                    }
                                }
                            }
                            if(!found){ // no duplicate found in the correct position
                                result[i] = 1; //updates result to correct letter, wrong spot
                            }
                            break;
                        }
                    }
                }
            }

        }
        numGuesses++;
        return result;
    }


    //Determines if a guess is correct or not
    public boolean isCorrect(){
        int[] result = checkGuess(guess);
        for(int i = 0; i < result.length; i++){
            if(result[i] != 2){
                return false;
            }
        }
        return true;
    }


    // Gets the current number of guesses
    public boolean getNumGuesses(){
        System.out.println(numGuesses);
        if(numGuesses == 6){
            numGuesses = 0;
            return false;
        }
        return true;
    }


    // Selects a random word from the data file to use as the correct word
    public String generateCorrectWord(){
        File file;
        String fileToRead;
        if(difficulty.getWordLength()==4){
            file = new File("data/easy.txt");
            fileToRead = file.getAbsolutePath();
        } else if (difficulty.getWordLength()==6){
            file = new File("data/hard.txt");
            fileToRead = file.getAbsolutePath();
        } else{
            file = new File("data/normal.txt");
            fileToRead = file.getAbsolutePath();
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            while(line != null) {
                String[] wordsLine = line.split(" ");
                 for(String word : wordsLine) {
                     words.add(word);
                }
                line = reader.readLine();
            }
            reader.close();
            Random rand = new Random(System.currentTimeMillis());
            correctWord = words.get(rand.nextInt(words.size()));
            System.out.println(correctWord);
            return correctWord;
        } catch (Exception e) {
            System.out.println(e);
            return correctWord;
        }
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

    public int getDifficultyWordLength() {
        return difficulty.getWordLength();
    }

    // Get guess
    public String getGuess(){
        return guess;
    }


    //Get Result
    public int[] getResult(){
        return result;
    }


}