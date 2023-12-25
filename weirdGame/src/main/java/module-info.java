module com.example.weirdgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.weirdgame to javafx.fxml;
    exports com.example.weirdgame;
}