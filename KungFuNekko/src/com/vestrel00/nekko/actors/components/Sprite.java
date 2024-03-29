package com.vestrel00.nekko.actors.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.actors.Actor;
import com.vestrel00.nekko.actors.states.Visibility;
import com.vestrel00.nekko.interf.Drawable;
import com.vestrel00.nekko.interf.Updatable;

public abstract class Sprite implements Updatable, Drawable {

	protected Actor actor;
	protected AtlasRegion currentTexture;
	protected float xScale;
	protected long animationDelay, lastAnimationTime;
	
	
	public Sprite(Actor actor){
		this.actor = actor;
		lastAnimationTime = 0L;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		if(actor.visibility == Visibility.VISIBLE){
			batch.setColor(Color.WHITE);
			batch.draw(currentTexture, actor.location.rect.x, actor.location.rect.y, 
					actor.location.rect.width * 0.5f,
					actor.location.rect.height * 0.5f, 
					actor.location.rect.width, 
					actor.location.rect.height, xScale, 1.0f, 0.0f);
			batch.setColor(KFNekko.worldColor);
		}
		
	}

}
