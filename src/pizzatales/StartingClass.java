package pizzatales;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

import pizzatales.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 641656516622083167L;
	public static int difficultylevel = 1;
	private static Player player;
	private Image image, character1, character2, characterMove1, characterMove2, currentSprite, background;
	private Image blooddrop;
	public static Image tileTree, tileGrass, tileWall, tileCave, tileStalag, tilePuddle, tileCaveRock, tileGate, tileCaveExit, tileLavaPuddle, tileWaterFlow;
	private int walkCounter = 1;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;
	private static PathFinder pf;
	private Animation anim;
	ArrayList<Firearm> playerweapons;
	private ArrayList<Armor> playerarmor;
	private static ArrayList<Explosion> explosions;
	

	private int weaponindex;
	private int armorindex;
	private int bginitx;
	private int bginity;
	private long clock = System.currentTimeMillis();

	enum GameState {
		Running, Dead, Paused
	}

	GameState state = GameState.Running;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	private ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<Enemy> enemyarray = new ArrayList<Enemy>();
	public static ArrayList<ArrayList<Enemy>> arenaenemies = new ArrayList<ArrayList<Enemy>>();
	public static ArrayList<ArrayList<EntryDoor>> arenaentrydoors = new ArrayList<ArrayList<EntryDoor>>();
	public static ArrayList<Entry<Integer,Integer>> arenacenters = new ArrayList<Entry<Integer,Integer>>();
	public static ArrayList<HitPoint> hitpoints = new ArrayList<HitPoint>();
	public ArrayList<EntryDoor> entrydoors = new ArrayList<EntryDoor>();
	public static EntryDoor activatedentry = null;
	public static int isInArena = -1;
	

	@Override
	public void init() {
		setSize(1280, 800);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Pizza Tales");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
		}

		// Image Setups
		background = getImage(base, "data/background.png");
		tileTree = getImage(base, "data/tree.png");
		tileGrass = getImage(base, "data/grass.png");
		tileWall = getImage(base, "data/wall.png");
		tileCave = getImage(base, "data/cave.png");
		tileStalag = getImage(base, "data/stalagmites.png");
		tilePuddle = getImage(base, "data/puddle.png");
		tileCaveRock = getImage(base, "data/caverock.png");
		tileGate = getImage(base, "data/gate.png");
		tileCaveExit = getImage(base, "data/caveexit.png");
		
		blooddrop = getImage(base, "data/blooddrop.png");
		Gun.leftSprite = getImage(base, "data/pistol1.png");
		Gun.rightSprite = getImage(base, "data/pistol2.png");
		Gun.upSprite = getImage(base, "data/pistol4.png");
		Gun.downSprite = getImage(base, "data/pistol3.png");
		Bullet.bulletsprite = getImage(base, "data/pistolprojectile.png");
		Shotgun.leftSprite = getImage(base, "data/shotgun1.png");
		Shotgun.rightSprite = getImage(base, "data/shotgun2.png");
		Shotgun.upSprite = getImage(base, "data/shotgun4.png");
		Shotgun.downSprite = getImage(base, "data/shotgun3.png");
		ShotgunBullet.bulletsprite = getImage(base, "data/shotgunprojectile.png");
		Rifle.leftSprite = getImage(base, "data/rifle1.png");
		Rifle.rightSprite = getImage(base, "data/rifle2.png");
		Rifle.upSprite = getImage(base, "data/rifle4.png");
		Rifle.downSprite = getImage(base, "data/rifle3.png");
		RifleBullet.bulletsprite = getImage(base, "data/rifleprojectile.png");
		Flamer.leftSprite = getImage(base, "data/flamer1.png");
		Flamer.rightSprite = getImage(base, "data/flamer2.png");
		Flamer.downSprite = getImage(base, "data/flamer3.png");
		Flamer.upSprite = getImage(base, "data/flamer4.png");
		FlamerFlame.bulletsprite = getImage(base, "data/flamerprojectile.png");
		Rocket.leftSprite = getImage(base, "data/rocket1.png");
		Rocket.rightSprite = getImage(base, "data/rocket2.png");
		Rocket.downSprite = getImage(base, "data/rocket3.png");
		Rocket.upSprite = getImage(base, "data/rocket4.png");
		BazookaBullet.bulletspriteLeft = getImage(base, "data/rocketprojectileleft.png");
		BazookaBullet.bulletspriteRight = getImage(base, "data/rocketprojectileright.png");
		BazookaBullet.bulletspriteUp = getImage(base, "data/rocketprojectileup.png");
		BazookaBullet.bulletspriteDown = getImage(base, "data/rocketprojectiledown.png");
		BazookaBullet.bulletsprite = getImage(base, "data/rocketprojectile.png");
		Smg.leftSprite = getImage(base, "data/smg1.png");
		Smg.rightSprite = getImage(base, "data/smg2.png");
		Smg.downSprite = getImage(base, "data/smg3.png");
		Smg.upSprite = getImage(base, "data/smg4.png");
		SmgBullet.bulletsprite = getImage(base, "data/smgprojectile.png");

		Tato.staySprite = getImage(base, "data/tato1.png");
		Tato.move1Sprite = getImage(base, "data/tato2.png");
		Tato.move2Sprite = getImage(base, "data/tato3.png");
		Tato.dieSprite = getImage(base, "data/tatoDie.png");
		Aubergine.staySprite = getImage(base, "data/aubergine1.png");
		Aubergine.move1Sprite = getImage(base, "data/aubergine2.png");
		Aubergine.move2Sprite = getImage(base, "data/aubergine3.png");
		Aubergine.dieSprite = getImage(base, "data/auberginedead.png");
		Broccoli.staySprite = getImage(base, "data/broccoli1.png");
		Broccoli.move1Sprite = getImage(base, "data/broccoli2.png");
		Broccoli.move2Sprite = getImage(base, "data/broccoli3.png");
		Broccoli.dieSprite = getImage(base, "data/broccolidead.png");
		Pepper.staySprite = getImage(base, "data/pepperLeft1.png");
		Pepper.move1Sprite = getImage(base, "data/pepperLeft2.png");
		Pepper.move2Sprite = getImage(base, "data/pepperLeft3.png");
		Pepper.staySpriteRight = getImage(base, "data/pepperRight1.png");
		Pepper.move1SpriteRight = getImage(base, "data/pepperRight2.png");
		Pepper.move2SpriteRight = getImage(base, "data/pepperRight3.png");
		Pepper.dieSprite = getImage(base, "data/pepperdead.png");
		Mushroom.staySprite = getImage(base, "data/shroom1.png");
		Mushroom.move1Sprite = getImage(base, "data/shroom2.png");
		Mushroom.move2Sprite = getImage(base, "data/shroom3.png");
		Mushroom.dieSprite = getImage(base, "data/shroomdead.png");
		SirTomato.staySprite = getImage(base, "data/sirtomatoleft1.png");
		SirTomato.move1Sprite = getImage(base, "data/sirtomatoleft2.png");
		SirTomato.move2Sprite = getImage(base, "data/sirtomatoleft3.png");
		SirTomato.staySpriteRight = getImage(base, "data/sirtomatoright1.png");
		SirTomato.move1SpriteRight = getImage(base, "data/sirtomatoright2.png");
		SirTomato.move2SpriteRight = getImage(base, "data/sirtomatoright3.png");
		SirTomato.dieSprite = getImage(base, "data/sirtomatodead.png");
		
		BazookaBulletExplosion.bazookaexplosionsprite = getImage(base, "data/bazookaexplosion.png");

		ArmorPotion.armorpotionsprite = getImage(base, "data/armor.png");
		HealthPotion.healthpotionsprite = getImage(base, "data/health.png");
		Lava.lavasprite = getImage(base, "data/puddlelava.png");
		Lava.lavaeffectsprite = getImage(base, "data/lavaeffect.png");
		WaterFlow.waterflowsprite = getImage(base, "data/waterflow.png");
		WaterFlow.watereffectsprite = getImage(base, "data/watereffect.png");
		
		/*
		 * anim = new Animation(); anim.addFrame(character1, 1250);
		 * anim.addFrame(character2, 50); currentSprite = anim.getImage();
		 */
	}

	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
		player = new Player();
		pf = new PathFinder();
		explosions = new ArrayList<Explosion>();
		playerweapons = new ArrayList<Firearm>();
		playerweapons.add(new Gun());
		playerweapons.add(new Shotgun());
		playerweapons.add(new Smg());
		playerweapons.add(new Rifle());
		playerweapons.add(new Flamer());
		playerweapons.add(new Rocket());
		for (Firearm firearm : playerweapons)
			firearm.setHolderProjectiles(player.getProjectiles());
		player.setWeapon(playerweapons.get(weaponindex));

		playerarmor = new ArrayList<Armor>();
		playerarmor.add(new PepperoniArmor());
		playerarmor.add(new CheeseArmor());
		playerarmor.add(new ChicagoArmor());
		playerarmor.add(new HawaiiArmor());
		playerarmor.add(new MargheritaArmor());
		
		player.setArmor(playerarmor.get(armorindex));
		loadArmor();

		bg1 = new Background(0, -200);
		bg2 = new Background(0, 3000);
		bginitx = bg1.getCenterX();
		bginity = bg1.getCenterY() - 15;

		// Initialize Tiles
		try {
			loadMap("data/L24.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadMap(String filename) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while (null != (line = reader.readLine())) {
			// no more lines to read
			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		reader.close();
		height = lines.size();

		pf.map = new boolean[width][height];
		char [][] charmap = new char[width][height];
		HashMap<Integer,EntryDoor> mentrydoors = new HashMap<Integer,EntryDoor>();
		HashMap<Integer, Tile> doors = new HashMap<Integer, Tile>();
		
		for (int j = 0; j < height; j++) {
			line = lines.get(j);
			for (int i = 0; i < width; i++) {
				if (i < line.length()) {
					char ch = line.charAt(i);
					if (ch == '0' || ItemFactory.isItemSupported(ch))
						pf.map[i][j] = true;
					if (ItemFactory.isItemSupported(ch)) {
						Item it = ItemFactory.getItem(i, j, ch);
						items.add(it);
						if (ch == 'i') {
							mentrydoors.put(height*i+j,(EntryDoor)it);
						}
					}
					if (Tile.isTileTypeSupported(ch)) {
						Tile t = new Tile(i, j, ch);
						tilearray.add(t);
						if (ch == 'd')
							doors.put(height*i+j,t);
					}
					if (EnemyFactory.isTileTypeSupported(ch)) {
						getEnemyarray().add(EnemyFactory.getEnemy(i, j, ch));
					}
					charmap[i][j] = ch;
				}
			}
		}
		ArrayList<Integer> nonobstacles = new ArrayList<Integer>();
		int k = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (charmap[i][j] == 'f') {
					MapUtil.getAccessibleArea(i,j,100,charmap,nonobstacles,mentrydoors, doors, k);
					if (!nonobstacles.isEmpty()) {
						int l = 0;
						ArrayList<Enemy> lenemies = new ArrayList<Enemy>();
						while (l < enemyarray.size()) {
							if (nonobstacles.contains(height*enemyarray.get(l).posx + enemyarray.get(l).posy)) {
								lenemies.add(enemyarray.get(l));
								enemyarray.get(l).sleep();
								enemyarray.get(l).setIsInArena(true);
							}
							l++;
						}
						l = 0;
						arenaenemies.add(lenemies);
						arenacenters.add(new SimpleEntry<Integer,Integer>(i,j));
						k++;
					}
				}
			}
		}
		k = 0;
		for (EntryDoor e : mentrydoors.values()) {
			if (e.isGoingIn() < 0)
				items.remove(e);
			else
				entrydoors.add(e);
		}
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void run() {
		if (state == GameState.Running) {
			while (true) {
				try {
					Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clock = System.currentTimeMillis();

				// Animation

				if (player.getSpeedX() != 0 || player.getSpeedY() != 0) {
					if (walkCounter % 30 == 0) {
						// player.getArmor().setSpriteWalk1();
						// currentSprite = player.getArmor().currentSprite;
						currentSprite = characterMove1;
					} else if (walkCounter % 15 == 0) {
						// player.getArmor().setSpriteWalk2();
						// currentSprite = player.getArmor().currentSprite;
						currentSprite = characterMove2;
					}
				} else {
					currentSprite = character1;
				}
				int i = 0;
				while (i < hitpoints.size()) {
					if (hitpoints.get(i).timer == 0)
						hitpoints.remove(i);
					else {
						hitpoints.get(i).timer--;
						i++;
					}	
				}
				updateExplosions();
				player.canmovedown = true;
				player.canmoveleft = true;
				player.canmoveright = true;
				player.canmoveup = true;
				for (Enemy e : enemyarray) {
					e.canmovedown = true;
					e.canmoveleft = true;
					e.canmoveright = true;
					e.canmoveup = true;
				}
				checkEnemiesCollision();
				checkTileCollisions();
				checkItemsCollision();
				updatePlayer();
				callEnemiesAIs();
				updateEnemies();

				bg1.update();
				bg2.update();
				// animate();
				updateTiles();
				updateItems();
				repaint(); // this calls paint
			
				if (isInArena < 0 && activatedentry != null) {
					int playerposx = (player.getCenterX()-bg1.getCenterX()+bginitx)/50;
					int playerposy = (player.getCenterY()-bg1.getCenterY()+bginity)/50;
					for (Tile t : activatedentry.getDoors()) {
						pf.map[(t.getCenterX()-bg1.getCenterX()+bginitx)/50][(t.getCenterY()-bg1.getCenterY()+bginity)/50] = true;
					}
					if (pf.getDirection(playerposx, playerposy, activatedentry.getOut().getPosX(), activatedentry.getOut().getPosY(),10, player.canmoveleft, player.canmoveup, player.canmoveright, player.canmovedown, true) > 0) {
						for (Enemy e : enemyarray) {
							e.sleep();
						}
						int l = 0;
						while (l < entrydoors.size()) {
							if (entrydoors.get(l).isGoingIn() == activatedentry.isGoingIn()) {
								items.remove(entrydoors.get(l));
							}
							l++;
						}
						for (Tile t : activatedentry.getDoors()) {
							tilearray.remove(t);
						}
						isInArena = activatedentry.isGoingIn();
						
						int arenacentery = arenacenters.get(isInArena).getValue() * 50 + bg1.getCenterY() - bginity;
						if (arenacentery > 400)
							player.setScrollingSpeed(player.getMOVESPEED());
						else
							player.setScrollingSpeed(-player.getMOVESPEED());
						boolean foundposition = false;
						int deltapx = 0;
						int deltapy = 0;
						if (activatedentry.getPosX() > activatedentry.getOut().getPosX())
							deltapx = -30;
						if (activatedentry.getPosX() < activatedentry.getOut().getPosX())
							deltapx = 30;
						if (activatedentry.getPosY() < activatedentry.getOut().getPosY())
							deltapy = -30;
						if (activatedentry.getPosY() > activatedentry.getOut().getPosY())
							deltapy = 30;
						while (Math.abs(arenacentery-400) > 20 || !foundposition) {
							try {
								Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							clock = System.currentTimeMillis();
							if (player.getSpeedX() != 0 || player.getSpeedY() != 0) {
								if (walkCounter % 30 == 0) {
									// player.getArmor().setSpriteWalk1();
									// currentSprite = player.getArmor().currentSprite;
									currentSprite = characterMove1;
								} else if (walkCounter % 15 == 0) {
									// player.getArmor().setSpriteWalk2();
									// currentSprite = player.getArmor().currentSprite;
									currentSprite = characterMove2;
								}
							} else {
								currentSprite = character1;
							}
							i = 0;
							while (i < hitpoints.size()) {
								if (hitpoints.get(i).timer == 0)
									hitpoints.remove(i);
								else {
									hitpoints.get(i).timer--;
									i++;
								}	
							}
							updateExplosions();
							player.canmovedown = true;
							player.canmoveleft = true;
							player.canmoveright = true;
							player.canmoveup = true;
							checkEnemiesCollision();
							checkTileCollisions();
							checkItemsCollision();
							ArrayList<Projectile> projectiles = player.getProjectiles();
							i = 0;
							while (i < projectiles.size()) {
								Projectile p = projectiles.get(i);
								if (p.isVisible() == true) {
									p.update();
									for (int j = 0; j < getEnemyarray().size(); j++) {
										Enemy e = getEnemyarray().get(j);
										if (e.alive == true) {
											if (p.checkCollision(e) == true) {
												p.doOnCollision(e);
												e.setHealth(e.getHealth() - p.damage);
												if (e.getHealth() < 1) {
													e.die();
													if(player.getHealth() < 20){
														player.setHealth(player.getHealth() + 1);
													}
												}
											}
										}
									}
									for (int j = 0; j < tilearray.size(); j++) {
										if (true == p.checkCollision(tilearray.get(j))) {
											p.doOnCollision(tilearray.get(j));
										}
									}
									i++;
								} else {
									projectiles.remove(i);
								}
							}
							for (Explosion e : explosions) {
								if (e.isProcing() && player.R.intersects(e.getR())) {
									if (player.getArmor().defense - e.damage < 0) {
										player.setHealth(player.getHealth() - e.damage + player.getArmor().defense);
										player.getArmor().setDefense(0);
										if (player.getHealth() < 1)
											state = GameState.Dead;
									} else {
										player.getArmor().setDefense(player.getArmor().getDefense() - e.damage);
									}
								}
							}
							playerposx = (player.getCenterX()-bg1.getCenterX()+bginitx+deltapx)/50;
							playerposy = (player.getCenterY()-bg1.getCenterY()+bginity+deltapy)/50;
							if (!foundposition) {
								switch (pf.getDirection(playerposx, playerposy, activatedentry.getOut().getPosX(), activatedentry.getOut().getPosY(),10, player.canmoveleft, player.canmoveup, player.canmoveright, player.canmovedown, true)) {
								case 0:
									player.controlledstopMoving();
									foundposition = true;
									break;
								case 1:
									player.controlledmoveLeft();
									break;
								case 2:
									player.controlledmoveUp();
									break;
								case 3:
									player.controlledmoveRight();
									break;
								case 4:
									player.controlledmoveDown();
									break;
								case 5:
									player.controlledmoveLeftUp();
									break;
								case 6:
									player.controlledmoveRightUp();
									break;
								case 7:
									player.controlledmoveRightDown();
									break;
								case 8:
									player.controlledmoveLeftDown();
									break;
								}
							}
							player.controlledupdate();
							updateEnemies();
							bg1.update();
							bg2.update();
							updateTiles();
							for (Tile t : activatedentry.getDoors())
								t.update();
							updateItems();
							repaint();
							arenacentery = arenacenters.get(isInArena).getValue() * 50 + bg1.getCenterY() - bginity;
							if (Math.abs(arenacentery-400) < 20)
								player.setScrollingSpeed(0);
							
						}
						for (Enemy e : enemyarray) {
							if (arenaenemies.get(activatedentry.isGoingIn()).contains(e))
								e.wakeup();
						}
						for (Tile t : activatedentry.getDoors()) {
							pf.map[(t.getCenterX()-bg1.getCenterX()+bginitx)/50][(t.getCenterY()-bg1.getCenterY()+bginity)/50] = false;
							tilearray.add(t);
						}
					} else {
						for (Tile t : activatedentry.getDoors()) {
							pf.map[(t.getCenterX()-bg1.getCenterX()+bginitx)/50][(t.getCenterY()-bg1.getCenterY()+bginity)/50] = false;
						}
					}
					activatedentry = null;
				}
				if (isInArena >= 0) {
					boolean stillgoing = false;
					for (Enemy e : arenaenemies.get(isInArena))
						stillgoing = stillgoing || e.alive;
					if (!stillgoing) {
						for (Enemy e : enemyarray) {
							if (!e.isInArena())
								e.wakeup();
						}
						for (EntryDoor ed : entrydoors) {
							if (ed.isGoingIn() == isInArena) {
								for (Tile t : ed.getDoors()) {
									tilearray.remove(t);
									pf.map[(t.getCenterX()-bg1.getCenterX()+bginitx)/50][(t.getCenterY()-bg1.getCenterY()+bginity)/50] = true;
								}
							}
						}
						isInArena = -1;
					}
				}
				
				if (walkCounter == 1000) {
					walkCounter = 0;
				}
				walkCounter++;
				if(player.getHealth() <= 0){
					state = GameState.Dead;
				}

			}
		}
	}

	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (state == GameState.Running) {
			g.drawImage(background, bg1.getCenterX(), bg1.getCenterY(), this);
			g.drawImage(background, bg2.getCenterX(), bg2.getCenterY(), this);
			paintItems(g);
			paintBelowTiles(g);
			for (Explosion e : explosions) {
				g.drawImage(e.getSprite(), e.getR().x, e.getR().y, this);
			}
			ArrayList<Projectile> projectiles = player.getProjectiles();
			for (int i = 0; i < getEnemyarray().size(); i++) {
				Enemy e = getEnemyarray().get(i);
				if (e.alive) {
					if (null != e.getWeapon()) {
						if (e.isAimingUp()) {
							g.drawImage(e.getWeapon().currentSprite, e.getCenterX() - 31, e.getCenterY() - 31, this);
							g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex, e.getCenterY() - e.halfsizey, this);
						} else {
							g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex, e.getCenterY() - e.halfsizey, this);
							g.drawImage(e.getWeapon().currentSprite, e.getCenterX() - 31, e.getCenterY() - 31, this);
						}
					} else {
						g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex, e.getCenterY() - e.halfsizey, this);
					}
				} else {
					g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex, e.getCenterY() - e.halfsizey, this);
				}
				for (int j = 0; j < e.getProjectiles().size(); j++) {
					Projectile p = e.getProjectiles().get(j);
					g.drawImage(p.getSprite(), p.getR().x, p.getR().y, this);
				}
			}
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = projectiles.get(i);
				// g.setColor(Color.YELLOW);
				// g.fillRect(p.getR().x, p.getR().y, p.getR().width,
				// p.getR().height);
				g.drawImage(p.getSprite(), p.getR().x, p.getR().y, this);
			}
			if (player.isAimingUp()) {
				g.drawImage(player.getWeapon().currentSprite, player.getCenterX() - 31, player.getCenterY() - 31, this);
				g.drawImage(currentSprite, player.getCenterX() - 31, player.getCenterY() - 31, this);
			} else {
				g.drawImage(currentSprite, player.getCenterX() - 31, player.getCenterY() - 31, this);
				g.drawImage(player.getWeapon().currentSprite, player.getCenterX() - 31, player.getCenterY() - 31, this);
			}
			for (int i = 0; i < hitpoints.size(); i++) {
				g.drawImage(blooddrop, hitpoints.get(i).getCenterX()-7, hitpoints.get(i).getCenterY()-7, this);
			}
			paintAboveTiles(g);
			paintItemEffect(g);
			g.setColor(Color.RED);
			g.fillRect(32, 37, 20, 20);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(player.getHealth()), 35, 51);
			g.setColor(Color.BLUE);
			g.fillRect(52, 37, 20, 20);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(player.getArmor().defense),55, 51);
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
		}

	}

	private void callEnemiesAIs() {
		for (Enemy e : getEnemyarray()) {
			e.launchAI();
		}
	}

	private void checkEnemiesCollision() {
		for (Enemy e : getEnemyarray()) {
			if (e.alive)
				e.checkEnemyCollisions();
		}
	}
	
	private void checkItemsCollision() {
		int i = 0;
		while (i < items.size()) {
			if (items.get(i).checkCollisionPlayer(player)) {
				items.remove(i);
			} else {
				i++;
			}
		}
	}

	private void checkTileCollisions() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			t.checkCollisions();
		}
	}
	
	private void updateExplosions() {
		int i = 0;
		while (i < explosions.size()) {
			Explosion e = explosions.get(i);
			e.update();
			if (e.isVisible()) {
				i++;
			} else {
				explosions.remove(i);
			}
		}
	}

	private void updatePlayer() {
		ArrayList<Projectile> projectiles = player.getProjectiles();
		int i = 0;
		while (i < projectiles.size()) {
			Projectile p = projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
				for (int j = 0; j < getEnemyarray().size(); j++) {
					Enemy e = getEnemyarray().get(j);
					if (e.alive == true) {
						if (p.checkCollision(e) == true) {
							p.doOnCollision(e);
							e.setHealth(e.getHealth() - p.damage);
							if (e.getHealth() < 1) {
								e.die();
								if(player.getHealth() < 20){
									player.setHealth(player.getHealth() + 1);
								}
							}
						}
					}
				}
				for (int j = 0; j < tilearray.size(); j++) {
					if (true == p.checkCollision(tilearray.get(j))) {
						p.doOnCollision(tilearray.get(j));
					}
				}
				i++;
			} else {
				projectiles.remove(i);
			}
		}
		for (Explosion e : explosions) {
			if (e.isProcing() && player.R.intersects(e.getR())) {
				if (player.getArmor().defense - e.damage < 0) {
					player.setHealth(player.getHealth() - e.damage + player.getArmor().defense);
					player.getArmor().setDefense(0);
					if (player.getHealth() < 1)
						state = GameState.Dead;
				} else {
					player.getArmor().setDefense(player.getArmor().getDefense() - e.damage);
				}
			}
		}
		player.update();
	}

	private void updateEnemies() {
		for (int j = 0; j < getEnemyarray().size(); j++) {
			Enemy e = getEnemyarray().get(j);
			e.update();
			int i = 0;
			while (i < e.getProjectiles().size()) {
				Projectile p = e.getProjectiles().get(i);
				if (p.isVisible() == true) {
					p.update();
					if (p.checkCollision(player)) {
						p.doOnCollision(player);
						if (player.getArmor().defense - p.damage < 0) {
							player.setHealth(player.getHealth() - p.damage + player.getArmor().defense);
							player.getArmor().setDefense(0);
							if (player.getHealth() < 1)
								state = GameState.Dead;
						} else {
							player.getArmor().setDefense(player.getArmor().getDefense() - p.damage);
						}
					}
					for (int k = 0; k < tilearray.size(); k++) {
						if (true == p.checkCollision(tilearray.get(k))) {
							p.doOnCollision(tilearray.get(j));
						}
					}
					i++;
				} else {
					e.getProjectiles().remove(i);
				}
			}
			for (Explosion ex : explosions) {
				if (ex.isProcing() && e.alive && e.R.intersects(ex.getR())) {
					e.setHealth(e.getHealth() - ex.damage);
					if (e.getHealth() < 1) {
						e.die();
					}
				}
			}
		}
	}

	private void updateTiles() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			t.update();
		}
	}
	
	private void updateItems() {
		for (Item i : items)
			i.update();
	}

	private void paintBelowTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			if (t.getCenterY() <= player.getCenterY() || !Tile.isTileBlocking(t.getType()))
				g.drawImage(t.getTileImage(), t.getCenterX() - 31, t.getCenterY() - 31, this);
		}
	}
	
	private void paintAboveTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			if (t.getCenterY() > player.getCenterY() && Tile.isTileBlocking(t.getType()))
				g.drawImage(t.getTileImage(), t.getCenterX() - 31, t.getCenterY() - 31, this);
		}
	}
	
	private void paintItems(Graphics g) {
		for (int i = 0; i < items.size(); i++) {
			Item it = items.get(i);
			g.drawImage(it.getSprite(), it.getCenterX() - 31, it.getCenterY() - 31, this);
		}
	}
	
	private void paintItemEffect(Graphics g){
		for (int i =0; i < items.size(); i++){
			Item it = items.get(i);
			if(it.effectactive == true){
				g.drawImage(it.getEffectSprite(), player.getCenterX()-31, player.getCenterY()-31, this);
			}
		}
		}

	public void animate() {
		anim.update(10);
	}

	public void loadArmor() {
		player.getArmor();
		character1 = getImage(base, player.getArmor().armor1);
		character2 = getImage(base, player.getArmor().armor2);
		characterMove1 = getImage(base, player.getArmor().armor3);
		characterMove2 = getImage(base, player.getArmor().armor4);
		player.setMOVESPEED(player.getArmor().getSpeed());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_Z:
			player.moveUp();
			break;

		case KeyEvent.VK_S:
			player.moveDown();
			break;

		case KeyEvent.VK_Q:
			player.moveLeft();
			break;

		case KeyEvent.VK_D:
			player.moveRight();
			break;

		case KeyEvent.VK_UP:
			if (0 == player.isShooting()) {
				player.setShooting(2);
			}
			break;

		case KeyEvent.VK_DOWN:
			if (0 == player.isShooting()) {
				player.setShooting(4);
			}
			break;

		case KeyEvent.VK_LEFT:
			if (0 == player.isShooting()) {
				player.setShooting(1);
			}
			break;

		case KeyEvent.VK_RIGHT:
			if (0 == player.isShooting()) {
				player.setShooting(3);
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_Z:
			player.stopMovingUp();
			break;
		case KeyEvent.VK_S:
			player.stopMovingDown();
			break;
		case KeyEvent.VK_Q:
			player.stopMovingLeft();
			break;
		case KeyEvent.VK_D:
			player.stopMovingRight();
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			player.setShooting(0);
			break;
		case KeyEvent.VK_E:
			weaponindex++;
			if (weaponindex == playerweapons.size())
				weaponindex = 0;
			player.setWeapon(playerweapons.get(weaponindex));
			break;
		case KeyEvent.VK_R:
			weaponindex--;
			if (weaponindex == -1)
				weaponindex = playerweapons.size() - 1;
			player.setWeapon(playerweapons.get(weaponindex));
			break;
		case KeyEvent.VK_A:
			armorindex++;
			if (armorindex == playerarmor.size())
				armorindex = 0;
			player.setArmor(playerarmor.get(armorindex));
			loadArmor();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	public static void setBg1(Background bg1) {
		StartingClass.bg1 = bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static void setBg2(Background bg2) {
		StartingClass.bg2 = bg2;
	}

	public static Player getPlayer() {
		return player;
	}

	public static PathFinder getPathFinder() {
		return pf;
	}
	
	public static ArrayList<Explosion> getExplosions() {
		return explosions;
	}

	/*
	 * public void setPlayer(Player player) { this.player = player; }
	 */

	public static ArrayList<Enemy> getEnemyarray() {
		return enemyarray;
	}
	/*
	 * public void setEnemyarray(ArrayList<Enemy> enemyarray) { this.enemyarray
	 * = enemyarray; }
	 */

	public ArrayList<Tile> getTilearray() {
		return tilearray;
	}

	public void setTilearray(ArrayList<Tile> tilearray) {
		this.tilearray = tilearray;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

}