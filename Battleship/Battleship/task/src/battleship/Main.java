package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    private static void shots(Player attack, Player protect){
        Scanner scanner = new Scanner(System.in);
        String shotPoint;
        int first;
        int second;
        String[][] field = protect.grtFieldShips();
        String[][] fieldMarks = protect.grtFieldMarks();
        ArrayList<Ship> ships = protect.getShips();

        Field.printFields(protect.grtFieldMarks(), attack.grtFieldShips());
        System.out.println(attack.getName() + ", it's your turn:");

        do{
            shotPoint = scanner.next();
            first = shotPoint.charAt(0) - 'A' + 1;
            second = Integer.parseInt((String) shotPoint.subSequence(1, shotPoint.length()));
        }while (!Checks.checkShot(first, second));
        if (field[first][second].contains("O")) {
            field[first][second] = "X ";
            fieldMarks[first][second] = "X ";
            if (!Checks.checkShips(ships, fieldMarks)){
                System.out.println("You hit a ship!");
            }
        } else if (field[first][second].contains("~")) {
            field[first][second] = "M ";
            fieldMarks[first][second] = "M ";
            System.out.println("You missed!");
        } else if (field[first][second].contains("X")){
            Field.print(fieldMarks);
            System.out.println("You missed!");
        } else {
            Field.print(fieldMarks);
            System.out.println("You missed!");
        }

    }

    public static void changePlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player\n");
        while (!scanner.nextLine().equals("")){}
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void battle(Player attack, Player protect){
        while (true){
            shots(attack, protect);
            if (protect.getShips().isEmpty()) {
                return;
            }
            changePlayer();
            shots(protect, attack);
            if (attack.getShips().isEmpty()) {
                return;
            }
            changePlayer();
        }


    }

    public static void main(String[] args) {


        /*====GAME====*/
        //Player1
        Player player1 = new Player("Player 1");
        changePlayer();
        //Player2
        Player player2 = new Player("Player 2");
        changePlayer();

        battle(player1, player2);
    }
}
