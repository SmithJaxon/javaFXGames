module com.example.snakeig {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeig to javafx.fxml;
    exports com.example.snakeig;
}