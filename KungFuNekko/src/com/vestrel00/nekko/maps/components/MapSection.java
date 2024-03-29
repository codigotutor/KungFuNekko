package com.vestrel00.nekko.maps.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.interf.Drawable;

public class MapSection implements Drawable{
	
	public Array<MapPiece> pieces;
	public Rectangle rect;
	
	public MapSection(float x, float y, float width, float height){
		rect = new Rectangle(x, y, width, height);
		pieces = new Array<MapPiece>();
	}
	
	
	public MapSection(Rectangle rect){
		this.rect = rect;
		pieces = new Array<MapPiece>();
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		if(KFNekko.camera.rect.overlaps(rect))
			for(int i=0; i<pieces.size; i++)
				pieces.get(i).draw(batch);
		
	}

}
