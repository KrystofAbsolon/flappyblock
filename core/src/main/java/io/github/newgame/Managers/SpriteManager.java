package io.github.newgame.Managers;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteManager {
    private static SpriteManager instance;
    public final SpriteBatch batch;
    public final HashMap<String, Texture> textures;

    private SpriteManager() {
        batch = new SpriteBatch();
        textures = new HashMap<>();
    }

    public static SpriteManager getInstance() {
        if(instance==null) {
            instance = new SpriteManager();
        }
        return instance;
    }

    public void beginBatch() {
        batch.begin();
    }

    public void endBatch() {
        batch.end();
    }

    public void disposeBatch() {
        batch.dispose();
    }

    public void draw(String texture, float x, float y) {
        batch.draw(textures.get(texture), x, y);
    }

    public void draw(String texture, float x, float y, float width, float height) {
        batch.draw(textures.get(texture), x, y, width, height);
    }
}