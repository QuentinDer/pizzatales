package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public class SirTomato extends Enemy {
	
	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, move1SpriteRight, move2SpriteRight;

	public SirTomato(int centerX, int centerY) {
		super(centerX, centerY, null, 30, 2, 50, 50);
		movementTime = ((int) (Math.random() * 50));
		R = new Rectangle(getCenterX() - 45, getCenterY() - 45, 90, 90);
	}
	
	@Override
	public void update() {
		super.update();
		R.setBounds(getCenterX() - 45, getCenterY() - 45, 90, 90);
	}
 
	@Override
	public void callAI() {
		if (alive == true) {

			switch (StartingClass.difficultylevel) {
			case 1:
				if (movementTime % 10 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					int pathresult = pf.getDirection(posx, posy, posplayerx, posplayery, 11, canmoveleft, canmoveup, canmoveright, canmovedown, true);
					switch (pathresult) {
					case 0:
						stopMoving();
						break;
					case 1:
						moveLeft();
						break;
					case 2:
						moveUp();
						break;
					case 3:
						moveRight();
						break;
					case 4:
						moveDown();
						break;
					case 5:
						moveLeftUp();
						break;
					case 6:
						moveRightUp();
						break;
					case 7:
						moveRightDown();
						break;
					case 8:
						moveLeftDown();
					}
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}
			movementTime++;
			if (movementTime == 1000) {
				movementTime = 0;
			}
		}
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
