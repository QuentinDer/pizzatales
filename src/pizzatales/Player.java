package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	private int MOVESPEED = 4;
	//private final static double MAXSCROLLINGPROPORTION = 0.5;
	//private final static int SCROLLINGRANGE = 150;
	private int centerX = 640;
	private int centerY = 100;
	private int speedX = 0;
	private int speedY = 0;
	private int scrollingSpeed = 0;
	private int health = 20;
	private boolean isMovingVer = false;
	private boolean isMovingHor = false;
	public boolean isColliding = false;
	public Rectangle R = new Rectangle(0,0,0,0);
	private Firearm weapon;
	private Armor armor;
	private boolean ismovingup;
	private boolean ismovingdown;
	private boolean ismovingleft;
	private boolean ismovingright;
	public boolean canmoveright = true;
	public boolean canmoveleft = true;
	public boolean canmoveup = true;
	public boolean canmovedown = true;

	private boolean isAimingUp = true;
	
	public Image currentSprite;

	// 0 = not, 1 = left, 2 = top, 3 = right, 4 = bottom
	private int isShooting = 0;
	public int walkCounter = 0;

	/*
	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();*/

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {

		// Moves Character or Scrolls Background accordingly.

		// Updates X Position
		
		
		speedY = 0;
		speedX = 0;
		scrollingSpeed = 0;
		if (ismovingup && canmoveup) {
			speedY += -MOVESPEED/2;
			scrollingSpeed += -MOVESPEED/2;
		}
		if (ismovingdown && canmovedown) {
			speedY += MOVESPEED/2;
			scrollingSpeed += MOVESPEED/2;
		}
		if (ismovingleft && canmoveleft) {
			speedX += -MOVESPEED;
		}
		if (ismovingright && canmoveright) {
			speedX += MOVESPEED;
		}
		if (0 == isShooting) {
			if (speedX > 0) {
				weapon.setSpriteRight();
				isAimingUp = false;
			}
			if (speedX < 0) {
				weapon.setSpriteLeft();
				isAimingUp = false;
			}
			if (speedY > 0) {
				weapon.setSpriteDown();
				isAimingUp = false;
			}
			if (speedY < 0) {
				weapon.setSpriteUp();
				isAimingUp = true;
			}
		}
			
		
		/*
		if (speedY > 0 && centerY > 200) {
			bg1.setSpeedY(0);
			bg2.setSpeedY(0);
		} else if (speedY == 0) {
			bg1.setSpeedY(0);
			bg2.setSpeedY(0);
		} else if (speedY < 0 && centerY < 50) {
			bg1.setSpeedY(0);
			bg2.setSpeedY(0);
			setSpeedY(0);
			setCenterY(getCenterY() + 2);
		}*/

		// Prevents going beyond X coordinate of 0 or 1250
		if (centerX + speedX <= 30) {
			centerX = 31;
		} else if (centerX + speedX >= 1249) {
			centerX = 1248;
		}

		// Prevents going beyond Y coordinate of 250 and 550
		if (centerY + speedY <= 249) {
			centerY = 250;
			scrollingSpeed = 2*speedY;
		} else if (centerY + speedY >= 550) {
			centerY = 549;
			scrollingSpeed = 2*speedY;
		}
		centerY += speedY;
		centerX += speedX;

		// Collision
		R.setRect(centerX - 22, centerY - 22, 45, 45);
		if (isShooting > 0) {
			if (weapon.isReady2Fire()) {
				switch (isShooting) {
				case 1:
					weapon.shootLeft(centerX, centerY);
					isAimingUp = false;
					break;
				case 2:
					weapon.shootUp(centerX, centerY);
					isAimingUp = true;
					break;
				case 3:
					weapon.shootRight(centerX, centerY);
					isAimingUp = false;
					break;
				case 4:
					weapon.shootDown(centerX, centerY);
					isAimingUp = false;
					break;
				}
			}
		}
		weapon.increaseShootingCounter();
		//animate();
	}
	
	/*
	public void animate(){
		walkCounter++;
		if (walkCounter == 1000)
			walkCounter = 0;
		if (isMovingHor() == true || isMovingVer() == true) {
			if (walkCounter % 30 == 0) {
				armor.setSpriteWalk1();
				currentSprite = armor.currentSprite;
				//currentSprite = characterMove1;
			} else if (walkCounter % 15 == 0) {
				armor.setSpriteWalk2();
				currentSprite = armor.currentSprite;
				//currentSprite = characterMove2;
			}
		} else if (isMovingVer() == false && isMovingHor() == false) {
			armor.setSpriteStay1();
			currentSprite = armor.currentSprite;
			//currentSprite = anim.getImage();
		}
	}*/
	
	public boolean isAimingUp() {
		return isAimingUp;
	}

	public int isShooting() {
		return isShooting;
	}

	public void setShooting(int isShooting) {
		this.isShooting = isShooting;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void moveRight() {
		ismovingright = true;
	}

	public void moveLeft() {
		ismovingleft = true;
	}

	public void moveUp() {
		ismovingup = true;
	}

	public void moveDown() {
		ismovingdown = true;
	}
	
	public void stopMovingRight() {
		ismovingright = false;
	}
	
	public void stopMovingLeft() {
		ismovingleft = false;
	}
	
	public void stopMovingUp() {
		ismovingup = false;
	}
	
	public void stopMovingDown() {
		ismovingdown = false;
	}
/*
	public void stopHor() {
		speedX = 0;
		setMovingHor(false);
	}

	public void stopVer() {
		this.setSpeedY(0);
		setMovingVer(false);
	}*/
/*
	public boolean isMovingVer() {
		return isMovingVer;
	}

	public void setMovingVer(boolean isMovingVer) {
		this.isMovingVer = isMovingVer;
	}

	public boolean isMovingHor() {
		return isMovingHor;
	}

	public void setMovingHor(boolean isMovingHor) {
		this.isMovingHor = isMovingHor;
	}
*/
	public double getSpeedX() {
		return speedX;
	}

	public double getSpeedY() {
		return speedY;
	}
	
	public int getMOVESPEED() {
		return MOVESPEED;
	}

	public void setMOVESPEED(int mOVESPEED) {
		MOVESPEED = mOVESPEED;
	}
	
	public double getScrollingSpeed() {
		return this.scrollingSpeed;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void addSpeedY(int speedY) {
		this.speedY += speedY /2;
		this.scrollingSpeed += speedY/2;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY /2;
		this.scrollingSpeed = speedY/2; 
	}


	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setWeapon(Firearm weapon) {
		this.weapon = weapon;
	}
	
	public Firearm getWeapon() {
		return this.weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}
}

