import javax.swing.*;
import java.awt.*;

public class HangmanGui {

    private static CardLayout cardLayout = new CardLayout();
    private static JPanel containerPanel = new JPanel();
    private static JPanel mainMenu = new JPanel();
    private static JPanel instructions = new JPanel();
    private static JPanel gameMenu = new JPanel();

    public static void Gui() {

        JFrame frame = new JFrame("Hangman");
        frame.setSize(380,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cardLayout.setVgap(20);
        cardLayout.setHgap(10);
        containerPanel.setLayout(cardLayout);
        containerPanel.add(mainMenu, "1");
        containerPanel.add(instructions, "2");
        containerPanel.add(gameMenu, "3");
        frame.add(containerPanel);


        mainMenu();

        frame.setVisible(true);
    }

    private static void mainMenu() {
    cardLayout.show(containerPanel, "1");

    JLabel gameType = new JLabel("          Please choose a game type below:          ");
    mainMenu.add(gameType);

    JButton movieGame = new JButton("Movie titles");
    movieGame.addActionListener(e -> preGameMenu("movie"));
    mainMenu.add(movieGame);

    JButton countryGame = new JButton("Country names");
    countryGame.addActionListener(e -> preGameMenu("country"));
    mainMenu.add(countryGame);
    }

    private static void preGameMenu(String type) {
        Logic.setupGame(type);

        cardLayout.show(containerPanel, "2");

        JLabel intro1 = new JLabel("Let's play Hangman! I'm thinking of a certain "+type+".");
        instructions.add(intro1);

        JLabel intro2 = new JLabel("If you can guess it letter by letter within 7 tries, you win.");
        instructions.add(intro2);

        JLabel intro3 = new JLabel("                    If you can't, then I win!                  ");
        instructions.add(intro3);

        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(e -> gameMenu());
        instructions.add(startGame);
    }

    private static void gameMenu() {
        cardLayout.show(containerPanel, "3");

        int lives = 7;
        JLabel hiddenTitles = new JLabel("You have "+lives+"/7 lives left");
        gameMenu.add(hiddenTitles);

        String hiddenTitle = Logic.game.getHiddenTitle();
        //Remove the println function later
        System.out.println(hiddenTitle);
        JLabel hiddenWord = new JLabel("              "+hiddenTitle+"             ");
        hiddenWord.setFont(new Font("hiddenTitle", Font.PLAIN, 20));
        gameMenu.add(hiddenWord);

        JTextField enterGuess = new JTextField(10);
        enterGuess.setToolTipText("Your guess goes here");
        String guess = enterGuess.getText();
        gameMenu.add(enterGuess);

        JButton makeGuess = new JButton("Make Guess");
        makeGuess.addActionListener(e -> Logic.game(guess));
        gameMenu.add(makeGuess);
    }
}
