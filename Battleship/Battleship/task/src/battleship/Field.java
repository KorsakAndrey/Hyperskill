package battleship;

public class Field {
    private final String[][] fieldShips = new String[11][11];
    private final String[][] fieldMarks = new String[11][11];

    public Field(){
        build(this.fieldShips);
        build(this.fieldMarks);
    }

    public static void print(String[][] in) {
        for (int i = 0; i < 11; i++ ) {
            for (int j = 0; j < 11; j++) {
                System.out.print(in[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void printFields(String[][] enemy, String[][] my) {
        for (int i = 0; i < 11; i++ ) {
            for (int j = 0; j < 11; j++) {
                System.out.print(enemy[i][j]);
            }
            System.out.print("\n");
        }

        System.out.println("---------------------");

        for (int i = 0; i < 11; i++ ) {
            for (int j = 0; j < 11; j++) {
                System.out.print(my[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


    private static void build(String[][] field){
        field[0][0] = "  ";

        for(int i = 1; i < field.length; i++) {
            field[0][i] = Integer.toString(i) + " ";
        }

        for(int i = 1; i < field[0].length; i++) {
            char temp = (char)((int)'A' + i - 1);
            field[i][0] = Character.toString(temp) + " ";
        }

        for (int i = 1; i < field.length; i++ ) {
            for (int j = 1; j < field[i].length; j++) {
                field[i][j] = "~ ";
            }
        }
    }

    public String[][] getShips() {
        return this.fieldShips;
    }

    public String[][] getMarks() {
        return this.fieldMarks;
    }
}
