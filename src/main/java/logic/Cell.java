package logic;

import java.util.Objects;

public class Cell {
    private final int x;
    private final int y;
    private boolean color; // if true -> black, else white

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell minus(Cell cell) {
        return new Cell(x - cell.x, y - cell.y);
    }

    public Cell plus(Cell cell) {
        return new Cell(x + cell.x, y + cell.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
