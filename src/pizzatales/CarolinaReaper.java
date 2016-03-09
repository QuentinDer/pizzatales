package pizzatales;

import java.awt.Image;

public class CarolinaReaper extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, 
	move1SpriteRight, move2SpriteRight, firering, streamleft, streamup, streamright, streamdown;
	
	public CarolinaReaper(int centerX, int centerY) {
		super(centerX, centerY, null, 100, 3, 31, 31, 25, 25);
	}

	@Override
	public void callAI() {
		
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
