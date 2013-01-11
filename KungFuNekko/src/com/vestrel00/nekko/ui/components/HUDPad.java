package com.vestrel00.nekko.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.vestrel00.nekko.KFNekko;
import com.vestrel00.nekko.actors.Nekko;
import com.vestrel00.nekko.interf.Drawable;
import com.vestrel00.nekko.interf.Touchable;
import com.vestrel00.nekko.interf.Updatable;

public class HUDPad implements Updatable, Drawable, Touchable{
	
	private Array<Rectangle> baseRects, rects;
	private Array<AtlasRegion> regions;
	//screen rectangle
	private Rectangle leftBase, rightBase, upBase, downBase, dPadBase, 
			jumpBase, attackBase, pauseBase, optionsBase, topLeftBase, topRightBase;
	
	private Rectangle left, right, up, down, dPad, jump, attack, pause, options, topLeft,
			topRight;
	
	private AtlasRegion dPadRegion, topRightRegion, topLeftRegion, attackRegion, 
						jumpRegion, pauseRegion, optionsRegion;
	

	private Nekko player;
	private HUDInputProcessor processor;
	
	
	public HUDPad(Nekko player, HUDInputProcessor processor){
		this.player = player;
		this.processor = processor;
		initRegions();
		initRects();	
	}


	private void initRects() {
		
		//SCREEN RECTS
		
		//not added to array 
		//la cruzeta individual dividida en arriba abajo izquierda derecha
		leftBase = new Rectangle(28.0f, 8.0f, 40.0f, 48.0f);
		rightBase = new Rectangle(112.0f, 8.0f, 40.0f, 48.0f);
		upBase = new Rectangle(66.0f, 56.0f, 49.0f, 38.0f);
		downBase = new Rectangle(70.0f, 8.0f, 40.0f, 48.0f);
		///////
		
		System.out.println("left tamaño width vale: "+ topLeftRegion.originalWidth);
		
		
		//esquina de arriba de la izquierda
		topLeftBase = new Rectangle(8.0f, 264.0f,
				(float) topLeftRegion.originalWidth,	
				(float) topLeftRegion.originalHeight);
		
		//esquina de arriba de la derecha
		topRightBase = new Rectangle(361.0f, 264.0f,
				(float) topRightRegion.originalWidth,	
				(float) topRightRegion.originalHeight);		
	
		// toda la cruzeta completa
		dPadBase = new Rectangle(27.0f, 9.0f,
				(float) dPadRegion.originalWidth,	
				(float) dPadRegion.originalHeight);
		
		// el cuadro de la derecha
		jumpBase = new Rectangle(396.0f, 9.0f,
				(float) jumpRegion.originalWidth,	
				(float) jumpRegion.originalHeight);	
	
		// la bola de abajo de enseguida del cuadro
		attackBase = new Rectangle(328.0f, 9.0f,
				(float) attackRegion.originalWidth,	
				(float) attackRegion.originalHeight);	
		
		//el rectangulito de la dercha es como el start
		//el cuadrito de la derecha
		pauseBase = new Rectangle(252.0f, 13.0f,
				(float) pauseRegion.originalWidth,	
				(float) pauseRegion.originalHeight);	
		
		//el rectangulito de la izquierda es como el select
		//de los controles
		optionsBase = new Rectangle(183.0f, 13.0f,
				(float) optionsRegion.originalWidth,	
				(float) optionsRegion.originalHeight);	
		
		
		baseRects = new Array<Rectangle>();
		baseRects.add(topLeftBase);
		baseRects.add(topRightBase);
		baseRects.add(dPadBase);
		baseRects.add(jumpBase);
		baseRects.add(attackBase);
		baseRects.add(pauseBase);
		baseRects.add(optionsBase);
		
		
		//SCENE
		
		//not added to array
		left = new Rectangle(leftBase);
		down = new Rectangle(downBase);
		right = new Rectangle(rightBase);
		up = new Rectangle(upBase);
		////
		
		topLeft = new Rectangle(topLeftBase);
		topRight = new Rectangle(topRightBase);		
		dPad = new Rectangle(dPadBase);
		jump = new Rectangle(jumpBase);	
		attack = new Rectangle(attackBase);	
		pause = new Rectangle(pauseBase);	
		options = new Rectangle(optionsBase);	
		
		rects = new Array<Rectangle>();
		rects.add(topLeft);
		rects.add(topRight);
		rects.add(dPad);
		rects.add(jump);
		rects.add(attack);
		rects.add(pause);
		rects.add(options);
	}


	private void initRegions() {
		
		topLeftRegion = KFNekko.resource.atlas.findRegion("topLeftCorner");
		topRightRegion = KFNekko.resource.atlas.findRegion("topRightCorner");
		dPadRegion = KFNekko.resource.atlas.findRegion("dPad");
		jumpRegion = KFNekko.resource.atlas.findRegion("jump");
		attackRegion = KFNekko.resource.atlas.findRegion("attack");
		pauseRegion = KFNekko.resource.atlas.findRegion("pause");
		optionsRegion = KFNekko.resource.atlas.findRegion("options");
		
		regions = new Array<AtlasRegion>();
		regions.add(topLeftRegion);
		regions.add(topRightRegion);
		regions.add(dPadRegion);
		regions.add(jumpRegion);
		regions.add(attackRegion);
		regions.add(pauseRegion);
		regions.add(optionsRegion);
	
	}


	@Override
	public void draw(SpriteBatch batch) {
		batch.setColor(Color.WHITE);
		for(int i=0; i<rects.size;i++)
			batch.draw(regions.get(i), rects.get(i).x, rects.get(i).y, 
					rects.get(i).width, rects.get(i).height);
		batch.setColor(KFNekko.worldColor);
	}


	@Override
	public void update() {
		
		
		// me toco borrar esto al final del video 5 porque
		// no detectaba bien las pulsaciones del muse.
		
		
		/*for(int i=0; i<rects.size;i++){
			rects.get(i).x = baseRects.get(i).x + KFNekko.camera.rect.x;
			rects.get(i).y = baseRects.get(i).y + KFNekko.camera.rect.y;
		}*/
		
		
		/*left.x = leftBase.x + KFNekko.camera.rect.x;
		left.y = leftBase.x + KFNekko.camera.rect.y;
		
		right.x = rightBase.x + KFNekko.camera.rect.x;
		right.y = rightBase.x + KFNekko.camera.rect.y;
		
		up.x = upBase.x + KFNekko.camera.rect.x;
		up.y = upBase.x + KFNekko.camera.rect.y;
		
		down.x = downBase.x + KFNekko.camera.rect.x;
		down.y = downBase.x + KFNekko.camera.rect.y;*/
		
		
	}


	@Override
	public boolean onTouchDown(float x, float y) {
		
		System.out.println("xy: ("+x+","+y+")");
		//System.out.println(x + "," + y);
		
		
		//System.out.println("datos de left:");
		//System.out.println("x: " + left.getX());
		//System.out.println("y: " + left.getY());
		
		if(left.contains(x,y)){
			//processor.attackManager.input(ComboAttackManager.INPUT_LEFT); 
			
			//System.out.println("x: " + attack.getX());
			//System.out.println("y: " + attack.getY());
			
			System.out.println("left");
			
			player.setCombatState(processor.attackManager
					.input(ComboAttackManager.INPUT_LEFT));
			
			return true;
		} else if(right.contains(x,y)){
			
			
			System.out.println("right");
			
			//processor.attackManager.input(ComboAttackManager.INPUT_RIGHT);
			player.setCombatState(processor.attackManager
					.input(ComboAttackManager.INPUT_RIGHT));
			
			return true;
		} else if(up.contains(x,y)){
			
			System.out.println("up");
			player.setCombatState(processor.attackManager
					.input(ComboAttackManager.INPUT_UP));
			
			return true;
		} else if(down.contains(x,y)){
			
			System.out.println("down");
			player.setCombatState(processor.attackManager
					.input(ComboAttackManager.INPUT_DOWN));
			
			return true;
		} else if(jump.contains(x,y)){
			
			System.out.println("jump");
			
			player.setCombatState(processor.attackManager
					.input(ComboAttackManager.INPUT_JUMP));
			
			
			player.jump();
			return true;
		} else if(attack.contains(x,y)){
			
			System.out.println("attack");
			//System.out.println("datos de attack:");
			//System.out.println("x: " + attack.getX());
			//System.out.println("y: " + attack.getY());
			
			player.setCombatState(processor.attackManager
						.input(ComboAttackManager.INPUT_ATTACK));
			return true;
		} else
			  return false;
			
	}
	
	
}
