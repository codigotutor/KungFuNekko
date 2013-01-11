package com.vestrel00.nekko.ui.components;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.vestrel00.nekko.actors.states.CombatState;

/**
 * Detects combos based on collected user input
 * 
 * Spin
 * down + jump + attack
 * 
 * Power Shot
 * down + right + right + attack or
 * down + left + left + attack
 * 
 * Flying Kick
 * down + right + jump + attack or
 * down + left + jump + attack
 * 
 * Super Uppercut
 * down + down + up + attack
 * 
 * One Two Combo
 * down + right + attack or
 * down + left + attack
 * 
 * Low Middle Kick
 * down + down + attack
 * 
 * High Kick
 * down + up + up + attack
 * 
 * Downward Kick
 * down + attack
 * 
 * Two Sided Attack
 * down + up + left + right + attack or
 * down + up + right + left + attack
 * 
 * 
 * Round Kick
 * down + left + right + attack or
 * down + right + left + attack
 * 
 * 
 * Uppercut
 * down + up + attack
 * 
 * @author usuario
 *
 */
public class ComboAttackManager {
	
	public static final int INPUT_LEFT = 0, INPUT_RIGHT = 1, INPUT_UP = 2,
			INPUT_DOWN = 3, INPUT_ATTACK = 4, INPUT_JUMP = 5;
	
	private static final long RESET_DELAY = 800000000L;
	private Array<Integer> history;
	private long lastInputTime;
									
	public ComboAttackManager(){
		history = new Array<Integer>();
	}
	
	/**
	 * This clears the input history after RESET_DELAY has passed and
	 * this will determine the combatState that it will return based on the
	 * input concatenated with the history
	 */
	public CombatState input(int input){
		//reset history if input past the recording time
		if(TimeUtils.nanoTime() - lastInputTime > RESET_DELAY
				&& history.size > 0)
			history.clear();
		
		//wait for INPUT_DOWN to start recording history
		if(history.size == 0 && input != INPUT_DOWN)
			//regular attack
			return (input == INPUT_ATTACK) ? CombatState.FASTSHOT :
					CombatState.IDLE;
		
		lastInputTime = TimeUtils.nanoTime();
		history.add(input);
		if(input == INPUT_ATTACK){
			CombatState state = recognize();
			history.clear();
			return state;
		} else
			 return CombatState.IDLE;
		
	}

	private CombatState recognize() {
		
		switch(history.size){
		case 2:
			return CombatState.DOWNWARDKICK;
		case 3:
			return recognize3();
		case 4:
			return recognize4();
		case 5:
			if(history.get(1) == INPUT_UP 
					&& ((history.get(3) == INPUT_RIGHT && history.get(2) == INPUT_LEFT) || 
							history.get(3) == INPUT_LEFT && history.get(2) == INPUT_RIGHT))
				return CombatState.TWOSIDEDATTACK;
				else
					return CombatState.FASTSHOT;
		default:
			return CombatState.FASTSHOT;
		}	
	}

	
	/**
	 * Receiving down + ? + ? + attack
	 */
	private CombatState recognize4() {
		
		switch(history.get(2)){
		case INPUT_RIGHT:
			switch(history.get(1)){
			case INPUT_RIGHT:
				return CombatState.POWERSHOT;
			case INPUT_LEFT:
				return CombatState.ROUNDKICK;
			default:
				return CombatState.FASTSHOT;
			}
		case INPUT_LEFT:
			switch(history.get(1)){
			case INPUT_LEFT:
				return CombatState.POWERSHOT;
			case INPUT_RIGHT:
				return CombatState.ROUNDKICK;
			default:
				return CombatState.FASTSHOT;
			}
		case INPUT_JUMP:
			switch(history.get(1)){
			case INPUT_LEFT:
			case INPUT_RIGHT:
				return CombatState.FLYINGKICK;
			default:
				return CombatState.FASTSHOT;
			}
		case INPUT_UP:
			switch(history.get(1)){
			case INPUT_UP:
				return CombatState.HIGHKICK;
			case INPUT_DOWN:
				return CombatState.SUPERUPPERCUT;
			default:
				return CombatState.FASTSHOT;
			}	
		default:	
			return CombatState.FASTSHOT;
		}
	}

	/**
	 * Receiving down + ? + attack
	 */
	private CombatState recognize3() {
		
		switch(history.get(1)){
		
		case INPUT_JUMP:
			return CombatState.SPIN;
		case INPUT_LEFT:
		case INPUT_RIGHT:
			 return CombatState.ONETWOCOMBO;
		case INPUT_DOWN:
			return CombatState.LOWMIDDLEKICK;
		default: //up
			return CombatState.UPPERCUT;
		}
		
	}
	
}
