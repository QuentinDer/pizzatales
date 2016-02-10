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
	
	//protected Animation anim;
	private int speed;
	
	protected Firearm weapon;
	private boolean isAimingUp = true;
	
	public Image currentSprite;

	public Enemy(int centerX, int centerY, Firearm weapon, int health, int speed, int difficultylevel) {
		super(centerX, centerY);
		weapon.setHolderProjectiles(projectiles);
		this.weapon = weapon;
		weapon.setFireRate(weapon.getFireRate() * (5 - difficultylevel));
		this.health = health * difficultylevel;
		this.speed = speed;
		bginitx = bg.getCenterX();
		bginity = bg.getCenterY();
		posx = centerX / 50;
		posy = centerY / 50;
		setStaySprite();
		R = new Rectangle(getCenterX() - 25, getCenterY() - 25, 50, 50);
	}

	public void checkCollision(Enemy e) {
		if (R.intersects(e.R)) {
			if (e.getCenterX() - getCenterX() >= 0)
				setCenterX(getCenterX()-2);
			else
				setCenterX(getCenterX()+2);
			if (e.getCenterY() - getCenterY() >= 0)
				setCenterY(getCenterY()-2);
			else
				setCenterY(getCenterY()+2);
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

		super.update();
		
		int currentposx = (getCenterX() - bg.getCenterX() + bginitx) / 50;
		int currentposy = (getCenterY() - bg.getCenterY() + bginity) / 50;
		if (currentposx != posx || currentposy != posy) {
			pf.map[posx][posy] = true;
			pf.map[currentposx][currentposy] = false;
			posx = currentposx;
			posy = currentposy;
		}
		
		R.setBounds(getCenterX() - 25, getCenterY() - 25, 50, 50);
		
		if (alive == true) {
			
			// Prevents going beyond X coordinate of 0 or 800
			if (centerX + speedX <= 60) {
				centerX = 61;
				setSpeedX(2);
			} else if (centerX + speedX >= 800) {
				centerX = 799;
				setSpeedX(-2);
			}
			
			if (isMoving) {
				walkCounter++;
				if (walkCounter == 1000)
					walkCounter = 0;
				if (walkCounter % 30 == 0) {
					setMove1Sprite();
				} else if (walkCounter % 15 == 0) {
					setMove2Sprite();
				}
			}
				

			// Collision
			// rectX.setRect(getCenterX() - 55, getCenterY() - 55, 50, 40);
			// rectY.setRect(getCenterX() - 50, getCenterY() - 60, 40, 50);

			// AI
		}
	}

	public abstract void callAI();/* {
		if (alive == true){
			setSpeedX(2);
		}
	}*/
	
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
		setSpeedX(speed);
		setSpeedY(0);
		isMoving = true;
	}
	
	protected void moveLeft() {
		isAimingUp = false;
		setSpeedX(-speed);
		setSpeedY(0);
		isMoving = true;
	}
	
	protected void moveUp() {
		isAimingUp = true;
		setSpeedX(0);
		setSpeedY(-speed);
		isMoving = true;
	}

	protected void moveDown() {
		isAimingUp = false;
		setSpeedX(0);
		setSpeedY(speed);
		isMoving = true;
	}
	
	protected void stopMoving() {
		setSpeedX(0);
		setSpeedY(0);
		isMoving = false;
		setStaySprite();
	}
	
	public Firearm getWeapon() {
		return weapon;
	}
	
	public abstract void setStaySprite();
	public abstract void setMove1Sprite();
	public abstract void setMove2Sprite();
	public abstract void setDieSprite();
}
