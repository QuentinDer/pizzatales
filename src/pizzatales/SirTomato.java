package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

import pizzatales.StartingClass.GameState;

public class SirTomato extends Enemy {
	
	private int inAnimation, slashdmg, dashdmg, dashspeed, tatocd, dashcd, tcd, dcd;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, 
		move1SpriteRight, move2SpriteRight, sirtomatothrowleft, sirtomatothrowright,
		dashSpriteRight, dashSpriteLeft;
	private int maxInAnimation;
	private boolean isDashing;
	private int dashSpeedX;
	
	public SirTomato(int centerX, int centerY) {
		super(centerX, centerY, null, 100, 3, 50, 50);
		movementTime = ((int) (Math.random() * 50));
		R = new Rectangle(getCenterX() - 45, getCenterY() - 45, 90, 90);
		slashdmg = 4;
		dashdmg = 5;
		switch (StartingClass.difficultylevel) {
		case 1:
			maxInAnimation = 60;
			tatocd = 300;
			dcd = Integer.MAX_VALUE;
			dashcd = Integer.MAX_VALUE;
			break;
		case 2:
			maxInAnimation = 50;
			tatocd = 250;
			dcd = Integer.MAX_VALUE;
			dashcd = Integer.MAX_VALUE;
			break;
		case 3:
			maxInAnimation = 40;
			tatocd = 200;
			dashcd = 180;
			dashspeed = 6;
			break;
		case 4:
			maxInAnimation = 30;
			tatocd = 120;
			dashcd = 120;
			dashspeed = 8;
			break;
		}
	}
	
	@Override
	public void update() {
		super.update();
		R.setBounds(getCenterX() - 45, getCenterY() - 45, 90, 90);
	}
 
	@Override
	public void callAI() {
		if (alive == true) {
			if (inAnimation>0) {
				inAnimation--;
				if (inAnimation == 0) {
					halfsizex = 50;
					halfsizey = 50;
					halfrsizex = 45;
					halfrsizey = 45;
					isDashing = false;
					speed = 3;
				}
			}
			if (isDashing && speedX != dashSpeedX) {
				halfsizex = 50;
				halfsizey = 50;
				halfrsizex = 45;
				halfrsizey = 45;
				isDashing = false;
				currentSprite = staySprite;
				speed = 3;
				inAnimation = 0;
			}
				
			
			//boss parameters difinition
			if (tcd > 0)
				tcd--;
			if (dcd > 0)
				dcd--;
			
			int posplayerx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
			int posplayery = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
			if (inAnimation == 0 && !isDashing) {
				if (movementTime % 10 == 0) {
					int tox = posplayerx;
					if (posx < posplayerx) {
						if (pf.map[posplayerx-1][posplayery])
							tox = posplayerx-1;
						else 
							tox = posplayerx+1;
					} else {
						if (pf.map[posplayerx+1][posplayery])
							tox = posplayerx+1;
						else
							tox = posplayerx-1;
					}
					int pathresult = pf.getDirectionToShoot(posx, posy, tox, posplayery, 50, canmoveleft, canmoveup, canmoveright, canmovedown, true);
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
					}
				}
				
				if (tcd == 0 && Math.abs(player.getCenterX() - getCenterX()) > 300) {
					if (player.getCenterX() < getCenterX()) {
						projectiles.add(new TomatoProjectile(centerX - 30,centerY,-1,0));
						stopMoving();
						currentSprite = sirtomatothrowleft;
						inAnimation = maxInAnimation;
						tcd = tatocd;
					} else {
						projectiles.add(new TomatoProjectile(centerX + 30,centerY,1,0));
						stopMoving();
						currentSprite = sirtomatothrowright;
						inAnimation = maxInAnimation;
						tcd = tatocd;
					}
				}
				if (dcd == 0 && Math.abs(player.getCenterX() - getCenterX()) > 100 && Math.abs(posplayery-posy)<=1) {
					if (player.getCenterX() > getCenterX()) {
						currentSprite = dashSpriteRight;
						moveRight();
						dashSpeedX = dashspeed;
					} else {
						currentSprite = dashSpriteLeft;
						moveLeft();
						dashSpeedX = -dashspeed;
					}
					speed = dashspeed;
					halfsizex = 100;
					halfrsizex = 90;
					isDashing = true;
					inAnimation = 50;
					dcd = dashcd;
				}
			}
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
	protected void checkCollisionPlayer() {
		if (R.intersects(player.R)) {
			if (Math.abs(player.getCenterX() - getCenterX()) > Math.abs(player.getCenterY() - getCenterY())) {
				if (player.getCenterX() - getCenterX() > 0) {
					canmoveright = false;
					player.canmoveleft = false;
				} else {
					canmoveleft = false;
					player.canmoveright = false;
				}
			} else {
				if (player.getCenterY() - getCenterY() > 0) {
					canmovedown = false;
					player.canmoveup = false;
				} else {
					canmoveup = false;
					player.canmovedown = false;
				}
			}
			if (isDashing) {
				if (player.getArmor().defense - dashdmg < 0) {
					player.setHealth(player.getHealth() - dashdmg + player.getArmor().defense);
					player.getArmor().setDefense(0);
				} else {
					player.getArmor().setDefense(player.getArmor().getDefense() - dashdmg);
				}
				halfsizex = 50;
				halfsizey = 50;
				halfrsizex = 45;
				halfrsizey = 45;
				isDashing = false;
				currentSprite = staySprite;
				inAnimation = 0;
				speed = 3;
			}
		}
	}
	
	@Override
	public void animate(){
		if (isMoving && inAnimation == 0 && !isDashing) {
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
