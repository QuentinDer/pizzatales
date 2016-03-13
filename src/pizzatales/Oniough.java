package pizzatales;

import java.awt.Image;

public class Oniough extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite,
	dashSpriteRight, dashSpriteLeft, slashSpriteLeft, slashSpriteRight;
	
	public Oniough(int centerX, int centerY) {
		super(centerX, centerY, null, 75, 2, 50, 50, 45, 45);
		halfbarx = 45;
		movementTime = ((int) (Math.random() * 50));
	}

	@Override
	public void callAI() {
		stopMoving();
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
	public void setGibsSprite() {
		currentSprite = dieSprite;
	}

	@Override
	public void setStaySpriteAlt() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove1SpriteAlt() {
		currentSprite = move1Sprite;
	}

	@Override
	public void setMove2SpriteAlt() {
		currentSprite = move2Sprite;
	}

}
