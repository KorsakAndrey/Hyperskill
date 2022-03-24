package battleship;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Sets {
    private static boolean setShip(String begin, String end, int distance, String name,
                                   String[][] fieldShips ,ArrayList<Ship> ships) {
        char firstChar = begin.charAt(0);
        char secondChar = end.charAt(0);
        int firstNumber = Integer.parseInt((String) begin.subSequence(1, begin.length()));
        int secondNumber = Integer.parseInt((String) end.subSequence(1, end.length()));
        int start = Integer.min(firstChar - 'A' + 1, secondChar - 'A' + 1);
        int stop = Integer.max(firstChar - 'A' + 1, secondChar - 'A' + 1);

        if((abs(secondChar - firstChar) == 0)){
            if ((abs(secondNumber - firstNumber) + 1) != distance){
                System.out.println("Error! Wrong length of the " + name + "! Try again:");
                return false;
            }
        }

        if(abs(secondNumber - firstNumber) == 0){
            if ((abs(secondChar - firstChar) + 1) != distance){
                System.out.println("Error! Wrong length of the " + name + "! Try again:");
                return false;
            }
        }

        if ((abs(secondNumber - firstNumber) != 0) && (abs(secondChar - firstChar) != 0)) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        for (int i = start - 1; i <= stop + 1 && i < fieldShips.length; i++ ){
            for (int j = firstNumber -1; j <= secondNumber +1 && j < fieldShips[i].length; j++) {
                if(fieldShips[i][j].contains("O")){
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
            }
        }
        if (firstNumber > secondNumber) {
            int temp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = temp;
        }
        for (int i = start; i <= stop ; i++ ){
            for (int j = firstNumber; j <= secondNumber ; j++) {
                fieldShips[i][j] = "O ";
            }
        }
        ships.add(new Ship(name,distance,begin, end));
        return true;
    }

    public static void setShips(String[][] fieldShips, ArrayList<Ship> ships) {
        int[] size ={
                5, 4, 3, 3, 2
        };
        String[] shipsName = {
                "Aircraft Carrier", "Battleship",
                "Submarine", "Cruiser", "Destroyer"
        };
        Scanner scanner = new Scanner(System.in);
        String begin;
        String end;

        for (int i = 0; i < size.length; i++) {
            System.out.println("Enter the coordinates of the " + shipsName[i] +  " (" + size[i] + " cells):\n");
            do {
                begin = scanner.next();
                end = scanner.next();

            } while (!setShip(begin, end, size[i], shipsName[i], fieldShips, ships));
            Field.print(fieldShips);

        }
    }
}
