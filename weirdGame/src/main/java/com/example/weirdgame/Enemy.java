package com.example.weirdgame;

import javafx.scene.paint.Color;

public class Enemy extends Bullet{
    //Inherits from bullet, so it has all the same properties.
    //It was created in case I wanted to set the values of
    //Animations etx in the objects rather than in the
    //Controller class

    public Enemy(double v, double startingX, double startingY, Color color) {
        super(v, startingX, startingY, color);

    }


}
