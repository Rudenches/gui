package Gui;


import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Board;
import logic.Cell;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RenjiView {
    int rows = 16;
    int columns = 16;
    private final Board board = new Board(columns, rows);
    private final Map<Cell, Boolean> existButtons = new HashMap<>(); // стоит ли шашка true -> да
    private final Map<Cell, Button> buttons = new HashMap<>();
    private Boolean inProcess = true; // false -> игра закончена
    private Boolean cellColor = false; // false -> white, true -> black
    private Boolean isWinningCombo = false;
    Label label = new Label();
    Label won = new Label();

    public void field(Stage stage, BorderPane root1, GridPane gridPane, Scene scene) throws NullPointerException {
        Pane pane = new Pane();
        Pane LabelPane = new Pane();

        label.setText("Black turn");
        label.setFont(Font.font("Times new Roman", 20));
        label.setLayoutX(410);
        label.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        LabelPane.getChildren().add(label);


        Button restartButton = new Button();
        restartButton.setLayoutX(425);
        restartButton.graphicProperty().setValue(new ImageView(new
                Image(Objects.requireNonNull(getClass().getResourceAsStream("pages/res.jpg")))));
        restartButton.setMinSize(50, 40);
        restartButton.setMaxSize(50, 40);

        restartButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                restartGame();
            }
        });
        pane.getChildren().add(restartButton);

        stage.setTitle("Renji");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        for (int column = 0; column <= columns; column++) {
            ColumnConstraints Column = new ColumnConstraints(40, 40, 40);
            gridPane.getColumnConstraints().add(Column);

            RowConstraints Row = new RowConstraints(40, 40, 40);
            gridPane.getRowConstraints().add(Row);

            for (int row = 0; row <= rows; row++) {

                Button button1 = new Button();
                button1.setMaxSize(40, 40);
                button1.setMinSize(40, 40);

                if (column == 0 && row == 0)
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/cellTopLeft.png"))));

                else if (column == 0 & row == 16)
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/cellBotLeft.png"))));

                else if (column == 16 & row == 16)
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/cellBotRight.png"))));

                else if (column == 16 & row == 0)
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/cellTopRight.png"))));

                else if (column == 16)
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/verticalRight.png"))));

                else if (column == 0)
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/verticalLeft.png"))));

                else if (row == 16)
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/horizontalBot.png"))));
                else if (row == 0)
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/horizontalTop.png"))));

                else
                    button1.graphicProperty().setValue(new ImageView
                            (new Image(getClass().getResourceAsStream("pages/cell.png"))));

                gridPane.add(button1, column, row);

                Cell cell = new Cell(row, column);

                button1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (inProcess) {
                            board.makeTurn(cell);
                            updateGame(cell);
                        }
                    }
                });

                if (row != 0 && row != 16 && column != 0 && column != 16)
                    buttons.put(cell, button1);
            }
        }

        gridPane.setLayoutX(130);
        gridPane.setLayoutY(25);
        root1.getChildren().add(gridPane);
        root1.setCenter(won);

        root1.setBottom(pane);
        root1.setTop(LabelPane);
    }

    private void updateGame(Cell cell) {
        if (0 < cell.getX() && cell.getX() < 16 && 0 < cell.getY() && cell.getY() < 16) {
            for (var entry : buttons.entrySet()) {
                var button = entry.getValue();
                board.updateBoard(cellColor);

                EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (inProcess) {
                            if (!existButtons.containsKey(cell)) {
                                cellColor = !cellColor;
                                if (cellColor) {
                                    button.graphicProperty().setValue(new ImageView(
                                            new Image(getClass().getResourceAsStream("pages/cageBlack.png"))));

                                } else {
                                    button.graphicProperty().setValue(new ImageView(new Image(
                                            getClass().getResourceAsStream("pages/cellWhite.png"))));
                                }
                                existButtons.put(cell, true);
                                if (isWinningCombo) inProcess = false;
                                if (!inProcess && cellColor) {
                                    label.setText("the end, Black won ");
                                    label.setLayoutX(380);
                                } else if (!inProcess) {
                                    label.setText("the end, White won");
                                    label.setLayoutX(380);
                                } else if (cellColor) {
                                    label.setText("White turn");
                                } else label.setText("Black turn");
                            }
                        }
                    }
                };

                if (inProcess) {
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
                }
            }
            if (board.winningCombo(cell) != null) {
                won.setScaleX(10);
                won.setScaleY(10);
                won.setFont(Font.font("Times new Roman",17));
                won.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                if (!cellColor) {
                    won.setText("Black won");
                } else won.setText("White won");
                isWinningCombo = true;
            }
        }
    }

    private void restartGame() {
        inProcess = true;
        cellColor = false;
        board.clearBoard();
        label.setText("black turn");
        label.setLayoutX(420);
        won.setText("");
        isWinningCombo = false;
        existButtons.clear();
        buttons.clear();
        RenjiApplication.restart();

    }
}
