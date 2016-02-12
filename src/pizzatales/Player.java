package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	private int MOVESPEED = 4;

	private int centerX = 400;
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
		if (speedY != 0) {
			centerY += speedY;
		}

		// Updates X Position
		centerX += speedX;
		
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

		// Prevents going beyond X coordinate of 0 or 800
		if (centerX + speedX <= 30) {
			centerX = 31;
		} else if (centerX + speedX >= 769) {
			centerX = 768;
		}

		// Prevents going beyond Y coordinate of 150 and 330
		if (centerY + speedY <= 150) {
			centerY = 149;
			scrollingSpeed = 2*speedY;
		} else if (centerY + speedY >= 280) {
			centerY = 279;
			scrollingSpeed = 2*speedY;
		}

		// Collision
		R.setRect(centerX - 25, centerY - 25, 50, 50);
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
		if (isColliding == false) {
			speedX = MOVESPEED;
			setMovingHor(true);
			if (0 == isShooting) {
				weapon.setSpriteRight();
				isAimingUp = false;
			}
		}
	}

	public void moveLeft() {
		if (isColliding == false) {
			speedX = -MOVESPEED;
			setMovingHor(true);
		}
		if (0 == isShooting) {
			weapon.setSpriteLeft();
			isAimingUp = false;
		}
	}

	public void moveUp() {
		if (isColliding == false) {
			this.setSpeedY(-MOVESPEED);
			setMovingVer(true);
		}
		if (0 == isShooting) {
			weapon.setSpriteUp();
			isAimingUp = true;
			
		}
	}

	public void moveDown() {
		if (isColliding == false) {
			this.setSpeedY(MOVESPEED);
			setMovingVer(true);
		}
		if (0 == isShooting) {
			weapon.setSpriteDown();
			isAimingUp = false;
		}
	}

	public void stopHor() {
		speedX = 0;
		setMovingHor(false);
	}

	public void stopVer() {
		this.setSpeedY(0);
		setMovingVer(false);
	}

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

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}
	
	public int getMOVESPEED() {
		return MOVESPEED;
	}

	public void setMOVESPEED(int mOVESPEED) {
		MOVESPEED = mOVESPEED;
	}
	
	public int getScrollingSpeed() {
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

	public void setSpeedY(int speedY) {
		this.speedY = speedY/2;
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
