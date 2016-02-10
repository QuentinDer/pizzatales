package pizzatales;

import java.awt.Image;

public class Mushroom extends Enemy {

	protected int movementParam;
	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;

	public Mushroom(int centerX, int centerY, int difficultylevel) {
		super(centerX,centerY, new Rifle(), 4, 3, difficultylevel);
		movementParam = ((int) (Math.random() * 50));
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		// centerX += speedX;
		// centerY += speedY;

		if (alive == true) {
			// AI
			if (!isShooting) {
				if (movementTime % 400 == movementParam) {
					moveRight();
				} else if (movementTime % 400 == movementParam + 75) {
					stopMoving();
				} else if (movementTime % 400 == movementParam + 100) {
					moveLeft();
				} else if (movementTime % 400 == movementParam + 175) {
					stopMoving();
				} else if (movementTime % 400 == movementParam + 200) {
					moveUp();
				} else if (movementTime % 400 == movementParam + 275) {
					stopMoving();
				} else if (movementTime % 400 == movementParam + 300) {
					moveDown();
				} else if (movementTime % 400 == movementParam + 375) {
					stopMoving();
				} else if (movementTime == 999) {
					movementTime = 0;
				}
				movementTime++;
			}
			if (getCenterY() > 30 && getCenterY() < 450) {
				int difX = player.getCenterX() - getCenterX();
				int difY = player.getCenterY() - getCenterY();
				int absdifX = Math.abs(difX);
				int absdifY = Math.abs(difY);
				if (absdifX < 80 && difY > 0 && difY < 400) {
					stopMoving();
					isShooting = true;
					if (weapon.isReady2Fire()) {
						shootDown();
					}
				} else if (absdifX < 80 && difY < 0 && difY > -400) {
					stopMoving();
					isShooting = true;
					if (weapon.isReady2Fire()) {
						shootUp();
					}
				} else if (absdifY < 80 && difX > 0 && difX < 400) {
					stopMoving();
					isShooting = true;
					if (weapon.isReady2Fire()) {
						shootRight();
					}
				} else if (absdifY < 80 && difX < 0 && difX > -400) {
					stopMoving();
					isShooting = true;
					if (weapon.isReady2Fire()) {
						shootLeft();
					}
				} else {
					isShooting = false;
				}
			}
			weapon.increaseShootingCounter();
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

}
