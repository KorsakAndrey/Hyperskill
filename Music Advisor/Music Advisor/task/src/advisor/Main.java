package advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner commandScanner = new Scanner(System.in);
        boolean appIsOn = true;

        for (int i = 0; i < args.length; i++){
            if(args[i].equals("-access")){
                if(args[i+1] != null) {
                    Controller.url = args[i+1];
                }
            }
            if(args[i].equals("-resource")){
                if(args[i+1] != null) {
                    Controller.serverApiPath = args[i+1];
                }
            }
            if(args[i].equals("-page")){
                if(args[i+1] != null) {
                    Controller.page = Integer.parseInt(args[i+1]);
                }
            }
        }

        while (appIsOn) {
            String command = commandScanner.nextLine();
            if (command.equals("exit")) {
                //appIsOn = false;
            }
            Controller.completing(command);
        }
    }
}
