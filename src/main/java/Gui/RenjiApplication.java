package Gui;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RenjiApplication extends Application {
    private static BorderPane root;
    private static GridPane grid;
    private static Scene scene;
    private static RenjiView view;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        RenjiApplication.stage = stage;
        restart();
    }

    public static void restart() {
        root = new BorderPane();
        grid = new GridPane();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(root, 900, 750, Color.LIGHTBLUE);
        view = new RenjiView();
        view.field(stage, root, grid, scene);

    }

    public static void main(String[] args) {
        launch();


    }
}