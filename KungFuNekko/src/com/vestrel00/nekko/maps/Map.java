package com.vestrel00.nekko.maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vestrel00.nekko.interf.Drawable;
import com.vestrel00.nekko.interf.Updatable;

public class Map implements Updatable, Drawable{

	public float width, height;
	
	public Map(){
		width = 1000.0f;
		height = 232.0f;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		
	}

	@Override
	public void update() {
		
	}

}