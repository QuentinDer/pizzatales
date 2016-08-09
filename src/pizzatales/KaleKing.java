package pizzatales;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public class KaleKing extends Enemy {

	private static final boolean TRICK = false;
	//private static float basicspeed = 2.5f;
	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	public static Image swipeDown, swipeRight, swipeLeft, swipeUp;
	public static Image boltsfiringsprite, dashSpriteRight, dashSpriteLeft;
	public static Image blinkingSprite;
	public static Image hulkSprite, hulkMove1, hulkMove2, hulkSwipeDown, hulkSwipeRight, hulkSwipeLeft, hulkSwipeUp, intermediateDieSprite;
	public static ArrayList<Image> phaseanim = new ArrayList<Image>();
	//public static Image phase1, phase2, phase3, phase4, phase5, phase6, phase7, phase8, phase9, phase10, phase11;
	private boolean darktrail/* = true*/;
	private boolean widerice;
	private int healicechange;
	private int slashdmg;
	private int phase = 1;
	private boolean isSlashing;
	private int slashDirection;
	private int slashCd;
	private boolean slashEnabled;
	private boolean hasSlashed;
	private int inAnimation;
	private int maxInAnimation;
	private int frozenduration;
	private float iceboltdmg;
	private int iceboltrange, iceboltspeed;
	private int waitmin, waitmax;
	private int boltscd, boltsmaxcd;
	private boolean boltsenabled;
	private int boltsfiring, boltsfiringduration;
	private int boltsfirerate, boltscirclerange, boltsbasefirerate;
	private int phasegroundrate, phaseanimrate, phaseanimmaxrate;
	private int darkiceprisoncd, darkiceprisonmaxcd;
	private int darkiceprisonrate, darkiceprisoncircle, darkiceprison;
	private int darkiceprisoncx, darkiceprisoncy;//, darkiceprisonfx, darkiceprisonfy;
	private boolean darkiceprisonenabled;
	//private StartingClass applet;
	private ArrayList<Integer> ring;
	private ArrayList<Integer> possibledarkicecenters;
	private boolean isAnimating = false;
	private int animcounter;
	private int animsprite;
	private ArrayList<Integer> todiscover = new ArrayList<Integer>();
	private ArrayList<Integer> nonobstacles = new ArrayList<Integer>();
	private int darkicer, darkiced;
	private float darkicedmg;
	private int[][] circlex;
	private int[][] circley;
	private int[] circles;
	private int idarkcircle;
	private boolean isDashing;
	private int dashspeed;
	private float dashSpeedX;
	private int dashcd, dashmaxcd, dashdmg;
	private boolean dashenabled;
	private int iceboltstyle;
	private boolean hulkenabled;
	private int hulkcd, hulkmaxcd, hulkduration, hulking;
	private float hulkspeed;
	public boolean swapdemand;
	public boolean swapped;
	private boolean tornadoenabled;
	private int ballcd, ballmaxcd, ballrange;
	private float balldmg;
	private KaleKingBlinkingItem[] blinkingpos;
	private boolean blinkingenabled;
	private int blinkingcd, blinkingmaxcd, blinking, blinkingduration, blinkingnumber, blinkingfollowingduration;
	private int fireringrange;
	private float fireringdmg;
	
	public KaleKing(int centerX, int centerY, StartingClass applet) {
		super(centerX, centerY, null, 300, 2.5f, 63, 50,
				48, 45);
		circles = new int[6];
		circlex = new int[6][];
		circley = new int[6][];
		circlex[0] = new int[]{0};
		circley[0] = new int[]{0};
		circles[0] = 1;
		circlex[1] = new int[]{-1,-1,-1,0,0,1,1,1};
		circley[1] = new int[]{-1,0,1,-1,1,-1,0,1};
		circles[1] = 8;
		circlex[2] = new int[]{-2,-2,-2,-1,-1,0,0,1,1,2,2,2};
		circley[2] = new int[]{-1,0,1,-2,2,-2,2,-2,2,-1,0,1};
		circles[2] = 12;
		circlex[3] = new int[]{-3,-3,-3,-2,-2,-1,-1,0,0,1,1,2,2,3,3,3};
		circley[3] = new int[]{-1,0,1,-2,2,-3,3,-3,3,-3,3,-2,2,-1,0,1};
		circles[3] = 16;
		circlex[4] = new int[]{-4,-4,-4,-4,-4,-3,-3,-3,-3,-2,-2,-2,-2,-1,-1,0,0,1,1,2,2,2,2,3,3,3,3,4,4,4,4,4};
		circley[4] = new int[]{-2,-1,0,1,2,-3,-2,2,3,-4,-3,3,4,-4,4,-4,4,-4,4,-4,-3,3,4,-3,-2,2,3,-2,-1,0,1,2};
		circles[4] = 32;
		circlex[5] = new int[]{-5,-5,-5,-5,-5,-5,-5,-4,-4,-4,-4,-3,-3,-3,-3,-2,-2,-1,-1,0,0,1,1,2,2,3,3,3,3,4,4,4,4,5,5,5,5,5,5,5};
		circley[5] = new int[]{-3,-2,-1,0,1,2,3,-4,-3,3,4,-5,-4,4,5,-5,5,-5,5,-5,5,-5,5,-5,5,-5,-4,4,5,-4,-3,3,4,-3,-2,-1,0,1,2,3};
		circles[5] = 40;
		halfbarx = 45;
		iceboltdmg = 1;
		iceboltrange = 1000;
		iceboltspeed = 10;
		boltsfiringduration = 120;
		//this.applet = applet;
		phasegroundrate = 10;
		phaseanimmaxrate = 7;
		darkicedmg = 3.f;
		darkiced = 50;
		dashdmg = 5;
		balldmg = 0.5f;
		slashdmg = 5;
		StartingClass.maskphase = 1;
		switch (StartingClass.difficultylevel) {
		case 1:
			waitmin = 120;
			waitmax = 240;
			boltsmaxcd = 120;
			frozenduration = 60;
			healicechange = 20;
			maxInAnimation = 60;
			boltsbasefirerate = 30;
			boltscirclerange = 0;
			darkiceprisonmaxcd = 600;
			darkiceprisonrate = 25;
			dashmaxcd = 360;
			dashspeed = 6;
			idarkcircle = 2;
			darkicer = 90;
			hulkmaxcd = 1200;
			hulkduration = 300;
			hulkspeed = 3.0f;
			ballmaxcd = 25;
			ballrange = 1500;
			blinkingduration = 120;
			blinkingfollowingduration = 30;
			blinkingmaxcd = 400;
			fireringrange = 250;
			fireringdmg = 0.6f;
			break;
		case 2:
			waitmin = 100;
			waitmax = 220;
			boltsmaxcd = 120;
			frozenduration = 70;
			healicechange = 30;
			maxInAnimation = 50;
			boltsbasefirerate = 25;
			boltscirclerange = 75;
			darkiceprisonmaxcd = 500;
			darkiceprisonrate = 20;
			dashmaxcd = 300;
			dashspeed = 7;
			idarkcircle = 3;
			darkicer = 80;
			hulkmaxcd = 1000;
			hulkduration = 360;
			hulkspeed = 3.5f;
			ballmaxcd = 20;
			ballrange = 2000;
			blinkingduration = 100;
			blinkingfollowingduration = 25;
			blinkingmaxcd = 400;
			fireringrange = 300;
			fireringdmg = 0.8f;
			break;
		case 3:
			waitmin = 80;
			waitmax = 200;
			boltsmaxcd = 120;
			frozenduration = 80;
			healicechange = 40;
			maxInAnimation = 40;
			boltsbasefirerate = 20;
			boltscirclerange = 150;
			darkiceprisonmaxcd = 400;
			darkiceprisonrate = 15;
			dashmaxcd = 240;
			dashspeed = 8;
			idarkcircle = 4;
			darkicer = 70;
			hulkmaxcd = 800;
			hulkduration = 420;
			hulkspeed = 4.0f;
			ballmaxcd = 15;
			ballrange = 2500;
			blinkingduration = 80;
			blinkingfollowingduration = 20;
			blinkingmaxcd = 400;
			fireringrange = 350;
			fireringdmg = 1.0f;
			break;
		case 4:
			waitmin = 60;
			waitmax = 180;
			boltsmaxcd = 120;
			frozenduration = 90;
			healicechange = 50;
			maxInAnimation = 30;
			boltsbasefirerate = 15;
			boltscirclerange = 225;
			darkiceprisonmaxcd = 300;
			darkiceprisonrate = 10;
			dashmaxcd = 180;
			dashspeed = 9;
			idarkcircle = 5;
			darkicer = 60;
			hulkmaxcd = 600;
			hulkduration = 500;
			hulkspeed = 4.5f;
			ballmaxcd = 10;
			ballrange = 3000;
			blinkingduration = 60;
			blinkingfollowingduration = 15;
			blinkingmaxcd = 400;
			fireringrange = 400;
			fireringdmg = 1.2f;
			break;
		}
		blinkingpos = new KaleKingBlinkingItem[StartingClass.difficultylevel];
		boltsenabled = true;
		boltscd = 180;
		darkiceprisoncd = 300;
		darkiceprisonenabled = true;
		slashEnabled = true;
	}

	@Override
	public void callAI() {
		if (isAnimating) {
			boolean test;
			int size;
			int j;
			if (animcounter % phasegroundrate == 0) {
				StartingClass.remask = true;
				j = 0;
				size = todiscover.size();
				StartingClass.map[player.posx][player.posy] = null;
				while (j < size) {
					int current = todiscover.get(0);
					int xj = current / StartingClass.height;
					int yj = current % StartingClass.height;
					if (ring.contains(current)) {
						boolean tt = false;
						for (int h = 0; h <= StartingClass.heightitemmap[xj][yj]; h++) {
							if (SummonedIce.class.isInstance(StartingClass.items[xj][yj][h])) {
								((SummonedIce)StartingClass.items[xj][yj][h]).previousbackground = this.getOutsideRingSprite(xj,yj,phase);
								tt = true;
							}
						}
						if (!tt)
							StartingClass.backgroundmap[xj][yj] = this.getOutsideRingSprite(xj,yj,phase);
						if (!nonobstacles.contains(current-StartingClass.height) && !todiscover.contains(current-StartingClass.height) && 0 != xj) {
							if (ring.contains(current-StartingClass.height)) {
								todiscover.add(current-StartingClass.height);
							}
						}
						if (!nonobstacles.contains(current+StartingClass.height) && !todiscover.contains(current+StartingClass.height) && StartingClass.width -1 != xj) {
							if (ring.contains(current+StartingClass.height)) {
								todiscover.add(current+StartingClass.height);
							}
						}
						if (!nonobstacles.contains(current-1) && !todiscover.contains(current-1) && 0 != yj) {
							if (ring.contains(current-1)) {
								todiscover.add(current-1);
							}
						}
						if (!nonobstacles.contains(current+1) && !todiscover.contains(current+1) && StartingClass.height-1 != yj) {
							if (ring.contains(current+1)) {
								todiscover.add(current+1);
							}
						}
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
						if (!nonobstacles.contains(current-StartingClass.height) && !todiscover.contains(current-StartingClass.height) && 0 != xj) {
							todiscover.add(current-StartingClass.height);
						}
						if (!nonobstacles.contains(current+StartingClass.height) && !todiscover.contains(current+StartingClass.height) && StartingClass.width -1 != xj) {
							todiscover.add(current+StartingClass.height);
						}
						if (!nonobstacles.contains(current-1) && !todiscover.contains(current-1) && 0 != yj) {
							todiscover.add(current-1);
						}
						if (!nonobstacles.contains(current+1) && !todiscover.contains(current+1) && StartingClass.height-1 != yj) {
							todiscover.add(current+1);
						}
					}
					
					todiscover.remove(0);
					nonobstacles.add(current);
					j++;
				}
				StartingClass.map[player.posx][player.posy] = player;
				if (StartingClass.difficultylevel<4) {
					if (player.getArmor().defense + 1 < player.getArmor().MAXDEF) {
						player.getArmor().setDefense(player.getArmor().getDefense()+1);
					} else
						player.getArmor().setDefense(player.getArmor().MAXDEF);
					if (player.getHealth() + 1 > player.getMaxHealth()) {
						player.setHealth(player.getMaxHealth());
					} else {
						player.setHealth(player.getHealth() + 1);
					} 
				}
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
				StartingClass.maskphase++;
				paintoverride = false;
				if (TRICK)
					health = maxHealth;
				incrementWaitingTimes();
			}
		} else {
			if (swapped)
				swapped = false;
			if (swapdemand) {
				int tmpcenterX = player.getCenterX();
				int tmpcenterY = player.getCenterY();
				player.setCenterX(getCenterX());
				player.setCenterY(getCenterY());
				setCenterX(tmpcenterX);
				setCenterY(tmpcenterY);
				swapdemand = false;
				swapped = true;
			}
			if (ring == null) {
				ring = new ArrayList<Integer>();
				StartingClass.map[posx][posy] = null;
				StartingClass.map[player.posx][player.posy] = null;
				MapUtil.getRing(posx, posy, 500, ring);
				possibledarkicecenters = new ArrayList<Integer>();
				MapUtil.getPossibleDarkCenters(posx,posy,500,idarkcircle,ring,possibledarkicecenters);
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
			if (blinkingcd > 0)
				blinkingcd--;
			if (blinking > 0) {
				blinking--;
				boolean canblink = false;
				for (int i = 0; i < blinkingnumber+1; i++) {
					canblink = canblink || blinkingpos[i].canblink;
				}
				if (canblink) {
					if (movementTime % 6 < 3)
						moveLeft();
					else
						moveRight();
					if (blinking == 0) {
						while (!blinkingpos[blinkingnumber].canblink)
							blinkingnumber--;
						KaleKingBlinkingItem blinkingitem = blinkingpos[blinkingnumber];
						StartingClass.removeItem(blinkingitem);
						if (StartingClass.map[blinkingitem.posx][blinkingitem.posy] == null) {
							StartingClass.map[posx][posy] = null;
							setCenterX(50*blinkingitem.posx+25+bg.getCenterX()-StartingClass.bginitx);
							setCenterY(50*blinkingitem.posy+25+bg.getCenterY()-StartingClass.bginity);
							blinkingitem.r.setBounds(blinkingitem.getCenterX() - 25, blinkingitem.getCenterY() - 25, 50, 50);
							StartingClass.map[blinkingitem.posx][blinkingitem.posy] = this;
						}
						fireRing();
						//TODO
						if (blinkingnumber == 0) {
							blinkingcd = blinkingmaxcd;
							incrementWaitingTimes();
							currentSprite = staySprite;
						} else {
							blinkingnumber--;
							blinking = this.blinkingfollowingduration;
						}
					}
				} else {
					blinking = 0;
					blinkingcd = blinkingmaxcd;
					incrementWaitingTimes();
					currentSprite = staySprite;
				}
			}
			if (hulkcd > 0)
				hulkcd--;
			if (hulking > 0) {
				hulking--;
				if (hulking == 0) {
					if (inAnimation == 0)
						currentSprite = staySprite;
					setBehaviour();
					hulkcd = hulkmaxcd;
					incrementWaitingTimes();
				}
			}
			if (dashcd > 0)
				dashcd--;
			if (slashCd > 0)
				slashCd--;
			if (boltscd > 0)
				boltscd--;
			if (boltsfiring > 0) {
				boltsfiring--;
				if (boltsfiring == 0) {
					currentSprite = staySprite;
					boltscd = boltsmaxcd;
					incrementWaitingTimes();
				} else if (boltsfiring % boltsfirerate == 0){
					float dist = (float)(Math.sqrt(Math.abs(player.getCenterX() - getCenterX())*Math.abs(player.getCenterX() - getCenterX())
							+Math.abs(player.getCenterY() - getCenterY())*Math.abs(player.getCenterY() - getCenterY())));
					float diffx = player.getCenterX() - getCenterX() - 54;
					float diffy = player.getCenterY() - getCenterY() + 27;
					float ndiffx = diffx;
					float ndiffy = diffy;
					int brange = iceboltrange;
					switch (iceboltstyle) {
					case 1:
						ndiffx = diffx + (float)(Math.random()*boltscirclerange) - boltscirclerange/2;
						ndiffy = diffy  + (float)(Math.random()*boltscirclerange) - boltscirclerange/2;
						break;
					case 2:
						ndiffx = diffx + dist*player.getSpeedX()/iceboltspeed;
						ndiffy = diffy  + dist*player.getSpeedY()/iceboltspeed;
						break;
					case 5:
						ndiffx = diffx + (float)(Math.random()*boltscirclerange) - boltscirclerange/2;
						ndiffy = diffy  + (float)(Math.random()*boltscirclerange) - boltscirclerange/2;
						brange = (int)(Math.random()*400)+400;
						break;
					}
					dist = (float)(Math.sqrt(Math.abs(ndiffx)*Math.abs(ndiffx)+Math.abs(ndiffy)*Math.abs(ndiffy)));
					float vectorx = ndiffx / dist;
					float vectory = ndiffy / dist;
					projectiles.add(new IceBolt(centerX+54,centerY-27,vectorx,vectory,iceboltspeed,iceboltdmg, brange, frozenduration, iceboltstyle, this));
				}
			}
			if (darkiceprisoncd > 0)
				darkiceprisoncd--;
			if (darkiceprison > 0) {
				darkiceprison--;
				if (darkiceprison == 0) {
					int nodarkicesize = (darkiceprisoncircle*3)+1;
					int[] nodarkicex = new int[nodarkicesize];
					int[] nodarkicey = new int[nodarkicesize];
					int[] nodarkpos = new int[nodarkicesize];
					Arrays.fill(nodarkpos, -1);
					
					for (int i = 0; i < nodarkicesize; i++) {
						boolean test = true;
						int index = 0;
						while (test) {
							index = (int)(Math.random()*circles[darkiceprisoncircle]);
							test = false;
							int j = 0;
							while (!test && j < nodarkicesize) {
								test = index == nodarkpos[j];
								j++;
							}
						}
						nodarkicex[i] = circlex[darkiceprisoncircle][index];
						nodarkicey[i] = circley[darkiceprisoncircle][index];
						nodarkpos[i] = index;
					}
					
					for (int i = 0; i < circles[darkiceprisoncircle]; i++) {
						int x = circlex[darkiceprisoncircle][i];
						int y = circley[darkiceprisoncircle][i];
						boolean test = true;
						int j = 0;
						while (test && j < nodarkicesize) {
							test = (x != nodarkicex[j] || y != nodarkicey[j]);
							j++;
						}
						if (test) {
							int dx = darkiceprisoncx + x;
							int dy = darkiceprisoncy + y;
							DarkIce darktrailice = new DarkIce(dx,dy,darkicer,darkiced,darkicedmg, frozenduration);
							darktrailice.setCenterX(50*dx+25+bg.getCenterX()-StartingClass.bginitx+(int)(Math.random()*20)-10);
							darktrailice.setCenterY(50*dy+25+bg.getCenterY()-StartingClass.bginity+(int)(Math.random()*20)-10);
							StartingClass.leavingitems.add(darktrailice);
						}
					}
					if (darkiceprisoncircle != 0) {
						darkiceprisoncircle--;
						darkiceprison = darkiceprisonrate;
					} else {
						currentSprite = staySprite;
						darkiceprisoncd = darkiceprisonmaxcd;
						incrementWaitingTimes();
					}
				}
			}
			if (inAnimation > 0) {
				inAnimation--;
				if (inAnimation == 0) {
					halfsizex = 68;
					halfsizey = 50;
					halfrsizex = 48;
					halfrsizey = 45;
					if (hulking == 0)
						currentSprite = staySprite;
					else
						currentSprite = hulkSprite;
					isSlashing = false;
					slashCd = 30;
					isDashing = false;
					initSpeed();
					showHealthBar = true;
					paintoverride = false;
				}
			}
			if (dashenabled && inAnimation == 0 && boltsfiring == 0 && darkiceprison == 0 && dashcd == 0 && blinking == 0 && hulking == 0 && Math.abs(player.getCenterX() - getCenterX()) > 120 && Math.abs(player.posy-posy)<=1) {
				boolean candash = true;
				if (player.getCenterX() > getCenterX()) {
					for (int i = posx+1; i < player.posx; i++)
						candash = candash && (StartingClass.map[i][posy] == null);
					if (candash) {
						currentSprite = dashSpriteRight;
						moveRight();
						dashSpeedX = dashspeed;
					}
				} else {
					for (int i = posx-1; i > player.posx; i--)
						candash = candash && (StartingClass.map[i][posy] == null);
					if (candash) {
						currentSprite = dashSpriteLeft;
						moveLeft();
						dashSpeedX = -dashspeed;
					}
				}
				if (candash) {
					speed = dashspeed;
					halfsizex = 110;
					halfsizey = 62;
					halfrsizex = 100;
					halfrsizey = 50;
					isDashing = true;
					paintoverride = true;
					showHealthBar = false;
					inAnimation = 50;
					dashcd = dashmaxcd;
					incrementWaitingTimes();
					R.setBounds((int)(centerX - halfrsizex + speedX), (int)(centerY - halfrsizey + speedY), 2*halfrsizex, 2*halfrsizey);
				}
			}
			if (inAnimation == 0 && boltsfiring == 0 && darkiceprison == 0 && hulking == 0 && blinking == 0) {
				if (TRICK) {
					if (health != maxHealth) {
						this.startPhaseAnimation();
						return;
					}
				} else {
					if (phase != 6 && health < maxHealth*(6-phase)/6) {
						this.startPhaseAnimation();
						return;
					}
				}
			}
			if (ballcd > 0)
				ballcd--;
			if (tornadoenabled && ballcd == 0) {
				projectiles.add(new KaleKingBall(centerX+54,centerY-17,0.f,0.f,balldmg,ballrange,this));
				ballcd = ballmaxcd;
			}
			if (inAnimation == 0 && boltsfiring == 0 && darkiceprison == 0 && blinking == 0) {
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
				if (slashEnabled && boltsfiring == 0 && darkiceprison == 0 && blinking == 0) {
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
							if (hulking == 0)
								currentSprite = swipeDown;
							else
								currentSprite = hulkSwipeDown;
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
							if (hulking == 0)
								currentSprite = swipeUp;
							else
								currentSprite = hulkSwipeUp;
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
							if (hulking == 0)
								currentSprite = swipeRight;
							else
								currentSprite = hulkSwipeRight;
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
							if (hulking == 0)
								currentSprite = swipeLeft;
							else
								currentSprite = hulkSwipeLeft;
							slashDirection = 1;
						}
					}
				}
				if (inAnimation == 0 && darkiceprisonenabled && darkiceprisoncd == 0 && boltsfiring == 0 && darkiceprison == 0 && blinking == 0) {
					stopMoving();
					currentSprite = boltsfiringsprite;
					darkiceprison = darkiceprisonrate;
					darkiceprisoncircle = idarkcircle;
					
					if (!possibledarkicecenters.contains(player.posx * StartingClass.height + player.posy)) {
						int dist = Integer.MAX_VALUE;
						int tmp;
						for (int k : possibledarkicecenters) {
							int xk = k / StartingClass.height;
							int yk = k % StartingClass.height;
							tmp = Math.abs(xk-player.posx)+Math.abs(yk-player.posy);
							if (tmp < dist) {
								dist = tmp;
								darkiceprisoncx = xk;
								darkiceprisoncy = yk;
							}
						}
					} else {
						darkiceprisoncx = player.posx;
						darkiceprisoncy = player.posy;
					}
				}
				if (inAnimation == 0 && boltsenabled && boltscd == 0 && boltsfiring == 0 && darkiceprison == 0 && blinking == 0) {
					stopMoving();
					initBoltStyle();
					currentSprite = boltsfiringsprite;
					boltsfiring = boltsfiringduration;
				}
				if (inAnimation == 0 && hulkenabled && hulkcd == 0 && hulking == 0 && boltsfiring == 0 && darkiceprison == 0  && blinking == 0) {
					stopMoving();
					boltsenabled = false;
					darkiceprisonenabled = false;
					dashenabled = false;
					blinkingenabled = false;
					setDefaultSpeed(hulkspeed);
					speed = hulkspeed;
					currentSprite = hulkSprite;
					hulking = hulkduration;
				}
				if (inAnimation == 0 && blinkingenabled && blinkingcd == 0 && hulking == 0 && boltsfiring == 0 && darkiceprison == 0  && blinking == 0) {
					stopMoving();
					currentSprite = blinkingSprite;
					int tmpx; 
					int tmpy;
					ArrayList<Integer> possibleblinkpositions = new ArrayList<Integer>();
					for (int i : StartingClass.arenainsidearea.get(StartingClass.isInArena)) {
						tmpx = i / StartingClass.height;
						tmpy = i % StartingClass.height;
						int dist = Math.abs(player.posx-tmpx)+Math.abs(player.posy-tmpy);
						if ((tmpx != player.posx || tmpy != player.posy) && dist<6 && dist>1 && StartingClass.map[tmpx][tmpy] == null) {
							possibleblinkpositions.add(i);
						}
					}
					int j = 0;
					blinkingnumber = -1;
					while (j < StartingClass.difficultylevel && !possibleblinkpositions.isEmpty()) {
						blinking = blinkingduration;
						int randpos = (int)(Math.random() * possibleblinkpositions.size());
						int blinkposx = possibleblinkpositions.get(randpos) / StartingClass.height;
						int blinkposy = possibleblinkpositions.get(randpos) % StartingClass.height;
						StartingClass.heightitemmap[blinkposx][blinkposy]++;
						StartingClass.blockmaxheight = Math.max(StartingClass.blockmaxheight, StartingClass.heightitemmap[blinkposx][blinkposy]);
						
						KaleKingBlinkingItem blinkingitem = new KaleKingBlinkingItem(blinkposx,blinkposy,0,0,true,StartingClass.heightitemmap[blinkposx][blinkposy]);
						blinkingitem.setCenterX(50*blinkposx+25+bg.getCenterX()-StartingClass.bginitx);
						blinkingitem.setCenterY(50*blinkposy+25+bg.getCenterY()-StartingClass.bginity);
						blinkingitem.r.setBounds(blinkingitem.getCenterX() - 22, blinkingitem.getCenterY() - 22, 45, 45);
						StartingClass.items[blinkposx][blinkposy][StartingClass.heightitemmap[blinkposx][blinkposy]] = blinkingitem;
						possibleblinkpositions.remove(randpos);
						blinkingpos[j] = blinkingitem;
						blinkingnumber++;
						j++;
					}
					possibleblinkpositions.clear();
				}
			}
		}
	}
	
	private void initBoltStyle() {
		//TODO
		switch(phase) {
		case 1:
			iceboltstyle = 1;
			break;
		case 2:
			if (Math.random()<0.5) {
				iceboltstyle = 1;
			} else {
				iceboltstyle = 2;
			}
			break;
		case 3:
			if (Math.random()<0.7) {
				iceboltstyle = 1;
			} else {
				iceboltstyle = 3;
			}
			
			break;
		case 4:
			if (Math.random()<0.7) {
				iceboltstyle = 4;
			} else {
				iceboltstyle = 5;
			}
			break;
		case 5:
			iceboltstyle = 1;
			break;
		case 6:
			iceboltstyle = 1;
			break;
		}
		setBoltParams();
	}
	
	private void setBoltParams() {
		switch(iceboltstyle) {
		case 1:
			iceboltdmg = 1;
			iceboltspeed = 10;
			boltsfiringduration = 120;
			boltsfirerate = boltsbasefirerate;
			iceboltrange = 1000;
			break;
		case 2:
			iceboltdmg = 1;
			iceboltspeed = 13;
			boltsfiringduration = 90;
			boltsfirerate = 40;
			iceboltrange = 1000;
			break;
		case 3:
			iceboltdmg = 0;
			iceboltspeed = 5;
			boltsfiringduration = 90;
			boltsfirerate = 50;
			iceboltrange = 800;
			break;
		case 4:
			iceboltspeed = 10;
			iceboltrange = 500;
			switch(StartingClass.difficultylevel) {
			case 1:
				iceboltdmg = 0.3f;
				boltsfiringduration = 120;
				boltsfirerate = 10;
				break;
			case 2:
				iceboltdmg = 0.5f;
				boltsfiringduration = 100;
				boltsfirerate = 8;
				break;
			case 3:
				iceboltdmg = 0.7f;
				boltsfiringduration = 80;
				boltsfirerate = 6;
				break;
			case 4:
				iceboltdmg = 0.9f;
				boltsfiringduration = 60;
				boltsfirerate = 4;
				break;
			}
			break;
		case 5:
			iceboltdmg = 1;
			iceboltspeed = 8;
			boltsfiringduration = 120;
			boltsfirerate = boltsbasefirerate;
			iceboltrange = 400;
			break;
		}
	}
	
	@Override
	public void update() {
		
		if (!alive && hasIntermediateDying()) {
			if (dying > 0) {
				dying--;
				if (dying == 0) {
					setDieSprite();
				}
			}
		}
		
		speedX = 0;
		speedY = 0;
		
		if (isDashing) {
			if ((dashSpeedX > 0 && !canmoveright) || (dashSpeedX < 0 && !canmoveleft)) {
				isDashing = false;
				paintoverride = false;
				showHealthBar = true;
				halfsizex = 68;
				halfsizey = 50;
				halfrsizex = 48;
				halfrsizey = 45;
				isDashing = false;
				currentSprite = staySprite;
				initSpeed();
				inAnimation = 0;
			} else {
				speedX = dashSpeedX;
				fcenterX += speedX - player.getScrollingSpeedX();
				fcenterY += speedY - player.getScrollingSpeedY();
				centerX = (int)fcenterX;
				centerY = (int)fcenterY;
			}
		} else {
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
		}
		
		
		R.setBounds((int)(centerX - halfrsizex + speedX), (int)(centerY - halfrsizey + speedY), 2*halfrsizex, 2*halfrsizey);
		
		if (alive == true) {
			
			posx = (getCenterX() - bg.getCenterX() + StartingClass.bginitx) / 50;
			posy = (getCenterY() - bg.getCenterY() + StartingClass.bginity) / 50;
			if (this.hasSeenU && !darktrail) {
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
			/*pposx = posx;
			pposy = posy;*/
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
	public void animate(){
		if (isMoving && inAnimation == 0 && !isDashing) {
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
			if (isDashing) {
				player.damage(dashdmg);
				halfsizex = 68;
				halfsizey = 50;
				halfrsizex = 48;
				halfrsizey = 45;
				isDashing = false;
				paintoverride = false;
				showHealthBar = true;
				currentSprite = staySprite;
				inAnimation = 0;
				initSpeed();
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
			boltscd += getNextWaitingTime();
		if (darkiceprisonenabled)
			darkiceprisoncd += getNextWaitingTime();
		if (dashenabled)
			dashcd += getNextWaitingTime();
		if (hulkenabled)
			hulkcd += getNextWaitingTime();
		if (blinkingenabled)
			blinkingcd += getNextWaitingTime();
	}

	private int getNextWaitingTime() {
		return (int)(Math.random()*(waitmax-waitmin)) + waitmin;
	}
	
	@Override
	public void setStaySprite() {
		if (hulking == 0)
			currentSprite = staySprite;
		else
			currentSprite = hulkSprite;
	}

	@Override
	public void setMove1Sprite() {
		if (hulking == 0)
			currentSprite = move1Sprite;
		else
			currentSprite = hulkMove1;
	}

	@Override
	public void setMove2Sprite() {
		if (hulking == 0)
			currentSprite = move2Sprite;
		else
			currentSprite = hulkMove2;
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
		if (hulking == 0)
			currentSprite = staySprite;
		else
			currentSprite = hulkSprite;
	}

	@Override
	public void setMove1SpriteAlt() {
		if (hulking == 0)
			currentSprite = move1Sprite;
		else
			currentSprite = hulkMove1;
	}

	@Override
	public void setMove2SpriteAlt() {
		if (hulking == 0)
			currentSprite = move2Sprite;
		else
			currentSprite = hulkMove2;
	}
	
	private void setBehaviour() {
		boltsenabled = true;
		switch (phase) {
		case 2:
			darkiceprisonenabled = false;
			dashenabled = true;
			setDefaultSpeed(2.5f);
			speed = 2.5f;
			hulkenabled = true;
			break;
		case 3:
			dashenabled = false;
			darkiceprisonenabled = true;
			setDefaultSpeed(1.5f);
			speed = 1.5f;
			hulkenabled = false;
			tornadoenabled = true;
			break;
		case 4:
			widerice = true;
			hulkenabled = true;
			darkiceprisonenabled = false;
			slashEnabled = true;
			setDefaultSpeed(3.0f);
			blinkingenabled = true;
			speed = 3.0f;
			tornadoenabled = false;
			break;
		case 5:
			darkiceprisonenabled = true;
			hulkenabled = false;
			setDefaultSpeed(1.25f);
			blinkingenabled = false;
			speed = 1.25f;
			slashEnabled = false;
			break;
		case 6:
			dashenabled = true;
			setDefaultSpeed(2.5f);
			speed = 2.5f;
			blinkingenabled = true;
			slashEnabled = true;
			hulkenabled = true;
			break;
		}
	}
	
	private void startPhaseAnimation() {
		stopMoving();
		animsprite = 0;
		currentSprite = phaseanim.get(0);
		
		isAnimating = true;
		
		//TODO
		phaseanimrate = phaseanimmaxrate - 3*Math.abs(posx-(StartingClass.maskmaxx+StartingClass.maskmaxy)/2)/((StartingClass.maskmaxx+StartingClass.maskmaxy)/2);
		
		halfsizex = 112;
		halfsizey = 100;
		halfrsizex = 48;
		halfrsizey = 45;
		
		phase++;
		StartingClass.maskphase++;
		
		//changing behaviour
		setBehaviour();
		if (phase == 6) {
			waitmin = waitmin/2;
			waitmax = waitmax/2;
		}
		if (phase == 2)
			hulkcd = 300;
		
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
			int tcenterX = 50*x+25+bg.getCenterX()-StartingClass.bginitx;
			int tcenterY = 50*y+25+bg.getCenterY()-StartingClass.bginity;
			Barrel b = new Barrel(tcenterX,tcenterY);
			b.setTileImage(StartingClass.tileBarrel);
			StartingClass.getTilearray().add(b);
			StartingClass.destroyabletiles.add(b);
			break;
		case 5:
			ans = BackgroundFactory.carpet;
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
	
	@Override
	public void damage(float projdmg) {
		if (!isAnimating && hulking == 0) {
			cdmg += projdmg;
			health -= projdmg;
			if (health < 0)
				die();
		}
	}
	
	private void fireRing() {
		projectiles.add(new KaleKingFlame(centerX,centerY,1.0f,0.0f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,1.0f,0.09f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.98f,0.17f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.96f,0.26f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.94f,0.34f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.91f,0.42f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.87f,0.5f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.82f,0.57f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.77f,0.64f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.71f,0.71f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.64f,0.77f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.57f,0.82f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.5f,0.87f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.42f,0.91f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.34f,0.94f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.26f,0.96f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.17f,0.98f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.09f,1.0f,10,fireringdmg,fireringrange));
		
		projectiles.add(new KaleKingFlame(centerX,centerY,-1.0f,0.0f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-1.0f,0.09f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.98f,0.17f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.96f,0.26f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.94f,0.34f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.91f,0.42f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.87f,0.5f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.82f,0.57f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.77f,0.64f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.71f,0.71f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.64f,0.77f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.57f,0.82f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.5f,0.87f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.42f,0.91f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.34f,0.94f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.26f,0.96f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.17f,0.98f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.09f,1.0f,10,fireringdmg,fireringrange));
		
		projectiles.add(new KaleKingFlame(centerX,centerY,0.0f,1.0f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,1.0f,-0.09f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.98f,-0.17f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.96f,-0.26f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.94f,-0.34f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.91f,-0.42f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.87f,-0.5f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.82f,-0.57f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.77f,-0.64f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.71f,-0.71f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.64f,-0.77f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.57f,-0.82f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.5f,-0.87f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.42f,-0.91f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.34f,-0.94f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.26f,-0.96f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.17f,-0.98f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,0.09f,-1.0f,10,fireringdmg,fireringrange));
		
		projectiles.add(new KaleKingFlame(centerX,centerY,0.0f,-1.0f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-1.0f,-0.09f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.98f,-0.17f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.96f,-0.26f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.94f,-0.34f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.91f,-0.42f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.87f,-0.5f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.82f,-0.57f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.77f,-0.64f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.71f,-0.71f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.64f,-0.77f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.57f,-0.82f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.5f,-0.87f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.42f,-0.91f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.34f,-0.94f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.26f,-0.96f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.17f,-0.98f,10,fireringdmg,fireringrange));
		projectiles.add(new KaleKingFlame(centerX,centerY,-0.09f,-1.0f,10,fireringdmg,fireringrange));
	}

	@Override
	public boolean hasIntermediateDying() {
		return true;
	}

	@Override
	public void setIntermediateDieSprite() {
		currentSprite = intermediateDieSprite;
		isDashing = false;
	}

}
