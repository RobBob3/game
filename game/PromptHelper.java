package game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class PromptHelper {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static boolean askYesOrNo(String questionAsked) {
        String questionAnswer;
        boolean answeredYes = true;
        boolean answeredQuestion = false;
        System.out.println(questionAsked);
        do {
            try {
                questionAnswer = reader.readLine();
                if (questionAnswer.equals("Y") || questionAnswer.equals("y")) {
                    answeredYes = true;
                    answeredQuestion = true;
                }
                else if (questionAnswer.equals("N") || questionAnswer.equals("n")) {
                    answeredYes = false;
                    answeredQuestion = true;
                }
                else {
                    System.out.println("You didn't answer the question, try again");
                }
            }
            catch(IOException ignored) {
            }
        }
        while(answeredQuestion == false);
        return answeredYes;
    }

    public static void printDivider() {
        System.out.println("================================");
    }

    public static void promptForEnter() {
        System.out.println("Press enter to continue.");
        try {
            reader.readLine();
        } catch (IOException ignored) {
        }
    }

    public static void pause(int timePaused) {
        try {
            Thread.sleep(timePaused);
        }
        catch(InterruptedException ignored) {
        }
    }

    public static int promptForChoice(int numberOfChoices, String questionAsked) {
        String userInput = null;
        do {
            try {
                System.out.println(questionAsked);
                userInput = reader.readLine();
                int numberEntered = Integer.parseInt(userInput);
                if (numberEntered < numberOfChoices && numberEntered >= 0) {
                    return numberEntered;
                } else {
                    System.out.println("That is not valid.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("That is not valid.");
            }
            catch (IOException e) {
            }
        }
        while (true);
    }
}

