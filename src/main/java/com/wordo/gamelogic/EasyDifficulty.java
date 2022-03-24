package com.wordo.gamelogic;

public class EasyDifficulty implements Difficulty{

    /**
     * Gets the required word length for EasyDifficulty
     * @return 4: int
     */
    public int getWordLength(){
        return 4;
    }

    /**
     * Gets the number of guesses allowed for EasyDifficulty
     * @return 6: int
     */
    public int getNumGuesses(){
        return 6;
    }
}
