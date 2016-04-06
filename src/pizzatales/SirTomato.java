package pizzatales;

import java.awt.Image;

public class SirTomato extends Enemy {
	
	private int inAnimation, slashdmg, dashdmg, dashspeed, tatocd, dashcd, tcd, dcd;
	private static final int basicspeed = 3;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, 
		move1SpriteRight, move2SpriteRight, sirtomatothrowleft, sirtomatothrowright,
		dashSpriteRight, dashSpriteLeft, slashSpriteLeft, slashSpriteRight;
	private int maxInAnimation;
	private boolean isDashing;
	private boolean isSlashing;
	private boolean isSlashingRight;
	private int slashCd;
	private boolean hasSlashed;
	private float dashSpeedX;
	
	public SirTomato(int centerX, int centerY) {
		super(centerX, centerY, null, 100, basicspeed, 50, 50, 45, 45);
		movementTime = ((int) (Math.random() * 50));
		halfbarx = 45;
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
			tatocd = 240;
			dcd = Integer.MAX_VALUE;
			dashcd = Integer.MAX_VALUE;
			break;
		case 3:
			maxInAnimation = 40;
			tatocd = 180;
			dashcd = 180;
			dashspeed = 6;
			break;
		case 4:
			maxInAnimation = 30;
			tatocd = 240;
			dashcd = 120;
			dashspeed = 8;
			break;
		}
	}
 
	@Override
	public void callAI() {
		if (inAnimation>0) {
			inAnimation--;
			if (inAnimation == 0) {
				halfsizex = 50;
				halfsizey = 50;
				halfrsizex = 45;
				halfrsizey = 45;
				isDashing = false;
				speed = basicspeed;
				currentSprite = staySprite;
				isSlashing = false;
				slashCd = 30;
				showHealthBar = true;
				paintoverride = false;
			}
		}
		if (slashCd > 0)
			slashCd--;
			
		
		//boss parameters difinition
		if (tcd > 0)
			tcd--;
		if (dcd > 0)
			dcd--;
		
		if (inAnimation == 0 && !isDashing) {
			if (movementTime % 10 == 0) {
				int tox = player.posx;
				if (posx < player.posx) {
					if (null == StartingClass.map[player.posx-1][player.posy])
						tox = player.posx-1;
					else 
						tox = player.posx+1;
				} else {
					if (null == StartingClass.map[player.posx+1][player.posy])
						tox = player.posx+1;
					else
						tox = player.posx-1;
				}
				int pathresult = pf.getDirectionToShoot(posx, posy, tox, player.posy, 50, canmoveleft, canmoveup, canmoveright, canmovedown, true);
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
			int diffx = Math.abs(player.getCenterX() - getCenterX());
			int diffy = player.getCenterY() - getCenterY();
			if (slashCd == 0 && diffx < 100 && diffy < 20 && diffy > -80) {
				stopMoving();
				hasSlashed = false;
				isSlashing = true;
				inAnimation = maxInAnimation;
				halfsizex = 100;
				halfrsizex = 85;
				halfsizey = 65;
				halfrsizey = 50;
				if (player.getCenterX() > getCenterX()) {
					currentSprite = slashSpriteRight;
					isSlashingRight = true;
				} else {
					currentSprite = slashSpriteLeft;
					isSlashingRight = false;
				}
			}
			if (tcd == 0 && diffx > 300) {
				if (player.getCenterX() < getCenterX()) {
					boolean test = true;
					for (int i = 0; i < 3; i++) {
						for (int j = -1; j < 2; j++) {
							if ((i != 0 || j != 0) && StartingClass.map[posx-i][posy+j] != null)
								test = false;
						}
					}
					if (test) {
						projectiles.add(new TomatoProjectile(centerX - 30,centerY,-1,0));
						stopMoving();
						currentSprite = sirtomatothrowleft;
						inAnimation = maxInAnimation;
						tcd = tatocd;
					}
				} else {
					boolean test = true;
					for (int i = 0; i < 3; i++) {
						for (int j = -1; j < 2; j++) {
							if ((i != 0 || j != 0) && StartingClass.map[posx+i][posy+j] != null)
								test = false;
						}
					}
					if (test) {
						projectiles.add(new TomatoProjectile(centerX + 30,centerY,1,0));
						stopMoving();
						currentSprite = sirtomatothrowright;
						inAnimation = maxInAnimation;
						tcd = tatocd;
					}
				}
			}
			if (dcd == 0 && diffx > 120 && Math.abs(player.posy-posy)<=1) {
				boolean candash = true;
				if (player.getCenterX() > getCenterX()) {
					for (int i = posx+1; i < player.posx; i++)
						candash = candash && (StartingClass.map[i][posy] == null);
					if (candash) {
						currentSprite = dashSpriteRight;
						moveRight();
						dashSpeedX = dashspeed;
					}
				} else {
					for (int i = posx-1; i > player.posx; i--)
						candash = candash && (StartingClass.map[i][posy] == null);
					if (candash) {
						currentSprite = dashSpriteLeft;
						moveLeft();
						dashSpeedX = -dashspeed;
					}
				}
				if (candash) {
					speed = dashspeed;
					halfsizex = 100;
					halfrsizex = 90;
					halfrsizey = 35;
					isDashing = true;
					paintoverride = true;
					showHealthBar = false;
					inAnimation = 50;
					dcd = dashcd;
					R.setBounds((int)(centerX - halfrsizex + speedX), (int)(centerY - halfrsizey + speedY), 2*halfrsizex, 2*halfrsizey);
				}
			}
		}
	}
	
	@Override
	public void update() {
		
		speedX = 0;
		speedY = 0;
		
		if (isDashing) {
			if ((dashSpeedX > 0 && !canmoveright) || (dashSpeedX < 0 && !canmoveleft)) {
				isDashing = false;
				paintoverride = false;
				showHealthBar = true;
				halfsizex = 50;
				halfsizey = 50;
				halfrsizex = 45;
				halfrsizey = 45;
				isDashing = false;
				currentSprite = staySprite;
				speed = basicspeed;
				inAnimation = 0;
			} else {
				speedX = dashSpeedX;
				fcenterX += speedX - player.getScrollingSpeedX();
				fcenterY += speedY - player.getScrollingSpeedY();
				centerX = (int)fcenterX;
				centerY = (int)fcenterY;
			}
		} else {
			if (ismovingup) {
				speedY += -speed;
			}
			if (ismovingdown) {
				speedY += speed;
			}
			if (ismovingleft) {
				speedX += -speed;
			}
			if (ismovingright) {
				speedX += speed;
			}
			if (speedY > 0 && !canmovedown)
				speedY = 0;
			if (speedY < 0 && !canmoveup)
				speedY = 0;
			if (speedX > 0 && !canmoveright)
				speedX = 0;
			if (speedX < 0 && !canmoveleft)
				speedX = 0;
			fcenterX += speedX - player.getScrollingSpeedX();
			fcenterY += speedY - player.getScrollingSpeedY();
			centerX = (int)fcenterX;
			centerY = (int)fcenterY;
		}
		
		R.setBounds((int)(centerX - halfrsizex + speedX), (int)(centerY - halfrsizey + speedY), 2*halfrsizex, 2*halfrsizey);
		
		if (alive == true) {
			
			posx = (getCenterX() - bg.getCenterX() + StartingClass.bginitx) / 50;
			posy = (getCenterY() - bg.getCenterY() + StartingClass.bginity) / 50;
			StartingClass.map[posx][posy] = this;
			animate();
			if (movementTime % 15 == 0 && !hasSeenU && centerX + halfsizex > 0 
					&& centerX - halfsizex< 1280 && centerY + halfsizey > 0 
					&& centerY - halfsizey < 800 && this.canSeePlayer()) {
				hasSeenU = true;
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
	public void checkCollisionPlayer() {
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
				player.damage(dashdmg);
				halfsizex = 50;
				halfsizey = 50;
				halfrsizex = 45;
				halfrsizey = 45;
				isDashing = false;
				paintoverride = false;
				showHealthBar = true;
				currentSprite = staySprite;
				inAnimation = 0;
				speed = basicspeed;
			}
			if (isSlashing && !hasSlashed) {
				if ((isSlashingRight && player.getCenterX() > centerX - 20 && player.getCenterY() < centerY + 60) || (!isSlashingRight && player.getCenterX() < centerX + 20 && player.getCenterY() < centerY + 60)) {
					hasSlashed = true;
					player.damage(slashdmg);
				}
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
	
	@Override
	public void setGibsSprite() {
		currentSprite = dieSprite;
	}
}
