package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ArrayList;

//import pizzatales.framework.Animation;

public abstract class Enemy extends Stuff {

	private int maxHealth, currentHealth, power;
	protected int health;
	protected boolean alive = true;
	protected URL base;
	protected boolean isMoving = false;
	protected int walkCounter = 1;

	public Rectangle R;
	protected int movementTime = ((int) Math.random() * 100) + 50;

	protected Player player = StartingClass.getPlayer();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	protected PathFinder pf = StartingClass.getPathFinder();
	protected int posx;
	protected int posy;
	protected Background bg = StartingClass.getBg1();
	protected int bginitx, bginity;
	
	public boolean canmoveup = true;
	public boolean canmovedown = true;
	public boolean canmoveright = true;
	public boolean canmoveleft = true;
	private boolean ismovingright;
	private boolean ismovingleft;
	private boolean ismovingup;
	private boolean ismovingdown;
	private boolean sleepy = false;
	public int halfsizex;
	public int halfsizey;
	
	//protected Animation anim;
	private int speed;
	
	protected Firearm weapon;
	private boolean isAimingUp = true;
	
	public Image currentSprite;
	private boolean isArenaEnemy;

	public Enemy(int centerX, int centerY, Firearm weapon, int health, int speed, int halfsizex, int halfsizey) {
		super(centerX, centerY);
		this.weapon = weapon;
		if (weapon != null) {
			weapon.setFireRate(weapon.getFireRate() * (5 - StartingClass.difficultylevel));
			weapon.setHolderProjectiles(projectiles);
		}
		this.health = health * StartingClass.difficultylevel;
		this.speed = speed;
		bginitx = bg.getCenterX();
		bginity = bg.getCenterY() - 15;
		posx = (centerX-15) / 50;
		posy = (centerY-15) / 50;
		setStaySprite();
		R = new Rectangle(getCenterX() - 22, getCenterY() - 22, 45, 45);
		this.halfsizex = halfsizex;
		this.halfsizey = halfsizey;
	}

	public void checkCollision(Enemy e) {
		if (R.intersects(e.R)) {
			if (Math.abs(e.getCenterX() - getCenterX()) > Math.abs(e.getCenterY() - getCenterY())) {
				if (e.getCenterX() - getCenterX() > 0)
					canmoveright = false;
				else
					canmoveleft = false;
			} else {
				if (e.getCenterY() - getCenterY() > 0)
					canmovedown = false;
				else
					canmoveup = false;
			}
		}
	}

	
	public void checkEnemyCollisions() {
		for (Enemy e : StartingClass.getEnemyarray()) {
			if (!e.equals(this) && e.alive)
				checkCollision(e);
		}
	}
	
	// Behavioral Methods
	@Override
	public void update() {

		setSpeedX(0);
		setSpeedY(0);
		if (ismovingright && canmoveright) {
			setSpeedX(speed);
		}
		if (ismovingleft && canmoveleft) {
			setSpeedX(-speed);
		}
		if (ismovingup && canmoveup) {
			setSpeedY(-speed);
		}
		if (ismovingdown && canmovedown) {
			setSpeedY(speed);
		}
		
		super.update();
		
		R.setBounds(getCenterX() - 22, getCenterY() - 22, 45, 45);
		
		if (alive == true) {
			
			int currentposx = (getCenterX() - bg.getCenterX() + bginitx) / 50;
			int currentposy = (getCenterY() - bg.getCenterY() + bginity) / 50;
			
			if (currentposx != posx || currentposy != posy) {
				pf.map[posx][posy] = true;
				pf.map[currentposx][currentposy] = false;
				posx = currentposx;
				posy = currentposy;
			}
			
			// Prevents going beyond X coordinate of 0 or 1280
			if (centerX + speedX <= 30) {
				centerX = 61;
			} else if (centerX + speedX >= 1250) {
				centerX = 1249;
			}
			
			animate();
				

			// Collision
			// rectX.setRect(getCenterX() - 55, getCenterY() - 55, 50, 40);
			// rectY.setRect(getCenterX() - 50, getCenterY() - 60, 40, 50);

			// AI
		}
	}

	public void launchAI() {
		if (!sleepy)
			callAI();
	}
	
	public abstract void callAI();/* {
		if (alive == true){
			setSpeedX(2);
		}
	}*/
	
	public void animate(){
		if (isMoving) {
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
	
	public void die() {
		alive = false;
		stopMoving();
		setDieSprite();
		pf.map[posx][posy] = true;
	}

	public void attack() {
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	/*
	 * public int getMovementParam() { return movementParam; }
	 * 
	 * public void setMovementParam(int movementParam) { this.movementParam =
	 * movementParam; }
	 */

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean isAimingUp() {
		return isAimingUp;
	}
	
	public void shootUp() {
		isAimingUp = true;
		weapon.shootUp(centerX, centerY);
	}

	public void shootDown() {
		isAimingUp = false;
		weapon.shootDown(centerX, centerY);
	}

	public void shootLeft() {
		isAimingUp = false;
		weapon.shootLeft(centerX, centerY);
	}

	public void shootRight() {
		isAimingUp = false;
		weapon.shootRight(centerX, centerY);
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	protected void moveRight() {
		isAimingUp = false;
		ismovingright = true;
		ismovingleft = false;
		ismovingup = false;
		ismovingdown = false;
		isMoving = true;
	}
	
	protected void moveLeft() {
		isAimingUp = false;
		ismovingright = false;
		ismovingleft = true;
		ismovingup = false;
		ismovingdown = false;
		isMoving = true;
	}
	
	protected void moveUp() {
		isAimingUp = true;
		ismovingright = false;
		ismovingleft = false;
		ismovingup = true;
		ismovingdown = false;
		isMoving = true;
	}

	protected void moveDown() {
		isAimingUp = false;
		ismovingright = false;
		ismovingleft = false;
		ismovingup = false;
		ismovingdown = true;
		isMoving = true;
	}
	
	protected void moveLeftUp() {
		isAimingUp = false;
		ismovingright = false;
		ismovingleft = true;
		ismovingup = true;
		ismovingdown = false;
		isMoving = true;
	}
	
	protected void moveRightUp() {
		isAimingUp = false;
		ismovingright = true;
		ismovingleft = false;
		ismovingup = true;
		ismovingdown = false;
		isMoving = true;
	}
	
	protected void moveLeftDown() {
		isAimingUp = false;
		ismovingright = false;
		ismovingleft = true;
		ismovingup = false;
		ismovingdown = true;
		isMoving = true;
	}
	
	protected void moveRightDown() {
		isAimingUp = false;
		ismovingright = true;
		ismovingleft = false;
		ismovingup = false;
		ismovingdown = true;
		isMoving = true;
	}
	
	protected void stopMoving() {
		if (ismovingright)
			setStaySpriteAlt();
		else
			setStaySprite();
		ismovingright = false;
		ismovingleft = false;
		ismovingup = false;
		ismovingdown = false;
		isMoving = false;
		
	}
	
	public Firearm getWeapon() {
		return weapon;
	}
	
	public void sleep() {
		sleepy = true;
		if (alive)
			stopMoving();
	}
	
	public void wakeup() {
		sleepy = false;
	}
	
	public void setIsInArena(boolean isInArena) {
		this.isArenaEnemy = isInArena;
	}
	
	public boolean isInArena() {
		return isArenaEnemy;
	}
	
	public abstract void setStaySprite();
	public abstract void setMove1Sprite();
	public abstract void setMove2Sprite();
	public abstract void setDieSprite();
	public abstract void setStaySpriteAlt();
	public abstract void setMove1SpriteAlt();
	public abstract void setMove2SpriteAlt();
}
