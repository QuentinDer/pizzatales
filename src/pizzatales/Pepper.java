package pizzatales;

import java.awt.Image;

public class Pepper extends Enemy {

	protected int movementTime;
	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, move1SpriteRight,
	move2SpriteRight;

	public Pepper(int centerX, int centerY) {
		super(centerX,centerY, new Flamer(), 5, (StartingClass.difficultylevel == 1)?2:((StartingClass.difficultylevel == 3)?4:3));
		movementTime = ((int) (Math.random() * 50));
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		if (alive == true) {
			switch(StartingClass.difficultylevel) {
			case 1:
				if (movementTime % 50 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					int pathresult = pf.getDirection(posx, posy, posplayerx, posplayery, 7);
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
					}
				}
				break;
			case 2:
				if (movementTime % 30 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					int pathresult = pf.getDirection(posx, posy, posplayerx, posplayery, 9);
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
					}
				}
				break;
			case 3:
				if (movementTime % 10 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					int pathresult = pf.getDirection(posx, posy, posplayerx, posplayery, 11);
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
					}
				}
				break;
			}
			if (movementTime % 5 == 0 && weapon.isReady2Fire()) {
				int diffx = Math.abs(getCenterX() - player.getCenterX());
				int diffy = Math.abs(getCenterY() - player.getCenterY());
				if (diffx + diffy < 100) {
					if (diffx > diffy) {
						if (player.getCenterX() > getCenterX())
							shootRight();
						else
							shootLeft();
					} else {
						if (player.getCenterY() > getCenterY())
							shootDown();
						else
							shootUp();
					}
				}
			}
			// AI

			weapon.increaseShootingCounter();
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

	@Override
	public void animate() {
		walkCounter++;
		if (getSpeedX() < 0) {
			if (walkCounter == 1000)
				walkCounter = 0;
			if (walkCounter % 30 == 0) {
				setMove1Sprite();
			} else if (walkCounter % 15 == 0) {
				setMove2Sprite();
			}
		} else if (getSpeedX() >= 0){
			if (walkCounter == 1000)
				walkCounter = 0;
			if (walkCounter % 30 == 0) {
				setMove1SpriteAlt();
			} else if (walkCounter % 15 == 0) {
				setMove2SpriteAlt();
			}
		}
	}
	
}
