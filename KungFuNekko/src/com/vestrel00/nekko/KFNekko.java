package com.vestrel00.nekko;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import com.vestrel00.nekko.actors.Actor;
import com.vestrel00.nekko.actors.Nekko;
import com.vestrel00.nekko.actors.components.Location;
import com.vestrel00.nekko.actors.states.CombatState;
import com.vestrel00.nekko.actors.states.FaceState;
import com.vestrel00.nekko.actors.states.HorizontalMotionState;
import com.vestrel00.nekko.actors.states.StatusState;
import com.vestrel00.nekko.actors.states.VerticalMotionState;
import com.vestrel00.nekko.interf.Drawable;
import com.vestrel00.nekko.interf.Updatable;
import com.vestrel00.nekko.maps.Map;
import com.vestrel00.nekko.maps.components.Background;
import com.vestrel00.nekko.ui.HUD;
import com.vestrel00.nekko.ui.components.HUDInputProcessor;


public class KFNekko implements ApplicationListener {

	public static final int VIEW_LOADING = 0;
	public static final int VIEW_GAME = 1;
	public static int view;
	
	public static SpriteBatch batch;
	public static Camera camera;
	public static Settings settings;
	public static Background background;
	public static Map map;
	public static Resource resource;
	public static HUD hud;
	public static Array<Actor> allies, enemies;
	public static Nekko player;
	
	public static final long UPS = 50L;
	public static final long UPDATE_TIME = 1000000000 / UPS;
	
	private  long lastUpdateTime, lastGCTime; 
	
	private static Array<Updatable> updatables;
	private static Array<Drawable> drawables;
	private static Array<Disposable> disposables;
	
	public static Color worldColor, targetWorldColor;
	public static float colorSpeed;
	
	@Override
	public void create() {
		view = VIEW_GAME;
		resource = new Resource();
		batch = new SpriteBatch();
		settings = new Settings();
		map = new Map();
		camera = new Camera();
		background = new Background(resource.atlas.findRegion("background"));
		
		initPlayer();
		
		hud = new HUD(player, new HUDInputProcessor(player));
	
		initVars();
		initArrays();
	}

	private void initPlayer() {
		
		Location location = new Location(240.0f, 160.0f, 8.0f, 
				22.0f, 100.0f, 18.0f);
		
		player = new Nekko(resource.atlas, location);
		player.setState(FaceState.RIGHT, 
				StatusState.ALIVE, 
				CombatState.IDLE, 
				HorizontalMotionState.IDLE, 
				VerticalMotionState.FALLING);
		
		location.setActor(player);
		camera.targetActor = player;
	}

	private void initVars() {
		worldColor = new Color(0.7f, 0.7f, 0.0f, 1.0f);
		targetWorldColor = new Color(worldColor);
		colorSpeed = 0.01f;
	}

	private void initArrays() {
		
		enemies = new Array<Actor>();
		allies = new Array<Actor>();
		allies.add(player);
		
		updatables = new Array<Updatable>();
		updatables.add(player);
		updatables.add(camera);
		updatables.add(hud);
		updatables.add(map);
		updatables.add(background);
		
		drawables = new Array<Drawable>();
		drawables.add(background);
		drawables.add(map);
		drawables.add(player);
		//drawables.add(hud);
		
		disposables = new Array<Disposable>();
		disposables.add(batch);
		disposables.add(resource);
		disposables.add(hud);
		
		
	}

	@Override
	public void dispose() {

		batch.dispose();
		resource.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		switch(view){
		case VIEW_LOADING:
			
			break;
		case VIEW_GAME:
			// update game objects
			
			// put max UPS 
			if(TimeUtils.nanoTime() - lastUpdateTime > UPDATE_TIME)
			{
				lastUpdateTime = TimeUtils.nanoTime();
				heapCheck();
				for(int i=0; i < updatables.size; i++)
					updatables.get(i).update();
				updateWorldColor();
			}
			
			
			//DRAW
			batch.setProjectionMatrix(camera.camera.combined);
			batch.setColor(Color.WHITE);
			
			batch.setColor(worldColor);
			batch.begin();
			for(int i=0; i<drawables.size; i++)
				drawables.get(i).draw(batch);
			batch.end();
			
			//draw separately since this conflicts with 
			//the SpriteBatch in use
			hud.draw(batch);
			
			break;
			
		
		}
		
	}

	
	private void updateWorldColor() {
		if(worldColor.r > targetWorldColor.r 
				&& (worldColor.r-=colorSpeed) < targetWorldColor.r){
			worldColor.r = targetWorldColor.r;
		}
		
		if(worldColor.g > targetWorldColor.g 
				&& (worldColor.g-=colorSpeed) < targetWorldColor.g){
			worldColor.g = targetWorldColor.g;
		}
		
		if(worldColor.b > targetWorldColor.b 
				&& (worldColor.b-=colorSpeed) < targetWorldColor.b){
			worldColor.b = targetWorldColor.b;
		}		
	}

	//if fps ever drops below 27 after 30 seconds from
	//the last call to System.gc(), we will then again attemp
	//to garbage collect.
	private void heapCheck() {
		
		if(Gdx.graphics.getFramesPerSecond() <27  && TimeUtils.nanoTime() - lastGCTime > 30000000000L){
		 lastGCTime = TimeUtils.nanoTime(); 	
		 System.gc();	
		}
	}
	
	
	public static void bumpWC(float r, float g, float b){
		worldColor.r += r;
		worldColor.g += g;
		worldColor.b += b;
		
		//clip
		if(worldColor.r > 1.0f)
		   worldColor.r = 1.0f;
		if(worldColor.g > 1.0f)
		   worldColor.g = 1.0f;
		if(worldColor.b > 1.0f)
		   worldColor.b = 1.0f;
			
	}
	

	/// /////////////////7
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
