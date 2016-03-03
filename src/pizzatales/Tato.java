package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class Tato extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	public int range = 470;

	public Tato(int centerX, int centerY) {
		super(centerX,centerY, new Gun(), 2, 2, 31, 31, 26, 26);
		movementTime = ((int) (Math.random() * 50));
	}

	// Behavioral Methods
	@Override
	public void callAI() {

		switch (StartingClass.difficultylevel) {
		case 1:
			if (movementTime % 30 == 0) {
				if (Math.abs(player.posy-posy)+Math.abs(player.posx-posx) < 15) {
					int pathresult = 1;
					int difX = player.getCenterX() - getCenterX();
					int difY = player.getCenterY() - getCenterY();
					int absdifX = Math.abs(difX);
					int absdifY = Math.abs(difY);
					int toshooty1 = (absdifY<=range)?posy:((difY>0)?((player.getCenterY() - range - bg.getCenterY() + StartingClass.bginity) / 50):((player.getCenterY() + range - bg.getCenterY() + StartingClass.bginity) / 50));
					int toshootx2 = (absdifX<=range)?posx:((difX>0)?((player.getCenterX() - range - bg.getCenterX() + StartingClass.bginitx) / 50):((player.getCenterX() + range - bg.getCenterX() + StartingClass.bginitx) / 50));
					pathresult = pf.getDirectionToShoot(posx, posy, player.posx, toshooty1, toshootx2, player.posy, 8, canmoveleft, canmoveup, canmoveright, canmovedown, false);
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
			if (movementTime % 20 == 0) {
				if (Math.abs(player.posy-posy)+Math.abs(player.posx-posx) < 15) {
					int pathresult = 1;
					int difX = player.getCenterX() - getCenterX();
					int difY = player.getCenterY() - getCenterY();
					ArrayList<Integer> tox = new ArrayList<Integer>();
					ArrayList<Integer> toy = new ArrayList<Integer>();
					int absdifX = Math.abs(difX);
					int absdifY = Math.abs(difY);
					if (difX > 0) {
						if (StartingClass.map[player.posx+1][player.posy] == null) {
							tox.add(player.posx+1);
							toy.add(player.posy);
						}
					} else {
						if (StartingClass.map[player.posx-1][player.posy] == null) {
							tox.add(player.posx-1);
							toy.add(player.posy);
						}
					}
					if (difY > 0) {
						if (StartingClass.map[player.posx][player.posy+1] == null) {
							tox.add(player.posx);
							toy.add(player.posy+1);
						}
					} else {
						if (StartingClass.map[player.posx][player.posy-1] == null) {
							tox.add(player.posx);
							toy.add(player.posy-1);
						}
					}
					int toshooty1 = (absdifY<=range)?posy:((difY>0)?((player.getCenterY() - range - bg.getCenterY() + StartingClass.bginity) / 50):((player.getCenterY() + range - bg.getCenterY() + StartingClass.bginity) / 50));
					int toshootx2 = (absdifX<=range)?posx:((difX>0)?((player.getCenterX() - range - bg.getCenterX() + StartingClass.bginitx) / 50):((player.getCenterX() + range - bg.getCenterX() + StartingClass.bginitx) / 50));
					int toshootx2R = player.posx;
					int toshooty1R = player.posy;
					if (difX > 0) {
						toshootx2R--;
						while (toshootx2R >= toshootx2 && null == StartingClass.map[toshootx2R][player.posy])
							toshootx2R--;
						toshootx2R++;
					} else {
						toshootx2R++;
						while (toshootx2R <= toshootx2 && null == StartingClass.map[toshootx2R][player.posy])
							toshootx2R++;
						toshootx2R--;
					}
					if (toshootx2R != player.posx) {
						tox.add(toshootx2R);
						toy.add(player.posy);
					}
					if (difY > 0) {
						toshooty1R--;
						while (toshooty1R >= toshooty1 && null == StartingClass.map[player.posx][toshooty1R])
							toshooty1R--;
						toshooty1R++;
					} else {
						toshooty1R++;
						while (toshooty1R <= toshooty1 && null == StartingClass.map[player.posx][toshooty1R])
							toshooty1R++;
						toshooty1R--;
					}
					if (toshooty1R != player.posy) {
						tox.add(player.posx);
						toy.add(toshooty1R);
					}
					pathresult = pf.getDirectionToShoot(posx, posy, tox, toy, 12, canmoveleft, canmoveup, canmoveright, canmovedown, false);
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
		case 3:
			if (movementTime % 10 == 0) {
				if (Math.abs(player.posy-posy)+Math.abs(player.posx-posx) < 15) {
					int pathresult = 1;
					ArrayList<Integer> tox = new ArrayList<Integer>();
					ArrayList<Integer> toy = new ArrayList<Integer>();
					if (StartingClass.map[player.posx+1][player.posy] == null) {
						tox.add(player.posx+1);
						toy.add(player.posy);
					}
					if (StartingClass.map[player.posx-1][player.posy] == null) {
						tox.add(player.posx-1);
						toy.add(player.posy);
					}
					if (StartingClass.map[player.posx][player.posy+1] == null) {
						tox.add(player.posx);
						toy.add(player.posy+1);
					}
					if (StartingClass.map[player.posx][player.posy-1] == null) {
						tox.add(player.posx+1);
						toy.add(player.posy-1);
					}
					pathresult = pf.getDirectionToShoot(posx, posy, tox, toy, 12, canmoveleft, canmoveup, canmoveright, canmovedown, false);
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
		case 4:
			if (movementTime % 05 == 0) {
				if (Math.abs(player.posy-posy)+Math.abs(player.posx-posx) < 15) {
					int pathresult = 1;
					int difX = player.getCenterX() - getCenterX();
					int difY = player.getCenterY() - getCenterY();
					ArrayList<Integer> tox = new ArrayList<Integer>();
					ArrayList<Integer> toy = new ArrayList<Integer>();
					int absdifX = Math.abs(difX);
					int absdifY = Math.abs(difY);
					if (difX > 0) {
						if (StartingClass.map[player.posx+1][player.posy] == null) {
							tox.add(player.posx+1);
							toy.add(player.posy);
						}
					} else {
						if (StartingClass.map[player.posx-1][player.posy] == null) {
							tox.add(player.posx-1);
							toy.add(player.posy);
						}
					}
					if (difY > 0) {
						if (StartingClass.map[player.posx][player.posy+1] == null) {
							tox.add(player.posx);
							toy.add(player.posy+1);
						}
					} else {
						if (StartingClass.map[player.posx][player.posy-1] == null) {
							tox.add(player.posx);
							toy.add(player.posy-1);
						}
					}
					int toshooty1 = (absdifY<=range)?posy:((difY>0)?((player.getCenterY() - range - bg.getCenterY() + StartingClass.bginity) / 50):((player.getCenterY() + range - bg.getCenterY() + StartingClass.bginity) / 50));
					int toshootx2 = (absdifX<=range)?posx:((difX>0)?((player.getCenterX() - range - bg.getCenterX() + StartingClass.bginitx) / 50):((player.getCenterX() + range - bg.getCenterX() + StartingClass.bginitx) / 50));
					int toshootx2R = player.posx;
					int toshooty1R = player.posy;
					if (difX > 0) {
						toshootx2R--;
						while (toshootx2R >= toshootx2 && null == StartingClass.map[toshootx2R][player.posy])
							toshootx2R--;
						toshootx2R++;
					} else {
						toshootx2R++;
						while (toshootx2R <= toshootx2 && null == StartingClass.map[toshootx2R][player.posy])
							toshootx2R++;
						toshootx2R--;
					}
					if (toshootx2R != player.posx) {
						tox.add(toshootx2R);
						toy.add(player.posy);
					}
					if (difY > 0) {
						toshooty1R--;
						while (toshooty1R >= toshooty1 && null == StartingClass.map[player.posx][toshooty1R])
							toshooty1R--;
						toshooty1R++;
					} else {
						toshooty1R++;
						while (toshooty1R <= toshooty1 && null == StartingClass.map[player.posx][toshooty1R])
							toshooty1R++;
						toshooty1R--;
					}
					if (toshooty1R != player.posy) {
						tox.add(player.posx);
						toy.add(toshooty1R);
					}
					pathresult = pf.getDirectionToShoot(posx, posy, tox, toy, 12, canmoveleft, canmoveup, canmoveright, canmovedown, true);
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
