package com.vestrel00.nekko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Resource implements Disposable{
	
	public TextureAtlas atlas;
	// add sound music
	
	
	public Resource(){
		atlas = new TextureAtlas(Gdx.files.internal("data/texture/main.pack"));
		
	}

	@Override
	public void dispose() {
		atlas.dispose();
		
	}

}
