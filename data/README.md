# Wordo
SWE4403 Team 10  
Ben Fleming, Duncan Campbell, Colby Foster, Grace Ashfield
---
### Project Data
Wordo has three different game modes: easy, normal, hard. Each mode has a different number of characters in the word, as such, we have three different files to pull data from depending on the game mode selected. At the beginning of each game, a word will be randomly selected from the file associated with the difficulty, and this will be the mystery word. As the users guesses, their guess will first be compared to the dictionary, to validate that their guess is a real word. If the users guess is not valid, a popup will appear letting them know; if the guess is valid (in the dictionary), the users guess will then be compared against the mystery word to see which letters are in the word and in the right places, and which are not.

#### Resources
4 letter words https://gist.github.com/paulcc/3799331
5 letter words https://www-cs-faculty.stanford.edu/~knuth/sgb-words.txt
6 letter words https://eslforums.com/6-letter-words/
dictionary https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt
