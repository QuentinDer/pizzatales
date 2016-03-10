package pizzatales;

import java.awt.Image;

public class CarolinaReaper extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, 
	move1SpriteRight, move2SpriteRight, firering, streamleft, streamup, streamright, streamdown;
	private int waitmin, waitmax;
	private int streamingtime, streamrate, blinkingtime;
	private float streamdmg;
	private int streamcd, blinkcd;
	private int streaming, blinking;
	
	public CarolinaReaper(int centerX, int centerY) {
		super(centerX, centerY, null, 100, 3, 31, 31, 25, 25);
		movementTime = ((int) (Math.random() * 50));
		streamrate = 7;
		switch (StartingClass.difficultylevel) {
		case 1:
			waitmin = 120;
			waitmax = 240;
			streamingtime = 120;
			streamdmg = 0.7f;
			break;
		case 2:
			waitmin = 100;
			waitmax = 220;
			streamingtime = 100;
			break;
		case 3:
			waitmin = 80;
			waitmax = 200;
			streamingtime = 80;
			break;
		case 4:
			waitmin = 60;
			waitmax = 180;
			streamingtime = 60;
			break;
		}
	}

	@Override
	public void callAI() {
		
	}
	
	private int getNextWaitingTime() {
		return (int)(Math.random()*(waitmax-waitmin)) + waitmin;
	}
	
	@Override
	public void setStaySprite() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove1Sprite() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove2Sprite() {
		currentSprite = staySprite;
	}

	@Override
	public void setDieSprite() {
		currentSprite = dieSprite;
	}
	
	@Override
	public void setStaySpriteAlt() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove1SpriteAlt() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove2SpriteAlt() {
		currentSprite = staySprite;
	}
	
	@Override
	public void setGibsSprite() {
		currentSprite = dieSprite;
	}
	
	@Override
	public void die() {
		super.die();
		projectiles.clear();
	}
}
