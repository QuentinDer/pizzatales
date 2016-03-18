package pizzatales;

import java.awt.Image;

public class Oniough extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite,
	dashSpriteRight, dashSpriteLeft, slashSpriteLeft, slashSpriteRight;
	
	private Garlnstein buddy;
	private int inAnimation;
	private int maxInAnimation;
	private int firecd;
	private int firemaxcd0, firemaxcd1, firemaxcd2;
	private int firingmode;
	
	public Oniough(int centerX, int centerY) {
		super(centerX, centerY, null, 75, 1, 50, 50, 45, 45);
		halfbarx = 45;
		movementTime = ((int) (Math.random() * 50));
		switch(StartingClass.difficultylevel) {
		case 1:
			firemaxcd0 = 40;
			break;
		case 2:
			firemaxcd0 = 35;
			break;
		case 3:
			firemaxcd0 = 30;
			break;
		case 4:
			firemaxcd0 = 25;
			break;
		}
	}

	@Override
	public void callAI() {
		if (inAnimation > 0) {
			inAnimation--;
			if (inAnimation == 0) {
				//TODO
			}
		}
		if (firecd > 0)
			firecd--;
		if (inAnimation == 0 && movementTime % 3 == 0) {
			StartingClass.map[player.posx][player.posy] = null;
			int dirplace = 0;
			int difPX = 50*posx+25+bg.getCenterX()-StartingClass.bginitx - centerX;
			int difPY = 50*posy+25+bg.getCenterY()-StartingClass.bginity - centerY;
			if (Math.abs(difPX) < 2) {
				if (difPY > 0)
					dirplace = 4;
				else
					dirplace = 2;
			} else if (Math.abs(difPY) < 2) {
				if (difPX > 0)
					dirplace = 3;
				else
					dirplace = 1;
			} else {
				if (difPX > 0) {
					if (difPY > 0)
						dirplace = 7;
					else
						dirplace = 6;
				} else {
					if (difPY > 0)
						dirplace = 8;
					else
						dirplace = 5;
				}
			}
			int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 200, canmoveleft, canmoveup, canmoveright, canmovedown, dirplace, false);
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
		if (firecd == 0) {
			switch(firingmode) {
			case 0:
				float diffx = player.getCenterX() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedX()/10.f - getCenterX();
				float diffy = player.getCenterY() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedY()/10.f - getCenterY();
				float vectorx = diffx / (Math.abs(diffx)+Math.abs(diffy));
				float vectory = diffy / (Math.abs(diffx)+Math.abs(diffy));
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory));
				firecd = firemaxcd0;
				break;
			case 1:
				//TODO
				break;
			case 2:
				//TODO
				break;
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
	
	@Override
	public void animate(){
		if (isMoving && inAnimation == 0) {
			walkCounter++;
			if (getSpeedX() <= 0) {
				if (walkCounter == 1000)
					walkCounter = 0;
				if (walkCounter % 30 == 0) {
					setMove1Sprite();
				} else if (walkCounter % 15 == 0) {
					setMove2Sprite();
				}
			} else {
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

}
