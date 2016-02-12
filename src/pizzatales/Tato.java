package pizzatales;

import java.awt.Image;

public class Tato extends Enemy {

	protected int movementParam;
	protected boolean isShooting;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	private final static int range = 270;

	public Tato(int centerX, int centerY) {
		super(centerX,centerY, new Gun(), 2, 2);
		movementParam = ((int) (Math.random() * 50));
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		// centerX += speedX;
		// centerY += speedY;

		if (alive == true) {

			switch (StartingClass.difficultylevel) {
			case 1:
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
					}
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
				break;
			case 2:
				if (movementTime % 60 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					if (Math.abs(posplayery-posy)+Math.abs(posplayerx-posx) < 15) {
						int pathresult = 1;
						if (posx != posplayerx && posy != posplayery) {
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
						} else {
							stopMoving();
							isShooting = false;
						}
						if (0 != pathresult) {
							isShooting = true;
							if (weapon.isReady2Fire()) {
								int diffx = Math.abs(getCenterX() - player.getCenterX());
								int diffy = Math.abs(getCenterY() - player.getCenterY());
								if (diffx > diffy && diffy < 80 && diffx < 400) {
									if (player.getCenterX() > getCenterX())
										shootRight();
									else
										shootLeft();
								} else if (diffx < 40 && diffy < 400){
									if (player.getCenterY() > getCenterY())
										shootDown();
									else
										shootUp();
								}
							}
						}
					}
				}
				break;
			case 3:
				if (movementTime % 40 == 0) {
					int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
					int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
					if (Math.abs(posplayery-posy)+Math.abs(posplayerx-posx) < 15) {
						int pathresult = 1;
						if (posx != posplayerx && posy != posplayery) {
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
						} else {
							stopMoving();
							isShooting = false;
						}
						if (0 != pathresult) {
							isShooting = true;
							if (weapon.isReady2Fire()) {
								int diffx = Math.abs(getCenterX() - player.getCenterX());
								int diffy = Math.abs(getCenterY() - player.getCenterY());
								if (diffx > diffy && diffy < 80 && diffx < 400) {
									if (player.getCenterX() > getCenterX())
										shootRight();
									else
										shootLeft();
								} else if (diffx < 40 && diffy < 400){
									if (player.getCenterY() > getCenterY())
										shootDown();
									else
										shootUp();
								}
							}
						}
					}
				}
				break;
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
