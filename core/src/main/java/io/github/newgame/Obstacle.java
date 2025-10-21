package io.github.newgame;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public class Obstacle {
    private final Random rand = new Random();
    public float height, x;
    public boolean bottom;

    public Obstacle() {
        height = rand.nextFloat(45) * 10;
        x = Gdx.graphics.getWidth();
        bottom = rand.nextBoolean();
    }

}
