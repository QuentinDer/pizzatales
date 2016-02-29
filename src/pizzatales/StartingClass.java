package pizzatales;

import java.applet.Applet;
import java.applet.AudioClip;
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
	
	private static Player player;
	private Image image, background;
	private Image blooddrop;
	public static Image tileTree, tileGrass, tileWall, tileCave, tileStalag, tileCaveRock, tileGate, tileCaveExit, tileLavaPuddle, tileWaterFlow, tilePikes, tileFlag;
	private int walkCounter = 1;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;
	private static PathFinder pf;
	private Animation anim;
	public static ArrayList<Firearm> playerweapons;
	public static ArrayList<Armor> playerarmor;
	private static ArrayList<Explosion> explosions;
	private AudioClip soundtrack;
	
	public static int difficultylevel = 4;
	public static final boolean TESTMODE = true;
	public static int currentlevel = TESTMODE?9:0;

	private int weaponindex;
	private int armorindex;
	public static int bginitx;
	public static int bginity;
	private int fps;
	private int fpscount;
	private long fpsclock = System.currentTimeMillis();
	private long clock = System.currentTimeMillis();

	enum GameState {
		Running, Dead, Paused, LevelEnded
	}

	public static GameState state = GameState.Running;

	public static BlockingStuff[][] map;
	public static int width;
	public static int height;
	
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Item> leavingitems = new ArrayList<Item>();
	public static ArrayList<Enemy> enemyarray = new ArrayList<Enemy>();
	public static ArrayList<ArrayList<Enemy>> arenaenemies = new ArrayList<ArrayList<Enemy>>();
	public static ArrayList<ArrayList<EntryDoor>> arenaentrydoors = new ArrayList<ArrayList<EntryDoor>>();
	public static ArrayList<Entry<Integer,Integer>> arenacenters = new ArrayList<Entry<Integer,Integer>>();
	public static ArrayList<HitPoint> hitpoints = new ArrayList<HitPoint>();
	public static ArrayList<ArrayList<Enemy>> hiddenenemies = new ArrayList<ArrayList<Enemy>>();
	public static ArrayList<ArrayList<Tile>> hiddentiles = new ArrayList<ArrayList<Tile>>();
	public static ArrayList<ArrayList<Item>> hiddenitems = new ArrayList<ArrayList<Item>>();
	public ArrayList<EntryDoor> entrydoors = new ArrayList<EntryDoor>();
	public static EntryDoor activatedentry = null;
	public static int isInArena = -1;
	public static int revealHidden = -1;
	private boolean centeringOnPlayerRequest = false;
	private boolean toggleScrollingModeRequest = false;
	private boolean showPlayerHealthBar = TESTMODE;
	/*
	 * ScrollingMode :
	 * 0 - noscrolling
	 * 1 - dynamic
	 * 2 - player centered
	 * 3 - player controlled scrolling //TODO implement ?
	 */
	public static int ScrollingMode = 1;
	

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

		soundtrack = getAudioClip(base, "data/Soundtrack.wav");
		
		// Image Setups
		
		CheeseArmor.staysprite1 = getImage(base, "data/cheese1.png");
		CheeseArmor.staysprite2 = getImage(base, "data/cheese2.png");
		CheeseArmor.movesprite1 = getImage(base, "data/cheese3.png");
		CheeseArmor.movesprite2 = getImage(base, "data/cheese4.png");
		ChicagoArmor.staysprite1 = getImage(base, "data/chicago1.png");
		ChicagoArmor.staysprite2 = getImage(base, "data/chicago2.png");
		ChicagoArmor.movesprite1 = getImage(base, "data/chicago3.png");
		ChicagoArmor.movesprite2 = getImage(base, "data/chicago4.png");
		HawaiiArmor.staysprite1 = getImage(base, "data/hawaii1.png");
		HawaiiArmor.staysprite2 = getImage(base, "data/hawaii2.png");
		HawaiiArmor.movesprite1 = getImage(base, "data/hawaii3.png");
		HawaiiArmor.movesprite2 = getImage(base, "data/hawaii4.png");
		MargheritaArmor.staysprite1 = getImage(base, "data/margherita1.png");
		MargheritaArmor.staysprite2 = getImage(base, "data/margherita2.png");
		MargheritaArmor.movesprite1 = getImage(base, "data/margherita3.png");
		MargheritaArmor.movesprite2 = getImage(base, "data/margherita4.png");
		PepperoniArmor.staysprite1 = getImage(base, "data/pepperoni1.png");
		PepperoniArmor.staysprite2 = getImage(base, "data/pepperoni2.png");
		PepperoniArmor.movesprite1 = getImage(base, "data/pepperoni3.png");
		PepperoniArmor.movesprite2 = getImage(base, "data/pepperoni4.png");
		
		tileTree = getImage(base, "data/tree.png");
		tileGrass = getImage(base, "data/grass.png");
		tileWall = getImage(base, "data/wall.png");
		tileCave = getImage(base, "data/cave.png");
		tileStalag = getImage(base, "data/stalagmites.png");
		WaterPuddle.sprite = getImage(base, "data/puddle.png");
		tileCaveRock = getImage(base, "data/caverock.png");
		tileGate = getImage(base, "data/gate.png");
		tileCaveExit = getImage(base, "data/caveexit.png");
		tilePikes = getImage(base, "data/pikes.png");
		tileFlag = getImage(base, "data/flag.png");
		
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
		TomatoProjectile.tomatoprojectilesprite = getImage(base, "data/sirtomatoprojectile.png");

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
		SirTomato.sirtomatothrowleft = getImage(base, "data/sirtomatothrowleft.png");
		SirTomato.sirtomatothrowright = getImage(base, "data/sirtomatothrowright.png");
		SirTomato.dashSpriteLeft = getImage(base, "data/sirtomatodashleft.png");
		SirTomato.dashSpriteRight = getImage(base, "data/sirtomatodashright.png");
		SirTomato.slashSpriteLeft = getImage(base, "data/sirtomatoswipeleft.png");
		SirTomato.slashSpriteRight = getImage(base, "data/sirtomatoswiperight.png");
		MushroomWizard.staySprite = getImage(base, "data/mushroomwizardleft1.png");
		MushroomWizard.move1Sprite = getImage(base, "data/mushroomwizardleft2.png");
		MushroomWizard.move2Sprite = getImage(base, "data/mushroomwizardleft3.png");
		MushroomWizard.staySpriteRight = getImage(base, "data/mushroomwizardright1.png");
		MushroomWizard.move1SpriteRight = getImage(base, "data/mushroomwizardright2.png");
		MushroomWizard.move2SpriteRight = getImage(base, "data/mushroomwizardright3.png");
		
		//MushroomWizard.
		
		BazookaBulletExplosion.bazookaexplosionsprite = getImage(base, "data/bazookaexplosion.png");
		TomatoProjectileExplosion.tomatoexplosionsprite = getImage(base, "data/sirtomatoprojectileexplosion.png");

		ArmorPotion.armorpotionsprite = getImage(base, "data/armor.png");
		HealthPotion.healthpotionsprite = getImage(base, "data/health.png");
		Lava.lavasprite = getImage(base, "data/puddlelava.png");
		Lava.lavaeffectsprite = getImage(base, "data/lavaeffect.png");
		WaterFlow.waterflowsprite = getImage(base, "data/waterflow.png");
		WaterFlow.watereffectsprite = getImage(base, "data/watereffect.png");
		PizzaBox.pizzaboxsprite = getImage(base, "data/pizzabox.png");
		
		/*
		 anim = new Animation(); anim.addFrame(character1, 1250);
		 anim.addFrame(character2, 50); currentSprite = anim.getImage();
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
		if (TESTMODE) {
			playerweapons.add(new Shotgun());
			playerweapons.add(new Smg());
			playerweapons.add(new Rifle());
			playerweapons.add(new Flamer());
			playerweapons.add(new Rocket());
		}
		for (Firearm firearm : playerweapons)
			firearm.setHolderProjectiles(player.getProjectiles());
		player.setWeapon(playerweapons.get(weaponindex));

		playerarmor = new ArrayList<Armor>();
		playerarmor.add(new PepperoniArmor());
		if (TESTMODE) {
			playerarmor.add(new CheeseArmor());
			playerarmor.add(new ChicagoArmor());
			playerarmor.add(new HawaiiArmor());
			playerarmor.add(new MargheritaArmor());
		}
		player.setArmor(playerarmor.get(armorindex));

		bg1 = new Background(0, -200);
		bg2 = new Background(0, 3000);
		player.setBackground(bg1);
		bginitx = bg1.getCenterX();
		bginity = bg1.getCenterY() - 15;

		// Initialize Tiles
		try {
			loadMap("data/"+Level.getMapName(currentlevel));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadMap(String filename) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();

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

		map = new BlockingStuff[width][height];
		char [][] charmap = new char[width][height];
		HashMap<Integer,EntryDoor> mentrydoors = new HashMap<Integer,EntryDoor>();
		HashMap<Integer, Tile> doors = new HashMap<Integer, Tile>();
		HashMap<Integer, HiddenTrigger> hiddentriggers = new HashMap<Integer, HiddenTrigger>();
		
		int posplayery = 0;
		
		for (int j = 0; j < height; j++) {
			line = lines.get(j);
			for (int i = 0; i < width; i++) {
				if (i < line.length()) {
					charmap[i][j] = line.charAt(i);
					if (charmap[i][j] == 'U')
						posplayery = j;
				}
			}
		}
		int deltapy = 8 - posplayery;
		bginity -= 50*deltapy;
		
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				char ch = charmap[i][j];
				if (ch =='U') {
					player.setCenterX(50*i+25);
					player.setCenterY(50*(j+deltapy)+40);
					map[player.posx][player.posy] = player;
				}
				if (ItemFactory.isItemSupported(ch)) {
					Item it = ItemFactory.getItem(i, j, deltapy, ch);
					items.add(it);
					if (ch == 'i') {
						mentrydoors.put(height*i+j,(EntryDoor)it);
					}
					if (ch == 'm') {
						hiddentriggers.put(height*i+j, (HiddenTrigger)it);
					}
				}
				if (Tile.isTileTypeSupported(ch)) {
					Tile t = new Tile(i, j+deltapy, ch);
					tilearray.add(t);
					if (ch == 'd')
						doors.put(height*i+j,t);
				}
				if (EnemyFactory.isTileTypeSupported(ch)) {
					getEnemyarray().add(EnemyFactory.getEnemy(i, j+deltapy, ch));
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
		for (EntryDoor e : mentrydoors.values()) {
			if (e.isGoingIn() < 0)
				items.remove(e);
			else
				entrydoors.add(e);
		}
		k=0;
		HashMap<Integer, Character> area = new HashMap<Integer, Character>();
		for (int i=0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (charmap[i][j] == 'y') {
					MapUtil.getHiddenAccesibleArea(i,j,100,charmap, area, hiddentriggers, k);
					if (!area.isEmpty()) {
						int l = 0;
						ArrayList<Enemy> lenemies = new ArrayList<Enemy>();
						while (l < enemyarray.size()) {
							if (area.containsKey(height*enemyarray.get(l).posx + enemyarray.get(l).posy)) {
								lenemies.add(enemyarray.get(l));
								enemyarray.remove(l);
							} else
								l++;
						}
						hiddenenemies.add(lenemies);
						ArrayList<Tile> ltiles = new ArrayList<Tile>();
						ArrayList<Item> litems = new ArrayList<Item>();
						l = 0;
						while (l < tilearray.size()) {
							int postx = (tilearray.get(l).getCenterX() - bg1.getCenterX() + bginitx) / 50;
							int posty = (tilearray.get(l).getCenterY() - bg1.getCenterY() + bginity) / 50;
							if (area.containsKey(height*postx+posty)) {
								tilearray.get(l).hideImage(Level.getHidingImage(currentlevel));
								ltiles.add(tilearray.get(l));
								tilearray.remove(l);
							} else
								l++;
						}
						for (Entry<Integer,Character> entry : area.entrySet()) {
							if (!Tile.isTileTypeSupported(entry.getValue())) {
								Tile t = new Tile(entry.getKey()/height, entry.getKey()%height+deltapy, entry.getValue());
								t.hideImage(Level.getHidingImage(currentlevel));
								tilearray.add(t);
								ltiles.add(t);
							}
						}
						l = 0;
						while (l < items.size()) {
							int posix = (items.get(l).getCenterX() - bg1.getCenterX() + bginitx) / 50;
							int posiy = (items.get(l).getCenterY() - bg1.getCenterY() + bginity) / 50;
							if (area.containsKey(height*posix+posiy)) {
								litems.add(items.get(l));
								items.remove(l);
							} else
								l++;
						}
						hiddentiles.add(ltiles);
						hiddenitems.add(litems);
						k++;
					}
				}
			}
		}
		if (TESTMODE) {
			for (Enemy e : enemyarray) {
				e.showHealthBar = true;
			}
			for (ArrayList<Enemy> liste : hiddenenemies) {
				for (Enemy e : liste) {
					e.showHealthBar = true;
				}
			}
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
			soundtrack.loop();
			background = getImage(base, "data/"+Level.getBackground(currentlevel));
			while (true) {
				while (state == GameState.Running) {
					try {
						Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					clock = System.currentTimeMillis();
					if (TESTMODE) {
						if (clock > fpsclock+1000) {
							fpsclock = clock;
							fps = fpscount;
							fpscount = 0;
						} else {
							fpscount++;
						}
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
					player.setMOVESPEED(player.getArmor().speed);
					for (Enemy e : enemyarray) {
						e.canmovedown = true;
						e.canmoveleft = true;
						e.canmoveright = true;
						e.canmoveup = true;
					}
					checkEnemiesCollision();
					checkItemsCollision();
					player.checkCollisionsWithBlockingStuff();
					for (Enemy e : getEnemyarray())
						map[e.posx][e.posy] = null;
					map[player.posx][player.posy] = null;
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
							map[t.posx][t.posy] = null;
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
								if (TESTMODE) {
									if (clock > fpsclock+1000) {
										fpsclock = clock;
										fps = fpscount;
										fpscount = 0;
									} else {
										fpscount++;
									}
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
								checkItemsCollision();
								player.checkCollisionsWithBlockingStuff();
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
											if (p.canbedestroyed) {
												for (Projectile pe : e.getProjectiles()) {
													if (pe.isVisible() && p.checkCollision(pe)) {
														p.doOnCollision(pe);
														pe.doOnCollision(p);
													}
												}
											}
										}
										Tile t = p.checkCollisionTile();
										if (null != t) {
											p.doOnCollision(t);
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
										} else {
											player.getArmor().setDefense(player.getArmor().getDefense() - e.damage);
										}
									}
								}
								playerposx = (player.getCenterX()-bg1.getCenterX()+bginitx+deltapx)/50;
								playerposy = (player.getCenterY()-bg1.getCenterY()+bginity+deltapy)/50;
								map[player.posx][player.posy] = null;
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
								for (Enemy e : getEnemyarray())
									map[e.posx][e.posy] = null;
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
							for (Enemy e : arenaenemies.get(activatedentry.isGoingIn())) {
								e.wakeup();
								e.showHealthBar = true;
							}
							for (Tile t : activatedentry.getDoors()) {
								tilearray.add(t);
								map[t.posx][t.posy] = t;
							}
						} else {
							for (Tile t : activatedentry.getDoors()) {
								map[t.posx][t.posy] = t;
							}
						}
						activatedentry = null;
						ScrollingMode = 0;
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
										map[t.posx][t.posy] = null;
									}
								}
							}
							ScrollingMode = 1;
							isInArena = -1;
						}
					}
					if (ScrollingMode > 0 && centeringOnPlayerRequest) {
						int delta = (400 - player.getCenterY())/10;
						int finaldelta = 400 - player.getCenterY() - delta*9;
						for (int l = 0; l < 9; l++) {
							bg1.setCenterY(bg1.getCenterY()+delta);
							bg2.setCenterY(bg2.getCenterY()+delta);
							for (Tile t : tilearray)
								t.setCenterY(t.getCenterY()+delta);
							for (Enemy e : enemyarray) {
								e.setCenterY(e.getCenterY()+delta);
								for (Projectile p : e.getProjectiles())
									p.setCenterY(p.getCenterY()+delta);
							}
							for (Projectile p : player.getProjectiles())
								p.setCenterY(p.getCenterY()+delta);
							for (Item it : items)
								it.setCenterY(it.getCenterY()+delta);
							for (Item it : leavingitems)
								it.setCenterY(it.getCenterY()+delta);
							for (Explosion e : explosions)
								e.setCenterY(e.getCenterY()+delta);
							player.setCenterY(player.getCenterY()+delta);
							repaint();
							try {
								Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							clock = System.currentTimeMillis();
							if (TESTMODE) {
								if (clock > fpsclock+1000) {
									fpsclock = clock;
									fps = fpscount;
									fpscount = 0;
								} else {
									fpscount++;
								}
							}
						}
						bg1.setCenterY(bg1.getCenterY()+finaldelta);
						bg2.setCenterY(bg2.getCenterY()+finaldelta);
						for (Tile t : tilearray)
							t.setCenterY(t.getCenterY()+finaldelta);
						for (Enemy e : enemyarray) {
							e.setCenterY(e.getCenterY()+finaldelta);
							for (Projectile p : e.getProjectiles())
								p.setCenterY(p.getCenterY()+finaldelta);
						}
						for (Projectile p : player.getProjectiles())
							p.setCenterY(p.getCenterY()+finaldelta);	
						for (Item it : items)
							it.setCenterY(it.getCenterY()+finaldelta);
						for (Item it : leavingitems)
							it.setCenterY(it.getCenterY()+finaldelta);
						for (Explosion e : explosions)
							e.setCenterY(e.getCenterY()+finaldelta);
						player.setCenterY(player.getCenterY()+finaldelta);
						centeringOnPlayerRequest = false;
					}
					if (ScrollingMode > 0 && toggleScrollingModeRequest) {
						ScrollingMode = 3-ScrollingMode;
						toggleScrollingModeRequest = false;
					}
					if (revealHidden >= 0) {
						for (Tile t : hiddentiles.get(revealHidden)) {
							t.reveal();
							if (t.getSprite() == null) {
								map[t.posx][t.posy] = null;
								tilearray.remove(t);
							}
						}
						for (Item it : hiddenitems.get(revealHidden)) {
							it.setCenterX(50*it.posx+bg1.getCenterX()-bginitx);
							it.setCenterY(50*it.posy+bg1.getCenterY()-bginity);
							items.add(it);
						}
						for (Enemy e : hiddenenemies.get(revealHidden)) {
							e.setCenterX(50*e.posx+bg1.getCenterX()-bginitx);
							e.setCenterY(50*e.posy+bg1.getCenterY()-bginity);
							enemyarray.add(e);
						}
						hiddentiles.get(revealHidden).clear();
						hiddenitems.get(revealHidden).clear();
						hiddenenemies.get(revealHidden).clear();
						revealHidden = -1;
					}
					
					if (walkCounter == 1000) {
						walkCounter = 0;
					}
					walkCounter++;
					if (player.getHealth() < 1) {
						state = GameState.Dead;
						soundtrack.stop();
					}
				}
				if (state == GameState.LevelEnded) {
					currentlevel++;
					this.clean();
					bg1.setCenterX(0);
					bg1.setCenterY(-200);
					bg2.setCenterX(0);
					bg2.setCenterY(3000);
					bginity = bg1.getCenterY();
					background = getImage(base, "data/"+Level.getBackground(currentlevel));
					try {
						this.loadMap("data/"+Level.getMapName(currentlevel));
					} catch (IOException e) {
						e.printStackTrace();
					}
					state = GameState.Running;
				}
				repaint();
				try {
					Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clock = System.currentTimeMillis();
				if (TESTMODE) {
					if (clock > fpsclock+1000) {
						fpsclock = clock;
						fps = fpscount;
						fpscount = 0;
					} else {
						fpscount++;
					}
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
		if (state != GameState.Dead) {
			g.drawImage(background, bg1.getCenterX(), bg1.getCenterY(), this);
			g.drawImage(background, bg2.getCenterX(), bg2.getCenterY(), this);
			paintItems(g);
			for (Explosion e : explosions) {
				g.drawImage(e.getSprite(), e.getR().x, e.getR().y, this);
			}
			ArrayList<Projectile> projectiles = player.getProjectiles();
			for (Enemy e : getEnemyarray()) {
				if (!e.alive) {
					g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex, e.getCenterY() - e.halfsizey, this);
					for (int j = 0; j < e.getProjectiles().size(); j++) {
						Projectile p = e.getProjectiles().get(j);
						g.drawImage(p.getSprite(), p.getCenterX()-p.halfsize, p.getCenterY()-p.halfsize, this);
					}
				}
			}
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (null != map[x][y]) {
						if (Enemy.class.isInstance(map[x][y])) {
							Enemy e = (Enemy)map[x][y];
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
							if (e.showHealthBar) {
								g.setColor(Color.GREEN);
								int lifetaken = ((e.getMaxHealth()-e.getHealth())*e.halfbar*2)/e.getMaxHealth();
								g.fillRect(e.getCenterX()-e.halfbar, e.getCenterY()-e.halfsizey, 2*e.halfbar-lifetaken, 2);
								g.setColor(Color.RED);
								g.fillRect(e.getCenterX()+e.halfbar-lifetaken, e.getCenterY()-e.halfsizey, lifetaken, 2);
							}
							for (int j = 0; j < e.getProjectiles().size(); j++) {
								Projectile p = e.getProjectiles().get(j);
								g.drawImage(p.getSprite(), p.getCenterX()-p.halfsize, p.getCenterY()-p.halfsize, this);
							}
						} else if (Player.class.isInstance(map[x][y])) {
							if (player.isAimingUp()) {
								g.drawImage(player.getWeapon().currentSprite, player.getCenterX() - 31, player.getCenterY() - 31, this);
								g.drawImage(player.currentSprite, player.getCenterX() - player.halfsizex, player.getCenterY() - player.halfsizey, this);
							} else {
								g.drawImage(player.currentSprite, player.getCenterX() - player.halfsizex, player.getCenterY() - player.halfsizey, this);
								g.drawImage(player.getWeapon().currentSprite, player.getCenterX() - 31, player.getCenterY() - 31, this);
							}
						} else
							g.drawImage(map[x][y].getSprite(),map[x][y].getCenterX() - map[x][y].halfrsizex, map[x][y].getCenterY() - map[x][y].halfsizey, this);
					}
				}
			}/*
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
					if (e.showHealthBar) {
						g.setColor(Color.GREEN);
						int lifetaken = ((e.getMaxHealth()-e.getHealth())*e.halfbar*2)/e.getMaxHealth();
						g.fillRect(e.getCenterX()-e.halfbar, e.getCenterY()-e.halfsizey, 2*e.halfbar-lifetaken, 2);
						g.setColor(Color.RED);
						g.fillRect(e.getCenterX()+e.halfbar-lifetaken, e.getCenterY()-e.halfsizey, lifetaken, 2);
					}
				}
			}*/
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = projectiles.get(i);
				// g.setColor(Color.YELLOW);
				// g.fillRect(p.getR().x, p.getR().y, p.getR().width,
				// p.getR().height);
				g.drawImage(p.getSprite(), p.getCenterX()-p.halfsize, p.getCenterY()-p.halfsize, this);
			}
			if (showPlayerHealthBar) {
				g.setColor(Color.GREEN);
				int lifetaken = ((20+player.getArmor().MAXDEF-player.getHealth()-player.getArmor().getDefense())*44)/(20+player.getArmor().MAXDEF);
				int armorp = (player.getArmor().getDefense()*44)/(20+player.getArmor().MAXDEF);
				g.fillRect(player.getCenterX()-22, player.getCenterY()-31, 44-lifetaken-armorp, 2);
				g.setColor(Color.BLUE);
				g.fillRect(player.getCenterX()+22-lifetaken-armorp, player.getCenterY()-31, armorp, 2);
				g.setColor(Color.RED);
				g.fillRect(player.getCenterX()+22-lifetaken, player.getCenterY()-31, lifetaken, 2);
			}
			for (int i = 0; i < hitpoints.size(); i++) {
				g.drawImage(blooddrop, hitpoints.get(i).getCenterX()-7, hitpoints.get(i).getCenterY()-7, this);
			}
			paintItemEffect(g);
			g.setColor(Color.RED);
			g.fillRect(32, 37, 20, 20);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(player.getHealth()), 35, 51);
			g.setColor(Color.BLUE);
			g.fillRect(52, 37, 20, 20);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(player.getArmor().defense),55, 51);
			if (TESTMODE) {
				g.setColor(Color.DARK_GRAY);
				g.fillRect(1200, 37, 20, 20);
				g.setColor(Color.WHITE);
				g.drawString(Integer.toString(fps), 1203, 51);
			}
		} else {
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
			if (e.alive) {
				e.checkCollisionsWithBlockingStuff();
				e.checkCollisionPlayer();
			}
		}
	}
	
	private void checkItemsCollision() {
		int i = 0;
		while (i < items.size()) {
			if (items.get(i).checkCollisionPlayer(player)) {
				leavingitems.add(items.get(i));
				items.remove(i);
			} else {
				i++;
			}
		}
	}
/*
	private void checkTileCollisions() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			t.checkCollisions();
		}
	}*/
	
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
					if (p.canbedestroyed) {
						for (Projectile pe : e.getProjectiles()) {
							if (pe.isVisible() && p.checkCollision(pe)) {
								p.doOnCollision(pe);
								pe.doOnCollision(p);
							}
						}
					}
				}
				Tile t = p.checkCollisionTile();
				if (null != t) {
					p.doOnCollision(t);
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
					if (player.getHealth() < 1) {
						state = GameState.Dead;
						soundtrack.stop();
					}
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
						} else {
							player.getArmor().setDefense(player.getArmor().getDefense() - p.damage);
						}
					}
					Tile t = p.checkCollisionTile();
					if (null != t) {
						p.doOnCollision(t);
					}
					if (p.canbedestroyed) {
						for (Projectile pe : player.getProjectiles()) {
							if (pe.isVisible() && p.checkCollision(pe)) {
								p.doOnCollision(pe);
								pe.doOnCollision(p);
							}
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
		for (Item i : leavingitems)
			i.update();
	}
/*
	private void paintBelowTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			if (t.getCenterY() <= player.getCenterY() || !Tile.isTileBlocking(t.getType()))
				g.drawImage(t.getSprite(), t.getCenterX() - 31, t.getCenterY() - 31, this);
		}
	}
	
	private void paintAboveTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			if (t.getCenterY() > player.getCenterY() && Tile.isTileBlocking(t.getType()))
				g.drawImage(t.getSprite(), t.getCenterX() - 31, t.getCenterY() - 31, this);
		}
	}*/
	
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
		int i = 0;
		while (i < leavingitems.size()) {
			if (leavingitems.get(i).effectactive) {
				g.drawImage(leavingitems.get(i).getEffectSprite(), player.getCenterX()-31, player.getCenterY()-31, this);
				i++;
			} else
				leavingitems.remove(i);
		}
	}

	public void animate() {
		anim.update(10);
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
			break;
		case KeyEvent.VK_SPACE:
			if (state == GameState.Paused) {
				state = GameState.Running;
				soundtrack.loop();
			}
			else if (state == GameState.Running) {
				state = GameState.Paused;
				soundtrack.stop();
			}	
			break;
		case KeyEvent.VK_C:
			if (ScrollingMode > 0)
				centeringOnPlayerRequest = true;
			break;
		case KeyEvent.VK_V:
			if (ScrollingMode > 0)
				toggleScrollingModeRequest = true;
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

	public void clean() {
		explosions.clear();
		tilearray.clear();
		items.clear();
		leavingitems.clear();
		enemyarray.clear();
		arenaenemies.clear();
		arenacenters.clear();
		hitpoints.clear();
		entrydoors.clear();
	}
}