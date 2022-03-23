package com.wordo.gamelogic;

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
    private static final GameLogic instance = new GameLogic();


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

            for(int i = 0; i < difficulty.getWordLength(); i++){ //iterates through the correct word
                for (int j = 0; j < difficulty.getWordLength(); j++) { //iterates through the guess
                    if(toLowerCase(correctWordArray[i]) == toLowerCase(guessArray[j])){ // If a letter match is found
                        System.out.println("test: i = " + i + ", j = " + j);
                        if(i == j){ // If the letter is in the correct spot
                            guessArray[j] = '*';
                            result[i] = 2; //updates result to show letter is correct, in the correct spot
                            break;
                        }
                        else{ // Correct letter && wrong spot; check to see if there is duplicate letter in the guess and see if it would be in the correct spot
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
        //keyboard.update(guess, result);
        return result;
    }

    public boolean verifyWord(String word){
        File fileToRead = new File("data/dictionary.txt");
        boolean ret = false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            while(line != null) {
                if(line.contains(word)){
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

    //Determines if a guess is correct or not
    public boolean isCorrect(){
        for(int i = 0; i < difficulty.getWordLength(); i++){
            if(result[i] != 2){
                return false;
            }
        }
        return true;
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

    // Get guess
    public String getGuess(){
        return guess;
    }


    //Get Result
    public int[] getResult(){
        return result;
    }


}