package battleship;

import static java.lang.Math.*;

public class Ship {
    private final String name;
    private final int size;
    private final int beginY;
    private  final int beginX;
    private final int endY;
    private final int endX;

    public Ship(String name, int size, String begin, String end) {
        this.name = name;
        this.beginX = min(Integer.parseInt((String) begin.subSequence(1, begin.length())),
                Integer.parseInt((String) end.subSequence(1, end.length())));
        this.endX = max(Integer.parseInt((String) begin.subSequence(1, begin.length())),
                Integer.parseInt((String) end.subSequence(1, end.length())));
        this.beginY = Integer.min(begin.charAt(0) - 'A' + 1, end.charAt(0) - 'A' + 1);
        this.endY = Integer.max(begin.charAt(0) - 'A' + 1, end.charAt(0) - 'A' + 1);
        this.size = size;
    }

    public boolean isSunk(String[][] fieldMarks) {
        for (int i = beginY;  i <= endY; i++){
            for (int j = beginX; j <= endX; j++) {
                if (!fieldMarks[i][j].equals("X ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
