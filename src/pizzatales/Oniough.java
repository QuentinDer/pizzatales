package pizzatales;

import java.awt.Image;

public class Oniough extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite,
	dashSpriteRight, dashSpriteLeft, slashSpriteLeft, slashSpriteRight;
	public static Image onioughStomp1, onioughStomp2;
	
	private int firecd;
	private int firemaxcd0, firemaxcd1, firemaxcd2;
	private int firingmode;
	private int firingmodecd;
	private int firingmodemaxcd;
	private int revivedGarlnsteinHealth;
	private int earthshaking;
	private int earthshakingcd;
	private int earthshakingmaxcd;
	private boolean earthshakingenabled;
	private int earthshakingduration;
	private int earthshakingtime;
	private int shaking;
	private boolean slowlaunched;
	
	public Oniough(int centerX, int centerY) {
		super(centerX, centerY, null, 60, 1, 50, 50, 45, 45);
		halfbarx = 45;
		firingmodemaxcd = 300;
		movementTime = ((int) (Math.random() * 50));
		switch(StartingClass.difficultylevel) {
		case 1:
			firemaxcd0 = 60;
			firemaxcd1 = 35;
			firemaxcd2 = 120;
			earthshakingmaxcd = 3600;
			earthshakingduration = 180;
			earthshakingtime = 180;
			revivedGarlnsteinHealth = 4;
			break;
		case 2:
			firemaxcd0 = 55;
			firemaxcd1 = 30;
			firemaxcd2 = 110;
			earthshakingmaxcd = 3000;
			earthshakingduration = 240;
			earthshakingtime = 160;
			revivedGarlnsteinHealth = 8;
			break;
		case 3:
			firemaxcd0 = 50;
			firemaxcd1 = 25;
			firemaxcd2 = 100;
			earthshakingmaxcd = 2400;
			earthshakingduration = 300;
			earthshakingtime = 140;
			revivedGarlnsteinHealth = 12;
			break;
		case 4:
			firemaxcd0 = 45;
			firemaxcd1 = 20;
			firemaxcd2 = 90;
			earthshakingmaxcd = 1800;
			earthshakingduration = 360;
			earthshakingtime = 120;
			revivedGarlnsteinHealth = 16;
			break;
		}
		firingmodecd = firingmodemaxcd;
	}

	@Override
	public void callAI() {
		
		if (firingmodecd > 0)
			firingmodecd--;
		if (firecd > 0)
			firecd--;
		if (earthshakingcd > 0)
			earthshakingcd--;
		if (earthshaking > 0) {
			earthshaking--;
			if (earthshaking == 0) {
				halfsizex = 50;
				currentSprite = staySprite;
			} else {
				switch(earthshaking*3/earthshakingtime) {
				case 0:
					halfsizex = 75;
					currentSprite = onioughStomp2;
					if (!slowlaunched) {
						slowlaunched = true;
						shaking = earthshakingduration;
						StartingClass.leavingitems.add(new FakeItemForSlow(earthshakingduration,1));
					}
					break;
				case 1:
					currentSprite = onioughStomp1;
					break;
				case 2:
					slowlaunched = false;
					break;
				}
			}
		}
		if (shaking > 0) {
			shaking--;
			if (shaking == 0) {
				player.setScrollingSpeedY(0);
			} else {
				if (movementTime % 10 < 5)
					player.setScrollingSpeedY(4);
				else
					player.setScrollingSpeedY(-4);
			}
		}
		if (earthshaking == 0 && movementTime % 3 == 0) {
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
			int pathresult = pf.getDirection(posx, posy, player.posx, player.posy, 200, canmoveleft, canmoveup, canmoveright, canmovedown, dirplace, false);
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
		if (!earthshakingenabled) {
			boolean test = false;
			for (Enemy e : StartingClass.arenaenemies.get(StartingClass.isInArena)) {
				if (Garlnstein.class.isInstance(e) && !((Garlnstein)e).fake && e.health < e.maxHealth*0.7f) {
					test = true;
				}
			}
			if (test || health < maxHealth*0.7f)
				earthshakingenabled = true;
		}
		if (earthshakingenabled && earthshakingcd == 0) {
			stopMoving();
			earthshaking = earthshakingtime;
			earthshakingcd = earthshakingmaxcd;
		}
		if (firingmodecd == 0) {
			firingmode = (firingmode+1)%3;
			firingmodecd = firingmodemaxcd;
		}
		if (firecd == 0 && earthshaking == 0) {
			float diffx = player.getCenterX() - getCenterX();
			float diffy = player.getCenterY() - getCenterY();
			float dist = (float)Math.sqrt(Math.abs(diffx)*Math.abs(diffx)+Math.abs(diffy)*Math.abs(diffy));
			float vectorx = diffx / dist;
			float vectory = diffy / dist;
			switch(firingmode) {
			case 0:
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory,10,500));
				firecd = firemaxcd0;
				break;
			case 1:
				diffx = player.getCenterX() - getCenterX();
				diffy = player.getCenterY() - getCenterY();
				dist = (float)Math.sqrt(Math.abs(diffx)*Math.abs(diffx)+Math.abs(diffy)*Math.abs(diffy));
				vectorx = diffx / dist;
				vectory = diffy / dist;
				double angle = Math.toDegrees(Math.acos(vectorx));
				if (vectory < 0)
					angle = 360-angle;
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory,10,300));
				projectiles.add(new OnioughProjectile(centerX,centerY,(float)(Math.cos(Math.toRadians(angle+10))),(float)(Math.sin(Math.toRadians(angle+10))),10,300));
				projectiles.add(new OnioughProjectile(centerX,centerY,(float)(Math.cos(Math.toRadians(angle-10))),(float)(Math.sin(Math.toRadians(angle-10))),10,300));
				firecd = firemaxcd1;
				break;
			case 2:
				diffx = player.getCenterX() + dist*player.getSpeedX()/(5.f+StartingClass.difficultylevel) - getCenterX();
				diffy = player.getCenterY() + dist*player.getSpeedY()/(5.f+StartingClass.difficultylevel) - getCenterY();
				dist = (float)Math.sqrt(Math.abs(diffx)*Math.abs(diffx)+Math.abs(diffy)*Math.abs(diffy));
				vectorx = diffx / dist;
				vectory = diffy / dist;
				OnioughProjectile center = new OnioughProjectile(centerX,centerY,vectorx,vectory,5+StartingClass.difficultylevel,1000);
				projectiles.add(center);
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory,5+StartingClass.difficultylevel,0,15+7*StartingClass.difficultylevel));
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory,5+StartingClass.difficultylevel,60,15+7*StartingClass.difficultylevel));
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory,5+StartingClass.difficultylevel,120,15+7*StartingClass.difficultylevel));
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory,5+StartingClass.difficultylevel,180,15+7*StartingClass.difficultylevel));
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory,5+StartingClass.difficultylevel,240,15+7*StartingClass.difficultylevel));
				projectiles.add(new OnioughProjectile(centerX,centerY,vectorx,vectory,5+StartingClass.difficultylevel,300,15+7*StartingClass.difficultylevel));
				firecd = firemaxcd2;
				break;
			}
		}
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
	
	@Override
	public void die() {
		super.die();
		for (Enemy e : StartingClass.enemyarray) {
			if (Garlnstein.class.isInstance(e) && !e.alive) {
				e.alive = true;
				e.setHealth(revivedGarlnsteinHealth);
				((Garlnstein)e).dashcd = (int)(Math.random()*((Garlnstein)e).dashmaxcd);
			}
		}
	}
	
	@Override
	public void animate(){
		if (earthshaking == 0 && isMoving) {
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

}
