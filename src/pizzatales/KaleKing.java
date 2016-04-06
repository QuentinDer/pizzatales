package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class KaleKing extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	public static Image swipeDown, swipeRight, swipeLeft, swipeUp;
	public static Image boltsfiringsprite;
	public static ArrayList<Image> phaseanim = new ArrayList<Image>();
	//public static Image phase1, phase2, phase3, phase4, phase5, phase6, phase7, phase8, phase9, phase10, phase11;
	private boolean widerice;
	private int healicechange;
	private int slashdmg;
	private int phase = 1;
	private boolean isSlashing;
	private int slashDirection;
	private int slashCd;
	private boolean hasSlashed;
	private int inAnimation;
	private int maxInAnimation;
	private int frozenduration;
	private float iceboltdmg;
	private int iceboltrange, iceboltspeed;
	private int waitmin, waitmax;
	private int boltcd, boltmaxcd;
	private boolean boltsenabled;
	private int boltsfiring, boltsfiringduration;
	private int boltsfirerate, boltscirclerange;
	private int phasegroundrate, phaseanimrate;
	private StartingClass applet;
	private ArrayList<Integer> ring;
	private boolean isAnimating = false;
	private int animcounter;
	private int animsprite;
	private ArrayList<Integer> todiscover = new ArrayList<Integer>();
	private ArrayList<Integer> nonobstacles = new ArrayList<Integer>();
	
	public KaleKing(int centerX, int centerY, StartingClass applet) {
		super(centerX, centerY, null, 200, 2.5f, 63, 50,
				48, 45);
		halfbarx = 45;
		iceboltdmg = 1;
		iceboltrange = 1000;
		iceboltspeed = 10;
		boltsfiringduration = 120;
		this.applet = applet;
		phasegroundrate = 15;
		phaseanimrate = 10;
		switch (StartingClass.difficultylevel) {
		case 1:
			waitmin = 120;
			waitmax = 240;
			boltmaxcd = 60;
			frozenduration = 60;
			healicechange = 20;
			maxInAnimation = 60;
			boltsfirerate = 30;
			boltscirclerange = 0;
			break;
		case 2:
			waitmin = 100;
			waitmax = 220;
			boltmaxcd = 60;
			frozenduration = 70;
			healicechange = 30;
			maxInAnimation = 50;
			boltsfirerate = 25;
			boltscirclerange = 75;
			break;
		case 3:
			waitmin = 80;
			waitmax = 200;
			boltmaxcd = 60;
			frozenduration = 80;
			healicechange = 40;
			maxInAnimation = 40;
			boltsfirerate = 20;
			boltscirclerange = 150;
			break;
		case 4:
			waitmin = 60;
			waitmax = 180;
			boltmaxcd = 60;
			frozenduration = 90;
			healicechange = 50;
			maxInAnimation = 30;
			boltsfirerate = 15;
			boltscirclerange = 225;
			break;
		}
		boltsenabled = true;
		slashdmg = 5;
	}

	@Override
	public void callAI() {
		if (isAnimating) {
			boolean test;
			int size;
			int j;
			if (animcounter % phasegroundrate == 0) {
				for (int tmpr : todiscover) {
					int xj = tmpr / StartingClass.height;
					int yj = tmpr % StartingClass.height;
					if (ring.contains(tmpr)) {
						boolean tt = false;
						for (int h = 0; h <= StartingClass.heightitemmap[xj][yj]; h++) {
							if (SummonedIce.class.isInstance(StartingClass.items[xj][yj][h])) {
								((SummonedIce)StartingClass.items[xj][yj][h]).previousbackground = this.getOutsideRingSprite(xj,yj,phase);
								tt = true;
							}
						}
						if (!tt)
							StartingClass.backgroundmap[xj][yj] = this.getOutsideRingSprite(xj,yj,phase);
					} else {
						boolean tt = false;
						for (int h = 0; h <= StartingClass.heightitemmap[xj][yj]; h++) {
							if (SummonedIce.class.isInstance(StartingClass.items[xj][yj][h])) {
								((SummonedIce)StartingClass.items[xj][yj][h]).previousbackground =  this.getInsideRingSprite(phase);
								tt = true;
							}
						}
						if (!tt)
							StartingClass.backgroundmap[xj][yj] = this.getInsideRingSprite(phase);
					}
				}
				StartingClass.remask = true;
				j = 0;
				size = todiscover.size();
				StartingClass.map[player.posx][player.posy] = null;
				while (j < size) {
					int current = todiscover.get(0);
					int xj = current / StartingClass.height;
					int yj = current % StartingClass.height;
					test = false;
					if (!nonobstacles.contains(current-StartingClass.height) && !todiscover.contains(current-StartingClass.height) && 0 != xj) {
						if (null == StartingClass.map[xj-1][yj]) {
							todiscover.add(current-StartingClass.height);
						} else
							test = true;
					}
					if (!nonobstacles.contains(current+StartingClass.height) && !todiscover.contains(current+StartingClass.height) && StartingClass.width -1 != xj) {
						if (null == StartingClass.map[xj+1][yj]) {
							todiscover.add(current+StartingClass.height);
						} else
							test = true;
					}
					if (!nonobstacles.contains(current-1) && !todiscover.contains(current-1) && 0 != yj) {
						if (null == StartingClass.map[xj][yj-1]) {
							todiscover.add(current-1);
						} else
							test = true;
					}
					if (!nonobstacles.contains(current+1) && !todiscover.contains(current+1) && StartingClass.height-1 != yj) {
						if (null == StartingClass.map[xj][yj+1]) {
							todiscover.add(current+1);
						} else
							test = true;
					}
					if (test)
						ring.add(current);
					todiscover.remove(0);
					nonobstacles.add(current);
					j++;
				}
				StartingClass.map[player.posx][player.posy] = player;
			}
			if (animcounter % phaseanimrate == 0) {
				animsprite++;
				if (animsprite == phaseanim.size())
					animsprite = 0;
				currentSprite = phaseanim.get(animsprite);
			}
			animcounter++;
			if (todiscover.isEmpty()) {
				isAnimating = false;
				currentSprite = staySprite;
				halfsizex = 68;
				halfsizey = 50;
				halfrsizex = 48;
				halfrsizey = 45;
				StartingClass.map[posx][posy] = this;
				paintoverride = false;
			}
		} else {
			
			if (ring == null) {
				ring = new ArrayList<Integer>();
				StartingClass.map[posx][posy] = null;
				StartingClass.map[player.posx][player.posy] = null;
				MapUtil.getRing(posx, posy, 500, ring);
				StartingClass.map[posx][posy] = this;
			}
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
			if (boltcd > 0)
				boltcd--;
			if (boltsfiring > 0) {
				boltsfiring--;
				if (boltsfiring == 0) {
					currentSprite = staySprite;
					boltcd = boltmaxcd;
					incrementWaitingTimes();
				} else if (boltsfiring % boltsfirerate == 0){
					int diffx = player.getCenterX() - getCenterX();
					int diffy = player.getCenterY() - getCenterY();
					float ndiffx = diffx - 54 + (float)(Math.random()*boltscirclerange) - boltscirclerange/2;
					float ndiffy = diffy + 27 + (float)(Math.random()*boltscirclerange) - boltscirclerange/2;
					float dist = (float)(Math.sqrt(Math.abs(ndiffx)*Math.abs(ndiffx)+Math.abs(ndiffy)*Math.abs(ndiffy)));
					float vectorx = ndiffx / dist;
					float vectory = ndiffy / dist;
					projectiles.add(new IceBolt(centerX+54,centerY-27,vectorx,vectory,iceboltspeed,iceboltdmg, iceboltrange, frozenduration));
				}
			}
			if (inAnimation > 0) {
				inAnimation--;
				if (inAnimation == 0) {
					halfsizex = 68;
					halfsizey = 50;
					halfrsizex = 48;
					halfrsizey = 45;
					currentSprite = staySprite;
					isSlashing = false;
					slashCd = 30;
				}
			}
			if (inAnimation == 0 && boltsfiring == 0) {
				if (phase != 6 && health < maxHealth*(6-phase)/6) {
					this.startPhaseAnimation();
					return;
				}
			}
			if (inAnimation == 0 && boltsfiring == 0) {
				if (movementTime % 05 == 0) {
					StartingClass.map[player.posx][player.posy] = null;
					int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 200, canmoveleft, canmoveup, canmoveright, canmovedown, 0, true);
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
				}
				int diffx = player.getCenterX() - getCenterX();
				int diffy = player.getCenterY() - getCenterY();
				if (slashCd == 0 && Math.abs(diffx) < 40 && Math.abs(diffy) < 96) {
					if (diffy > 0) {
						stopMoving();
						hasSlashed = false;
						isSlashing = true;
						inAnimation = maxInAnimation;
						halfsizex = 62;
						halfrsizex = 75;
						halfsizey = 55;
						halfrsizey = 73;
						currentSprite = swipeDown;
						slashDirection = 4;
					} else {
						stopMoving();
						hasSlashed = false;
						isSlashing = true;
						inAnimation = maxInAnimation;
						halfsizex = 62;
						halfrsizex = 75;
						halfsizey = 55;
						halfrsizey = 73;
						currentSprite = swipeUp;
						slashDirection = 2;
					}
				} else if (slashCd == 0 && Math.abs(diffy) < 40 && Math.abs(diffx) < 115) {
					if (diffx > 0) {
						stopMoving();
						hasSlashed = false;
						isSlashing = true;
						inAnimation = maxInAnimation;
						halfsizex = 91;
						halfrsizex = 90;
						halfsizey = 65;
						halfrsizey = 55;
						currentSprite = swipeRight;
						slashDirection = 3;
					} else {
						stopMoving();
						hasSlashed = false;
						isSlashing = true;
						inAnimation = maxInAnimation;
						halfsizex = 91;
						halfrsizex = 90;
						halfsizey = 65;
						halfrsizey = 55;
						currentSprite = swipeLeft;
						slashDirection = 1;
					}
				}
				if (inAnimation == 0 && boltsenabled && boltcd == 0 && boltsfiring == 0) {
					stopMoving();
					currentSprite = boltsfiringsprite;
					boltsfiring = boltsfiringduration;
				}
			}
		}
	}
	
	@Override
	public void update() {
		
		speedX = 0;
		speedY = 0;
		
		if (ismovingup) {
			speedY += -speed;
		}
		if (ismovingdown) {
			speedY += speed;
		}
		if (ismovingleft) {
			speedX += -speed;
		}
		if (ismovingright) {
			speedX += speed;
		}
		if (speedY > 0 && !canmovedown)
			speedY = 0;
		if (speedY < 0 && !canmoveup)
			speedY = 0;
		if (speedX > 0 && !canmoveright)
			speedX = 0;
		if (speedX < 0 && !canmoveleft)
			speedX = 0;
		fcenterX += speedX - player.getScrollingSpeedX();
		fcenterY += speedY - player.getScrollingSpeedY();
		centerX = (int)fcenterX;
		centerY = (int)fcenterY;
		
		R.setBounds((int)(centerX - halfrsizex + speedX), (int)(centerY - halfrsizey + speedY), 2*halfrsizex, 2*halfrsizey);
		
		if (alive == true) {
			
			posx = (getCenterX() - bg.getCenterX() + StartingClass.bginitx) / 50;
			posy = (getCenterY() - bg.getCenterY() + StartingClass.bginity) / 50;
			if (this.hasSeenU) {
				int px = posx;
				int py = posy;
				boolean testsumice = false;
				for (int h = 0; h <= StartingClass.heightitemmap[px][py]; h++) {
					if (SummonedIce.class.isInstance(StartingClass.items[px][py][h])) {
						testsumice = true;
						((SummonedIce)StartingClass.items[px][py][h]).initEffectTimer();
					}
				}
				if (!testsumice) {
					int h = 0;
					while (h <= StartingClass.heightitemmap[px][py] && BackgroundItem.class.isInstance(StartingClass.items[px][py][h]))
						h++;
					for (int hi = StartingClass.heightitemmap[px][py]; hi>=h;hi--) {
						StartingClass.items[px][py][hi+1] = StartingClass.items[px][py][hi];
						StartingClass.items[px][py][hi+1].height++;
					}
					StartingClass.heightitemmap[px][py]++;
					StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[px][py]);
					//System.out.println("pposx:"+pposx+" pposy:"+pposy+" h:"+h+" heightmax:"+StartingClass.heightitemmap[pposx][pposy]);
					boolean healing = ((int)(Math.random()*healicechange)<1);
					SummonedIce sum = new SummonedIce(px,py,0,0,false,h,healing);
					sum.setCenterX(50*px+25+bg.getCenterX()-StartingClass.bginitx);
					sum.setCenterY(50*py+25+bg.getCenterY()-StartingClass.bginity);
					sum.previousbackground = StartingClass.backgroundmap[px][py];
					if (healing)
						StartingClass.backgroundmap[px][py] = SummonedIce.healingice;
					else
						StartingClass.backgroundmap[px][py] = BackgroundFactory.ice;
					StartingClass.items[px][py][h] = sum;
					StartingClass.remask = true;
				}
				if (widerice) {
					testsumice = false;
					if (getCenterX() > 50*posx+bg.getCenterX()-StartingClass.bginitx)
						px = posx+1;
					else
						px = posx-1;
					for (int h = 0; h <= StartingClass.heightitemmap[px][py]; h++) {
						if (SummonedIce.class.isInstance(StartingClass.items[px][py][h])) {
							testsumice = true;
							((SummonedIce)StartingClass.items[px][py][h]).initEffectTimer();
						}
					}
					if (!testsumice) {
						int h = 0;
						while (h <= StartingClass.heightitemmap[px][py] && BackgroundItem.class.isInstance(StartingClass.items[px][py][h]))
							h++;
						for (int hi = StartingClass.heightitemmap[px][py]; hi>=h;hi--) {
							StartingClass.items[px][py][hi+1] = StartingClass.items[px][py][hi];
							StartingClass.items[px][py][hi+1].height++;
						}
						StartingClass.heightitemmap[px][py]++;
						StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[px][py]);
						//System.out.println("pposx:"+pposx+" pposy:"+pposy+" h:"+h+" heightmax:"+StartingClass.heightitemmap[pposx][pposy]);
						boolean healing = ((int)(Math.random()*healicechange)<1);
						SummonedIce sum = new SummonedIce(px,py,0,0,false,h,healing);
						sum.setCenterX(50*px+25+bg.getCenterX()-StartingClass.bginitx);
						sum.setCenterY(50*py+25+bg.getCenterY()-StartingClass.bginity);
						sum.previousbackground = StartingClass.backgroundmap[px][py];
						if (healing)
							StartingClass.backgroundmap[px][py] = SummonedIce.healingice;
						else
							StartingClass.backgroundmap[px][py] = BackgroundFactory.ice;
						StartingClass.items[px][py][h] = sum;
						StartingClass.remask = true;
					}
					testsumice = false;
					if (getCenterY() > 50*posy+bg.getCenterY()-StartingClass.bginity)
						py = posy+1;
					else
						py = posy-1;
					for (int h = 0; h <= StartingClass.heightitemmap[px][py]; h++) {
						if (SummonedIce.class.isInstance(StartingClass.items[px][py][h])) {
							testsumice = true;
							((SummonedIce)StartingClass.items[px][py][h]).initEffectTimer();
						}
					}
					if (!testsumice) {
						int h = 0;
						while (h <= StartingClass.heightitemmap[px][py] && BackgroundItem.class.isInstance(StartingClass.items[px][py][h]))
							h++;
						for (int hi = StartingClass.heightitemmap[px][py]; hi>=h;hi--) {
							StartingClass.items[px][py][hi+1] = StartingClass.items[px][py][hi];
							StartingClass.items[px][py][hi+1].height++;
						}
						StartingClass.heightitemmap[px][py]++;
						StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[px][py]);
						//System.out.println("pposx:"+pposx+" pposy:"+pposy+" h:"+h+" heightmax:"+StartingClass.heightitemmap[pposx][pposy]);
						boolean healing = ((int)(Math.random()*healicechange)<1);
						SummonedIce sum = new SummonedIce(px,py,0,0,false,h,healing);
						sum.setCenterX(50*px+25+bg.getCenterX()-StartingClass.bginitx);
						sum.setCenterY(50*py+25+bg.getCenterY()-StartingClass.bginity);
						sum.previousbackground = StartingClass.backgroundmap[px][py];
						if (healing)
							StartingClass.backgroundmap[px][py] = SummonedIce.healingice;
						else
							StartingClass.backgroundmap[px][py] = BackgroundFactory.ice;
						StartingClass.items[px][py][h] = sum;
						StartingClass.remask = true;
					}
					testsumice = false;
					px = posx;
					for (int h = 0; h <= StartingClass.heightitemmap[px][py]; h++) {
						if (SummonedIce.class.isInstance(StartingClass.items[px][py][h])) {
							testsumice = true;
							((SummonedIce)StartingClass.items[px][py][h]).initEffectTimer();
						}
					}
					if (!testsumice) {
						int h = 0;
						while (h <= StartingClass.heightitemmap[px][py] && BackgroundItem.class.isInstance(StartingClass.items[px][py][h]))
							h++;
						for (int hi = StartingClass.heightitemmap[px][py]; hi>=h;hi--) {
							StartingClass.items[px][py][hi+1] = StartingClass.items[px][py][hi];
							StartingClass.items[px][py][hi+1].height++;
						}
						StartingClass.heightitemmap[px][py]++;
						StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[px][py]);
						//System.out.println("pposx:"+pposx+" pposy:"+pposy+" h:"+h+" heightmax:"+StartingClass.heightitemmap[pposx][pposy]);
						boolean healing = ((int)(Math.random()*healicechange)<1);
						SummonedIce sum = new SummonedIce(px,py,0,0,false,h,healing);
						sum.setCenterX(50*px+25+bg.getCenterX()-StartingClass.bginitx);
						sum.setCenterY(50*py+25+bg.getCenterY()-StartingClass.bginity);
						sum.previousbackground = StartingClass.backgroundmap[px][py];
						if (healing)
							StartingClass.backgroundmap[px][py] = SummonedIce.healingice;
						else
							StartingClass.backgroundmap[px][py] = BackgroundFactory.ice;
						StartingClass.items[px][py][h] = sum;
						StartingClass.remask = true;
					}
				}
			}
			StartingClass.map[posx][posy] = this;
			animate();
			if (movementTime % 15 == 0 && !hasSeenU && centerX + halfsizex > 0 
					&& centerX - halfsizex< 1280 && centerY + halfsizey > 0 
					&& centerY - halfsizey < 800 && this.canSeePlayer()) {
				hasSeenU = true;
			}
				
		}
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
	
	private void incrementWaitingTimes() {
		if (boltsenabled)
			boltcd += getNextWaitingTime();
	}

	private int getNextWaitingTime() {
		return (int)(Math.random()*(waitmax-waitmin)) + waitmin;
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
		StartingClass.maskminx = -1;
		StartingClass.maskminy = -1;
		StartingClass.maskmaxx = -1;
		StartingClass.maskmaxy = -1;
	}

	@Override
	public void setGibsSprite() {
		currentSprite = dieSprite;
	}

	@Override
	public void setStaySpriteAlt() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove1SpriteAlt() {
		currentSprite = move1Sprite;
	}

	@Override
	public void setMove2SpriteAlt() {
		currentSprite = move2Sprite;
	}
	
	private void startPhaseAnimation() {
		stopMoving();
		animsprite = 0;
		currentSprite = phaseanim.get(0);
		//TODO
		
		isAnimating = true;
		
		halfsizex = 112;
		halfsizey = 100;
		halfrsizex = 48;
		halfrsizey = 45;
		
		phase++;
		
		paintoverride = true;
		animcounter = 0;
		
		todiscover.clear();
		nonobstacles.clear();
		todiscover.add(StartingClass.height*posx+posy);
		
	}
	
	private Image getInsideRingSprite(int phase) {
		Image ans = null;
		switch (phase) {
		case 2:
			ans = BackgroundFactory.grass;
			break;
		case 3:
			ans = BackgroundFactory.cave;
			break;
		case 4:
			ans = BackgroundFactory.dirt;
			break;
		case 5:
			ans = BackgroundFactory.brick;
			break;
		case 6:
			ans = BackgroundFactory.mountain;
			break;
		default:
			break;
		}
		return ans;
	}
	
	private Image getOutsideRingSprite(int x, int y, int phase) {
		Image ans = null;
		int h = 0;
		while (h <= StartingClass.heightitemmap[x][y]) {
			if (SnowBank.class.isInstance(StartingClass.items[x][y][h]) || Lava.class.isInstance(StartingClass.items[x][y][h])) {
				StartingClass.removeItem(StartingClass.items[x][y][h]);
			} else
				h++;
		}
		switch (phase) {
		case 2:
			ans = BackgroundFactory.dirt;
			break;
		case 3:
			h = 0;
			while (h <= StartingClass.heightitemmap[x][y] && BackgroundItem.class.isInstance(StartingClass.items[x][y][h]))
				h++;
			for (int i = StartingClass.heightitemmap[x][y]; i >= h ; i--) {
				StartingClass.items[x][y][h].height++;
				StartingClass.items[x][y][h+1] = StartingClass.items[x][y][h];
			}
			StartingClass.heightitemmap[x][y]++;
			StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[x][y]);
			Lava lava = new Lava(x,y,0,0,false,h);
			lava.setCenterX(50*x+25+bg.getCenterX()-StartingClass.bginitx);
			lava.setCenterY(50*y+25+bg.getCenterY()-StartingClass.bginity);
			lava.r.setBounds(lava.getCenterX() - 22, lava.getCenterY() - 22, 45, 45);
			StartingClass.items[x][y][h] = lava;
			ans = BackgroundFactory.lava;
			break;
		case 4:
			ans = WoodBridge.sprite;
			break;
		case 5:
			ans = Carpet.sprite;
			break;
		case 6:
			h = 0;
			while (h <= StartingClass.heightitemmap[x][y] && BackgroundItem.class.isInstance(StartingClass.items[x][y][h]))
				h++;
			for (int i = StartingClass.heightitemmap[x][y]; i >= h ; i--) {
				StartingClass.items[x][y][h].height++;
				StartingClass.items[x][y][h+1] = StartingClass.items[x][y][h];
			}
			StartingClass.heightitemmap[x][y]++;
			StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[x][y]);
			SnowBank snow = new SnowBank(x,y,0,0,false,h);
			snow.setCenterX(50*x+25+bg.getCenterX()-StartingClass.bginitx);
			snow.setCenterY(50*y+25+bg.getCenterY()-StartingClass.bginity);
			snow.r.setBounds(snow.getCenterX() - 22, snow.getCenterY() - 22, 45, 45);
			StartingClass.items[x][y][h] = snow;
			ans = BackgroundFactory.snow;
			break;
		default:
			break;
		}
		return ans;
	}

}
