package pizzatales;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

//import pizzatales.framework.Animation;

public abstract class Enemy extends BlockingStuff {

	protected int maxHealth;
	protected float health;
	//private int power;
	protected boolean alive = true;
	static public boolean bloodymess = false;
	protected URL base;
	protected boolean isMoving = false;
	protected int walkCounter = 1;
	private int waiting = 0;
	private int nextdestx;
	private int nextdesty;
	private int waitpatindex = 0;

	protected int movementTime = ((int) Math.random() * 100) + 50;

	protected Player player = StartingClass.getPlayer();
	protected PathFinder pf = StartingClass.getPathFinder();
	
	public boolean paintoverride;
	public boolean canmoveup = true;
	public boolean canmovedown = true;
	public boolean canmoveright = true;
	public boolean canmoveleft = true;
	protected boolean ismovingright;
	protected boolean ismovingleft;
	protected boolean ismovingup;
	protected boolean ismovingdown;
	private boolean sleepy = false;
	protected boolean hasSeenU = false;
	public int halfbarx = 22;
	public int halfbary = 25;
	protected int range;
	private float ppdmg;
	private float pdmg;
	protected float cdmg;
	private int[][] waitingpattern;
	public boolean sliding;
	private boolean previoussliding;
	private float slidingSpeedY;
	private float slidingSpeedX;
	
	private final static int visionRange = 15;
	private final static float slightincrease = (float)0.5;
	
	protected Firearm weapon;
	public boolean isAimingUp = true;
	protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public boolean showHealthBar;
	
	//protected Animation anim;
	private float defaultspeed;
	protected float speed;
	
	protected Image currentSprite;
	private boolean isArenaEnemy;

	public Enemy(int centerX, int centerY, Firearm weapon, int health, float speed, int halfsizex, int halfsizey, int halfrsizex, int halfrsizey) {
		super(centerX, centerY, halfsizex, halfsizey, halfrsizex, halfrsizey);
		halfbary = halfrsizey;
		this.weapon = weapon;
		if (weapon != null) {
			weapon.setFireRate(weapon.getFireRate() * (5 - StartingClass.difficultylevel));
			weapon.setHolderProjectiles(projectiles);
		}
		this.health = health * StartingClass.difficultylevel;
		this.maxHealth = (int)this.health;
		this.defaultspeed = speed;
		this.speed = speed;
		setStaySprite();
	}
	
	public void damage(float projdmg) {
		cdmg += projdmg;
		health -= projdmg;
		if (health < 0)
			die();
	}
	
	private final void chekCollisionsWithItems(int x, int y) {
		if (StartingClass.map[x][y] == null || !Tile.class.isInstance(StartingClass.map[x][y])) {
			int h = 0;
			while (h <= StartingClass.heightitemmap[x][y]) {
				if (BackgroundItem.class.isInstance(StartingClass.items[x][y][h])) {
					if ((h == StartingClass.heightitemmap[x][y] || !BackgroundItem.class.isInstance(StartingClass.items[x][y][h+1]))&& StartingClass.items[x][y][h].checkCollisionEnemy(this)) {
						StartingClass.leavingitems.add(StartingClass.items[x][y][h]);
						StartingClass.removeItem(StartingClass.items[x][y][h]);
					} else
						h++;
				} else if (StartingClass.items[x][y][h].checkCollisionEnemy(this)) {
					StartingClass.leavingitems.add(StartingClass.items[x][y][h]);
					StartingClass.removeItem(StartingClass.items[x][y][h]);
				} else
					h++;
			}
		}
	}
	
	public void checkCollisionsWithItems() {
		chekCollisionsWithItems(posx,posy);
		if (posx != 0) {
			chekCollisionsWithItems(posx-1,posy);
			if (posy != 0)
				chekCollisionsWithItems(posx-1,posy-1);
			if (posy != StartingClass.height - 1)
				chekCollisionsWithItems(posx-1,posy+1);
		}
		if (posx != StartingClass.width - 1) {
			chekCollisionsWithItems(posx+1,posy);
			if (posy != 0)
				chekCollisionsWithItems(posx+1,posy-1);
			if (posy != StartingClass.height - 1)
				chekCollisionsWithItems(posx+1,posy+1);
		}
		if (posy != 0)
			chekCollisionsWithItems(posx,posy-1);
		if (posy != StartingClass.height - 1)
			chekCollisionsWithItems(posx,posy+1);
	}
	
	public void checkCollisionsWithBlockingStuff() {
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
	
	// Behavioral Methods
	@Override
	public void update() {
		
		if (sliding && !previoussliding) {
			speedX = slidingSpeedX;
			speedY = slidingSpeedY;
		}
		if (!sliding) {
			speedY = 0;
			speedX = 0;
		}
		if (ismovingup) {
			if (!sliding)
				speedY += -speed;
			else if (walkCounter % 30 == 0 && speed != 0)
				speedY -= 1;
		}
		if (ismovingdown) {
			if (!sliding)
				speedY += speed;
			else if (walkCounter % 30 == 0 && speed != 0)
				speedY += 1;
		}
		if (ismovingleft) {
			if (!sliding)
				speedX += -speed;
			else if (walkCounter % 30 == 0 && speed != 0)
				speedX -= 1;
		}
		if (ismovingright) {
			if (!sliding)
				speedX += speed;
			else if (walkCounter % 30 == 0 && speed != 0)
				speedX += 1;
		}
		if (speedY > 0 && !canmovedown)
			speedY = 0;
		if (speedY < 0 && !canmoveup)
			speedY = 0;
		if (speedX > 0 && !canmoveright)
			speedX = 0;
		if (speedX < 0 && !canmoveleft)
			speedX = 0;
		slidingSpeedX = speedX;
		slidingSpeedY = speedY;
		
		super.update();
		
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
		
		previoussliding = sliding;
		
		ppdmg = pdmg;
		pdmg = cdmg;
		cdmg = 0;
	}

	public void launchAI() {
		if (alive) {
			if (!sleepy) {
				if (hasSeenU)
					callAI();
				else
					followWaitingPattern();
			}
				
			movementTime++;
			if (movementTime == 1000) {
				movementTime = 0;
			}
		}
	}
	
	private void followWaitingPattern() {
		if (waitingpattern != null) {
			if (waiting > 0)
				waiting--;
			if (waiting == 0) {
				/*if (centerY < 800) {
					System.out.println("pos: "+posx+"-"+posy+" "+nextdestx+"-"+nextdesty+" "+waitingpattern.length+" "+waitpatindex);
				}*/
				if (posx == nextdestx && posy == nextdesty) {
					stopMoving();
					waiting = waitingpattern[waitpatindex][2];
					waitpatindex++;
					if (waitpatindex == waitingpattern.length)
						waitpatindex = 0;
					nextdestx = waitingpattern[waitpatindex][0];
					nextdesty = waitingpattern[waitpatindex][1];
				} else {
					if (movementTime % 05 == 0) {
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
						switch(pf.getDirection(posx, posy, nextdestx, nextdesty, 10, canmoveleft, canmoveup, canmoveright, canmovedown, dirplace, false)) {
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
				}
			}
		}
	}
	
	public void initWaitingPattern() {
		if (waitingpattern == null) {
			//Set default waiting pattern
			ArrayList<ArrayList<Integer>> tmpwp = new ArrayList<ArrayList<Integer>>();
			if (posx != 0 && StartingClass.map[posx-1][posy] == null) {
				ArrayList<Integer> right = new ArrayList<Integer>();
				right.add(posx-1);
				right.add(posy);
				right.add((int)(Math.random()*120)+60);
				tmpwp.add(right);
			}
			if (posx != StartingClass.width - 1 && StartingClass.map[posx+1][posy] == null) {
				ArrayList<Integer> left = new ArrayList<Integer>();
				left.add(posx+1);
				left.add(posy);
				left.add((int)(Math.random()*120)+60);
				tmpwp.add(left);
			}
			if (posy != 0 && StartingClass.map[posx][posy-1] == null) {
				ArrayList<Integer> up = new ArrayList<Integer>();
				up.add(posx);
				up.add(posy-1);
				up.add((int)(Math.random()*120)+60);
				tmpwp.add(up);
			}
			if (posy != StartingClass.height-1 && StartingClass.map[posx][posy+1] == null) {
				ArrayList<Integer> down = new ArrayList<Integer>();
				down.add(posx);
				down.add(posy+1);
				down.add((int)(Math.random()*120)+60);
				tmpwp.add(down);
			}
			int sz = tmpwp.size();
			if (sz != 0) {
				waitingpattern = new int[sz*2][3];
				int i = 0;
				while (!tmpwp.isEmpty()) {
					int index = (int)(Math.random()*tmpwp.size());
					waitingpattern[i][0] = tmpwp.get(index).get(0);
					waitingpattern[i][1] = tmpwp.get(index).get(1);
					waitingpattern[i][2] = tmpwp.get(index).get(2);
					waitingpattern[i+1][0] = posx;
					waitingpattern[i+1][1] = posy;
					waitingpattern[i+1][2] = (int)(Math.random()*120)+60;
					tmpwp.remove(index);
					i += 2;
				}
				nextdestx = waitingpattern[0][0];
				nextdesty = waitingpattern[0][1];
			}
		}
	}
	
	public void setWaitingPattern(int[][] waitingpattern) {
		this.waitingpattern = waitingpattern;
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
		if (ppdmg + pdmg + cdmg > 3) {
			setGibsSprite();
			player.isGrinning = Math.max(player.isGrinning, 50);
		} else
			setDieSprite();
		if (bloodymess) {
			setGibsSprite();
			BazookaBulletExplosion bmess = new BazookaBulletExplosion(centerX+30, centerY+30);
			bmess.procfrequency = 20;
			StartingClass.explosions.add(bmess);
		}
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
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
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
	public abstract void setGibsSprite();
	public abstract void setStaySpriteAlt();
	public abstract void setMove1SpriteAlt();
	public abstract void setMove2SpriteAlt();
	
	protected boolean canSeePlayer() {
		if (Math.abs(posx-player.posx)+Math.abs(posy-player.posy) > visionRange)
			return false;
		boolean ans = true;
		if (Math.abs(posx-player.posx)>Math.abs(posy-player.posy)) {
			if (player.posx>posx) {
				float deltay = (player.posy-posy)/((float)(player.posx-posx));
				float inc = (deltay>0)?slightincrease:-slightincrease;
				for (int i = 1; i < player.posx-posx; i++) {
					ans = ans && (StartingClass.map[posx+i][posy+(int)(deltay*i+inc)] == null);
				}
			} else {
				float deltay = (player.posy-posy)/((float)(posx-player.posx));
				float inc = (deltay>0)?slightincrease:-slightincrease;
				for (int i = 1; i < posx-player.posx; i++) {
					ans = ans && (StartingClass.map[posx-i][posy+(int)(deltay*i+inc)] == null);
				}
			}
		} else {
			if (player.posy>posy) {
				float deltax = (player.posx-posx)/((float)(player.posy-posy));
				float inc = (deltax>0)?slightincrease:-slightincrease;
				for (int i = 1; i < player.posy-posy; i++) {
					ans = ans && (StartingClass.map[posx+(int)(deltax*i+inc)][posy+i] == null);
				}
			} else {
				float deltax = (player.posx-posx)/((float)(posy-player.posy));
				float inc = (deltax>0)?slightincrease:-slightincrease;
				for (int i = 1; i < posy-player.posy; i++) {
					ans = ans && (StartingClass.map[posx+(int)(deltax*i+inc)][posy-i] == null);
				}
			}
		}
		return ans;
	}
	
	public void initSpeed() {
		speed = defaultspeed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setDefaultSpeed(float defaults) {
		defaultspeed = defaults;
		speed = defaultspeed;
	}
	
	public float getDefaultSpeed() {
		return defaultspeed;
	}
}