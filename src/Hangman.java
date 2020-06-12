import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    String randomPhrase;
    StringBuilder hiddenPhrase;
    String previousGuesses;

    public Hangman(){
        randomPhrase ="";
        hiddenPhrase = null;
        previousGuesses = "";
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!\n");
        Hangman hangman = new Hangman();
        hangman.play();
    }

    /**
     * create a single phrase randomly chosen from a list
     */
    public void randomPhrase(){
        List<String> phraseList = null;

        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        assert phraseList != null;
        randomPhrase=phraseList.get((int) (Math.random() * phraseList.size()));
    }

    /**
     * create the initial hidden phrase from randomPhrase
     */
    public void generateHiddenPhrase(){
        hiddenPhrase = new StringBuilder();
        char ch;
        for(int i=0;i<randomPhrase.length();i++){
            ch=randomPhrase.charAt(i);
            if(Character.isLetter(ch)){
                ch = '*';
            }
            hiddenPhrase.append(ch);
        }
    }

    /**
     * @return ch character from user input
     */
    public char getGuess(){
        Scanner input = new Scanner(System.in);
        System.out.print("Guess a letter: ");
        return input.next().charAt(0);
    }

    /**
     * returns whether a letter matches and modifies the partially hidden phrase if there is a match.
     * @param guess character from user input
     * @return in matched
     */
    public boolean processGuess(char guess){
        boolean isMissed = true;
        char guessLowerCase=Character.toLowerCase(guess);
        char guessUpperCase=Character.toUpperCase(guess);
        String phraseLowerCase = randomPhrase.toLowerCase();

        if(!Character.isLetter(guess)){
            System.out.println(guess+" is not a letter\n");
        }
        if(hiddenPhrase.indexOf(guessLowerCase+"")>=0||hiddenPhrase.indexOf(guessUpperCase+"")>=0){
            System.out.println("This correct guess had been made\n");
        }
        if(previousGuesses.contains(guess+"")){
            System.out.println("This incorrect guess had been made\n");
        }
        for(int i = 0; i<hiddenPhrase.length();i++) {
            if (guessLowerCase == phraseLowerCase.charAt(i)) {
                hiddenPhrase.replace(i, i + 1, randomPhrase.charAt(i) + "");
                isMissed = false;
            }
        }
        return isMissed;
    }

    /**
     * count missed to determine if the user win/lose
     */
    public void getResult(){
        // Set the number of n and miss
        int n = randomPhrase.length()/3;
        int miss = 0;

        // Declare local variables
        boolean isMissed;
        char ch;

        while (miss < n && !hiddenPhrase.toString().equals(randomPhrase)){
            System.out.println("Current phrase: " + hiddenPhrase);
            ch = getGuess();
            isMissed = processGuess(ch);
            if (isMissed) {
                miss++;
                System.out.println("The guessed letter does not occur in the phrase");
                System.out.println("There are " + miss + " miss(es) and " + (n-miss) + " chance(s) left\n");
            }
            previousGuesses += ch;
        }

        if (miss < n) {
            System.out.println("\nYou won!");
            System.out.println("The phrase is: " + randomPhrase);
        } else {
            System.out.println("\nYou lost, try again!");
        }
    }

    /**
     * play a game of hangman, this version 3 uses methods and data members
     */
    public void play(){
        this.randomPhrase();
        this.generateHiddenPhrase();
        this.getResult();
    }
}