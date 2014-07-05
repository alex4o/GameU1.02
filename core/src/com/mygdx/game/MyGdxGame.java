package com.mygdx.game;

import android.graphics.Color;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MyGdxGame implements ApplicationListener,GestureDetector.GestureListener {
	SpriteBatch batch;
    Texture glowBlock;
    Texture background;
    ArrayList<Sprite> Sprites = new ArrayList<Sprite>();
    GestureDetector gd;
    int h,w;
    Sprite backgroundSprite;
	@Override
	public void create () {
		batch = new SpriteBatch();

        glowBlock = new Texture("gaben.png");
        background = new Texture("gloriouspc.jpg");
        backgroundSprite = new com.badlogic.gdx.graphics.g2d.Sprite(background);

       // Sprites.add(backgroundSprite);
        gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        backgroundSprite.setSize(w,h);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        //batch.draw(background,0,0);
        batch.setColor(Color.WHITE);
        backgroundSprite.draw(batch);
        for(Sprite cur : Sprites){
            cur.draw(batch);
            cur.rotate(1f);
        }
        //glowSprite.translate(1,0);

        //glowSprite.draw(batch);

		batch.end();
	}

    public void dispose() {
        batch.dispose();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Sprite glowSprite = new com.badlogic.gdx.graphics.g2d.Sprite(glowBlock);
        glowSprite.setY((h - y)-(glowSprite.getHeight()/2));
        glowSprite.setX(x-(glowSprite.getWidth()/2));
        Sprites.add(glowSprite);
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {


        return true;
    }

    @Override
    public boolean longPress(float x, float y) {

        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {

        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,Vector2 pointer1, Vector2 pointer2) {

        return true;
    }

    @Override
    public boolean panStop(float a,float b,int c,int d){

        return true;
    }

}
