package com.vestrel00.nekko.maps.components;

import com.badlogic.gdx.utils.Array;
import com.vestrel00.nekko.actors.Actor;

public class Platform {
	
	
	private Array<MapSection> sections;
	private MapSection section;
	
	
	public Platform(Array<MapSection> sections){
		this.sections = sections;
	}
	
	
	public boolean hitPlatform(Actor actor, float dx, float dy){
		setSection(actor.location.x, actor.location.rect.y);
	}
	

}
