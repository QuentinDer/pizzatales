package pizzatales;

import java.awt.Image;

public class Pepper extends Enemy {

	protected int movementParam;
	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	private final static int maxmp = 8;
	private PathFinder pathfinder = new PathFinder();
	private Background bg = StartingClass.getBg1();
	private int bginitx, bginity;

	public Pepper(int centerX, int centerY, int difficultylevel) {
		super(centerX,centerY, new Flamer(), 6, 3, difficultylevel);
		movementParam = ((int) (Math.random() * 50));
		bginitx = bg.getCenterX();
		bginity = bg.getCenterY();
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		if (alive == true) {
			// AI
			int posx = (getCenterX() - bg.getCenterX() + bginitx) / 50;
			int posy = (getCenterY() - bg.getCenterY() + bginity) / 50;
			int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
			int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
			if (movementParam % 10 == 0) {
				switch (pathfinder.getDirection(posx, posy, posplayerx, posplayery, maxmp)) {
				case 0:
					stopMoving();
					isShooting = false;
					break;
				case 1:
					isShooting = true;
					if (weapon.isReady2Fire()) {
						shootLeft();
					}
					moveLeft();
					break;
				case 2:
					isShooting = true;
					if (weapon.isReady2Fire()) {
						shootUp();
					}
					moveUp();
					break;
				case 3:
					isShooting = true;
					if (weapon.isReady2Fire()) {
						shootRight();
					}
					moveRight();
					break;
				case 4:
					isShooting = true;
					if (weapon.isReady2Fire()) {
						shootDown();
					}
					moveDown();
					break;
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
