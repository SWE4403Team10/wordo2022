package com.wordo.gamelogic;

public class NormalDifficulty implements Difficulty{

    /**
     * Gets the required word length for NormalDifficulty
     * @return 5: int
     */
    public int getWordLength(){
        return 5;
    }

    /**
     * Gets the number of guesses allowed for NormalDifficulty
     * @return 6: int
     */
    public int getNumGuesses(){
        return 6;
    }
}
