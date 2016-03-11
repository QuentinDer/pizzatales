package pizzatales;

import java.awt.Image;

public class CarolinaReaper extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite, staySpriteRight, 
	move1SpriteRight, move2SpriteRight, firering, streamleft, streamup, streamright, streamdown;
	private int waitmin, waitmax;
	private int streamingtime, streamrate, blinkingtime;
	private float streamdmg;
	private int streamcd, blinkcd;
	private int streaming, blinking;
	private int streamrange = 500;
	private int streamr = 0;
	private int streamtox, streamtoy;
	private boolean blinkenabled;
	private int leftx, rightx, upy, downy;
	
	public CarolinaReaper(int centerX, int centerY) {
		super(centerX, centerY, null, 100, 3, 31, 31, 25, 25);
		movementTime = ((int) (Math.random() * 50));
		streamrate = 4;
		switch (StartingClass.difficultylevel) {
		case 1:
			waitmin = 120;
			waitmax = 240;
			streamingtime = 120;
			streamdmg = 0.3f;
			break;
		case 2:
			waitmin = 100;
			waitmax = 220;
			streamingtime = 100;
			streamdmg = 0.6f;
			break;
		case 3:
			waitmin = 80;
			waitmax = 200;
			streamingtime = 80;
			streamdmg = 0.9f;
			break;
		case 4:
			waitmin = 60;
			waitmax = 180;
			streamingtime = 60;
			streamdmg = 1.2f;
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
				streamcd = getNextWaitingTime();
				//TODO add other cds
			}
		}
		if (streaming == 0 && streamcd > 0)
			streamcd--;
		if (streamr > 0)
			streamr--;
		if (streamcd == 0 && streaming == 0 && movementTime % 3 == 0) {
			StartingClass.map[player.posx][player.posy] = null;
			int dirplace = 0;
			int difPX = 50*posx+25+bg.getCenterX()-StartingClass.bginitx - centerX;
			int difPY = 50*posy+40+bg.getCenterY()-StartingClass.bginity - centerY;
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
		} else if (streaming == 0 && movementTime % 3 == 0) {
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
			int difPY = 50*posy+40+bg.getCenterY()-StartingClass.bginity - centerY;
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
		if (streamcd == 0 && streaming == 0 && Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY) < streamrange + 50) {
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
				if (StartingClass.difficultylevel > 2) {
					//if (!predictivestream) {
						streamtox = player.getCenterX();
						streamtoy = player.getCenterY();
					/*} else {
						streamtox = player.getCenterX() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedX()/12;
						streamtoy = player.getCenterY() + (Math.abs(player.getCenterX()-centerX)+Math.abs(player.getCenterY()-centerY))*player.getSpeedY()/12;
					}*/
				}
				float vectorX = (streamtox-centerX)/(float)(Math.abs(streamtox-centerX)+Math.abs(streamtoy-centerY));
				float vectorY = (streamtoy-centerY)/(float)(Math.abs(streamtox-centerX)+Math.abs(streamtoy-centerY));
				projectiles.add(new FlamerFlame(centerX, centerY, vectorX, vectorY, 10, streamdmg, streamrange));
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
	
	private boolean isCorner(int x, int y) {
		return ((x == rightx && y == downy) || (x == rightx && y == upy) || (x == leftx && y == downy) || (x == leftx && y == upy));
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
	
	@Override
	public void setStaySprite() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove1Sprite() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove2Sprite() {
		currentSprite = staySprite;
	}

	@Override
	public void setDieSprite() {
		currentSprite = dieSprite;
	}
	
	@Override
	public void setStaySpriteAlt() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove1SpriteAlt() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove2SpriteAlt() {
		currentSprite = staySprite;
	}
	
	@Override
	public void setGibsSprite() {
		currentSprite = dieSprite;
	}
	
	@Override
	public void die() {
		super.die();
		projectiles.clear();
	}
}
