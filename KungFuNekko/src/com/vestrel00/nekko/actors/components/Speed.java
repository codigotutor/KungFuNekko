package com.vestrel00.nekko.actors.components;

public class Speed {
	
	public static final int DIRECTION_UP = 1, 
							DIRECTION_DOWN = -1,
							DIRECTION_LEFT = -1,
							DIRECTION_RIGHT = 1;

	private static final float HORI_ACCEL = 3.0f,
							   VERT_ACCEL = 4.0f;
	
	public int xDirection, yDirection;
	public float xSpeed, ySpeed, maxXSpeed, maxYSpeed;
	
	
	public Speed(float maxXSpeed, float maxYSpeed){
		this.maxXSpeed = maxXSpeed;
		this.maxYSpeed = maxYSpeed;
		
	}
	
	public Speed(){
		init();
	}
	
	
	private void init(){
		xDirection = DIRECTION_RIGHT;
		yDirection = DIRECTION_LEFT;
		xSpeed = 0.0f;
		ySpeed = 0.0f;
		
	}
	
	
	public void decel(){
		decreaseHorizontalSpeed();
		
	}
	
	
	
	public void accelRight(){
		if(xDirection == DIRECTION_LEFT){
			if(xSpeed > 0.0f)
				decreaseHorizontalSpeed();
			else
				xDirection = DIRECTION_RIGHT;
		}
		else
			increaseHorizontalSpeed();
	}
	
	
	public void accelLeft(){
		if(xDirection == DIRECTION_RIGHT){
			if(xSpeed > 0.0f)
				decreaseHorizontalSpeed();
			else
				xDirection = DIRECTION_LEFT;
		}
		else
			increaseHorizontalSpeed();
	}
	

	
	
	public void accelDown(){
		if(xDirection == DIRECTION_UP){
			if(xSpeed > 0.0f)
				decreaseVerticalSpeed();
			else
				xDirection = DIRECTION_DOWN;
		}
		else
			increaseVerticalSpeed();
	}
	

	public void accelUp(){
		if(xDirection == DIRECTION_DOWN){
			if(xSpeed > 0.0f)
				decreaseVerticalSpeed();
			else
				xDirection = DIRECTION_UP;
		}
		else
			increaseVerticalSpeed();
	}
	
	public void increaseHorizontalSpeed(){
		
		if(xSpeed < maxXSpeed && (xSpeed += HORI_ACCEL) > maxXSpeed)
			xSpeed = maxXSpeed;
		
	}
	
	public void decreaseHorizontalSpeed(){
		
		if(xSpeed > 0.0f && (xSpeed -= HORI_ACCEL) < 0.0f)
			xSpeed = maxXSpeed;
	}
	
	
	public void increaseVerticalSpeed(){
		
		if(ySpeed < maxYSpeed && (ySpeed += VERT_ACCEL) > maxYSpeed)
			ySpeed = maxYSpeed;
	}
	
	public void decreaseVerticalSpeed(){
		
		if(ySpeed > 0.0f && (ySpeed -= VERT_ACCEL) < 0.0f)
			ySpeed = maxYSpeed;	
	}

	
	public float getXVelocity(){
		return xSpeed * (float)xDirection;
	}

	public float getYVelocity(){
		return ySpeed * (float)yDirection;
	}
}
