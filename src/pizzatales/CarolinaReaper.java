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
		currentSprite = move1Sprite;
	}

	@Override
	public void setMove2Sprite() {
		currentSprite = move2Sprite;
	}

	@Override
	public void setDieSprite() {
		currentSprite = dieSprite;
	}
	
	@Override
	public void setStaySpriteAlt() {
		currentSprite = staySpriteRight;
	}

	@Override
	public void setMove1SpriteAlt() {
		currentSprite = move1SpriteRight;
	}

	@Override
	public void setMove2SpriteAlt() {
		currentSprite = move2SpriteRight;
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
