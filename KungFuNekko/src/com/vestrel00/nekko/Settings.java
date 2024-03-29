package com.vestrel00.nekko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
	
	public Preferences prefs = Gdx.app.getPreferences("Nekko Settings");
	
	public float viewWidth, viewHeight, viewWidthHalf, viewHeightHalf,
				 viewWidthQuarter, viewHeightQuarter;
	
	public Settings(){
		Gdx.input.setCatchBackKey(false);
		Gdx.input.setCatchMenuKey(false);
		
		viewWidth = prefs.getFloat("width", 480.0f);
		viewHeight = prefs.getFloat("Height", 320.0f);
		
		viewWidthHalf = viewWidth * 0.5f;
		viewHeightHalf = viewHeight * 0.5f;
		
		viewWidthQuarter = viewWidth * 0.25f;
		viewHeightQuarter = viewHeight * 0.25f;
		
		
	}

}
