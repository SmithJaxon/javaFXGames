package com.example.weirdgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Circle {
    private double startingX;
    private double startingY;

    private double radius;
    private boolean isAlive;


    public Bullet(double v, double startingX, double startingY, Color color) {
        //Sets some basic values
        super(v);
        radius = v;
        this.startingX = startingX;
        this.startingY = startingY;

        setLayoutX(startingX);
        setLayoutY(startingY);

        setFill(color);
        isAlive = true;
    }

    //These are methods for getting the position of an element on the screen
    //This is needed since animations change their visual position, but not their
    //Transform position

    public double getRealPositionX(){
        return startingX + getTranslateX();
    }

    public double getRealPositionY(){
        return startingY + getTranslateY();
    }

    //I never used this, but it was easy code to write in case I need it
    public boolean isTouchingSquare(double top, double left, double right, double bottom){



        return (getRealPositionX() -radius >= left && getRealPositionX()+radius <= right) && (getRealPositionY()-radius >= top && getRealPositionY()+radius <= bottom);

    }

    //This checks for collisions with other circles
    public boolean isTouchingCircle(double x, double y, double radius){
        return Math.abs(getRealPositionX() - x) < radius && Math.abs(getRealPositionY() - y) < 2.05*radius;
    }

    //These set and check values that decide if the object is 'alive'
    //Objects are only checked for collisions while 'alive'
    public boolean getIsAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
