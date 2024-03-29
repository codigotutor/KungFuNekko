package com.vestrel00.nekko.actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.actors.components.Location;
import com.vestrel00.nekko.actors.components.NekkoSprite;
import com.vestrel00.nekko.actors.states.CombatState;

public class Nekko extends Actor {
	
	private NekkoSprite nekkoSprite;

	public Nekko(TextureAtlas atlas, Location location) {
		super(location);
		nekkoSprite = new NekkoSprite(this, atlas);
		sprite = nekkoSprite;
	}

	
	private void executeCombatMove() {
		switch(combatState){
		case SPIN:
			
			break;
		case FASTSHOT:
			
			break;
		case POWERSHOT:
			
			break;
		case FLYINGKICK:
			
			break;
		case SUPERUPPERCUT:
			
			break;
		case ONETWOCOMBO:
			
			break;
		case LOWMIDDLEKICK:
			
			break;
		case HIGHKICK:
			
			break;
		case DOWNWARDKICK:
			
			break;
		case TWOSIDEDATTACK:
			
			break;
		case ROUNDKICK:
			
			break;
		case UPPERCUT:
			
			break;
		}
		
	}
	
	
	@Override
	public void setCombatState(CombatState combatState) {
		// wait for the combat move in execution to finish
		if(nekkoSprite.combatIndex == 0){
			this.combatState = combatState;
			executeCombatMove();
			KFNekko.bumpWC(0.2f, 0.2f, 0.2f);
		}
	}
	
	@Override
	public boolean jump() {
		
		if(super.jump()){
			nekkoSprite.jumpIndex = 0;
			return true;
		}
		return false;
	}


	@Override
	public void onDeactivateCombat() {
		switch(combatState){
		case SPIN:
			
			break;
		case FASTSHOT:
			
			break;
		case POWERSHOT:
			
			break;
		case FLYINGKICK:
			
			break;
		case SUPERUPPERCUT:
			
			break;
		case ONETWOCOMBO:
			
			break;
		case LOWMIDDLEKICK:
			
			break;
		case HIGHKICK:
			
			break;
		case DOWNWARDKICK:
			
			break;
		case TWOSIDEDATTACK:
			
			break;
		case ROUNDKICK:
			
			break;
		case UPPERCUT:
			
			break;
		}
		
		
		super.onDeactivateCombat();
	}
	
	
	

}
