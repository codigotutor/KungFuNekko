package com.vestrel00.nekko.maps.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.interf.Drawable;

public class MapPiece implements Drawable{
	
	public Rectangle rect;
	public AtlasRegion[] regions;
	public float[] regionXCoords, regionYCoords;
	public float[] horizontal, vertical, slope;
	
	
	public MapPiece(float width, float height){
		rect = new Rectangle(0,0,width, height);
	}
	
	/**
	 * 
	 * After assembling the pieces together in object coordinate,
	 * we (literally) translate/move to the coodinates we want in the scene with
	 * the bottom left corner on this map piece at offsetX and offsetY.
	 * 
	 */
	public void translate(float offsetX, float offsetY){
		rect.x += offsetX;
		rect.y += offsetY;
		int i=0;
		for(i=0; i<regions.length;i++){
			regionXCoords[i] += offsetX;
			regionYCoords[i] += offsetY;
		}
		if(horizontal != null)
			for(i=0; i<horizontal.length;i+=3){
				horizontal[i] += offsetX; //x1
				horizontal[i+1] += offsetY; // y1 = y2
				horizontal[i+2] += offsetX; //x2
			}

		if(vertical != null)
			for(i=0; i<vertical.length;i+=3){
				vertical[i] += offsetY;  // y1
				vertical[i+1] += offsetX; // x1 = x2
				vertical[i+2] += offsetY; //y2
			}
		
		if(slope != null)
			for(i=0; i<slope.length;i+=4){
				slope[i] += offsetX; //x1
				slope[i+1] += offsetY; //y1
				slope[i+2] += offsetX; //x2
				slope[i+3] += offsetY; //y2
			}
		
	}
	
	
	@Override
	public void draw(SpriteBatch batch) {
		if(KFNekko.camera.rect.overlaps(rect))
			for(int i=0; i<regions.length; i++)
				batch.draw(regions[i], regionXCoords[i], regionYCoords[i]);
	}

}
