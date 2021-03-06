package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class CarolinaReaper extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, firering, streamleft, streamup, streamright, streamdown;
	public static Image staySpriteOnFire, move1SpriteOnFire, move2SpriteOnFire, fireringOnFire, intermediateDieSprite;
	private int waitmin, waitmax;
	private int streamingtime, streamrate, blinkingtime;
	private int barrelsrate;
	private float streamdmg, fireringdmg;
	private int streammaxcd, blinkmaxcd, trapsmaxcd, barrelthrowingmaxcd;
	private int streamcd, blinkcd;
	private int streaming, blinking;
	private int streamrange = 500;
	private int barrelrange = 500;
	private int streamr = 0;
	private int streamtox, streamtoy;
	private int spawningmaxcd, spawningcd, trapscd, barrelthrowingcd;
	private int nbbarrels;
	private int maxnbbarrels;
	private boolean isThrowingBarrels;
	private int nextbarrelcd;
	private boolean streamingenabled, blinkenabled, spawningenabled, trapsenabled, barrelthrowingenabled;
	public static boolean canblink = true;
	private int fireringrange;
	private int leftx, rightx, upy, downy;
	private ReaperBlinkingItem blinkingitem;
	public static int trapscount;
	public static boolean notifytrap;
	private int maxtraps;
	private boolean isOnFire = false;
	private ArrayList<Integer> insidearena;
	
	public CarolinaReaper(int centerX, int centerY) {
		super(centerX, centerY, null, 100, 2, 31, 31, 25, 25);
		movementTime = ((int) (Math.random() * 50));
		streamrate = 4;
		barrelsrate = 15;
		streamingenabled = true;
		switch (StartingClass.difficultylevel) {
		case 1:
			waitmin = 120;
			waitmax = 240;
			streamingtime = 120;
			streamdmg = 0.3f;
			streammaxcd = 60;
			blinkmaxcd = 300;
			blinkingtime = 120;
			fireringrange = 250;
			fireringdmg = 0.6f;
			spawningmaxcd = 180;
			maxtraps = 2;
			trapsmaxcd = 600;
			break;
		case 2:
			waitmin = 100;
			waitmax = 220;
			streamingtime = 100;
			streamdmg = 0.6f;
			streammaxcd = 60;
			blinkmaxcd = 250;
			blinkingtime = 100;
			barrelthrowingmaxcd = 360;
			fireringrange = 300;
			fireringdmg = 0.8f;
			spawningmaxcd = 140;
			maxtraps = 4;
			trapsmaxcd = 500;
			break;
		case 3:
			waitmin = 80;
			waitmax = 200;
			streamingtime = 80;
			streamdmg = 0.9f;
			streammaxcd = 60;
			blinkmaxcd = 200;
			blinkingtime = 80;
			barrelthrowingmaxcd = 300;
			fireringrange = 350;
			fireringdmg = 1.0f;
			spawningmaxcd = 100;
			maxtraps = 6;
			trapsmaxcd = 400;
			maxnbbarrels = 4;
			break;
		case 4:
			waitmin = 60;
			waitmax = 180;
			streamingtime = 60;
			streamdmg = 1.2f;
			streammaxcd = 60;
			blinkmaxcd = 150;
			blinkingtime = 60;
			barrelthrowingmaxcd = 240;
			fireringrange = 400;
			fireringdmg = 1.2f;
			spawningmaxcd = 60;
			maxtraps = 8;
			trapsmaxcd = 300;
			maxnbbarrels = 8;
			break;
		}
	}

	@Override
	public void callAI() {
		if (leftx == 0 && StartingClass.isInArena >= 0) {
			leftx = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(0) / StartingClass.height;
			upy = StartingClass.arenainsidearea.get(StartingClass.isInArena).get(0) % StartingClass.height;
			for (int i : StartingClass.arenainsidearea.get(StartingClass.isInArena)) {
				int x = i / StartingClass.height;
				int y = i % StartingClass.height;
				if (x < leftx)
					leftx = x;
				if (y < upy)
					upy = y;
			}
			downy = upy + 14;
			rightx = leftx + 24;
		}
		if (streaming > 0) {
			streaming--;
			if (streaming == 0) {
				currentSprite = staySprite;
				streamcd = streammaxcd;
				incrementWaitingTimes();
			}
		}
		if (streaming == 0 && streamcd > 0)
			streamcd--;
		if (streamr > 0)
			streamr--;
		if (blinkcd > 0)
			blinkcd--;
		if (spawningcd > 0)
			spawningcd--;
		if (trapscd > 0)
			trapscd--;
		if (nextbarrelcd > 0)
			nextbarrelcd--;
		if (blinking > 0) {
			blinking--;
			if (blinking == 0) {
				StartingClass.removeItem(blinkingitem);
				if (StartingClass.map[blinkingitem.posx][blinkingitem.posy] == null) {
					StartingClass.map[posx][posy] = null;
					setCenterX(50*blinkingitem.posx+25+bg.getCenterX()-StartingClass.bginitx);
					setCenterY(50*blinkingitem.posy+25+bg.getCenterY()-StartingClass.bginity);
					blinkingitem.r.setBounds(blinkingitem.getCenterX() - 25, blinkingitem.getCenterY() - 25, 50, 50);
					StartingClass.map[blinkingitem.posx][blinkingitem.posy] = this;
				}
				blinkingitem = null;
				fireRing();
				if (isOnFire)
					currentSprite = staySpriteOnFire;
				else
					currentSprite = staySprite;
				blinkcd = blinkmaxcd;
				incrementWaitingTimes();
			}
		}
		if (barrelthrowingcd > 0)
			barrelthrowingcd--;
		if (!isThrowingBarrels && streamingenabled && streamcd == 0 && streaming == 0 && blinking == 0 && movementTime % 3 == 0) {
			StartingClass.map[player.posx][player.posy] = null;
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
			int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 200, canmoveleft, canmoveup, canmoveright, canmovedown, dirplace, true);
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
		} else if (!isThrowingBarrels && streaming == 0 && blinking == 0 && movementTime % 3 == 0) {
			int tox = 0;
			int toy = 0;
			switch(getFarestCorner(player.posx,player.posy)) {
			case 1:
				tox = leftx;
				toy = upy;
				break;
			case 2:
				tox = rightx;
				toy = upy;
				break;
			case 3:
				tox = rightx;
				toy = downy;
				break;
			case 4:
				tox = leftx;
				toy = downy;
				break;
			}
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
			int pathresult = pf.getDirection(posx, posy, tox, toy, 200, canmoveleft, canmoveup, canmoveright, canmovedown, dirplace, StartingClass.difficultylevel>2);
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
		if (spawningenabled && spawningcd == 0) {
			ArrayList<Integer> possiblespawningpos = new ArrayList<Integer>();
			int tmpx, tmpy;
			for (Integer i : StartingClass.arenainsidearea.get(StartingClass.isInArena)) {
				tmpx = i / StartingClass.height;
				tmpy = i % StartingClass.height;
				int dist = Math.abs(player.posx-tmpx)+Math.abs(player.posy-tmpy);
				if (tmpx != rightx && tmpx != leftx && tmpy != upy && tmpy != downy && (tmpx != player.posx || tmpy != player.posy) && dist>1 && StartingClass.map[tmpx][tmpy] == null) {
					possiblespawningpos.add(i);
				}
			}
			if (!possiblespawningpos.isEmpty()) {
				
				int randpos = (int)(Math.random()*possiblespawningpos.size());
				int postx = possiblespawningpos.get(randpos) / StartingClass.height;
				int posty = possiblespawningpos.get(randpos) % StartingClass.height;
				if (health > 0.15f * maxHealth && (int)(Math.random()*20) == 0) {
					StartingClass.heightitemmap[postx][posty]++;
					StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[postx][posty]);
					if ((int)(Math.random()*3) == 0) {
						ArmorPotion potion = new ArmorPotion(postx,posty,0,0,true,StartingClass.heightitemmap[postx][posty]);
						potion.setCenterX(50*postx+25+bg.getCenterX()-StartingClass.bginitx);
						potion.setCenterY(50*posty+25+bg.getCenterY()-StartingClass.bginity);
						potion.r.setBounds(potion.getCenterX() - 22, potion.getCenterY() - 22, 45, 45);
						StartingClass.items[postx][posty][StartingClass.heightitemmap[postx][posty]] = potion;
					} else {
						HealthPotion potion = new HealthPotion(postx,posty,0,0,true,StartingClass.heightitemmap[postx][posty]);
						potion.setCenterX(50*postx+25+bg.getCenterX()-StartingClass.bginitx);
						potion.setCenterY(50*posty+25+bg.getCenterY()-StartingClass.bginity);
						potion.r.setBounds(potion.getCenterX() - 22, potion.getCenterY() - 22, 45, 45);
						StartingClass.items[postx][posty][StartingClass.heightitemmap[postx][posty]] = potion;
					}
				} else if (trapsenabled && trapscd == 0 && trapscount < maxtraps) {
					trapscount++;
					int height = 0;
					int maxheight = 0;
					for (int h = 0; h <= StartingClass.heightitemmap[postx][posty]; h++) {
						if (BackgroundItem.class.isInstance(StartingClass.items[postx][posty][h]))
							height++;
						else {
							StartingClass.items[postx][posty][h].height++;
							maxheight = Math.max(maxheight, StartingClass.items[postx][posty][h].height);
						}
					}
					maxheight = Math.max(maxheight, height);
					StartingClass.heightitemmap[postx][posty] = maxheight;
					StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, maxheight);
					ReaperTrap trap = new ReaperTrap(postx,posty,0,0,true,height);
					trap.setCenterX(50*postx+25+bg.getCenterX()-StartingClass.bginitx);
					trap.setCenterY(50*posty+25+bg.getCenterY()-StartingClass.bginity);
					trap.r.setBounds(trap.getCenterX() - 22, trap.getCenterY() - 22, 45, 45);
					StartingClass.items[postx][posty][height] = trap;
					trapscd = trapsmaxcd;
				}
				
				int tcenterX = 50*postx+25+bg.getCenterX()-StartingClass.bginitx;
				int tcenterY = 50*posty+25+bg.getCenterY()-StartingClass.bginity;
				Barrel b = new Barrel(tcenterX,tcenterY);
				b.setTileImage(StartingClass.tileBarrel);
				StartingClass.getTilearray().add(b);
				StartingClass.destroyabletiles.add(b);
				spawningcd = spawningmaxcd;
			}
			possiblespawningpos.clear();
		}
		if (blinking > 0) {
			if (canblink) {
				if (movementTime % 6 < 3)
					moveLeft();
				else
					moveRight();
			} else {
				blinking = 0;
				blinkcd = blinkmaxcd;
				incrementWaitingTimes();
				blinkingitem = null;
				if (isOnFire)
					currentSprite = staySpriteOnFire;
				else
					currentSprite = staySprite;
			}
		}
		if (!trapsenabled && health < 0.4f * maxHealth)
			trapsenabled = true;
		if (!spawningenabled && health < 0.75f * maxHealth)
			spawningenabled = true;
		if (!blinkenabled && health < 0.90f * maxHealth)
			blinkenabled = true;
		if (StartingClass.difficultylevel > 2 && streamingenabled && !barrelthrowingenabled && health < 0.5f * maxHealth)
			barrelthrowingenabled = true;
		if (StartingClass.difficultylevel > 1 && streamingenabled && health < 0.15f * maxHealth) {
			streamingenabled = false;
			barrelthrowingenabled = false;
			blinkmaxcd = blinkmaxcd / 2;
			waitmin = waitmin / 2;
			waitmax = waitmax / 2;
			spawningmaxcd = spawningmaxcd / 3;
			isOnFire = true;
		}
		if (!isThrowingBarrels && blinkenabled && blinkcd == 0 && streaming == 0 && blinking == 0) {
			int tmpx;
			int tmpy;
			ArrayList<Integer> possibleblinkpositions = new ArrayList<Integer>();
			for (int i : StartingClass.arenainsidearea.get(StartingClass.isInArena)) {
				tmpx = i / StartingClass.height;
				tmpy = i % StartingClass.height;
				int dist = Math.abs(player.posx-tmpx)+Math.abs(player.posy-tmpy);
				if ((tmpx != player.posx || tmpy != player.posy) && dist<4 && dist>1 && StartingClass.map[tmpx][tmpy] == null) {
					possibleblinkpositions.add(i);
				}
			}
			if (!possibleblinkpositions.isEmpty()) {
				canblink = true;
				blinking = blinkingtime;
				int randpos = (int)(Math.random() * possibleblinkpositions.size());
				int blinkposx = possibleblinkpositions.get(randpos) / StartingClass.height;
				int blinkposy = possibleblinkpositions.get(randpos) % StartingClass.height;
				StartingClass.heightitemmap[blinkposx][blinkposy]++;
				StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[blinkposx][blinkposy]);
				blinkingitem = new ReaperBlinkingItem(blinkposx,blinkposy,0,0,true,StartingClass.heightitemmap[blinkposx][blinkposy]);
				blinkingitem.setCenterX(50*blinkposx+25+bg.getCenterX()-StartingClass.bginitx);
				blinkingitem.setCenterY(50*blinkposy+25+bg.getCenterY()-StartingClass.bginity);
				blinkingitem.r.setBounds(blinkingitem.getCenterX() - 22, blinkingitem.getCenterY() - 22, 45, 45);
				StartingClass.items[blinkposx][blinkposy][StartingClass.heightitemmap[blinkposx][blinkposy]] = blinkingitem;
				//System.out.println("Blinking item: "+blinkposx+":"+blinkposy+" "+StartingClass.heightitemmap[blinkposx][blinkposy]);
				if (isOnFire)
					currentSprite = fireringOnFire;
				else
					currentSprite = firering;
			}
			possibleblinkpositions.clear();
		}
		if (!isThrowingBarrels && barrelthrowingenabled && barrelthrowingcd == 0 && streaming == 0 && blinking == 0 && Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY) < barrelrange + 50 && Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY) > 200) {
			stopMoving();
			isThrowingBarrels = true;
			nbbarrels = 0;
		}
		if (isThrowingBarrels) {
			if (nextbarrelcd == 0 && nbbarrels < this.maxnbbarrels) {
				/*int toshootx = (int)(Math.random()*100)-50+player.getCenterX(); //TODO
				int toshooty= (int)(Math.random()*100)-50+player.getCenterY();*/
				int toshootx = (int)(Math.random()*100)-50+player.getCenterX() + (int)((Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedX()/9.f);
				int toshooty = (int)(Math.random()*100)-50+player.getCenterY() + (int)((Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedY()/9.f);
				float vectorX = (toshootx-centerX)/(float)(Math.abs(toshootx-centerX)+Math.abs(toshooty-centerY));
				float vectorY = (toshooty-centerY)/(float)(Math.abs(toshootx-centerX)+Math.abs(toshooty-centerY));
				if (Math.abs(toshootx-centerX) > Math.abs(toshooty-centerY)) {
					if (toshootx > centerX)
						currentSprite = streamright;
					else
						currentSprite = streamleft;
				} else {
					if (toshooty > centerY)
						currentSprite = streamdown;
					else
						currentSprite = streamup;
				}
				projectiles.add(new ReaperBarrelProjectile(centerX, centerY, vectorX, vectorY, 7, 2.0f, 40, 20, barrelrange));
				nextbarrelcd = barrelsrate;
				nbbarrels++;
				if (nbbarrels == maxnbbarrels) {
					isThrowingBarrels = false;
					barrelthrowingcd = barrelthrowingmaxcd;
					incrementWaitingTimes();
					currentSprite = staySprite;
				}
			}
		}
		if (streaming == 0 && blinking == 0) {
			if (notifytrap) {
				notifytrap = false;
				streamcd = Math.min(streamcd, 90);
				blinkcd = Math.min(blinkcd, 90);
			}
		}
		if (!isThrowingBarrels && streamingenabled && streamcd == 0 && streaming == 0 && blinking == 0 && Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY) < streamrange + 50) {
			streaming = streamingtime;
			stopMoving();
			streamr = 0;
			//if (!predictivestream) {
				streamtox = player.getCenterX();
				streamtoy = player.getCenterY();
			/*} else {
				streamtox = player.getCenterX() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedX()/12;
				streamtoy = player.getCenterY() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedY()/12;
			}*/
			if (Math.abs(streamtox-centerX) > Math.abs(streamtoy-centerY)) {
				if (streamtox > centerX)
					currentSprite = streamright;
				else
					currentSprite = streamleft;
			} else {
				if (streamtoy > centerY)
					currentSprite = streamdown;
				else
					currentSprite = streamup;
			}
		}
		if (streaming > 0) {
			if (streamr == 0) {
				if (StartingClass.difficultylevel > 1) {
					//if (!predictivestream) {
						streamtox = player.getCenterX();
						streamtoy = player.getCenterY();
					/*} else {
						streamtox = player.getCenterX() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedX()/12;
						streamtoy = player.getCenterY() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedY()/12;
					}*/
				}
				
				float dist = (float)(Math.sqrt(Math.abs(streamtox-centerX)*Math.abs(streamtox-centerX)+Math.abs(streamtoy-centerY)*Math.abs(streamtoy-centerY)));
				float vectorX = (streamtox-centerX)/dist;
				float vectorY = (streamtoy-centerY)/dist;
				projectiles.add(new FlamerFlame(centerX, centerY, vectorX, vectorY, 6+StartingClass.difficultylevel, streamdmg, streamrange));
				if (Math.abs(streamtox-centerX) > Math.abs(streamtoy-centerY)) {
					if (streamtox > centerX)
						currentSprite = streamright;
					else
						currentSprite = streamleft;
				} else {
					if (streamtoy > centerY)
						currentSprite = streamdown;
					else
						currentSprite = streamup;
				}
				streamr = streamrate;
			}
		}
		
		//int pathresult = 
	}
	/*
	private boolean isCorner(int x, int y) {
		return ((x == rightx && y == downy) || (x == rightx && y == upy) || (x == leftx && y == downy) || (x == leftx && y == upy));
	}*/
	
	private void incrementWaitingTimes() {
		streamcd += getNextWaitingTime();
		if (blinkenabled)
			blinkcd += getNextWaitingTime();
		if (barrelthrowingenabled)
			barrelthrowingcd += getNextWaitingTime();
	}
	
	private int getNextWaitingTime() {
		return (int)(Math.random()*(waitmax-waitmin)) + waitmin;
	}
	
	private int getFarestCorner(int x, int y) {
		int ans = 1;
		int dist = Math.abs(x-leftx)+Math.abs(y-upy);
		int tmpdist = Math.abs(x-rightx)+Math.abs(y-upy);
		if (tmpdist > dist) {
			dist = tmpdist;
			ans = 2;
		}
		tmpdist = Math.abs(x-rightx)+Math.abs(y-downy);
		if (tmpdist > dist) {
			dist = tmpdist;
			ans = 3;
		}
		tmpdist = Math.abs(x-leftx)+Math.abs(y-downy);
		if (tmpdist > dist) {
			dist = tmpdist;
			ans = 4;
		}
		return ans;
	}
	
	private void fireRing() {
		projectiles.add(new ReaperRingFlame(centerX,centerY,1.0f,0.0f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,1.0f,0.09f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.98f,0.17f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.96f,0.26f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.94f,0.34f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.91f,0.42f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.87f,0.5f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.82f,0.57f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.77f,0.64f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.71f,0.71f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.64f,0.77f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.57f,0.82f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.5f,0.87f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.42f,0.91f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.34f,0.94f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.26f,0.96f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.17f,0.98f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.09f,1.0f,10,fireringdmg,fireringrange));
		
		projectiles.add(new ReaperRingFlame(centerX,centerY,-1.0f,0.0f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-1.0f,0.09f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.98f,0.17f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.96f,0.26f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.94f,0.34f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.91f,0.42f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.87f,0.5f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.82f,0.57f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.77f,0.64f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.71f,0.71f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.64f,0.77f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.57f,0.82f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.5f,0.87f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.42f,0.91f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.34f,0.94f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.26f,0.96f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.17f,0.98f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.09f,1.0f,10,fireringdmg,fireringrange));
		
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.0f,1.0f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,1.0f,-0.09f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.98f,-0.17f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.96f,-0.26f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.94f,-0.34f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.91f,-0.42f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.87f,-0.5f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.82f,-0.57f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.77f,-0.64f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.71f,-0.71f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.64f,-0.77f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.57f,-0.82f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.5f,-0.87f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.42f,-0.91f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.34f,-0.94f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.26f,-0.96f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.17f,-0.98f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.09f,-1.0f,10,fireringdmg,fireringrange));
		
		projectiles.add(new ReaperRingFlame(centerX,centerY,0.0f,-1.0f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-1.0f,-0.09f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.98f,-0.17f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.96f,-0.26f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.94f,-0.34f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.91f,-0.42f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.87f,-0.5f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.82f,-0.57f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.77f,-0.64f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.71f,-0.71f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.64f,-0.77f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.57f,-0.82f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.5f,-0.87f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.42f,-0.91f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.34f,-0.94f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.26f,-0.96f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.17f,-0.98f,10,fireringdmg,fireringrange));
		projectiles.add(new ReaperRingFlame(centerX,centerY,-0.09f,-1.0f,10,fireringdmg,fireringrange));
	}
	
	@Override
	public void animate(){
	}
	
	@Override
	public void setStaySprite() {
		if (isOnFire)
			currentSprite = staySpriteOnFire;
		else
			currentSprite = staySprite;
	}

	@Override
	public void setMove1Sprite() {
		if (isOnFire)
			currentSprite = move1SpriteOnFire;
		else
			currentSprite = move1Sprite;
	}

	@Override
	public void setMove2Sprite() {
		if (isOnFire)
			currentSprite = move2SpriteOnFire;
		else
			currentSprite = move2Sprite;
	}

	@Override
	public void setDieSprite() {
		currentSprite = dieSprite;
		projectiles.clear();
		fireRing();
		for (int j :insidearena) {
			int jx = j / StartingClass.height;
			int jy = j % StartingClass.height;
			if (StartingClass.map[jx][jy] != null && Barrel.class.isInstance(StartingClass.map[jx][jy])) {
				((Barrel)StartingClass.map[jx][jy]).damage(10.0f);
			}
		}
		insidearena.clear();
	}
	
	@Override
	public void setStaySpriteAlt() {
		if (isOnFire)
			currentSprite = staySpriteOnFire;
		else
			currentSprite = staySprite;
	}

	@Override
	public void setMove1SpriteAlt() {
		if (isOnFire)
			currentSprite = move1SpriteOnFire;
		else
			currentSprite = move1Sprite;
	}

	@Override
	public void setMove2SpriteAlt() {
		if (isOnFire)
			currentSprite = move2SpriteOnFire;
		else
			currentSprite = move2Sprite;
	}
	
	@Override
	public void setGibsSprite() {
		projectiles.clear();
		currentSprite = dieSprite;
		fireRing();
		for (int j :insidearena) {
			int jx = j / StartingClass.height;
			int jy = j % StartingClass.height;
			if (StartingClass.map[jx][jy] != null && Barrel.class.isInstance(StartingClass.map[jx][jy])) {
				((Barrel)StartingClass.map[jx][jy]).damage(10.0f);
			}
		}
		insidearena.clear();
	}
	
	@Override
	public void die() {
		if (alive) {
			projectiles.clear();
			for (int i = 0; i < StartingClass.width; i++) {
				for (int j = 0; j < StartingClass.height; j++) {
					int h = 0;
					while (h<= StartingClass.heightitemmap[i][j]) {
						if (ReaperTrap.class.isInstance(StartingClass.items[i][j][h]) || ReaperBlinkingItem.class.isInstance(StartingClass.items[i][j][h]))
							StartingClass.removeItem(StartingClass.items[i][j][h]);
						else
							h++;
					}
				}
			}
			if (blinkingitem != null)
				StartingClass.removeItem(blinkingitem);
			blinkingitem = null;
			projectiles.add(new ReaperRingFlame(centerX,centerY,1.0f,0.0f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.96f,0.26f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.87f,0.5f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.71f,0.71f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.5f,0.87f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.26f,0.96f,3.3f,fireringdmg,150));
			
			projectiles.add(new ReaperRingFlame(centerX,centerY,-1.0f,0.0f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.96f,0.26f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.87f,0.5f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.71f,0.71f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.5f,0.87f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.26f,0.96f,3.3f,fireringdmg,150));
			
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.0f,1.0f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.96f,-0.26f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.87f,-0.5f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.71f,-0.71f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.5f,-0.87f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,0.26f,-0.96f,3.3f,fireringdmg,150));

			projectiles.add(new ReaperRingFlame(centerX,centerY,0.0f,-1.0f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.96f,-0.26f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.87f,-0.5f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.71f,-0.71f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.5f,-0.87f,3.3f,fireringdmg,150));
			projectiles.add(new ReaperRingFlame(centerX,centerY,-0.26f,-0.96f,3.3f,fireringdmg,150));
			
			insidearena = new ArrayList<Integer>();
			for (int j : StartingClass.arenainsidearea.get(StartingClass.isInArena)) {
				insidearena.add(j);
			}
		}
		super.die();
	}

	@Override
	public boolean hasIntermediateDying() {
		return true;
	}

	@Override
	public void setIntermediateDieSprite() {
		currentSprite = intermediateDieSprite;
		dying = 90;
	}
}
