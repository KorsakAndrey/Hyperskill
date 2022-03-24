package advisor;

import java.util.Scanner;

public class Controller {
    private static String firsCommand = null;
    public static String secondCommand = null;
    private static boolean authorized = false;
    public static String url = "https://accounts.spotify.com";
    public static String serverApiPath = "https://api.spotify.com";
    public static int page = 5;
    public static String accessToken;

    private static void setCommand(String command) {
        Scanner scanner = new Scanner(command);
        firsCommand = scanner.next();
        if (scanner.hasNext()) {
            secondCommand =scanner.nextLine().replaceFirst(" ", "");
        }
    }

    public static void completing (String command) {
        setCommand(command);
        if(firsCommand.equals("auth")){
            authorized = Authorization.auth(url);
        }
        if(authorized || firsCommand.equals("exit")) {
            Commands.answer(firsCommand, secondCommand);
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}
