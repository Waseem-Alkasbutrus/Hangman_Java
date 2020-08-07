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

    /**
     * checks the Movies file and picks a random title in the list
     * @return a string title of the movie that was picked
     */
    public static String randomMovie() {
        File movies = new File("Movies");
        Scanner movieReader;

        String movieChoice = null;
        int line = (int) (Math.random() * 139)+ 1;

        try {
            movieReader = new Scanner(movies);
            for (int i = 1; i <= line; i++) {
                if (movieReader.hasNextLine()) {
                    movieChoice = movieReader.nextLine();
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("[Error] movies file not found");
        } catch (Exception e) {
            System.out.println("[ERROR] couldn't select a movie");
        }
        return movieChoice;
    }

    /**
     * checks the countries file and picks a random title in the list
     * @return a string title of the country that was picked
     */
    public static String randomCountry() {
        File countries = new File("Countries");
        Scanner countryReader;

        String countryChoice = null;
        int line = (int) (Math.random() * 257)+ 1;

        try {
            countryReader = new Scanner(countries);
            for (int i = 1; i <= line; i++) {
                if (countryReader.hasNextLine()) {
                    countryChoice = countryReader.nextLine();
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("[Error] Countries file not found");
        } catch (Exception e) {
            System.out.println("[ERROR] couldn't select a Country");
        }
        return countryChoice;
    }

    public static String randomTitle(int gameId) {
        String[] gameTypes = {"Movies", "Countries"};
        File titles = new File(gameTypes[gameId]);
        Scanner reader;

        double lineCount = 0;

        while (true) {
            try {
                reader = new Scanner(titles);
                if (reader.hasNextLine()) {
                lineCount++;
                } else {
                    break;
                }
            } catch (Exception exception) {
                System.out.println("[ERROR] Line counter error!");
            }
        }
        String titleChoice = null;
        int line = (int) (Math.random() * lineCount)+ 1;

        try {
            reader = new Scanner(titles);
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

