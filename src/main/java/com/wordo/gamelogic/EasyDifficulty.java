package com.wordo.gamelogic;

public class EasyDifficulty implements Difficulty{

    // Gets the rule for the length of appropriate words
    public int getWordLength(){
        return 4;
    }

    // Gets the rule for the number of guesses
    public int getNumGuesses(){
        return 6;
    }
}
