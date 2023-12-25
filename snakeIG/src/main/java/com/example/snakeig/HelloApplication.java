package com.example.snakeig;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class HelloApplication extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    public void mainWindow() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        AnchorPane pane = loader.load();

        HelloController controller = loader.getController();


        Scene scene = new Scene(pane);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.move();
                    }
                });
            }
        };
        timer.schedule(task, 0, 110);


        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP, W -> controller.onUpKeyPress();
                case DOWN, S -> controller.onDownKeyPress();
                case LEFT, A -> controller.onLeftKeyPress();
                case RIGHT, D -> controller.onRightKeyPress();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("mySnake");
        primaryStage.show();
        primaryStage.setResizable(true);


    }




    public static void main(String[] args) {


        launch(args);
    }

}
