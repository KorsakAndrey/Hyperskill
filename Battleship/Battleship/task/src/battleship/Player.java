package battleship;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final Field field;
    private final ArrayList<Ship> ships;

    public Player(String name) {
        this.name = name;
        this.field = new Field();
        this.ships = new ArrayList<>();

        System.out.println(name + ", place your ships on the game field\n");
        Field.print(this.field.getShips());
        Sets.setShips(this.field.getShips(), this.ships);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

    public String[][] grtFieldShips() {
        return this.field.getShips();
    }

    public String[][] grtFieldMarks() {
        return this.field.getMarks();
    }
}
