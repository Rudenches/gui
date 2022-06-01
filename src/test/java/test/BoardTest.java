package test;

import logic.Board;
import logic.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void winningCombo() {

    }
    @Test
    public void winningComboDiagonalTest() {
        Board board = new Board(16, 16);
        for (int i = 1; i < 6; i++) {
            board.makeTurn(new Cell(i, i));
            board.updateBoard(true);
        }
        if (board.winningCombo(new Cell(3, 3)) != null) {
            assertNotNull(board.winningCombo(new Cell(5, 5)));
        }
        else fail();
    }

    @Test
    public void winningComboHorizontalTest(){
        Board board = new Board(16,16);
        for (int i = 3; i < 8; i++) {
            board.makeTurn(new Cell(i,5));
            board.updateBoard(true);
        }
        if (board.winningCombo(new Cell(5,5)) != null) {
            assertNotNull(board.winningCombo(new Cell(7,5)));
        }
        else fail();
    }
    @Test
    public void winnigComboVerticalTest() {
        Board board = new Board(16,16);
        for (int i = 4; i < 9; i++) {
            board.makeTurn(new Cell(6,i));
            board.updateBoard(false);
        }
        if (board.winningCombo(new Cell(6,5)) != null) {
            assertNotNull(board.winningCombo(new Cell(6,6)));
        }
        else  fail();
    }
}
