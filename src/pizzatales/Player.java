package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class Player extends BlockingStuff {

	private float MOVESPEED = 4;
	//private final static double MAXSCROLLINGPROPORTION = 0.5;
	//private final static int SCROLLINGRANGE = 150;
	private float scrollingSpeedY = 0;
	private float scrollingSpeedX = 0;
	private float health = 20;
	private int maxhealth = 20;
	private Armor armor;
	private float defBoost = 1;
	private boolean ismovingup;
	private boolean ismovingdown;
	private boolean ismovingleft;
	private boolean ismovingright;
	private boolean controlledismovingup;
	private boolean controlledismovingdown;
	private boolean controlledismovingleft;
	private boolean controlledismovingright;
	public boolean sliding;
	private boolean previoussliding;
	private float slidingSpeedY;
	private float slidingSpeedX;
	public boolean canmoveright = true;
	public boolean canmoveleft = true;
	public boolean canmoveup = true;
	public boolean canmovedown = true;
	public boolean wannashootleft, wannashootright, wannashootup, wannashootdown;
	private boolean isMoving;
	
	protected Firearm weapon;
	public boolean isAimingUp = true;
	protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public boolean showHealthBar;
	private Hat hat;
	public int isGrinning;
	
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
	
	public void initState() {
		canmovedown = true;
		canmoveleft = true;
		canmoveright = true;
		canmoveup = true;
		sliding = false;
		MOVESPEED = armor.speed;
		maxhealth = 20;
		armor.MAXDEF = armor.DEFMAXDEF;
		health = Math.min(health, maxhealth);
		armor.defense = Math.min(armor.defense, armor.MAXDEF);
		defBoost = 1.0f;
		weapon.initState();
		Enemy.bloodymess = false;
		if (hat != null)
			hat.effect();
	}
	
	private final void chekCollisionsWithItems(int x, int y) {
		if (StartingClass.map[x][y] == null || !Tile.class.isInstance(StartingClass.map[x][y])) {
			int h = 0;
			while (h <= StartingClass.heightitemmap[x][y]) {
				if (BackgroundItem.class.isInstance(StartingClass.items[x][y][h])) {
					if ((h == StartingClass.heightitemmap[x][y] || !BackgroundItem.class.isInstance(StartingClass.items[x][y][h+1]))&& StartingClass.items[x][y][h].checkCollisionPlayer(this)) {
						StartingClass.leavingitems.add(StartingClass.items[x][y][h]);
						StartingClass.removeItem(StartingClass.items[x][y][h]);
					} else
						h++;
				} else if (StartingClass.items[x][y][h].checkCollisionPlayer(this)) {
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
		
		if (isGrinning > 0)
			isGrinning--;
		
		if (sliding && !previoussliding) {
			speedX = slidingSpeedX;
			speedY = slidingSpeedY;
		}
		if (!sliding) {
			speedY = 0;
			speedX = 0;
		}
		
		
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
		if (sliding) {
			speedX = slidingSpeedX;
			speedY = slidingSpeedY;
		}
		if (ismovingup) {
			if (!sliding)
				speedY += -MOVESPEED;
			else if (speedY < 0) {
				if (walkCounter % Math.max(1, ((int)(-speedY*(armor.MAXDEF*5f/16f+4.75f)-armor.MAXDEF*5f/4f+25f))) == 0)
					speedY -= 1;
			} else if (walkCounter % ((int)(speedY*(armor.MAXDEF/16f-5.25f)-armor.MAXDEF*15f/16f+68.25f)) == 0) {
				speedY -= 1;
			}
		}
		if (ismovingdown) {
			if (!sliding)
				speedY += MOVESPEED;
			else if (speedY > 0) {
				if (walkCounter % Math.max(1, ((int)(speedY*(armor.MAXDEF*5f/16f+4.75f)-armor.MAXDEF*5f/4f+25f))) == 0)
					speedY += 1;
			} else if (walkCounter % ((int)(-speedY*(armor.MAXDEF/16f-5.25f)-armor.MAXDEF*15f/16f+68.25f)) == 0) {
				speedY += 1;
			}
		}
		if (ismovingleft) {
			if (!sliding)
				speedX += -MOVESPEED;
			else if (speedX < 0) {
				if (walkCounter % Math.max(1, ((int)(-speedX*(armor.MAXDEF*5f/16f+4.75f)-armor.MAXDEF*5f/4f+25f))) == 0)
					speedX -= 1;
			} else if (walkCounter % ((int)(speedX*(armor.MAXDEF/16f-5.25f)-armor.MAXDEF*15f/16f+68.25f)) == 0) {
				speedX -= 1;
			}
		}
		if (ismovingright) {
			if (!sliding)
				speedX += MOVESPEED;
			else if (speedX > 0) {
				if (walkCounter % Math.max(1, ((int)(speedX*(armor.MAXDEF*5f/16f+4.75f)-armor.MAXDEF*5f/4f+25f))) == 0)
					speedX += 1;
			} else if (walkCounter % ((int)(-speedX*(armor.MAXDEF/16f-5.25f)-armor.MAXDEF*15f/16f+68.25f)) == 0) {
				speedX += 1;
			}
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
		previoussliding = sliding;
		isShooting = 0;
		if (wannashootup)
			isShooting = 2;
		if (wannashootdown)
			isShooting = 4;
		if (wannashootleft) {
			isShooting = 1;
			if (wannashootup)
				isShooting = 5;
			if (wannashootdown)
				isShooting = 8;
		}
		if (wannashootright) {
			isShooting = 3;
			if (wannashootup)
				isShooting = 6;
			if (wannashootdown)
				isShooting = 7;
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
				case 5:
					weapon.shootUpLeft(centerX, centerY);
					isAimingUp = false;
					break;
				case 6:
					weapon.shootUpRight(centerX, centerY);
					isAimingUp = false;
					break;
				case 7:
					weapon.shootDownRight(centerX, centerY);
					isAimingUp = false;
					break;
				case 8:
					weapon.shootDownLeft(centerX, centerY);
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
		if (isGrinning > 0)
			isGrinning--;
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
				if(ismovingright)
					currentSprite = armor.getMoveSpriteRight1();
				else
					currentSprite = armor.getMoveSpriteLeft1();
			} else if (walkCounter % 15 == 0) {
				if(ismovingright)
					currentSprite = armor.getMoveSpriteRight2();
				else
					currentSprite = armor.getMoveSpriteLeft2();
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
		if(health <= 0){
			currentSprite = armor.getDeathSprite();
		}
	}
	
	public boolean isAimingUp() {
		return isAimingUp;
	}

	public int isShooting() {
		return isShooting;
	}
/*
	public void setShooting(int isShooting) {
		this.isShooting = isShooting;
	}*/

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
	
	public float getMOVESPEED() {
		return MOVESPEED;
	}

	public void setMOVESPEED(float mOVESPEED) {
		MOVESPEED = mOVESPEED;
	}
	
	public float getScrollingSpeedX() {
		return this.scrollingSpeedX;
	}
	
	public float getScrollingSpeedY() {
		return this.scrollingSpeedY;
	}
	
	public void setScrollingSpeedY(float scrollingspeed) {
		this.scrollingSpeedY = scrollingspeed;
	}
	
	public void setScrollingSpeedX(float scrollingspeedX) {
		this.scrollingSpeedX = scrollingspeedX;
	}
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
	
	public void setMaxHealth(int maxhealth) {
		this.maxhealth = maxhealth;
	}
	
	public int getMaxHealth() {
		return maxhealth;
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
	
	public void setHat(Hat hat) {
		this.hat = hat;
	}
	
	public Hat getHat() {
		return hat;
	}

	@Override
	public Image getSprite() {
		return currentSprite;
	}
	
	public void damage(float dmg) {
		if (armor.getDefense() - dmg*2.0f*defBoost/3.0f < 0) {
			health -= dmg*defBoost - armor.getDefense();
			armor.setDefense(0.f);
		} else {
			armor.setDefense(armor.getDefense() - dmg*2.0f*defBoost/3.0f);
			health -= dmg*defBoost/3.0f;
		}
	}

	public float getDefBoost() {
		return defBoost;
	}

	public void setDefBoost(float defBoost) {
		this.defBoost = defBoost;
	}
}