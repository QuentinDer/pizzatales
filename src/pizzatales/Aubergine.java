package pizzatales;

import java.awt.Image;

public class Aubergine extends Enemy {

	protected int movementParam;
	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	private final static int range = 420;

	public Aubergine(int centerX, int centerY) {
		super(centerX,centerY, new Smg(), 4, 2);
		movementParam = ((int) (Math.random() * 50));
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		if (alive == true) {

			switch (StartingClass.difficultylevel) {
			case 1:
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
						pathresult = pf.getDirectionToShoot(posx, posy, posplayerx, toshooty1, toshootx2, posplayery, 8, canmoveleft, canmoveup, canmoveright, canmovedown);
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
						pathresult = pf.getDirectionToShoot(posx, posy, posplayerx, toshooty1, toshootx2, posplayery, 8, canmoveleft, canmoveup, canmoveright, canmovedown);
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
				if (movementTime % 20 == 0) {
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
						pathresult = pf.getDirectionToShoot(posx, posy, posplayerx, toshooty1, toshootx2, posplayery, 12, canmoveleft, canmoveup, canmoveright, canmovedown);
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
				if (movementTime % 10 == 0) {
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
						int toshootx2R = posplayerx;
						int toshooty1R = posplayery;
						if (difX > 0) {
							toshootx2R--;
							while (toshootx2R >= toshootx2 && pf.map[toshootx2R][posplayery])
								toshootx2R--;
							toshootx2R++;
						} else {
							toshootx2R++;
							while (toshootx2R <= toshootx2 && pf.map[toshootx2R][posplayery])
								toshootx2R++;
							toshootx2R--;
						}
						if (difY > 0) {
							toshooty1R--;
							while (toshooty1R >= toshooty1 && pf.map[posplayerx][toshooty1R])
								toshooty1R--;
							toshooty1R++;
						} else {
							toshooty1R++;
							while (toshooty1R <= toshooty1 && pf.map[posplayerx][toshooty1R])
								toshooty1R++;
							toshooty1R--;
						}
						pathresult = pf.getDirectionToShoot(posx, posy, posplayerx, toshooty1R, toshootx2R, posplayery, 12, canmoveleft, canmoveup, canmoveright, canmovedown);
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
				int diffx = Math.abs(getCenterX() - player.getCenterX());
				int diffy = Math.abs(getCenterY() - player.getCenterY());
				if (diffx > diffy && diffy < 120 && diffx < 480) {
					if (player.getCenterX() > getCenterX())
						shootRight();
					else
						shootLeft();
				} else if (diffx < 120 && diffy < 480){
					if (player.getCenterY() > getCenterY())
						shootDown();
					else
						shootUp();
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
