package com.mygdx.game;


import android.graphics.Color;
import android.provider.SyncStateContract;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.graphics.Camera;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class MyGdxGame implements ApplicationListener,GestureDetector.GestureListener {
	SpriteBatch batch;
    Texture glowBlock;
    Texture background;
    ArrayList<WObject> Sprites = new ArrayList<WObject>();
    GestureDetector gd;
    int h,w;
    Sprite backgroundSprite;

    World world = new World(new Vector2(0, -10), true);
    Box2DDebugRenderer debugRenderer;

    private double currentTime;
    private float accumulator = 0;

    com.badlogic.gdx.graphics.Camera camera;

    @Override
	public void create () {
		batch = new SpriteBatch();
        debugRenderer =  new Box2DDebugRenderer();
        glowBlock = new Texture("gaben.png");
        background = new Texture("gloriouspc.jpg");
        backgroundSprite = new Sprite(background);

       // Sprites.add(backgroundSprite);
        gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w,h);
        camera.normalizeUp();
        camera.update();
        backgroundSprite.setSize(w,h);


	}


	@Override
	public void render () {

        double newTime = TimeUtils.millis() / 1000.0;
        double frameTime = Math.min(newTime - currentTime, 0.25);
        float deltaTime = (float)frameTime;

        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }


		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(background,0,0);
        batch.setColor(Color.WHITE);
        backgroundSprite.draw(batch);
        for(WObject cur : Sprites){
            cur.draw(batch);
            cur.glowSprite.rotate(1f);
        }
        //glowSprite.translate(1,0);
        //debugRenderer.render(world, camera.combined);

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




        //Sprite glowSprite = new com.badlogic.gdx.graphics.g2d.Sprite(glowBlock);
        WObject o = new WObject(camera,world,glowBlock,x,y,h,w);
        Sprites.add(o);
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

class WObject{
    Sprite glowSprite;
    static BodyDef bodyDef = new BodyDef();
    Body body;
    CircleShape circle;
    FixtureDef fixtureDef;
    Fixture fixture;
    WObject(Camera camera,World world,Texture t,float x,float y, float h,float w){

        glowSprite = new com.badlogic.gdx.graphics.g2d.Sprite(t);

        bodyDef.type = BodyDef.BodyType.DynamicBody;
     //   Vector3 pos = new Vector3(x, y, 0);
     //   camera.unproject(pos);
        bodyDef.position.set(h/2-(glowSprite.getWidth()/2),w/2-(glowSprite.getHeight()/2));
        //bodyDef.position.set(pos.y,pos.x);

        body = world.createBody(bodyDef);

        circle = new CircleShape();
        circle.setRadius(35f);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 12f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;


        fixture = body.createFixture(fixtureDef);

        glowSprite.setY(body.getPosition().y);
        glowSprite.setX(body.getPosition().x);
    }

    void draw(SpriteBatch b){
        glowSprite.setY(body.getPosition().y);
        glowSprite.setX(body.getPosition().x);
        glowSprite.draw(b);

    }
}