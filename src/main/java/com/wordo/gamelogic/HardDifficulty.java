package com.wordo.gamelogic;

public class HardDifficulty implements Difficulty{

    // Gets the rule for the length of appropriate words
    public int getWordLength(){
        return 6;
    }

    // Gets the rule for the number of guesses
    public int getNumGuesses(){
        return 6;
    }
}
