package com.wordo.gamelogic;

import com.wordo.ui.GameUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Character.toLowerCase;

public class GameLogic {

    private Difficulty difficulty;
    private String correctWord = "";
    private String guess = "";
    public int[] result;
    private int numGuesses = 0;
    private GameUI gameUI;
    private static final GameLogic instance = new GameLogic();


    /**
     * GameLogic Singleton constructor
     */
    private GameLogic(){}


    /**
     * Method to grab the instance of GameLogic
     * @return instance: GameLogic
     */
    public static GameLogic getInstance(){
        return instance;
    }


    /**
     * This method compares a guess to the correct word and returns the result of the comparison
     * @param guess
     * @return result: int[]
     */
    public int[] checkGuess(String guess){
        this.guess = guess;
        int[] result = new int[difficulty.getWordLength()];
        this.result = result;
        char[] correctWordArray = correctWord.toCharArray();
        char[] guessArray = guess.toCharArray();
        boolean found = false;

        if(guess.length() == difficulty.getWordLength()){
            for(int i = 0; i < difficulty.getWordLength(); i++){
                for (int j = 0; j < difficulty.getWordLength(); j++) {
                    if(toLowerCase(correctWordArray[i]) == toLowerCase(guessArray[j])){
                        if(i == j){
                            guessArray[j] = '*';
                            result[i] = 2;
                            break;
                        }
                        else{
                            if(j+1<difficulty.getWordLength() && i>j){
                                for(int k = j+1; k < difficulty.getWordLength(); k++){
                                    if(toLowerCase(correctWordArray[i]) == toLowerCase(guessArray[k]) && i == k){
                                        guessArray[j] = '*';
                                        found = true;
                                        break;
                                    }
                                }
                            }
                            if(!found){
                                result[j] = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        numGuesses++;
        gameUI.getKeyboard().update(guess, result);
        return result;
    }


    /**
     * Method verifies a string is a valid word in the dictionary
     * @param word
     * @return ret: boolean
     */
    public boolean verifyWord(String word){
        File fileToRead = new File("data/dictionary.txt");
        boolean ret = false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            while(line != null) {
                if(line.equalsIgnoreCase(word)){
                    ret = true;
                }
                line = reader.readLine();
            }
            reader.close();
            return ret;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    /**
     * Method determines if a guess is correct
     * @return
     */
    public boolean isCorrect(){
        for(int i = 0; i < difficulty.getWordLength(); i++){
            if(result[i] != 2){
                return false;
            }
        }
        return true;
    }


    /**
     * This method returns if there are still guesses to be made
     * @return boolean
     */
    public boolean getNumGuesses(){
        if(numGuesses == 6){
            return false;
        }
        return true;
    }


    /**
     * This method resets the number of guesses
     */
    public void setNumGuesses(){
        numGuesses = 0;
    }


    /**
     * This method sets the GameUI for GameLogic
     * @param gameUI
     */
    public void setGameUI(GameUI gameUI){
        this.gameUI = gameUI;
    }

    /**
     * Selects a random word from a text file depending on the current difficulty
     * @return correctWord: String
     */
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
            return correctWord;
        } catch (Exception e) {
            System.out.println(e);
            return correctWord;
        }
    }


    /**
     * Allows to difficulty to be changed by inputting the correct String
     * @param difficultyIn
     * @return boolean
     */
    public boolean setDifficulty(String difficultyIn){
        if(difficultyIn.equalsIgnoreCase("easy")){
            difficulty = new EasyDifficulty();
        }
        else if(difficultyIn.equalsIgnoreCase("normal")){
            difficulty = new NormalDifficulty();
        }
        else if(difficultyIn.equalsIgnoreCase("hard")){
            difficulty = new HardDifficulty();
        }
        else{
            return false;
        }
        return true;
    }


    /**
     * Gets the word length depending on the current difficulty
     * @return difficulty.getWordLength(): int
     */
    public int getDifficultyWordLength() {
        return difficulty.getWordLength();
    }


    /**
     * Returns the stored guess
     * @return guess: String
     */
    public String getGuess(){
        return guess;
    }


    /**
     * Returns the stored result
     * @return result: int[]
     */
    public int[] getResult(){
        return result;
    }
}