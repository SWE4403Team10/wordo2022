package com.wordo.gamelogic;

public class NormalDifficulty implements Difficulty{

    public int getWordLength(){
        return 5;
    }
    public int getNumGuesses(){
        return 6;
    }
}
