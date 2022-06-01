package logic;


import java.util.*;

public class Board {
    private Cell turnCell;
    private final Set<Cell> set = new HashSet<>();
    static public final int TO_WIN_LENGTH = 5;
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Set<Cell> getSet() {
        return set;
    }

    public void makeTurn(Cell cell) {
        turnCell = cell;
    }

    public void updateBoard(Boolean cellColor) {
        turnCell.setColor(cellColor);
        set.add(turnCell);

    }

    private Cell getCellFromSet(Cell currentCell) {
        for (Cell item : set)
            if (item.equals(currentCell)) return item;
        return null;
    }

    public WinningCombo winningCombo(Cell cell) {
        if (!set.contains(cell)) return null;
        int line = 0;
        Cell startCell = null;
        Cell endCell = null;
        Cell step = new Cell(1, 1);
        for (int direction = 0; direction < 4; direction++) {
            for (int i = 0; i < 2; i++) {
                Cell currentCell = cell;
                Boolean stepColor;
                do {
                    line++;
                    if (i == 0) {
                        startCell = currentCell;
                        currentCell = currentCell.plus(step);
                    } else {
                        endCell = currentCell;
                        currentCell = currentCell.minus(step);
                    }
                    if (getCellFromSet(currentCell) == null) break;
                    stepColor = getCellFromSet(currentCell).getColor(); // not null
                } while (stepColor == getCellFromSet(cell).getColor()); // not null
            }
            if (line > TO_WIN_LENGTH) {
                return new WinningCombo(startCell, endCell, step);
            } else {
                line = 0;
                if (direction == 0) step = new Cell(1, 0);
                else if (direction == 1) step = new Cell(0, 1);
                else step = new Cell(-1, 1);
            }
        }
        return null;
    }

    public void clearBoard() {
        set.clear();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

