import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    String randomPhrase;
    StringBuilder hiddenPhrase;
    String previousGuesses;

    public Hangman(){ }
    /**
     * returns a single phrase randomly chosen from a list
     * @param phraseList from file on main
     * @return randomPhrase
     */
    public String randomPhrase(List<String> phraseList){
        randomPhrase=phraseList.get((int)Math.random()*phraseList.size());
        return randomPhrase;
    }

    /**
     * returns the initial hidden phrase from randomPhrase
     * @return hiddenPhrase
     */
    public StringBuilder generateHiddenPhrase(){
        hiddenPhrase = new StringBuilder();
        char ch;
        for(int i=0;i<randomPhrase.length();i++){
            ch=randomPhrase.charAt(i);
            if(Character.isLetter(ch)){
                ch = '*';
            }
            hiddenPhrase.append(ch);
        }
        return hiddenPhrase;
    }

    /**
     * @param input gets input guess from user
     * @return ch character from user input
     */
    public char getGuess(Scanner input){
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
            System.out.println(guess+" is not a letter");
        }
        if(hiddenPhrase.indexOf(guessLowerCase+"")>=0||hiddenPhrase.indexOf(guessUpperCase+"")>=0){
            System.out.println("This correct guess had been made");
        }
        if(previousGuesses.contains(guess+"")){
            System.out.println("This incorrect guess had been made");
        }
        for(int i = 0; i<hiddenPhrase.length();i++) {
            if (guessLowerCase == phraseLowerCase.charAt(i)) {
                hiddenPhrase.replace(i, i + 1, randomPhrase.charAt(i) + "");
                isMissed = false;
            }
        }
        return isMissed;
    }

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        List<String> phraseList = null;

        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Welcome to Hangman!\n");
        hangman.randomPhrase(phraseList);


        // Call generateHiddenPhrase
        hangman.generateHiddenPhrase();

        // Set the number of n and miss
        int n = hangman.randomPhrase.length()/3;
        int miss = 0;

        // Declare local variables
        boolean isMissed;
        hangman.previousGuesses = "";
        char ch;

        // Get user's input
        Scanner input = new Scanner(System.in);

        while (miss < n && !hangman.hiddenPhrase.toString().equals(hangman.randomPhrase)){
            System.out.println("Current phrase: " + hangman.hiddenPhrase);
            ch = hangman.getGuess(input);
            isMissed = hangman.processGuess(ch);
            if (isMissed) {
                miss++;
                System.out.println("The guessed letter does not occur in the phrase");
                System.out.println("There are " + miss + " miss(es) and " + (n - miss) + " chance(s) left");
            }
            hangman.previousGuesses += ch;
        }

        System.out.println("");
        if (miss < n) {
            System.out.println("You win!");
            System.out.println("The phrase is: " + hangman.randomPhrase);
        } else {
            System.out.println("You lose");
        }
    }
}