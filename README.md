# Wordo
SWE4403 Team 10  
Ben Fleming, Duncan Campbell, Colby Foster, Grace Ashfield
---
### Project Overview

#### 💡 Project Objective
The objective of this project is to design reusable and extendable software by implementing design patterns, 
following good coding practices, and working in teams. Team 10 is creating a word puzzle game, called Wordo. 
This project was started from scratch and includes many design patterns that were taught in 
SWE 4403 - Software Architecture and Design Patterns.
   

#### 💻 Project Description
For this project, we created a single player client-based application in Java called Wordo. The objective of this game 
is to guess a mystery word in a certain number of tries. Players will receive feedback after each guess in the form of 
coloured tiles to help them guess the word. A grey letter means it isn’t in the word, while a yellow letter signals it 
is in the word but in the wrong position, a green letter means it’s in the word and in the correct place. 
Players can customize the games difficulty each time they play. There are three game modes: easy, normal, and hard. 
Each mode will have words of different lengths, four, five, or six letters depending on the difficulty. 
After the player chooses the difficulty and presses the play button, the game will start. Once the game begins, a virtual 
keyboard will be on the screen whose keys will change colour as the tiles change colours. The tiles in each row serve as 
an indicator to how many letters are in the word, and the number of rows indicates how many attempts the player has to guess 
the mystery word. Every guess the user takes, must be the proper length shown in tiles, and the word must be 
a valid word in the dictionary. If the user correctly guesses the word within the allotted attempts, then they win the game.
