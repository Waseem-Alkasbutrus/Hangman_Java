import java.util.Arrays;
import java.util.Scanner;

public class Logic {

    public static Game game;
    public static int lives = 7;

    public static void main(String[] args) {
        HangmanGui.Gui();
    }

    public static void setupGame(String FileName) {
        game = new Game(FileName);
    }

    public static int makeGuess(char guess) {
        int result;
        String title = game.getTitle();
        if (title.indexOf(guess) >= 0) {
            for (int i = 0; i <= title.lastIndexOf(guess); i++) {
                
            }
        }
        return result;
    }
    public static boolean game(String text) {
        String guessHistory = "¶";
        String title = game.getTitle();
        String hiddenTitle = game.getHiddenTitle();
        boolean hasWon = false;

        while (true) {

            char[] titleArray = title.toCharArray();
            char[] hiddenTitleArray = hiddenTitle.toCharArray();
            char[] guessArray = text.toCharArray();
            char guess = guessArray[0];

            if ((title.indexOf(guess) >= 0) && (guessHistory.indexOf(guess) <= 0)) {

                for (int i = 0; i <= title.lastIndexOf(guess); i++) {
                    int X = title.indexOf(guess, i);
                    hiddenTitleArray[X] = guess;
                }

                if (Arrays.equals(hiddenTitleArray, titleArray)) {
                    hasWon = true;
                    break;
                }
                guessHistory += guess;

            } else if (guessHistory.indexOf(guess) >= 0) {
                //displays message: "you already guessed 'guess'...try again

            } else {
                lives--;
                if (lives < 1) {
                    break;
                }
                guessHistory += guess;
            }
        }
        return hasWon;
    }
}
