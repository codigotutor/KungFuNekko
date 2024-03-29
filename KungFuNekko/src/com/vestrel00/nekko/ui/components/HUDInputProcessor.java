package com.vestrel00.nekko.ui.components;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.actors.Actor;
import com.vestrel00.nekko.actors.states.FaceState;
import com.vestrel00.nekko.actors.states.HorizontalMotionState;

public class HUDInputProcessor implements InputProcessor {

	public ComboAttackManager attackManager;
	private Vector3 touchPos;
	private Actor player;
	
	public HUDInputProcessor(Actor player){
		this.player = player;
		touchPos = new Vector3();
		attackManager = new ComboAttackManager();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		switch(keycode){
		case Keys.DOWN:
			attackManager.input(ComboAttackManager.INPUT_DOWN);
			return true;
		case Keys.LEFT:
			attackManager.input(ComboAttackManager.INPUT_LEFT);
			player.horizontalMotionState = HorizontalMotionState.MOVING;
			player.faceState = FaceState.LEFT;
			return true;
		case Keys.RIGHT:
			attackManager.input(ComboAttackManager.INPUT_RIGHT);
			player.horizontalMotionState = HorizontalMotionState.MOVING;
			player.faceState = FaceState.RIGHT;
			return true;
		case Keys.UP:
			attackManager.input(ComboAttackManager.INPUT_UP);
			return true;
		case Keys.SPACE: // jump
			attackManager.input(ComboAttackManager.INPUT_JUMP);
			player.jump();
			return true;
		default: // any other key is attack
			player.setCombatState(attackManager.input(ComboAttackManager.INPUT_ATTACK));
			return true;
		}
	}

	@Override
	public boolean keyUp(int keycode) {
		
		switch(keycode){
		case Keys.LEFT:
		case Keys.RIGHT:
			player.horizontalMotionState = HorizontalMotionState.IDLE;
			return true;
		default:
			return true;
		}
	}
	
	

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		
		
		
		touchPos.set(screenX,screenY,0);
		KFNekko.camera.camera.unproject(touchPos);
		return (KFNekko.hud.onTouchDown(touchPos.x, touchPos.y)) ? true : false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
