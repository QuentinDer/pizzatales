package pizzatales;

import java.awt.Image;

public class Garlnstein extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	public static Image slashRight, slashLeft, slashUp, slashDown;
	public static Image dashRight, dashLeft, dashUp, dashDown, dashBlinking;
	
	private int slashcd, repelcd;
	private int maxInAnimation;
	private boolean isDashing;
	private boolean isSlashing;
	private boolean hasSlashed;
	private int slashDirection;
	private int dashcd, revivecd;
	private int dashblinking;
	private int dashblinkingtime;
	private int inAnimation;
	private int dashmaxcd, revivemaxcd;
	private Oniough buddy;
	private int basicspeed = 2;
	private int slashdmg = 3;
	private int dashdmg = 4;
	private int dashspeed;
	private float dashSpeedY;
	private float dashSpeedX;
	private float fspeedX;
	private float fspeedY;
	private float fcenterX;
	private float fcenterY;
	
	public Garlnstein(int centerX, int centerY, boolean fake) {
		super(centerX, centerY, null, fake?10:75, 3, 31, 31,
				25, 25);
		movementTime = ((int) (Math.random() * 50));
		switch(StartingClass.difficultylevel) {
		case 1:
			maxInAnimation = 60;
			dashmaxcd = 600;
			dashblinkingtime = 90;
			dashspeed = 10;
			break;
		case 2:
			maxInAnimation = 50;
			dashmaxcd = 450;
			dashblinkingtime = 80;
			dashspeed = 12;
			break;
		case 3:
			maxInAnimation = 40;
			dashmaxcd = 300;
			dashblinkingtime = 70;
			dashspeed = 14;
			break;
		case 4:
			maxInAnimation = 30;
			dashmaxcd = 150;
			dashblinkingtime = 60;
			dashspeed = 16;
			break;
		}
	}

	@Override
	public void callAI() {
		if (inAnimation > 0) {
			inAnimation--;
			if (inAnimation == 0) {
				halfsizex = 31;
				halfsizey = 31;
				halfrsizex = 25;
				halfrsizey = 25;
				speed = basicspeed;
				currentSprite = staySprite;
				slashcd = 30;
			}
		}
		if (dashblinking > 0) {
			dashblinking--;
			if (dashblinking == 0) {
				float diffx = player.getCenterX() - getCenterX();
				float diffy = player.getCenterY() - getCenterY();
				dashSpeedX = diffx * dashspeed / (Math.abs(diffx)+Math.abs(diffy));
				dashSpeedY = diffy * dashspeed / (Math.abs(diffx)+Math.abs(diffy));
				if (Math.abs(dashSpeedX)>Math.abs(dashSpeedY)) {
					halfsizex = 75;
					halfrsizex = 65;
					halfsizey = 31;
					halfrsizey = 25;
					if (dashSpeedX > 0)
						currentSprite = dashRight;
					else
						currentSprite = dashLeft;
				} else {
					halfsizex = 31;
					halfrsizex = 25;
					halfsizey = 70;
					halfrsizey = 60;
					if (dashSpeedY > 0)
						currentSprite = dashDown;
					else
						currentSprite = dashUp;
				}
				fcenterX = centerX;
				fcenterY = centerY;
				isDashing = true;
				dashcd = dashmaxcd;
			} else {
				if (movementTime % 6 < 3)
					moveLeft();
				else
					moveRight();
			}
		}
		if (!isDashing && dashcd > 0)
			dashcd--;
		if (slashcd > 0)
			slashcd --;
		if (buddy != null && !buddy.alive && revivecd == 0 && dashblinking == 0 && inAnimation == 0 && movementTime % 3 == 0) {
			
		} else if (inAnimation == 0 && dashblinking == 0 && movementTime % 3 == 0) {
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
			int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 200, canmoveleft, canmoveup, canmoveright, canmovedown, dirplace, true);
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
		int diffx = player.getCenterX() - centerX;
		int diffy = player.getCenterY() - centerY;
		if (inAnimation == 0 && slashcd == 0 && Math.abs(diffx) < 20 && Math.abs(diffy) < 60) {
			if (diffy > 0) {
				stopMoving();
				hasSlashed = false;
				isSlashing = true;
				inAnimation = maxInAnimation;
				halfsizex = 31;
				halfrsizex = 25;
				halfsizey = 70;
				halfrsizey = 60;
				currentSprite = slashDown;
				slashDirection = 4;
			} else {
				stopMoving();
				hasSlashed = false;
				isSlashing = true;
				inAnimation = maxInAnimation;
				halfsizex = 31;
				halfrsizex = 25;
				halfsizey = 70;
				halfrsizey = 60;
				currentSprite = slashUp;
				slashDirection = 2;
			}
		} else if (inAnimation == 0 && slashcd == 0 && Math.abs(diffy) < 20 && Math.abs(diffx) < 60) {
			if (diffx > 0) {
				stopMoving();
				hasSlashed = false;
				isSlashing = true;
				inAnimation = maxInAnimation;
				halfsizex = 75;
				halfrsizex = 65;
				halfsizey = 31;
				halfrsizey = 25;
				currentSprite = slashRight;
				slashDirection = 3;
			} else {
				stopMoving();
				hasSlashed = false;
				isSlashing = true;
				inAnimation = maxInAnimation;
				halfsizex = 75;
				halfrsizex = 65;
				halfsizey = 31;
				halfrsizey = 25;
				currentSprite = slashLeft;
				slashDirection = 1;
			}
		}
		if (inAnimation == 0 && dashblinking == 0 && dashcd == 0) {
			stopMoving();
			dashblinking = dashblinkingtime;
			halfsizex = 40;
			halfsizey = 40;
			currentSprite = dashBlinking;
		}
	}
	
	@Override
	public void update() {
		
		speedX = 0;
		speedY = 0;
		
		if (isDashing) {
			if ((dashSpeedX > 0 && !canmoveright) || (dashSpeedX < 0 && !canmoveleft) || (dashSpeedY > 0 && !canmovedown) || (dashSpeedY < 0 && !canmoveup)) {
				isDashing = false;
				halfsizex = 31;
				halfsizey = 31;
				halfrsizex = 25;
				halfrsizey = 25;
				speed = basicspeed;
				currentSprite = staySprite;
				slashcd = 30;
			} else {
				fspeedY = 0;
				fspeedX = 0;
				fspeedY = dashSpeedY;
				fspeedX = dashSpeedX;
				fcenterX += fspeedX - player.getScrollingSpeedX();
				fcenterY += fspeedY - player.getScrollingSpeedY();
				centerX = (int)fcenterX;
				centerY = (int)fcenterY;
				speedX = (int)fspeedX;
				speedY = (int)fspeedY;
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
			centerX += speedX - player.getScrollingSpeedX();
			centerY += speedY - player.getScrollingSpeedY();
		}
		
		R.setBounds(getCenterX() - halfrsizex + speedX, getCenterY() - halfrsizey + speedY, 2*halfrsizex, 2*halfrsizey);
		
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
	public void animate(){
		if (isMoving && inAnimation == 0 && dashblinking == 0 && !isDashing) {
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
				if (player.getArmor().defense - dashdmg < 0) {
					player.setHealth(player.getHealth() - dashdmg + player.getArmor().defense);
					player.getArmor().setDefense(0);
				} else {
					player.getArmor().setDefense(player.getArmor().getDefense() - dashdmg);
				}
				halfsizex = 31;
				halfsizey = 31;
				halfrsizex = 25;
				halfrsizey = 25;
				isDashing = false;
				currentSprite = staySprite;
				inAnimation = 0;
				speed = basicspeed;
			}
			if (isSlashing && !hasSlashed) {
				boolean test = false;
				switch (slashDirection) {
				case 1:
					if (player.getCenterX() < centerX)
						test = true;
					break;
				case 2:
					if (player.getCenterY() < centerY)
						test = true;
					break;
				case 3:
					if (player.getCenterX() > centerX)
						test = true;
					break;
				case 4:
					if (player.getCenterY() > centerY)
						test = true;
					break;
				}
				if (test) {
					hasSlashed = true;
					if (player.getArmor().defense - slashdmg < 0) {
						player.setHealth(player.getHealth() - slashdmg + player.getArmor().defense);
						player.getArmor().setDefense(0);
					} else {
						player.getArmor().setDefense(player.getArmor().getDefense() - slashdmg);
					}
				}
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
}
