package pizzatales;

import java.awt.Image;

public class Pepper extends Enemy {

	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, move1SpriteRight,
	move2SpriteRight;

	public Pepper(int centerX, int centerY) {
		super(centerX,centerY, new Flamer(), 5, (StartingClass.difficultylevel == 1)?2:((StartingClass.difficultylevel == 3)?4:3), 31, 31, 26, 26);
		movementTime = ((int) (Math.random() * 50));
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		switch(StartingClass.difficultylevel) {
		case 1:
			if (movementTime % 30 == 0) {
				StartingClass.map[player.posx][player.posy] = null;
				int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 7, canmoveleft, canmoveup, canmoveright, canmovedown, false);
				StartingClass.map[player.posx][player.posy] = player;
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
			if (movementTime % 20 == 0) {
				StartingClass.map[player.posx][player.posy] = null;
				int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 9, canmoveleft, canmoveup, canmoveright, canmovedown, false);
				StartingClass.map[player.posx][player.posy] = player;
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
				StartingClass.map[player.posx][player.posy] = null;
				int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 11, canmoveleft, canmoveup, canmoveright, canmovedown, false);
				StartingClass.map[player.posx][player.posy] = player;
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
		case 4:
			if (movementTime % 10 == 0) {
				StartingClass.map[player.posx][player.posy] = null;
				int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 12, canmoveleft, canmoveup, canmoveright, canmovedown, true);
				StartingClass.map[player.posx][player.posy] = player;
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
					break;
				}
			}
			break;
		}
		if (movementTime % 5 == 0 && weapon.isReady2Fire()) {
			int diffx = Math.abs(getCenterX() - player.getCenterX());
			int diffy = Math.abs(getCenterY() - player.getCenterY());
			if (diffx + diffy < 150) {
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
