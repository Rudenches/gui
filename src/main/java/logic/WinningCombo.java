package logic;

import logic.Cell;

public class WinningCombo {
    private final Cell startCell;
    private final Cell endCell;
    private final Cell directionCell;

    public WinningCombo(Cell startCell, Cell endCell, Cell directionCell) {
        this.startCell = startCell;
        this.endCell = endCell;
        this.directionCell = directionCell;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    public Cell getDirectionCell() {
        return directionCell;
    }
}

