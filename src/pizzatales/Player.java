package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class Player extends BlockingStuff {

	private int MOVESPEED = 4;
	//private final static double MAXSCROLLINGPROPORTION = 0.5;
	//private final static int SCROLLINGRANGE = 150;
	private int scrollingSpeedY = 0;
	private int scrollingSpeedX = 0;
	private int health = 20;
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
	private boolean isMoving;
	
	protected Firearm weapon;
	public boolean isAimingUp = true;
	protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public boolean showHealthBar;
	
	public Image currentSprite;

	// 0 = not, 1 = left, 2 = top, 3 = right, 4 = bottom
	private int isShooting = 0;
	private int walkCounter = 0;
	private int stayCounter = 0;
	
	public Player() {
		super(640,100, 31,31,23,23);
	}

	private Background bg;
	
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

	public void checkCollisionsWithBlockingStuff() {
		/*if (diffX > diffY && diffY < 45) {
			if (player.getCenterX() <= this.getCenterX()) {
				player.canmoveright = false;
				;
			} else {
				player.canmoveleft = false;
			}
		} else if (diffX < diffY && diffX < 45) {
			if (player.getCenterY() <= this.getCenterY()) {
				player.canmovedown = false;
			} else {
				player.canmoveup = false;
			}
		}*/
		if (posx != 0) {
			if (null != StartingClass.map[posx-1][posy] && R.intersects(StartingClass.map[posx-1][posy].R)) {
				canmoveleft = false;
			}
			if (posy != 0 && null != StartingClass.map[posx-1][posy-1] && R.intersects(StartingClass.map[posx-1][posy-1].R)) {
				int diffX = Math.abs(StartingClass.map[posx-1][posy-1].getCenterX()-centerX);
				int diffY = Math.abs(StartingClass.map[posx-1][posy-1].getCenterY()-centerY);
				if (diffX > diffY && diffY < 42)
					canmoveleft = false;
				else if (diffX < diffY && diffX < 42)
					canmoveup = false;
			}
			if (posy != StartingClass.height - 1 && null != StartingClass.map[posx-1][posy+1] && R.intersects(StartingClass.map[posx-1][posy+1].R)) {
				int diffX = Math.abs(StartingClass.map[posx-1][posy+1].getCenterX()-centerX);
				int diffY = Math.abs(StartingClass.map[posx-1][posy+1].getCenterY()-centerY);
				if (diffX > diffY && diffY < 42)
					canmoveleft = false;
				else if (diffX < diffY && diffX < 42)
					canmovedown = false;
			}
		}
		if (posx != StartingClass.width - 1) {
			if (null != StartingClass.map[posx+1][posy] && R.intersects(StartingClass.map[posx+1][posy].R)) {
				canmoveright = false;
			}
			if (posy != 0 && null != StartingClass.map[posx+1][posy-1] && R.intersects(StartingClass.map[posx+1][posy-1].R)) {
				int diffX = Math.abs(StartingClass.map[posx+1][posy-1].getCenterX()-centerX);
				int diffY = Math.abs(StartingClass.map[posx+1][posy-1].getCenterY()-centerY);
				if (diffX > diffY && diffY < 42)
					canmoveright = false;
				else if (diffX < diffY && diffX < 42)
					canmoveup = false;
			}
			if (posy != StartingClass.height - 1 && null != StartingClass.map[posx+1][posy+1] && R.intersects(StartingClass.map[posx+1][posy+1].R)) {
				int diffX = Math.abs(StartingClass.map[posx+1][posy+1].getCenterX()-centerX);
				int diffY = Math.abs(StartingClass.map[posx+1][posy+1].getCenterY()-centerY);
				if (diffX > diffY && diffY < 42)
					canmoveright = false;
				else if (diffX < diffY && diffX < 42)
					canmovedown = false;
			}
		}
		if (posy != 0 && null != StartingClass.map[posx][posy-1] && R.intersects(StartingClass.map[posx][posy-1].R)) {
			canmoveup = false;
		}
		if (posy != StartingClass.height - 1 && null != StartingClass.map[posx][posy+1] && R.intersects(StartingClass.map[posx][posy+1].R)) {
			canmovedown = false;
		}
	}

	protected void shootUp() {
		isAimingUp = true;
		weapon.shootUp(centerX, centerY);
	}

	protected void shootDown() {
		isAimingUp = false;
		weapon.shootDown(centerX, centerY);
	}

	protected void shootLeft() {
		isAimingUp = false;
		weapon.shootLeft(centerX, centerY);
	}

	protected void shootRight() {
		isAimingUp = false;
		weapon.shootRight(centerX, centerY);
	}
	
	@Override
	public void update() {

		// Moves Character or Scrolls Background accordingly.

		// Updates X Position
		
		speedY = 0;
		speedX = 0;
		
		// Prevents going beyond X coordinate of 0 or 1250
		if (centerX - MOVESPEED <= 30) {
			canmoveleft = false;
		}
		if (centerX + MOVESPEED >= 1249) {
			canmoveright = false;
		}
		// Prevents going beyond X coordinate of 0 or 800
		if (centerY - MOVESPEED <= 30) {
			canmoveup = false;
		}
		if (centerY + MOVESPEED >= 769) {
			canmovedown = false;
		}
		
		if (ismovingup && canmoveup) {
			speedY += -MOVESPEED;
		}
		if (ismovingdown && canmovedown) {
			speedY += MOVESPEED;
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
		// Prevents going beyond Y coordinate of 250 and 550
		if (StartingClass.levelwithyscrolling) {
			switch (StartingClass.ScrollingMode) {
			case 0: // no scrolling
				scrollingSpeedY = 0;
				break;
			case 1: // dynamic
				if (centerY + speedY <= 249 && speedY < 0) {
					scrollingSpeedY = speedY;
				} else if (centerY + speedY >= 550 && speedY > 0) {
					scrollingSpeedY = speedY;
				} else {
					scrollingSpeedY = speedY - speedY/2;
				}
				break;
			case 2: // player centered
				scrollingSpeedY = speedY;
				break;
			case 3: // player controlled.
				break;
			}
			if (scrollingSpeedY>0 && 
					(bg.getCenterY()-scrollingSpeedY - StartingClass.bginity < - (StartingClass.height-16) * 50) 
					|| (scrollingSpeedY<0 && (bg.getCenterY()-scrollingSpeedY - StartingClass.bginity> 0)))
				scrollingSpeedY = 0;
			speedY -= scrollingSpeedY;
		}
		if (StartingClass.levelwithxscrolling) {
			switch (StartingClass.ScrollingMode) {
			case 0: // no scrolling
				scrollingSpeedX = 0;
				break;
			case 1: // dynamic
				if (centerX + speedX <= 350 && speedX < 0) {
					scrollingSpeedX = speedX;
				} else if (centerX + speedX >= 930 && speedX > 0) {
					scrollingSpeedX = speedX;
				} else {
					scrollingSpeedX = speedX - speedX/2;
				}
				break;
			case 2: // player centered
				scrollingSpeedX = speedX;
				break;
			case 3: // player controlled.
				break;
			}
			if ((scrollingSpeedX > 0 && 
					bg.getCenterX()-scrollingSpeedX - StartingClass.bginitx < - (StartingClass.width-26) * 50 + 30) 
					|| (scrollingSpeedX < 0 && bg.getCenterX()-scrollingSpeedX - StartingClass.bginitx>0))
				scrollingSpeedX = 0;
			speedX -= scrollingSpeedX;
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
		StartingClass.map[posx][posy] = this;
		isMoving = ismovingdown || ismovingright || ismovingup || ismovingleft;
		animate();
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
		speedY -= scrollingSpeedY;
		speedX -= scrollingSpeedX;
		centerY += speedY;
		centerX += speedX;
		R.setRect(centerX +speedX- 25, centerY +speedY- 25, 50, 50);
		weapon.increaseShootingCounter();
		posx = (centerX - bg.getCenterX() + StartingClass.bginitx) / 50;
		posy = (centerY - bg.getCenterY() + StartingClass.bginity) / 50;
		StartingClass.map[posx][posy] = this;
		isMoving = controlledismovingdown || controlledismovingright || controlledismovingup || controlledismovingleft;
		animate();
	}
	
	
	public void animate(){
		if (isMoving) {
			stayCounter = 0;
			walkCounter++;
			if (walkCounter == 1000)
				walkCounter = 0;
			if (walkCounter % 30 == 0) {
				currentSprite = armor.getMoveSprite1();
			} else if (walkCounter % 15 == 0) {
				currentSprite = armor.getMoveSprite2();
			}
		} else {
			stayCounter++;
			walkCounter = 0;
			if (stayCounter == 1000)
				stayCounter = 0;
			if (stayCounter % 300 > 270)
				currentSprite = armor.getStaySprite2();
			else
				currentSprite = armor.getStaySprite();
		}
	}
	
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
	
	public int getMOVESPEED() {
		return MOVESPEED;
	}

	public void setMOVESPEED(int mOVESPEED) {
		MOVESPEED = mOVESPEED;
	}
	
	public int getScrollingSpeedX() {
		return this.scrollingSpeedX;
	}
	
	public int getScrollingSpeedY() {
		return this.scrollingSpeedY;
	}
	
	public void setScrollingSpeedY(int scrollingspeed) {
		this.scrollingSpeedY = scrollingspeed;
	}
	
	public void setScrollingSpeedX(int scrollingspeedX) {
		this.scrollingSpeedX = scrollingspeedX;
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
		setMOVESPEED(armor.getSpeed());
	}

	@Override
	public Image getSprite() {
		return currentSprite;
	}
}

