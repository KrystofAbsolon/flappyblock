package io.github.newgame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.newgame.Managers.SpriteManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    SpriteManager sm;
    ShapeRenderer sr;
    float x, y, vel, grav;
    ArrayList<Obstacle> obstacles;

    @Override
    public void create() {
        sm = SpriteManager.getInstance();
        sr = new ShapeRenderer();
        x = 0;
        y = 200;
        vel = 0;
        grav = -3;
        obstacles = new ArrayList<>();
    }

    public void addTexture(String path, String name) {
        sm.textures.put(name, new Texture(path));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        sm.beginBatch();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.PINK);
        sr.rect(100, y, 64, 64);
        sr.setColor(Color.WHITE);
        if(y + 64 < 0 || y > Gdx.graphics.getHeight()) Gdx.app.exit();
        for(Obstacle o: obstacles) { 
            sr.rect(o.x, o.bottom ? 0: Gdx.graphics.getHeight() - o.height, 100, o.height);
            if(o.x >= 0 && o.x <= 164 && ((o.bottom &&  y <= o.height) || (!o.bottom && y + 64 >= Gdx.graphics.getHeight() - o.height))) Gdx.app.exit();
            o.x-=10;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) vel += 100 * (vel + Math.sin(1));
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        y += vel + grav;
        vel = 0;
        x--;
        sr.end();
        sm.endBatch();
        if(x%60==0) obstacles.add(new Obstacle());
    }

    @Override
    public void dispose() {
        sm.disposeBatch();
    }
}
