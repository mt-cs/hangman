public class HangmanPlayer extends Hangman {
    protected String letters="ETAOINSHRDLUCMFWYPVBGKQJXZ";
    protected int index=0;

    @Override
    public char getGuess() {
        return letters.charAt(index++);
    }

    @Override
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
            System.out.println("Bot suggests: " + ch);
            isMissed = processGuess(ch);
            if (isMissed) {
                miss++;
                System.out.println("The guessed letter does not occur in the phrase");
                System.out.println("There are " + miss + " miss(es) and " + (n-miss) + " chance(s) left\n");
            }
            previousGuesses += ch;
        }

        if (miss < n) {
            System.out.println("\nBot won!");
            System.out.println("The phrase is: " + randomPhrase);
        } else {
            System.out.println("\nBot lost! lol ");
        }
    }

    public static void main(String[] args) {
        Hangman hangmanPlayer = new HangmanPlayer();
        hangmanPlayer.play();
    }
}
