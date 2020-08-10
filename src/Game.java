import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private static String title;
    private static String hiddenTitle;

    Game() {
        title = "[ERROR] PLEASE ENTER GAME-ID!";
        hiddenTitle = hideTitle(title);
    }

    Game(String FileName) {
        title = randomTitle(FileName);
        hiddenTitle = hideTitle(title);
    }

    public static String randomTitle(String FileName) {
        File titles = new File(FileName);
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

    /**
     * generates a string that is a replica of the title, where all characters are turned into '_' (provided they are an int or an alphabet letter)
     * @param title the title of the movie
     * @return a string with all alphabetical letters are swapped for '_'
     */
    public static String hideTitle(String title) {

        String hiddenTitle = "";
        char[] titleChars = title.toCharArray();

        for(int i = 0; i < title.length(); i++) {
            if((titleChars[i] >= 'a' && titleChars[i] <= 'z') || (titleChars[i] <= '0' && titleChars[i] >= '9')) {
                hiddenTitle += '_';
            } else {
                hiddenTitle += titleChars[i];
            }
        }
        return hiddenTitle;
    }

    public static String getTitle() {
        return title;
    }

    public static String getHiddenTitle() {
        return hiddenTitle;
    }

}
