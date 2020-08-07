import java.util.Arrays;
import java.util.Scanner;

public class Logic {

    public static Game game;

    public static void main(String[] args) {
        boolean playAgain = true;
        while (playAgain) {
            setupGame();
            boolean hasWon = game();

            if (hasWon) {
                System.out.println("YOU WIN!!!");
            } else {
                System.out.println("You lost...better luck next time :(");
            }
            System.out.println("The movie title was '"+game.getTitle()+"'.");
            System.out.println("===================================");

            playAgain = playAgain();
        }
    }

    private static void setupGame() {
        System.out.println("===================================");
        System.out.println("Choose the game type:");
        System.out.println("[0] Movie titles");
        System.out.println("[1] Country names");

        int gameId = 0;
        String[] gameType = {"movie", "country"};

        while (true) {
            try {
                Scanner gameIdScanner = new Scanner(System.in);
                gameId = gameIdScanner.nextInt();
                if (gameId <= gameType.length) {
                    break;
                } else {
                    System.out.println("[ERROR] not a valid option!");
                }
            } catch (Exception exception) {
                System.out.println("[ERROR] not a valid option!");
            }
        }

        game = new Game(gameId);

        System.out.println("===================================");
        System.out.println("Let's play Hangman! I'm thinking of a certain "+gameType[gameId]+".");
        System.out.println("if you can guess its name letter by letter in less than 7 tries,");
        System.out.println("you win. If you can't, then I win!");
        System.out.println("lets begin!!");
        System.out.println("===================================");

    }

    private static boolean game() {
        int lives = 7;
        String guessHistory = "¶";
        String title = game.getTitle();
        boolean hasWon = false;

        while (true) {

            char[] titleArray = title.toCharArray();
            char[] hiddenTitle = game.getHiddenTitle();

            System.out.println("You have " + lives + "/7 lives");
            System.out.println(hiddenTitle);
            System.out.println("Enter a letter:");

            Scanner guesser = new Scanner(System.in);
            char guess = guesser.next().charAt(0);

            System.out.println("===================================");

            if ((title.indexOf(guess) >= 0) && (guessHistory.indexOf(guess) <= 0)) {

                for (int i = 0; i <= title.lastIndexOf(guess); i++) {
                    int X = title.indexOf(guess, i);
                    hiddenTitle[X] = guess;
                }

                if (Arrays.equals(hiddenTitle, titleArray)) {
                    hasWon = true;
                    break;
                }
                System.out.println("The title contains the letter '"+guess+"'!");
                System.out.println(" ");
                guessHistory += guess;

            } else if(guessHistory.indexOf(guess) >= 0) {
                System.out.println("you already guessed '"+guess+"'... Try again.");
                System.out.println(" ");

            } else {
                lives--;
                if (lives < 1) {
                    break;
                }
                System.out.println("The title doesn't contain the letter '" + guess + "'.");
                System.out.println(" ");
                guessHistory += guess;
            }
        }
        return hasWon;
    }

    private static boolean playAgain() {
        boolean playAgain = true;
        while (true) {
            System.out.println("what do you want to do next?");
            System.out.println("-0- Exit");
            System.out.println("-1- Play again");
            Scanner scanner = new Scanner(System.in);

            try {
                int restartGame = scanner.nextInt();
                System.out.println("===================================");

                if(restartGame == 0) {
                    System.out.println("Thank you for playing, Goodbye!");
                    playAgain = false;
                    break;
                } else if (restartGame == 1) {
                    System.out.println("[Restarting Game...]");
                    break;
                } else {
                    System.out.println("[ERROR] not a valid option!");
                }
            } catch (Exception exception) {
                System.out.println("[ERROR] not a valid option!");
            }
        }
        return playAgain;
    }
}
