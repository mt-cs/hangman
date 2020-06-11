Project 1 CS112 University of San Francisco

Hangman Version 3 and Version 4

Functional requirements 

The phrases for the game should be read in from a file (this code is given to you in starter code). The game chooses a random phrase from the list. This is the phrase the player tries to guess. 
The game generates a hidden phrase in which all letters in the phrase are replaced with asterisks. Spaces and punctuation-- all non-letters-- are left unhidden. So if the phrase is "Joe + Programmer", the initial partially hidden phrase is:    ***  + ********** 
The user guesses a letter. All occurrences of the letter in the phrase are replaced in the partially hidden phrase. For our example, if the user guessed "o", the new partially hidden phrase would change to: * o *  + ** o*******  
If a user guesses something that is not a letter (e.g., ‘%’) the user should be notified but not penalized a miss. Case should be ignored in terms of matching-- 'a' is the same as 'A'.
If the guessed letter does not occur in the phrase, the user is notified of the miss and how many misses/chances are left.
The user should NOT be penalized for guessing a previously correct or incorrect guess, but should be notified. 
If the user misses n times, the game is lost and the user should be notified. If all letters in the phrase are guessed, the user wins! You can set the number of guesses 'n' how you want.
Provide a reasonable user interface such that the players is given instructions on how to play, the number of misses and previous misses are displayed, and it is clear how to proceed at each step. Test this out by performing a usability test with at least two people!

Style Requirements

Style your code using the Google Java Style Guidelines (Links to an external site.). For this first project, be sure and follow the formatting (Links to an external site.) and naming (Links to an external site.) guides. 
Coding Specifications (all versions)

Each time the program runs, choose a random phrase.
Define the hidden phrase as a StringBuilder, because you will be changing it as the game is played.
Do not use static methods other than the main

version 3: provide the same methods as in version 2, but also define data members for "phrase", "hiddenPhrase", and "previousGuesses". You will have fewer parameters in your methods for this version.

version 4:
Modify your version 3 so that the game plays the same but the player will be a bot.

* There is no user input-- when you run the app, it shows the bot's guesses and the system's feedback.

* You must have at least two classes in your app--Hangman and HangmanPlayer 

 *You can earn most of the points by creating a not that intelligent bot player, e.g., one that guesses 'a', then 'b', then 'c', and so on, until they win or lose. You are strongly encouraged to implement such a simple player first! 