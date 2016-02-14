package pizzatales;

import java.awt.Image;

public class Broccoli extends Enemy {

	protected int movementParam;
	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	private final static int range = 720;

	public Broccoli(int centerX, int centerY) {
		super(centerX,centerY, new Rocket(), 4, 1);
		movementParam = ((int) (Math.random() * 50));
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		if (alive == true) {

			switch (StartingClass.difficultylevel) {
			case 1:
				if (movementTime % 50 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					if (Math.abs(posplayery-posy)+Math.abs(posplayerx-posx) < 15) {
						int pathresult = 1;
						int difX = player.getCenterX() - getCenterX();
						int difY = player.getCenterY() - getCenterY();
						int absdifX = Math.abs(difX);
						int absdifY = Math.abs(difY);
						int toshooty1 = (absdifY<=range)?posy:((difY>0)?((player.getCenterY() - range - bg.getCenterY() + bginity) / 50):((player.getCenterY() + range - bg.getCenterY() + bginity) / 50));
						int toshootx2 = (absdifX<=range)?posx:((difX>0)?((player.getCenterX() - range - bg.getCenterX() + bginitx) / 50):((player.getCenterX() + range - bg.getCenterX() + bginitx) / 50));
						pathresult = pf.getDirectionToShoot(posx, posy, posplayerx, toshooty1, toshootx2, posplayery, 8);
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
					} else {
						stopMoving();
					}
				}
				break;
			case 2:
				if (movementTime % 40 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					if (Math.abs(posplayery-posy)+Math.abs(posplayerx-posx) < 15) {
						int pathresult = 1;
						int difX = player.getCenterX() - getCenterX();
						int difY = player.getCenterY() - getCenterY();
						int absdifX = Math.abs(difX);
						int absdifY = Math.abs(difY);
						int toshooty1 = (absdifY<=range)?posy:((difY>0)?((player.getCenterY() - range - bg.getCenterY() + bginity) / 50):((player.getCenterY() + range - bg.getCenterY() + bginity) / 50));
						int toshootx2 = (absdifX<=range)?posx:((difX>0)?((player.getCenterX() - range - bg.getCenterX() + bginitx) / 50):((player.getCenterX() + range - bg.getCenterX() + bginitx) / 50));
						pathresult = pf.getDirectionToShoot(posx, posy, posplayerx, toshooty1, toshootx2, posplayery, 8);
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
					} else {
						stopMoving();
					}
				}
				break;
			case 3:
				if (movementTime % 30 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					if (Math.abs(posplayery-posy)+Math.abs(posplayerx-posx) < 15) {
						int pathresult = 1;
						int difX = player.getCenterX() - getCenterX();
						int difY = player.getCenterY() - getCenterY();
						int absdifX = Math.abs(difX);
						int absdifY = Math.abs(difY);
						int toshooty1 = (absdifY<=range)?posy:((difY>0)?((player.getCenterY() - range - bg.getCenterY() + bginity) / 50):((player.getCenterY() + range - bg.getCenterY() + bginity) / 50));
						int toshootx2 = (absdifX<=range)?posx:((difX>0)?((player.getCenterX() - range - bg.getCenterX() + bginitx) / 50):((player.getCenterX() + range - bg.getCenterX() + bginitx) / 50));
						pathresult = pf.getDirectionToShoot(posx, posy, posplayerx, toshooty1, toshootx2, posplayery, 12);
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
					} else {
						stopMoving();
					}
				}
				break;
			case 4:
				if (movementTime % 15 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					if (Math.abs(posplayery-posy)+Math.abs(posplayerx-posx) < 15) {
						int pathresult = 1;
						int difX = player.getCenterX() - getCenterX();
						int difY = player.getCenterY() - getCenterY();
						int absdifX = Math.abs(difX);
						int absdifY = Math.abs(difY);
						int toshooty1 = (absdifY<=range)?posy:((difY>0)?((player.getCenterY() - range - bg.getCenterY() + bginity) / 50):((player.getCenterY() + range - bg.getCenterY() + bginity) / 50));
						int toshootx2 = (absdifX<=range)?posx:((difX>0)?((player.getCenterX() - range - bg.getCenterX() + bginitx) / 50):((player.getCenterX() + range - bg.getCenterX() + bginitx) / 50));
						pathresult = pf.getDirectionToShoot(posx, posy, posplayerx, toshooty1, toshootx2, posplayery, 12);
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
					} else {
						stopMoving();
					}
				}
				break;
			}
			if (weapon.isReady2Fire()) {
				int centerpX = 50*posx + bg.getCenterX() - bginitx;
				int centerpY = 50*posy + bg.getCenterY() - bginity;
				int diffx = Math.abs(getCenterX() - player.getCenterX());
				int diffy = Math.abs(getCenterY() - player.getCenterY());
				if (diffx > diffy && diffy < 120 && diffx < range && Math.abs(centerpY-getCenterY())<10) {
					if (player.getCenterX() > getCenterX()) {
						if ((posx == pf.map.length - 2) ||
						(posx == pf.map.length - 3 && pf.map[posx+1][posy]) ||
						(posx < pf.map.length - 3 && pf.map[posx+1][posy] && pf.map[posx+2][posy]))
							shootRight();
					}
					else {
						if ((posx == 1) ||
							(posx == 2 && pf.map[1][posy]) ||
							(posx > 2 && pf.map[posx-1][posy] && pf.map[posx-2][posy]))
							shootLeft();
					}
				} else if (diffx < 120 && diffy < range && Math.abs(centerpX-getCenterX())<10){
					if (player.getCenterY() > getCenterY()) {
						if ((posy == pf.map[0].length - 2) ||
								(posy == pf.map[0].length - 3 && pf.map[posx][posy+1]) ||
								(posy < pf.map[0].length - 3 && pf.map[posx][posy+1] && pf.map[posx][posy+2]))
									shootDown();
					}
					else {
						if ((posy == 1) ||
								(posy == 2 && pf.map[posx][1]) ||
								(posy > 2 && pf.map[posx][posy-1] && pf.map[posx][posy-2]))
								shootUp();
					}
				}
			}
			movementTime++;
			if (movementTime == 1000) {
				movementTime = 0;
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
