package com.example.snakeig;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;



public class HelloController {

    @FXML
    AnchorPane pane;

    @FXML
    Rectangle greenBox;

    ArrayList<Rectangle> body = new ArrayList<>();
    int bodyCounter = 0;

    @FXML
    Rectangle fruity;

    @FXML
    Button startButton;

    @FXML
    Label score;



    Random ran = new Random();


    ArrayList<Integer> xAxis = new ArrayList<>();
    ArrayList<Integer> yAxis = new ArrayList<>();

    ArrayList<Double> layoutXs = new ArrayList<>();
    ArrayList<Double> layoutYs = new ArrayList<>();
    ArrayList<String> directions = new ArrayList<>();

    int movements = 0;
    
    int squareSize = 20;

    int actions = 0;

    int fruitX;
    int fruitY;

    int distance;

    String direction = "";

    Integer scoreInt = 0;

    Boolean movingX = true;
    Boolean movingY = true;



    public void onUpKeyPress() {
        if(movingX)  {
            direction = "y";

            distance = -squareSize;
        }

    }

    public void onDownKeyPress() {
        if(movingX) {
            direction = "y";

            distance = squareSize;
        }
    }

    public void onLeftKeyPress() {
        if(movingY) {

            direction = "x";
            distance = -squareSize;
        }
    }

    public void onRightKeyPress() {
        if(movingY) {
            direction = "x";
            distance = squareSize;
        }
    }

    public void onEnterKeyPress() {
        for (int i = 0; i < 20; i++) {
            xAxis.add(16 + (squareSize * i));
            yAxis.add(34 + squareSize * i);
        }
        fruitX = xAxis.get(ran.nextInt(20));
        fruitY = yAxis.get(ran.nextInt(20));


        fruity.setLayoutX(fruitX);
        fruity.setLayoutY(fruitY);

        startButton.setDisable(true);
        startButton.setVisible(false);

        fruity.setVisible(true);

        score.setText("Score - " + scoreInt);
    }

    public void spawnFruit() {


        fruitX = xAxis.get(ran.nextInt(20));
        fruitY = yAxis.get(ran.nextInt(20));

        if (fruitX != greenBox.getLayoutX() && fruitY != greenBox.getLayoutY()) {
            fruity.setLayoutX(fruitX);
            fruity.setLayoutY(fruitY);
        } else spawnFruit();


    }

    public void ifFruit() {
        if ((greenBox.getLayoutX() == fruity.getLayoutX()) && (greenBox.getLayoutY() == fruity.getLayoutY())) {
            System.out.println("That was at");
            System.out.println("FruitX: " + fruity.getLayoutX() + "\n FruitY: " + fruity.getLayoutY());

            scoreInt++;
            score.setText("Score - " + scoreInt);

            spawnFruit();

            body.add(new Rectangle(squareSize, squareSize));
            if(bodyCounter % 2 == 0) {
                body.get(bodyCounter).setFill(Color.GREEN);
            }else{
                body.get(bodyCounter).setFill(Color.rgb(29, 144, 19));
            }
            body.get(bodyCounter).setLayoutX(greenBox.getLayoutX());
            body.get(bodyCounter).setLayoutY(greenBox.getLayoutY());
            pane.getChildren().add(body.get(bodyCounter));

            bodyCounter++;

        }
    }

    public void move() {




        if (greenBox.getLayoutY() < 460 && greenBox.getLayoutY() > 20 && greenBox.getLayoutX() > 0 && greenBox.getLayoutX() < 440) {
            System.out.println("GreenY: " + greenBox.getLayoutY() + "\n GreenX: " + greenBox.getLayoutX());
            if(direction.equalsIgnoreCase("y")){
                movingY = true;
                movingX = false;
                greenBox.setLayoutY(greenBox.getLayoutY() + distance);
            }else if(direction.equalsIgnoreCase("x")){
                movingX = true;
                movingY = false;
                greenBox.setLayoutX(greenBox.getLayoutX()+ distance);
            }
            layoutXs.add(greenBox.getLayoutX());
            layoutYs.add(greenBox.getLayoutY());
            directions.add(direction);

            if(bodyCounter>0){

                for(int i = 0; i < bodyCounter; i++){


                    body.get(i).setLayoutX(layoutXs.get(movements-(i+1)));
                    body.get(i).setLayoutY(layoutYs.get(movements-(i+1)));

                    if((body.get(i).getLayoutX() == greenBox.getLayoutX()) && (body.get(i).getLayoutY() == greenBox.getLayoutY())){
                        death();
                    }
                }
            }
            movements++;
            ifFruit();
            actions++;


        } else {
            death();

        }


    }
    public void death(){
        for(int i = 0; i < bodyCounter; i++){

            body.get(i).setVisible(false);
        }
        body.clear();
        bodyCounter = 0;
        scoreInt = 0;

        startButton.setText("You lost! click or enter to play again");

        startButton.setDisable(false);
        startButton.setVisible(true);

        distance = 0;

        fruity.setVisible(false);

        greenBox.setLayoutX(216);
        greenBox.setLayoutY(234);

    }

}
