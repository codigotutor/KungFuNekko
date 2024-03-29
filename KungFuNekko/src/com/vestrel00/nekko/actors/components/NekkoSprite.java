package com.vestrel00.nekko.actors.components;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.actors.Actor;
import com.vestrel00.nekko.actors.states.FaceState;

public class NekkoSprite extends Sprite {

	
	//attack frames
	private AtlasRegion[] idle, walk, jump, spinKick, powerShot,
			fastShot, flyingKick, superUppercut, oneTwoCombo, lowMiddleKick, highKick,
			downwardKick, twoSidedAttack, roundKick, uppercut;
	
	public int idleIndex=0, walkIndex = 0, jumpIndex=0, combatIndex=0;
	
	public NekkoSprite(Actor actor, TextureAtlas atlas) {
		super(actor);
		initTextures(atlas);

	}

	private void initTextures(TextureAtlas atlas) {
		idle = new AtlasRegion[4];
		highKick = new AtlasRegion[6];
		fastShot = new AtlasRegion[6];
		powerShot = new AtlasRegion[7];
		uppercut = new AtlasRegion[7]; //added 1 frame
		flyingKick = new AtlasRegion[8];
		walk = new AtlasRegion[8];
		jump = new AtlasRegion[8];
		downwardKick = new AtlasRegion[9]; //added 1 frame
		twoSidedAttack = new AtlasRegion[9]; //added 1 frame
		roundKick = new AtlasRegion[9]; //added 1 frame
		spinKick = new AtlasRegion[10]; //added 1 frame
		oneTwoCombo = new AtlasRegion[10];
		lowMiddleKick = new AtlasRegion[12];
		superUppercut = new AtlasRegion[13];
		
		int i = 0;
		
		for(i=0;i<4;i++)
			idle[i] = atlas.findRegion("nekkoIdle" + String.valueOf(i));
		
		for(i=0;i<6;i++){
			highKick[i] = atlas.findRegion("nekkoHighKick" + String.valueOf(i));
			fastShot[i] = atlas.findRegion("nekkoFastShot" + String.valueOf(i));
		}
		
		for(i=0;i<7;i++){
			powerShot[i] = atlas.findRegion("nekkoPowerShot" + String.valueOf(i));
			uppercut[i] = atlas.findRegion("nekkoUppercut" + String.valueOf(i));
		}
		
		for(i=0;i<8;i++){
			flyingKick[i] = atlas.findRegion("nekkoflyingKick" + String.valueOf(i));
			walk[i] = atlas.findRegion("nekkoWalk" + String.valueOf(i));
			jump[i] = atlas.findRegion("nekkoJump" + String.valueOf(i));
		}
		
		for(i=0;i<9;i++){
			downwardKick[i] = atlas.findRegion("nekkoDownwardKick" + String.valueOf(i));
			twoSidedAttack[i] = atlas.findRegion("nekkoTwoSidedAttack" + String.valueOf(i));
			roundKick[i] = atlas.findRegion("nekkoRoundKick" + String.valueOf(i));
		}
		
		for(i=0;i<10;i++){
			spinKick[i] = atlas.findRegion("nekkoSpin" + String.valueOf(i));
			oneTwoCombo[i] = atlas.findRegion("nekkoOneTwoCombo" + String.valueOf(i));
		}
		
		for(i=0;i<12;i++)
			lowMiddleKick[i] = atlas.findRegion("nekkoLowMiddleKick" + String.valueOf(i));

		
		for(i=0;i<13;i++)
			superUppercut[i] = atlas.findRegion("nekkoSuperUppercut" + String.valueOf(i));
	
		
		currentTexture = idle[0];
	}

	@Override
	public void update() {
		if(TimeUtils.nanoTime() - lastAnimationTime < animationDelay)
			return;
		
		lastAnimationTime = TimeUtils.nanoTime();
		
		xScale = (actor.faceState == FaceState.LEFT) ? -1.0f : 1.0f;
		
		
		//animate
		if(switchCombatState())
			return;
		if(switchVerticalMotionState())
			return;
		
		switchHorizontalMotionState();
			
		
	}

	private boolean switchHorizontalMotionState() {
		
		if(actor.location.onPlatform || actor.location.onSlope)
			switch(actor.horizontalMotionState){
				case IDLE:
					if(++idleIndex == idle.length)
						idleIndex = 0;
					currentTexture = idle[idleIndex];
					animationDelay = 120000000L;
					return true;
				case MOVING:
					if(++walkIndex == walk.length)
						walkIndex = 0;
					currentTexture = walk[walkIndex];
					animationDelay = 20000000L;
					return true;
				default:
						return false;
			}
		else
			 return false;
	}

	private boolean switchVerticalMotionState() {
		switch(actor.verticalMotionState){
		case JUMPING:
			if(++jumpIndex >= jump.length)
				return false;
			currentTexture = jump[jumpIndex];
			animationDelay = 60000000L;
			return true;
		default:
			return false;
		}
	}

	private boolean switchCombatState() {
		switch(actor.combatState){
		case SPIN:
			if(++combatIndex == spinKick.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 7){
				actor.attack(KFNekko.enemies, 1, false);
				activateEffect();
			}
			currentTexture = spinKick[combatIndex];
			animationDelay = 40000000L;
			return true;
		case FASTSHOT:
			if(++combatIndex == fastShot.length){
				 combatIndex = 0;
			 	 actor.onDeactivateCombat();
			}
			if(combatIndex == 4){
				actor.attack(KFNekko.enemies, 1, false);
				activateEffect();
			}
			currentTexture = fastShot[combatIndex];
			animationDelay = 30000000L;
			return true;
		case POWERSHOT:
			if(++combatIndex == powerShot.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 5){
				actor.attack(KFNekko.enemies, 2, false);
				activateEffect();
			}
			currentTexture = powerShot[combatIndex];
			animationDelay = 60000000L;
			return true;
		case FLYINGKICK:
			if(++combatIndex == flyingKick.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 5){
				actor.attack(KFNekko.enemies, 1, true);
				activateEffect();
			}
			currentTexture = flyingKick[combatIndex];
			animationDelay = 30000000L;
			return true;
		case SUPERUPPERCUT:
			if(++combatIndex == superUppercut.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 5){
				actor.attack(KFNekko.enemies, 3, false);
				activateEffect();
			}
			currentTexture = superUppercut[combatIndex];
			animationDelay = 20000000L;
			return true;
		case ONETWOCOMBO:
			if(++combatIndex == oneTwoCombo.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 4 || combatIndex == 7){
				actor.attack(KFNekko.enemies, 1, false);
				activateEffect();
			}
			currentTexture = oneTwoCombo[combatIndex];
			animationDelay = 40000000L;
			return true;
		case LOWMIDDLEKICK:
			if(++combatIndex == lowMiddleKick.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 2 || combatIndex == 8){
				actor.attack(KFNekko.enemies, 1, false);
				activateEffect();
			}
			currentTexture = lowMiddleKick[combatIndex];
			animationDelay = 45000000L;
			return true;
		case HIGHKICK:
			if(++combatIndex == highKick.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 2){
				actor.attack(KFNekko.enemies, 2, true);
				activateEffect();
			}
			currentTexture = highKick[combatIndex];
			animationDelay = 60000000L;
			return true;
		case DOWNWARDKICK:
			if(++combatIndex == downwardKick.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 7){
				actor.attack(KFNekko.enemies, 2, true);
				activateEffect();
			}
			currentTexture = downwardKick[combatIndex];
			animationDelay = 50000000L;
			return true;
		case TWOSIDEDATTACK:
			if(++combatIndex == twoSidedAttack.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 6){
				actor.attack(KFNekko.enemies, 1, true);
				activateEffect();
			}
			currentTexture = twoSidedAttack[combatIndex];
			animationDelay = 50000000L;
			return true;
		case ROUNDKICK:
			if(++combatIndex == roundKick.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 5){
				actor.attack(KFNekko.enemies, 2, true);
				activateEffect();
			}
			currentTexture = roundKick[combatIndex];
			animationDelay = 50000000L;
			return true;
		case UPPERCUT:
			if(++combatIndex == uppercut.length){
				 combatIndex = 0;
				 actor.onDeactivateCombat();
			}
			if(combatIndex == 4){
				actor.attack(KFNekko.enemies, 2, true);
				activateEffect();
			}
			currentTexture = uppercut[combatIndex];
			animationDelay = 70000000L;
			return true;
		default:	
			return false;
		}
	}

	private void activateEffect() {

		switch(actor.combatState){
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
		
}
