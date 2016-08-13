package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class MushroomWizard extends Enemy {

	private int inAnimation;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, 
		move1SpriteRight, move2SpriteRight, swipeDown, swipeRight, swipeLeft, swipeUp, shooting, 
		summoning, intermediateDieSprite, swipeWindup;
	private int maxInAnimation;
	private int ballInAnimation;
	private int maxBallInAnimation;
	private int ballcd;
	private int bcd;
	private int bcd2;
	private int slashdmg;
	private int randg, randy, randb,randr;
	private int phase = 1;
	private boolean isSlashing;
	private int slashDirection;
	private int slashCd;
	private boolean hasSlashed;
	private StartingClass applet;
	private int nextball;
	private int slashwindup, slashwinduptime;
	
	public MushroomWizard(int centerX, int centerY, StartingClass applet) {
		super(centerX, centerY, new FakeMushroomWeapon(25,-12), 75, (StartingClass.difficultylevel>2)?1:2, 50, 50, 45, 45);
		movementTime = ((int) (Math.random() * 50));
		halfbarx = 45;
		slashdmg = 4;
		slashwinduptime = 20;
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
			maxInAnimation = 50;
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
			randg = 33;
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
			randy = 36;
			randb = 39;
			randr = 40;
			break;
		}
		nextball = getNextBall();
		((FakeMushroomWeapon)weapon).setSpriteBall(nextball);
	}

	@Override
	public void callAI() {
		if (StartingClass.maskminx == -1) {
			int minx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(0) / StartingClass.height;
			int miny = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(0) % StartingClass.height;
			int maxx = minx;
			int maxy = miny;
			for (int i : StartingClass.arenainsidearea.get(StartingClass.isInArena)) {
				int x = i / StartingClass.height;
				int y = i % StartingClass.height;
				if (x < minx)
					minx = x;
				if (x > maxx)
					maxx = x;
				if (y < miny)
					miny = y;
				if (y > maxy)
					maxy = y;
			}
			StartingClass.maskminx = minx;
			StartingClass.maskminy = miny;
			StartingClass.maskmaxx = maxx;
			StartingClass.maskmaxy = maxy;
		}
		if (slashCd > 0)
			slashCd--;
		if (ballInAnimation>0) {
			ballInAnimation--;
			if (ballInAnimation == 0) {
				currentSprite = staySprite;
				nextball = getNextBall();
				((FakeMushroomWeapon)weapon).setSpriteBall(nextball);
			}
		}
		if (slashwindup > 0) {
			slashwindup--;
			if (slashwindup == 0) {
				hasSlashed = false;
				inAnimation = maxInAnimation;
				switch(slashDirection) {
				case 1:
					halfsizex = 100;
					halfrsizex = 85;
					halfsizey = 50;
					halfrsizey = 45;
					currentSprite = swipeLeft;
					break;
				case 2:
					halfsizex = 50;
					halfrsizex = 45;
					halfsizey = 80;
					halfrsizey = 70;
					currentSprite = swipeUp;
					break;
				case 3:
					halfsizex = 100;
					halfrsizex = 85;
					halfsizey = 50;
					halfrsizey = 45;
					currentSprite = swipeRight;
					break;
				case 4:
					halfsizex = 50;
					halfrsizex = 45;
					halfsizey = 80;
					halfrsizey = 70;
					currentSprite = swipeDown;
					break;
				}
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
				slashCd = 30;
			}
		}
		if (bcd > 0)
			bcd--;
		if (bcd2 > 0)
			bcd2--;
		if (inAnimation == 0 && !isSlashing) {
			int pathresult = 0;
			StartingClass.map[player.posx][player.posy] = null;
			pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 200, canmoveleft, canmoveup, canmoveright, canmovedown, 0, true);
			StartingClass.map[player.posx][player.posy] = player;
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
			if (health < maxHealth/5 && phase < Math.max(StartingClass.difficultylevel+1,4))
				animationNextPhase();
			int diffx = player.getCenterX() - getCenterX();
			int diffy = player.getCenterY() - getCenterY();
			if (slashCd == 0 && Math.abs(diffx) < 20 && Math.abs(diffy) < 100) {
				if (diffy > 0) {
					stopMoving();
					hasSlashed = true;
					isSlashing = true;
					slashDirection = 4;
					slashwindup = slashwinduptime;
					currentSprite = swipeWindup;
				} else {
					stopMoving();
					hasSlashed = true;
					isSlashing = true;
					slashDirection = 2;
					slashwindup = slashwinduptime;
					currentSprite = swipeWindup;
				}
			} else if (slashCd == 0 && Math.abs(diffy) < 20 && Math.abs(diffx) < 100) {
				if (diffx > 0) {
					stopMoving();
					hasSlashed = true;
					isSlashing = true;
					slashDirection = 3;
					slashwindup = slashwinduptime;
					currentSprite = swipeWindup;
				} else {
					stopMoving();
					hasSlashed = true;
					isSlashing = true;
					slashDirection = 1;
					slashwindup = slashwinduptime;
					currentSprite = swipeWindup;
				}
			}//TODO
			if (bcd == 0 && phase > 2) {
				bcd = ballcd;
				projectiles.add(new MushroomWizardBall(centerX + 30,centerY,0.f,-1.f,centerX,centerY,getNextBall(),phase % 2 == 0));
			}
			if (bcd2 == 0 && (StartingClass.difficultylevel > 2 || Math.abs(diffx) + Math.abs(diffy) > 200)) {
				diffx -= 30;
				float dist = (float)(Math.sqrt(Math.abs(diffx)*Math.abs(diffx)+Math.abs(diffy)*Math.abs(diffy)));
				if (StartingClass.difficultylevel > 2)
					bcd2 = (2 - (phase % 2))*ballcd;
				else
					bcd2 = ballcd;
				if (phase % 2 == 0) {
					diffx = player.getCenterX() + (int)(dist*player.getSpeedX()/10.f) - getCenterX();
					diffy = player.getCenterY() + (int)(dist*player.getSpeedY()/10.f) - getCenterY();
					dist = (float)(Math.sqrt(Math.abs(diffx)*Math.abs(diffx)+Math.abs(diffy)*Math.abs(diffy)));
				}
				float vectorx = diffx / dist;
				float vectory = diffy / dist;
				projectiles.add(new MushroomWizardBall(centerX + 30,centerY,vectorx,vectory,nextball,phase % 2 == 0));
				nextball = getNextBall();
				if (phase <= 2) {
					if (StartingClass.difficultylevel < 3)
						stopMoving();
					weapon.currentSprite = null;
					currentSprite = shooting;
					ballInAnimation = maxBallInAnimation;
				} else
					((FakeMushroomWeapon)weapon).setSpriteBall(nextball);
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
					player.damage(slashdmg);
				}
			}
		}
	}
	
	@Override
	public void die() {
		super.die();
		//projectiles.clear();
	}

	@Override
	public void animate(){
		if (isMoving && inAnimation == 0 && ballInAnimation == 0 && !isSlashing) {
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
		
		int toheal = maxHealth * (StartingClass.difficultylevel+1-phase)/(StartingClass.difficultylevel+1);
		float deltaheal = (toheal-health)/(240.0f);
		float fhealth = (float)health;
		float summoningdelta = (toheal-health)/((float)7);
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
			
			player.initState();
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
			
			if (summoningstep == 0 && fhealth > toheal - 6 * summoningdelta) {
				//Adding Lava 1
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
				} while (StartingClass.heightitemmap[poslx][posly]>=0 || Math.abs(player.posx-poslx) <=1 || Math.abs(player.posy-posly) <= 1);
				Lava l1 = new Lava(poslx,posly,0,0,false,0);
				l1.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				l1.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				applet.backgroundmap[poslx][posly] = BackgroundFactory.lava;
				StartingClass.remask = true;
				l1.r.setBounds(l1.getCenterX() - 22, l1.getCenterY() - 22, 45, 45);
				StartingClass.items[poslx][posly][0] = l1;
				StartingClass.heightitemmap[poslx][posly]++;
				summoningstep++;
			}
			if (summoningstep == 1 && fhealth > toheal - 5 * summoningdelta) {
				//Adding healthpotion 1
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
				} while (StartingClass.heightitemmap[poslx][posly]>=0 || Math.abs(player.posx-poslx) >3 || Math.abs(player.posy-posly) > 3);
				HealthPotion h1 = new HealthPotion(poslx,posly,0,0,true,0);
				h1.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				h1.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				h1.r.setBounds(h1.getCenterX() - 22, h1.getCenterY() - 22, 45, 45);
				StartingClass.items[poslx][posly][0] = h1;
				StartingClass.heightitemmap[poslx][posly]++;
				summoningstep++;
			}
			if (summoningstep == 2 && fhealth > toheal - 4 * summoningdelta) {
				//Adding Lava 2
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
				} while (StartingClass.heightitemmap[poslx][posly]>=0 || Math.abs(player.posx-poslx) <=1 || Math.abs(player.posy-posly) <= 1);
				Lava l2 = new Lava(poslx,posly,0,0,false,0);
				l2.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				l2.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				applet.backgroundmap[poslx][posly] = BackgroundFactory.lava;
				StartingClass.remask = true;
				l2.r.setBounds(l2.getCenterX() - 22, l2.getCenterY() - 22, 45, 45);
				StartingClass.items[poslx][posly][0] = l2;
				StartingClass.heightitemmap[poslx][posly]++;
				summoningstep++;
			}
			if (summoningstep == 3 && fhealth > toheal - 3 * summoningdelta) {
				//Adding WaterFlow 1
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
				} while (StartingClass.heightitemmap[poslx][posly]>=0 || Math.abs(player.posx-poslx) <=1 || Math.abs(player.posy-posly) <= 1);
				WaterFlow w1 = new WaterFlow(poslx,posly,0,0,false,0);
				w1.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				w1.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				applet.backgroundmap[poslx][posly] = BackgroundFactory.waterflow;
				StartingClass.remask = true;
				w1.r.setBounds(w1.getCenterX() - 22, w1.getCenterY() - 22, 45, 45);
				StartingClass.items[poslx][posly][0] = w1;
				StartingClass.heightitemmap[poslx][posly]++;
				summoningstep++;
			}
			if (summoningstep == 4 && fhealth > toheal - 2 * summoningdelta) {
				//Adding Lava 3
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
				} while (StartingClass.heightitemmap[poslx][posly]>=0 || Math.abs(player.posx-poslx) <=1 || Math.abs(player.posy-posly) <= 1);
				Lava l3 = new Lava(poslx,posly,0,0,false,0);
				l3.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				l3.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				applet.backgroundmap[poslx][posly] = BackgroundFactory.lava;
				StartingClass.remask = true;
				l3.r.setBounds(l3.getCenterX() - 22, l3.getCenterY() - 22, 45, 45);
				StartingClass.items[poslx][posly][0] = l3;
				StartingClass.heightitemmap[poslx][posly]++;
				summoningstep++;
			}
			if (summoningstep == 5 && fhealth > toheal - 1 * summoningdelta) {
				//Adding armorpotion 1
				do {
					posl = (int)(Math.random()*insideareasize);
					poslx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) / StartingClass.height;
					posly = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(posl) % StartingClass.height;
				} while (StartingClass.heightitemmap[poslx][posly]>=0 || Math.abs(player.posx-poslx) > 3 || Math.abs(player.posy-posly) > 3);
				ArmorPotion a1 = new ArmorPotion(poslx,posly,0,0,true,0);
				a1.setCenterX(50*poslx+25+bg.getCenterX()-StartingClass.bginitx);
				a1.setCenterY(50*posly+25+bg.getCenterY()-StartingClass.bginity);
				a1.r.setBounds(a1.getCenterX() - 22, a1.getCenterY() - 22, 45, 45);
				StartingClass.items[poslx][posly][0] = a1;
				StartingClass.heightitemmap[poslx][posly]++;
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

	@Override
	public boolean hasIntermediateDying() {
		return true;
	}

	@Override
	public void setIntermediateDieSprite() {
		currentSprite = intermediateDieSprite;
	}
}
