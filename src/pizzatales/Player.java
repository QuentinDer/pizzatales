package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player extends Point {

	private int MOVESPEED = 4;
	//private final static double MAXSCROLLINGPROPORTION = 0.5;
	//private final static int SCROLLINGRANGE = 150;
	private int speedX = 0;
	private int speedY = 0;
	private int scrollingSpeed = 0;
	private int health = 20;
	public boolean isColliding = false;
	public Rectangle R = new Rectangle(0,0,0,0);
	private Firearm weapon;
	private Armor armor;
	private boolean ismovingup;
	private boolean ismovingdown;
	private boolean ismovingleft;
	private boolean ismovingright;
	private boolean controlledismovingup;
	private boolean controlledismovingdown;
	private boolean controlledismovingleft;
	private boolean controlledismovingright;
	public boolean canmoveright = true;
	public boolean canmoveleft = true;
	public boolean canmoveup = true;
	public boolean canmovedown = true;
	public int posx;
	public int posy;

	private boolean isAimingUp = true;
	
	public Image currentSprite;

	// 0 = not, 1 = left, 2 = top, 3 = right, 4 = bottom
	private int isShooting = 0;
	public int walkCounter = 0;
	
	public Player() {
		super(640,100);
	}

	private Background bg;

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void setBackground(Background bg) {
		this.bg = bg;
		posx = (centerX - bg.getCenterX() + StartingClass.bginitx) / 50;
		posy = (centerY - bg.getCenterY() + StartingClass.bginity) / 50;
	}
	
	@Override
	public void setCenterX(int centerX) {
		super.setCenterX(centerX);
		posx = (centerX - bg.getCenterX() + StartingClass.bginitx) / 50;
	}
	
	@Override
	public void setCenterY(int centerY) {
		super.setCenterY(centerY);
		posy = (centerY - bg.getCenterY() + StartingClass.bginity) / 50;
	}

	public void update() {

		// Moves Character or Scrolls Background accordingly.

		// Updates X Position
		
		
		speedY = 0;
		speedX = 0;
		scrollingSpeed = 0;
		if (ismovingup && canmoveup) {
			if (StartingClass.isInArena >= 0) {
				speedY += -MOVESPEED;
			} else {
				speedY += -MOVESPEED/2;
				scrollingSpeed += -MOVESPEED/2;
			}
		}
		if (ismovingdown && canmovedown) {
			if (StartingClass.isInArena >= 0) {
				speedY += MOVESPEED;
			} else {
				speedY += MOVESPEED/2;
				scrollingSpeed += MOVESPEED/2;
			}
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
		if (StartingClass.isInArena < 0) {
			if (centerY + speedY <= 249) {
				centerY = 250;
				scrollingSpeed = 2*speedY;
			} else if (centerY + speedY >= 550) {
				centerY = 549;
				scrollingSpeed = 2*speedY;
			}
		}
		centerY += speedY;
		centerX += speedX;

		// Collision
		R.setRect(centerX + speedX - 25, centerY + speedY - 25, 50, 50);
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
		
		posx = (centerX - bg.getCenterX() + StartingClass.bginitx) / 50;
		posy = (centerY - bg.getCenterY() + StartingClass.bginity) / 50;
		//animate();
	}
	
	public void controlledupdate() {
		speedY = 0;
		speedX = 0;
		if (controlledismovingright && canmoveright) {
			speedX = MOVESPEED;
		}
		if (controlledismovingleft && canmoveleft) {
			speedX = -MOVESPEED;
		}
		if (controlledismovingup && canmoveup) {
			speedY = -MOVESPEED;
		}
		if (controlledismovingdown && canmovedown) {
			speedY = MOVESPEED;
		}
		speedY -= scrollingSpeed;
		centerY += speedY;
		centerX += speedX;
		R.setRect(centerX +speedX- 25, centerY +speedY- 25, 50, 50);
		weapon.increaseShootingCounter();
		posx = (centerX - bg.getCenterX() + StartingClass.bginitx) / 50;
		posy = (centerY - bg.getCenterY() + StartingClass.bginity) / 50;
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
	
	
	protected void controlledmoveRight() {
		isAimingUp = false;
		controlledismovingright = true;
		controlledismovingleft = false;
		controlledismovingup = false;
		controlledismovingdown = false;
	}
	
	public void controlledmoveLeft() {
		isAimingUp = false;
		controlledismovingright = false;
		controlledismovingleft = true;
		controlledismovingup = false;
		controlledismovingdown = false;
	}
	
	public void controlledmoveUp() {
		isAimingUp = true;
		controlledismovingright = false;
		controlledismovingleft = false;
		controlledismovingup = true;
		controlledismovingdown = false;
	}

	public void controlledmoveDown() {
		isAimingUp = false;
		controlledismovingright = false;
		controlledismovingleft = false;
		controlledismovingup = false;
		controlledismovingdown = true;
	}
	
	public void controlledmoveLeftUp() {
		isAimingUp = false;
		controlledismovingright = false;
		controlledismovingleft = true;
		controlledismovingup = true;
		controlledismovingdown = false;
	}
	
	public void controlledmoveRightUp() {
		isAimingUp = false;
		controlledismovingright = true;
		controlledismovingleft = false;
		controlledismovingup = true;
		controlledismovingdown = false;
	}
	
	public void controlledmoveLeftDown() {
		isAimingUp = false;
		controlledismovingright = false;
		controlledismovingleft = true;
		controlledismovingup = false;
		controlledismovingdown = true;
	}
	
	public void controlledmoveRightDown() {
		isAimingUp = false;
		controlledismovingright = true;
		controlledismovingleft = false;
		controlledismovingup = false;
		controlledismovingdown = true;
	}
	
	public void controlledstopMoving() {
		isAimingUp = false;
		controlledismovingright = false;
		controlledismovingleft = false;
		controlledismovingup = false;
		controlledismovingdown = false;
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
	
	public int getScrollingSpeed() {
		return this.scrollingSpeed;
	}
	
	public void setScrollingSpeed(int scrollingspeed) {
		this.scrollingSpeed = scrollingspeed;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void addSpeedY(int speedY) {
		this.speedY += speedY /2;
		this.scrollingSpeed += speedY/2;
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

