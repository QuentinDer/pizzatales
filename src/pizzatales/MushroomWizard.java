package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MushroomWizard extends Enemy {

	private int inAnimation;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, 
		move1SpriteRight, move2SpriteRight, swipeDown, swipeRight, swipeLeft, swipeUp, shooting, summoning;
	private int maxInAnimation;
	private int ballInAnimation;
	private int maxBallInAnimation;
	private int ballcd;
	private int bcd;
	private int slashdmg;
	private int randg, randy, randb,randr;
	private int phase = 1;
	private boolean isSlashing;
	private int slashDirection;
	private boolean hasSlashed;
	private JFrame applet;
	private int nextball;
	
	public MushroomWizard(int centerX, int centerY, JFrame applet) {
		super(centerX, centerY, new FakeMushroomWeapon(25,-12), 75, (StartingClass.difficultylevel>2)?1:2, 50, 50, 45, 45);
		movementTime = ((int) (Math.random() * 50));
		halfbarx = 45;
		slashdmg = 4;
		this.applet = applet;
		switch(StartingClass.difficultylevel) {
		case 1:
			maxInAnimation = 60;
			maxBallInAnimation = 45;
			ballcd = 90;
			randg = 5;
			randy = 7;
			randb = 9;
			randr = 10;
			break;
		case 2:
			maxInAnimation = 30;
			maxBallInAnimation = 30;
			ballcd = 60;
			randg = 10;
			randy = 15;
			randb = 19;
			randr = 20;
			break;
		case 3:
			maxInAnimation = 40;
			maxBallInAnimation = 15;
			//phase = 3;
			ballcd = 30;
			randg = 34;
			randy = 35;
			randb = 38;
			randr = 40;
			break;
		case 4:
			maxInAnimation = 30;
			maxBallInAnimation = 12;
			//phase = 4;
			ballcd = 22;
			randg = 34;
			randy = 35;
			randb = 38;
			randr = 40;
			break;
		}
		nextball = getNextBall();
		((FakeMushroomWeapon)weapon).setSpriteBall(nextball);
	}

	@Override
	public void callAI() {
		if (ballInAnimation>0) {
			ballInAnimation--;
			if (ballInAnimation == 0) {
				currentSprite = staySprite;
				nextball = getNextBall();
				((FakeMushroomWeapon)weapon).setSpriteBall(nextball);
			}
		}
		if (inAnimation>0) {
			inAnimation--;
			if (inAnimation == 0) {
				halfsizex = 50;
				halfsizey = 50;
				halfrsizex = 45;
				halfrsizey = 45;
				currentSprite = staySprite;
				isSlashing = false;
			}
		}
		if (bcd > 0)
			bcd--;
		if (inAnimation == 0) {
			switch (StartingClass.difficultylevel) {
			case 1:
				int pathresult = 0;
				ArrayList<Integer> tox = new ArrayList<Integer>();
				ArrayList<Integer> toy = new ArrayList<Integer>();
				if (player.posx != StartingClass.width && StartingClass.map[player.posx+1][player.posy] == null) {
					tox.add(player.posx+1);
					toy.add(player.posy);
				}
				if (player.posx != 0 && StartingClass.map[player.posx-1][player.posy] == null) {
					tox.add(player.posx-1);
					toy.add(player.posy);
				}
				if (player.posy != StartingClass.height && StartingClass.map[player.posx][player.posy+1] == null) {
					tox.add(player.posx);
					toy.add(player.posy+1);
				}
				if (player.posy != 0 && StartingClass.map[player.posx][player.posy-1] == null) {
					tox.add(player.posx);
					toy.add(player.posy-1);
				}
				pathresult = pf.getDirectionToShoot(posx, posy, tox, toy, 50, canmoveleft, canmoveup, canmoveright, canmovedown, 0, false);
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
				}
				break;
			case 2:
				pathresult = 0;
				tox = new ArrayList<Integer>();
				toy = new ArrayList<Integer>();
				if (player.posx != StartingClass.width && StartingClass.map[player.posx+1][player.posy] == null) {
					tox.add(player.posx+1);
					toy.add(player.posy);
				}
				if (player.posx != 0 && StartingClass.map[player.posx-1][player.posy] == null) {
					tox.add(player.posx-1);
					toy.add(player.posy);
				}
				if (player.posy != StartingClass.height && StartingClass.map[player.posx][player.posy+1] == null) {
					tox.add(player.posx);
					toy.add(player.posy+1);
				}
				if (player.posy != 0 && StartingClass.map[player.posx][player.posy-1] == null) {
					tox.add(player.posx);
					toy.add(player.posy-1);
				}
				pathresult = pf.getDirectionToShoot(posx, posy, tox, toy, 50, canmoveleft, canmoveup, canmoveright, canmovedown, 0, false);
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
				}
				break;
			case 3:
				pathresult = 0;
				tox = new ArrayList<Integer>();
				toy = new ArrayList<Integer>();
				if (player.posx != StartingClass.width && StartingClass.map[player.posx+1][player.posy] == null) {
					tox.add(player.posx+1);
					toy.add(player.posy);
				}
				if (player.posx != 0 && StartingClass.map[player.posx-1][player.posy] == null) {
					tox.add(player.posx-1);
					toy.add(player.posy);
				}
				if (player.posy != StartingClass.height && StartingClass.map[player.posx][player.posy+1] == null) {
					tox.add(player.posx);
					toy.add(player.posy+1);
				}
				if (player.posy != 0 && StartingClass.map[player.posx][player.posy-1] == null) {
					tox.add(player.posx);
					toy.add(player.posy-1);
				}
				pathresult = pf.getDirectionToShoot(posx, posy, tox, toy, 50, canmoveleft, canmoveup, canmoveright, canmovedown, 0, false);
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
				}
				break;
			case 4:
				pathresult = 0;
				tox = new ArrayList<Integer>();
				toy = new ArrayList<Integer>();
				if (player.posx != StartingClass.width && StartingClass.map[player.posx+1][player.posy] == null) {
					tox.add(player.posx+1);
					toy.add(player.posy);
				}
				if (player.posx != 0 && StartingClass.map[player.posx-1][player.posy] == null) {
					tox.add(player.posx-1);
					toy.add(player.posy);
				}
				if (player.posy != StartingClass.height && StartingClass.map[player.posx][player.posy+1] == null) {
					tox.add(player.posx);
					toy.add(player.posy+1);
				}
				if (player.posy != 0 && StartingClass.map[player.posx][player.posy-1] == null) {
					tox.add(player.posx);
					toy.add(player.posy-1);
				}
				pathresult = pf.getDirectionToShoot(posx, posy, tox, toy, 50, canmoveleft, canmoveup, canmoveright, canmovedown, 0, false);
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
				}
				break;
			}
			if (health < maxHealth/5 && phase < StartingClass.difficultylevel)
				animationNextPhase();
			int diffx = player.getCenterX() - getCenterX();
			int diffy = player.getCenterY() - getCenterY();
			if (Math.abs(diffx) < 20 && Math.abs(diffy) < 100) {
				if (diffy > 0) {
					stopMoving();
					hasSlashed = false;
					isSlashing = true;
					inAnimation = maxInAnimation;
					halfsizex = 50;
					halfrsizex = 45;
					halfsizey = 80;
					halfrsizey = 70;
					currentSprite = swipeDown;
					slashDirection = 4;
				} else {
					stopMoving();
					hasSlashed = false;
					isSlashing = true;
					inAnimation = maxInAnimation;
					halfsizex = 50;
					halfrsizex = 45;
					halfsizey = 80;
					halfrsizey = 70;
					currentSprite = swipeUp;
					slashDirection = 2;
				}
			} else if (Math.abs(diffy) < 20 && Math.abs(diffx) < 100) {
				if (diffx > 0) {
					stopMoving();
					hasSlashed = false;
					isSlashing = true;
					inAnimation = maxInAnimation;
					halfsizex = 100;
					halfrsizex = 85;
					halfsizey = 50;
					halfrsizey = 45;
					currentSprite = swipeRight;
					slashDirection = 3;
				} else {
					stopMoving();
					hasSlashed = false;
					isSlashing = true;
					inAnimation = maxInAnimation;
					halfsizex = 100;
					halfrsizex = 85;
					halfsizey = 50;
					halfrsizey = 45;
					currentSprite = swipeLeft;
					slashDirection = 1;
				}
			}
			if (bcd == 0 && (StartingClass.difficultylevel > 2 || Math.abs(diffx) + Math.abs(diffy) > 200)) {
				bcd = ballcd;
				if (phase % 2 == 0) {
					diffx = player.getCenterX() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))/12*player.getSpeedX() - getCenterX();
					diffy = player.getCenterY() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))/12*player.getSpeedY() - getCenterY();
				}
				float vectorx = (diffx-30) / ((float)(Math.abs(diffx-30)+Math.abs(diffy)));
				float vectory = diffy / ((float)(Math.abs(diffx-30)+Math.abs(diffy)));
				projectiles.add(new MushroomWizardBall(centerX + 30,centerY,vectorx,vectory,nextball,phase % 2 == 0));
				if (phase > 2) {
					projectiles.add(new MushroomWizardBall(centerX + 30,centerY,vectorx,vectory,centerX,centerY,getNextBall(),phase % 2 == 0));
					nextball = getNextBall();
				} else {
					if (StartingClass.difficultylevel < 3)
						stopMoving();
					weapon.currentSprite = null;
					currentSprite = shooting;
					ballInAnimation = maxBallInAnimation;
				}
			}
		}
	}
	
	private int getNextBall() {
		int nrd = ((int) (Math.random() * randr));
		if (nrd < randg)
			return 1;
		else if (nrd < randy)
			return 2;
		else if (nrd < randb)
			return 3;
		else
			return 4;
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
	public void setStaySpriteAlt() {
		currentSprite = staySpriteRight;
	}

	@Override
	public void setMove1SpriteAlt() {
		currentSprite = move1SpriteRight;
	}

	@Override
	public void setMove2SpriteAlt() {
		currentSprite = move2SpriteRight;
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
	public void die() {
		super.die();
		projectiles.clear();
	}

	@Override
	public void animate(){
		if (isMoving && inAnimation == 0 && ballInAnimation == 0) {
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
	
	private void animationNextPhase() {
		projectiles.clear();
		player.getProjectiles().clear();
		stopMoving();
		player.controlledstopMoving();
		currentSprite = summoning;
		((FakeMushroomWeapon)weapon).currentSprite = null;
		
		int insideareasize = StartingClass.arenainsidearea.get(StartingClass.isInArena).size();
		//add 2 lavatiles
		int posl;
		int poslx;
		int posly;
		boolean test;
		
		int toheal = maxHealth * (StartingClass.difficultylevel-phase)/StartingClass.difficultylevel;
		float deltaheal = (toheal-health)/(240.0f);
		float fhealth = (float)health;
		float summoningdelta = (toheal-health)/((float)6);
		int summoningstep = 0;
		
		int animcounter = 0;
		
		while (health < toheal) {
			//StartingClass.computationtime += System.nanoTime() - StartingClass.nanoclock;
			try {
				Thread.sleep(Math.abs(17 - System.currentTimeMillis() + StartingClass.clock));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//StartingClass.nanoclock = System.nanoTime();
			StartingClass.clock = System.currentTimeMillis();
			if (StartingClass.TESTMODE) {
				if (StartingClass.clock > StartingClass.fpsclock+1000) {
					StartingClass.fpsclock = StartingClass.clock;
					StartingClass.fps = StartingClass.fpscount;
					StartingClass.fpscount = 0;
					/*StartingClass.cmptime = StartingClass.computationtime / StartingClass.fps / 1000;
					StartingClass.computationtime = 0;*/
				} else {
					StartingClass.fpscount++;
				}
			}
			int i = 0;
			while (i < StartingClass.hitpoints.size()) {
				if (StartingClass.hitpoints.get(i).timer == 0)
					StartingClass.hitpoints.remove(i);
				else {
					StartingClass.hitpoints.get(i).timer--;
					i++;
				}	
			}
			
			player.canmovedown = true;
			player.canmoveleft = true;
			player.canmoveright = true;
			player.canmoveup = true;
			for (Explosion e : StartingClass.explosions) {
				if (e.isProcing() && player.R.intersects(e.getR())) {
					if (player.getArmor().defense - e.damage < 0) {
						player.setHealth(player.getHealth() - e.damage + player.getArmor().defense);
						player.getArmor().setDefense(0);
					} else {
						player.getArmor().setDefense(player.getArmor().getDefense() - e.damage);
					}
				}
			}
			player.controlledupdate();
			StartingClass.updateExplosions();
			for (Enemy e : StartingClass.getEnemyarray()) {
				if (e.alive)
					StartingClass.map[e.posx][e.posy] = null;
			}
			StartingClass.updateEnemies();
			int exi = 0;
			int exsize = 0;
			while (exi < exsize) {
				Explosion e = StartingClass.explosions.get(exi);
				int dt = 0;
				while (dt < StartingClass.destroyabletiles.size()) {
					if (e.isProcing() && e.getR().intersects(StartingClass.destroyabletiles.get(dt).R)) {
						if (!StartingClass.destroyabletiles.get(dt).damage(e.damage))
							dt++;
					} else
						dt++;
				}
				exi++;
			}
			bg.update();
			StartingClass.updateTiles();
			StartingClass.updateItems();
			
			fhealth += deltaheal;
			health = (int)fhealth;
			
			if (summoningstep == 0 && fhealth > toheal - 5 * summoningdelta) {
				//Adding Lava 1
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
					test = false;
					for (Item it : StartingClass.items)
						test = test || (it.posx == poslx && it.posy == posly);
				} while (test || Math.abs(player.posx-poslx) <=1 || Math.abs(player.posy-posly) <= 1);
				Lava l1 = new Lava(poslx,posly,0,0,false,0);
				l1.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				l1.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				l1.r.setBounds(l1.getCenterX() - 22, l1.getCenterY() - 22, 45, 45);
				StartingClass.items.add(l1);
				summoningstep++;
			}
			if (summoningstep == 1 && fhealth > toheal - 4 * summoningdelta) {
				//Adding healthpotion 1
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
					test = false;
					for (Item it : StartingClass.items)
						test = test || (it.posx == poslx && it.posy == posly);
				} while (test || Math.abs(player.posx-poslx) >3 || Math.abs(player.posy-posly) > 3);
				HealthPotion h1 = new HealthPotion(poslx,posly,0,0,true,0);
				h1.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				h1.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				h1.r.setBounds(h1.getCenterX() - 22, h1.getCenterY() - 22, 45, 45);
				StartingClass.items.add(h1);
				summoningstep++;
			}
			if (summoningstep == 2 && fhealth > toheal - 3 * summoningdelta) {
				//Adding WaterFlow 1
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
					test = false;
					for (Item it : StartingClass.items)
						test = test || (it.posx == poslx && it.posy == posly);
				} while (test || Math.abs(player.posx-poslx) <=1 || Math.abs(player.posy-posly) <= 1);
				WaterFlow w1 = new WaterFlow(poslx,posly,0,0,false,0);
				w1.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				w1.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				w1.r.setBounds(w1.getCenterX() - 22, w1.getCenterY() - 22, 45, 45);
				StartingClass.items.add(w1);
				summoningstep++;
			}
			if (summoningstep == 3 && fhealth > toheal - 2 * summoningdelta) {
				//Adding Lava 2
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
					test = false;
					for (Item it : StartingClass.items)
						test = test || (it.posx == poslx && it.posy == posly);
				} while (test || Math.abs(player.posx-poslx) <=1 || Math.abs(player.posy-posly) <= 1);
				Lava l2 = new Lava(poslx,posly,0,0,false,0);
				l2.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				l2.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				l2.r.setBounds(l2.getCenterX() - 22, l2.getCenterY() - 22, 45, 45);
				StartingClass.items.add(l2);
				summoningstep++;
			}
			if (summoningstep == 4 && fhealth > toheal - 1 * summoningdelta) {
				//Adding armorpotion 1
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
					test = false;
					for (Item it : StartingClass.items)
						test = test || (it.posx == poslx && it.posy == posly);
				} while (test || Math.abs(player.posx-poslx) > 3 || Math.abs(player.posy-posly) > 3);
				ArmorPotion a1 = new ArmorPotion(poslx,posly,0,0,true,0);
				a1.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				a1.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				a1.r.setBounds(a1.getCenterX() - 22, a1.getCenterY() - 22, 45, 45);
				StartingClass.items.add(a1);
				summoningstep++;
			}
			if (animcounter % 20 < 10)
				player.setScrollingSpeedY(2);
			else
				player.setScrollingSpeedY(-2);
			animcounter++;
			applet.repaint();
		}
		player.setScrollingSpeedY(0);
		phase++;
		currentSprite = staySprite;
		nextball = getNextBall();
		if (phase > 2)
			((FakeMushroomWeapon)weapon).setSpriteBall(1);
		else
			((FakeMushroomWeapon)weapon).setSpriteBall(nextball);
	}
	
	@Override
	public void setGibsSprite() {
		currentSprite = dieSprite;
	}
}
