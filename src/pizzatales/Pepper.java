package pizzatales;

import java.awt.Image;

public class Pepper extends Enemy {

	protected int movementParam;
	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	private final static int maxmp = 8;

	public Pepper(int centerX, int centerY, int difficultylevel) {
		super(centerX,centerY, new Flamer(), 6, 3, difficultylevel);
		movementParam = ((int) (Math.random() * 50));
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		if (alive == true) {
			// AI
			int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
			int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
			if (movementParam % 10 == 0) {
				int pathresult = pf.getDirection(posx, posy, posplayerx, posplayery, maxmp);
				switch (pathresult) {
				case 0:
					stopMoving();
					isShooting = false;
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
				if (0 != pathresult) {
					isShooting = true;
					if (weapon.isReady2Fire()) {
						int diffx = Math.abs(getCenterX() - player.getCenterX());
						int diffy = Math.abs(getCenterY() - player.getCenterY());
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
				
			}
			weapon.increaseShootingCounter();
			movementParam++;
			if (movementParam == 1000) {
				movementParam = 0;
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
	
}
