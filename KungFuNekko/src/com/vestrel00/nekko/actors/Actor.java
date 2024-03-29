package com.vestrel00.nekko.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.vestrel00.nekko.actors.components.Location;
import com.vestrel00.nekko.actors.components.Sprite;
import com.vestrel00.nekko.actors.states.CombatState;
import com.vestrel00.nekko.actors.states.FaceState;
import com.vestrel00.nekko.actors.states.HorizontalMotionState;
import com.vestrel00.nekko.actors.states.StatusState;
import com.vestrel00.nekko.actors.states.VerticalMotionState;
import com.vestrel00.nekko.actors.states.Visibility;
import com.vestrel00.nekko.interf.Drawable;
import com.vestrel00.nekko.interf.Updatable;

public abstract class Actor implements Updatable, Drawable{

	public Sprite sprite;
	public Location location;
	
	public FaceState faceState;
	public StatusState statusState;
	public CombatState combatState;
	public HorizontalMotionState horizontalMotionState;
	public VerticalMotionState verticalMotionState;
	public Visibility visibility;
	
	
	public Actor(Location location){
		this.location = location;
	}
	
	public void setState(FaceState faceState, StatusState statusState,
			CombatState combatState, HorizontalMotionState horizontalMotionState,
			VerticalMotionState verticalMotionState){
		this.faceState = faceState;
		this.statusState = statusState;
		this.combatState = combatState;
		this.horizontalMotionState = horizontalMotionState;
		this.verticalMotionState = verticalMotionState;
	}

	@Override
	public void draw(SpriteBatch batch) {
		
		if(statusState == StatusState.ALIVE 
				&& visibility == Visibility.VISIBLE){
			sprite.draw(batch);
		}
		
	}

	@Override
	public void update() {
		
		location.update();
		
		if(statusState == StatusState.ALIVE 
				&& visibility == Visibility.VISIBLE){
			sprite.update();
		}
		
	}
	
	
	
	public boolean jump(){
		
		return location.jump();
	}
	
	
	public void attack(Array<Actor> actors, int damage, boolean aoe){
		
	}

	public void onDeactivateCombat() {
		combatState = CombatState.IDLE;
		
	}

	public void setCombatState(CombatState combatState) {
		this.combatState = combatState;
		
	}
	
	
}
