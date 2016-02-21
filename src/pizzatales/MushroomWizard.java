package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public class MushroomWizard extends Enemy {

	private int inAnimation;
	private static final int basicspeed = 3;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, 
		move1SpriteRight, move2SpriteRight, swipeDown, swipeRight, swipeLeft, swipeUp;
	private int maxInAnimation;
	
	public MushroomWizard(int centerX, int centerY) {
		super(centerX, centerY, null, 100, basicspeed, 50, 50);
		halfrsizex = 45;
		halfrsizey = 45;
		R = new Rectangle(getCenterX() - 45, getCenterY() - 45, 90, 90);
	}

	@Override
	public void callAI() {
		// TODO Auto-generated method stub
		
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

}
