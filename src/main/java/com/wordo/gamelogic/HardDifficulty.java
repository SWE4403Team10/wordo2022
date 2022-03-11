package com.wordo.gamelogic;

public class HardDifficulty implements Difficulty{

    public int getWordLength(){
        return 6;
    }
    public int getNumGuesses(){
        return 6;
    }
}
