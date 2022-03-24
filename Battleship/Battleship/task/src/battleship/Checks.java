package battleship;

import java.util.ArrayList;

public class Checks {
    public static boolean checkShot(int first, int second) {
        if (!((first > 0 && first <= 10) && (second > 0 && second <= 10 ))) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }
        return true;
    }

    public static boolean checkShips(ArrayList<Ship> ships, String[][] fieldMarks) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).isSunk(fieldMarks)) {
                ships.remove(i);
                if(ships.isEmpty()) {
                    System.out.println("You sank the last ship. You won. Congratulations!\n");
                } else {
                    System.out.println("You sank a ship! Specify a new target:\n");
                }
                return true;
            }
        }
        return false;
    }
}
