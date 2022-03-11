package com.wordo.gamelogic;

public class EasyDifficulty implements Difficulty{

    public int getWordLength(){
        return 4;
    }
    public int getNumGuesses(){
        return 6;
    }
}
