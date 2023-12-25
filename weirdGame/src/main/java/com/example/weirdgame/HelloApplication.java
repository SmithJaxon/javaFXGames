package com.example.weirdgame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {

    //This whole file is for Creating and managing the javaFX scene
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 505);
        stage.setTitle("TankGame");
        stage.setScene(scene);

        //This chunk of code is for creating a 'tick' timer in the game
        //Which is code that runs every x amount of time
        //It doesn't work in the controller class, so I did it here
        HelloController controller = fxmlLoader.getController();
        Timer timer = new Timer();
        TimerTask frameCounter = new TimerTask() {
            @Override
            public void run() {
                //This platform runLater is needed because the operations
                //In the controller class run on a separate thread so this
                //Gives the other thread the code to run rather than
                //Running it on the current thread. This is part of the JavaFX library
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.tickTimer();
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(frameCounter, 1000, 20);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}