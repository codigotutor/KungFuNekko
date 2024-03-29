package com.vestrel00.nekko.maps.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.interf.Drawable;
import com.vestrel00.nekko.interf.Updatable;

public class Background implements Updatable, Drawable{
	
	private AtlasRegion background;
	private float yOffset, xOffset, maxXOffset, xOffsetRatio;
	
	public Background(AtlasRegion background)
	{
		this.background = background;
		//the height of the bottom hud cover
		yOffset = KFNekko.settings.viewHeight * 0.1f;
		//calculate the mount of unseen space in the texture
		maxXOffset = (float)background.originalWidth - KFNekko.settings.viewWidth;
		//ratio of map width to maxOffset
		xOffsetRatio = maxXOffset / KFNekko.map.width;
		
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(background, KFNekko.camera.rect.x - xOffset, KFNekko.camera.rect.y + yOffset);
		
	}

	@Override
	public void update() {
		xOffset = xOffsetRatio * KFNekko.camera.rect.x;
		
	}

}
