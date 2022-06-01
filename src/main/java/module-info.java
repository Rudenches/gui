module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens Gui to javafx.fxml;
    exports Gui;
    exports logic;
    opens logic to javafx.fxml;
}