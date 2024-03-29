package com.vestrel00.nekko.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Disposable;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.actors.Nekko;
import com.vestrel00.nekko.interf.Drawable;
import com.vestrel00.nekko.interf.Touchable;
import com.vestrel00.nekko.interf.Updatable;
import com.vestrel00.nekko.ui.components.HUDInputProcessor;
import com.vestrel00.nekko.ui.components.HUDPad;

public class HUD implements Updatable, Drawable, Disposable, Touchable{

	private ShapeRenderer shape;
	private HUDPad pad;
	
	
	public HUD(Nekko player, HUDInputProcessor processor){
		shape = new ShapeRenderer();
		pad = new HUDPad(player,processor);
		Gdx.input.setInputProcessor(processor);
	}
	
	@Override
	public void update() {
		pad.update();
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		shape.setProjectionMatrix(KFNekko.camera.camera.combined);
		shape.setColor(Color.BLACK);
		shape.begin(ShapeType.FilledRectangle);
		
		//draw bottom over
		shape.filledRect(KFNekko.camera.rect.x, 
						 KFNekko.camera.rect.y, 
						 KFNekko.camera.rect.width, 
						 KFNekko.camera.rect.height * 0.21f);
	
		//draw top cover
		shape.filledRect(KFNekko.camera.rect.x, 
						 KFNekko.camera.rect.y + KFNekko.camera.rect.height * 0.9f, 
						 KFNekko.camera.rect.width, 
						 KFNekko.camera.rect.height * 0.1f);
		shape.end();
		
		
		shape.begin(ShapeType.FilledCircle);
		//top left circle
		shape.filledCircle(KFNekko.camera.rect.x + 50.0f, 
				   KFNekko.camera.rect.y+350.0f, 100.0f);
		//top right circle
		shape.filledCircle(KFNekko.camera.rect.x + 430.0f, 
				   KFNekko.camera.rect.y+350.0f, 100.0f);
		//bottom left circle
		shape.filledCircle(KFNekko.camera.rect.x + 90.0f, 
				   KFNekko.camera.rect.y, 100.0f);
		
		shape.end();
		
		batch.begin();
		pad.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}

	@Override
	public boolean onTouchDown(float x, float y) {
		
		return pad.onTouchDown(x, y);
	}

}
