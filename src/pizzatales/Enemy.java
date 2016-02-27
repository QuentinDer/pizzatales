package pizzatales;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

//import pizzatales.framework.Animation;

public abstract class Enemy extends BlockingStuff {

	protected int maxHealth, health;
	//private int power;
	protected boolean alive = true;
	protected URL base;
	protected boolean isMoving = false;
	protected int walkCounter = 1;

	protected int movementTime = ((int) Math.random() * 100) + 50;

	protected Player player = StartingClass.getPlayer();
	protected PathFinder pf = StartingClass.getPathFinder();
	
	public boolean canmoveup = true;
	public boolean canmovedown = true;
	public boolean canmoveright = true;
	public boolean canmoveleft = true;
	private boolean ismovingright;
	private boolean ismovingleft;
	private boolean ismovingup;
	private boolean ismovingdown;
	private boolean sleepy = false;
	public int halfbar = 22;
	protected int range;
	
	protected Firearm weapon;
	public boolean isAimingUp = true;
	protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public boolean showHealthBar;
	
	//protected Animation anim;
	protected int speed;
	
	protected Image currentSprite;
	private boolean isArenaEnemy;

	public Enemy(int centerX, int centerY, Firearm weapon, int health, int speed, int halfsizex, int halfsizey, int halfrsizex, int halfrsizey) {
		super(centerX, centerY, halfsizex, halfsizey, halfrsizex, halfrsizey);
		this.weapon = weapon;
		if (weapon != null) {
			weapon.setFireRate(weapon.getFireRate() * (5 - StartingClass.difficultylevel));
			weapon.setHolderProjectiles(projectiles);
		}
		this.health = health * StartingClass.difficultylevel;
		this.maxHealth = this.health;
		this.speed = speed;
		setStaySprite();
	}
	
	public void checkCollisionsWithBlockingStuff() {
		if (posx != 0) {
			if (null != StartingClass.map[posx-1][posy] && R.intersects(StartingClass.map[posx-1][posy].R)) {
				canmoveleft = false;
			}
			if (posy != 0 && null != StartingClass.map[posx-1][posy-1] && R.intersects(StartingClass.map[posx-1][posy-1].R)) {
				if (Math.abs(StartingClass.map[posx-1][posy-1].getCenterX()-centerX) > Math.abs(StartingClass.map[posx-1][posy-1].getCenterY()-centerY))
					canmoveleft = false;
				else 
					canmoveup = false;
			}
			if (posy != StartingClass.height - 1 && null != StartingClass.map[posx-1][posy+1] && R.intersects(StartingClass.map[posx-1][posy+1].R)) {
				if (Math.abs(StartingClass.map[posx-1][posy+1].getCenterX()-centerX) > Math.abs(StartingClass.map[posx-1][posy+1].getCenterY()-centerY))
					canmoveleft = false;
				else 
					canmovedown = false;
			}
		}
		if (posx != StartingClass.width - 1) {
			if (null != StartingClass.map[posx+1][posy] && R.intersects(StartingClass.map[posx+1][posy].R)) {
				canmoveright = false;
			}
			if (posy != 0 && null != StartingClass.map[posx+1][posy-1] && R.intersects(StartingClass.map[posx+1][posy-1].R)) {
				if (Math.abs(StartingClass.map[posx+1][posy-1].getCenterX()-centerX) > Math.abs(StartingClass.map[posx+1][posy-1].getCenterY()-centerY))
					canmoveright = false;
				else 
					canmoveup = false;
			}
			if (posy != StartingClass.height - 1 && null != StartingClass.map[posx+1][posy+1] && R.intersects(StartingClass.map[posx+1][posy+1].R)) {
				if (Math.abs(StartingClass.map[posx+1][posy+1].getCenterX()-centerX) > Math.abs(StartingClass.map[posx+1][posy+1].getCenterY()-centerY))
					canmoveright = false;
				else 
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
/*
	private void checkCollision(Enemy e) {
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
	}*/
	
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
		}
	}

	/*
	public void checkEnemyCollisions() {
		for (Enemy e : StartingClass.getEnemyarray()) {
			if (!e.equals(this) && e.alive)
				checkCollision(e);
		}
		checkCollisionPlayer();
	}*/
	
	// Behavioral Methods
	@Override
	public void update() {

		// Prevents going beyond X coordinate of 0 or 1280
		if (centerX + speedX <= 30) {
			canmoveleft = false;
		} else if (centerX + speedX >= 1250) {
			canmoveright = false;
		}
		
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
		
		if (alive == true) {
			
			posx = (getCenterX() - bg.getCenterX() + StartingClass.bginitx) / 50;
			posy = (getCenterY() - bg.getCenterY() + StartingClass.bginity) / 50;
			StartingClass.map[posx][posy] = this;
			animate();
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
			if (walkCounter == 1000)
				walkCounter = 0;
			if (getSpeedX() <= 0) {
				if (walkCounter % 30 == 0) {
					setMove1Sprite();
				} else if (walkCounter % 15 == 0) {
					setMove2Sprite();
				}
			} else {
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
		StartingClass.map[posx][posy] = null;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

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
	

	public Firearm getWeapon() {
		return weapon;
	}
	
	public void setWeapon(Firearm weapon_) {
		weapon = weapon_;
		weapon.setHolderProjectiles(projectiles);
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
	
	public boolean isAimingUp() {
		return isAimingUp;
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
	
	public Image getSprite() {
		return currentSprite;
	}
	
	public abstract void setStaySprite();
	public abstract void setMove1Sprite();
	public abstract void setMove2Sprite();
	public abstract void setDieSprite();
	public abstract void setStaySpriteAlt();
	public abstract void setMove1SpriteAlt();
	public abstract void setMove2SpriteAlt();
}
