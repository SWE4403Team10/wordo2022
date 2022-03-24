package com.wordo.gamelogic;

public class HardDifficulty implements Difficulty{

    /**
     * Gets the required word length for HardDifficulty
     * @return 6: int
     */
    public int getWordLength(){
        return 6;
    }

    /**
     * Gets the number of guesses allowed for HardDifficulty
     * @return 6: int
     */
    public int getNumGuesses(){
        return 6;
    }
}
