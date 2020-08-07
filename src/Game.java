import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private static String title;
    private static char[] hiddenTitle;

    Game() {
        title = "[ERROR] PLEASE ENTER GAMEID!";
        hiddenTitle = hideTitle(title);
    }

    Game(int gameId) {
        title = randomTitle(gameId);
        hiddenTitle = hideTitle(title);
    }

    /**
     * generates a string that is a replica of the title, where all characters are turned into '_' (provided they are an int or an alphabet letter)
     * @param title the title of the movie
     * @return a string with all alphabetical letters are swapped for '_'
     */
    public static char[] hideTitle(String title) {

        char[] hiddenTitle = title.toCharArray();

        for(int i = 0; i < title.length(); i++) {
            if((hiddenTitle[i] >= 'a' && hiddenTitle[i] <= 'z') || (hiddenTitle[i] <= '0' && hiddenTitle[i] >= '9')) {
                hiddenTitle[i] = '_';
            }
        }
        return hiddenTitle;
    }

    public static String randomTitle(int gameId) {
        String[] gameTypes = {"Movies", "Countries"};
        String gameType = gameTypes[gameId];
        File titles = new File(gameType);
        double lineCount = 0;
            try {
                Scanner fileCounter = new Scanner(titles);
                while (fileCounter.hasNextLine()) {
                    fileCounter.nextLine();
                    lineCount++;
                }
            } catch (Exception exception) {
                System.out.println("[ERROR] Line counter error!");
            }

        String titleChoice = null;
        int line = (int) (Math.random() * lineCount)+ 1;

        try {
            Scanner reader = new Scanner(titles);
            for (int i = 1; i <= line; i++) {
                if (reader.hasNextLine()) {
                    titleChoice = reader.nextLine();
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("[Error] Titles file not found");
        } catch (Exception e) {
            System.out.println("[ERROR] couldn't select a Title");
        }
        return titleChoice;
    }

    public static String getTitle() {
        return title;
    }

    public static char[] getHiddenTitle() {
        return hiddenTitle;
    }

}

