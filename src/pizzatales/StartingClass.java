package pizzatales;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.prefs.Preferences;
import java.util.AbstractMap.SimpleEntry;
import java.awt.DisplayMode;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Container;

import pizzatales.framework.Animation;

public class StartingClass extends JFrame implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 641656516622083167L;

	Container contentPane;
	private boolean endlevelmenuloaded;
	private boolean threadstarted = false;
	private boolean azerty = true;
	private static Player player;
	private Image image;
	private Image blooddrop;
	private Image grinningsprite;
	private Image iconScreen;
	public static Image tileTree, /*tileGrass, */tileWall, tileCave, tileStalag, tileCaveRock, tileGate, tileCaveExit,
			tileLavaPuddle, tileWaterFlow, tilePikes, tileFlag, tileRock, tileDecoy, tileBarrel, tileCandelabrum, 
			tileCrate, tileChest, tileBlack, tileChestOpen, tilePineTree, tileMudWall, tileSnowRock, tileSky;
	private Graphics second;
	private static Background bg;
	private static PathFinder pf;
	private Animation anim;
	public static ArrayList<Firearm> playerweapons;
	public static ArrayList<Armor> playerarmor;
	public static ArrayList<Hat> playerhats;
	public static ArrayList<Explosion> explosions;
	public static int nextlevel;
	public boolean playSound = false;
	public boolean gunSoundLoaded = false;
	public URL gunSoundurl = null;
	protected boolean playMusic = true;

	private Clip clip;
	
	//TODO protected static ArrayList<Clip> gunclips = new ArrayList<Clip>();
	
	private boolean fullscreenmode = false;
	public static final boolean TESTMODE = false;
	public static int difficultylevel = TESTMODE ? 3 : 1;
	public static int currentlevel = TESTMODE ? 1: 1;
	private final int maxlevel = 20;
	private int currentmaxlevel = 1;

	public static int maskminx = -1, maskmaxx = -1, maskminy = -1, maskmaxy = -1;
	public static int maskphase;
	
	public int weaponindex;
	private int armorindex;
	private int hatindex;
	private int deathCountdown;
	public static int bginitx;
	public static int bginity;
	public static int fps;
	public static int fpscount;
	public static long fpsclock = System.currentTimeMillis();
	public static long clock = System.currentTimeMillis();
	
	private Preferences prefs = Preferences.userNodeForPackage(StartingClass.class);
	/*
	 * public static long nanoclock = System.nanoTime(); public static int
	 * computationtime; public static int cmptime;
	 */

	enum GameState {
		Running, Dead, Paused, Menu, Exit
	}

	public static GameState state = GameState.Menu;

	public static BlockingStuff[][] map;
	public static Image[][] backgroundmap;
	private Image[][][] maskmap;
	
	public static int width;
	public static int height;
	public static int[][] heightitemmap;
	public static boolean remask;

	private static ArrayList<Tile> tilearray = new ArrayList<Tile>();
	//public static ArrayList<Item> items = new ArrayList<Item>();
	public static Item[][][] items;
	public static ArrayList<Item> leavingitems = new ArrayList<Item>();
	public static ArrayList<Enemy> enemyarray = new ArrayList<Enemy>();
	public static ArrayList<ArrayList<Enemy>> arenaenemies = new ArrayList<ArrayList<Enemy>>();
	public static ArrayList<ArrayList<EntryDoor>> arenaentrydoors = new ArrayList<ArrayList<EntryDoor>>();
	public static ArrayList<Entry<Integer, Integer>> arenacenters = new ArrayList<Entry<Integer, Integer>>();
	public static ArrayList<ArrayList<Integer>> arenainsidearea = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<HitPoint> hitpoints = new ArrayList<HitPoint>();
	public static ArrayList<ArrayList<Enemy>> hiddenenemies = new ArrayList<ArrayList<Enemy>>();
	public static ArrayList<ArrayList<Tile>> hiddentiles = new ArrayList<ArrayList<Tile>>();
	public static ArrayList<ArrayList<Item>> hiddenitems = new ArrayList<ArrayList<Item>>();
	public static ArrayList<DestroyableTile> destroyabletiles = new ArrayList<DestroyableTile>();
	public ArrayList<EntryDoor> entrydoors = new ArrayList<EntryDoor>();
	public static EntryDoor activatedentry = null;
	public static int isInArena = -1;
	public static int revealHidden = -1;
	private boolean centeringOnPlayerRequest = false;
	private boolean toggleScrollingModeRequest = false;
	private boolean showPlayerHealthBar = false;
	public static int blockmaxheight;
	public static StartingClass me;
	/*
	 * ScrollingMode : 0 - noscrolling 1 - dynamic 2 - player centered 3 -
	 * player controlled scrolling //TODO implement ?
	 */
	public static int ScrollingMode = 1;
	public static boolean levelwithxscrolling = true;
	public static boolean levelwithyscrolling = true;

	public StartingClass() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		loadResources();
		loadGameState();
		/* TODO
		Thread soundcleaner = new Thread() {
			@Override
			public void run() {
				Clip clip;
				while (true) {
					clip = null;
					synchronized (gunclips) {
						if (!gunclips.isEmpty() && !gunclips.get(0).isRunning()) {
							clip = gunclips.remove(0);
						}
					}
					if (null != clip)
						clip.close();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		soundcleaner.setPriority(Thread.MIN_PRIORITY);
		soundcleaner.setDaemon(true);
		soundcleaner.start();*/
		if (!TESTMODE) {
			initUI();
		}
	}
	
	private void saveGameState() {
		prefs.putBoolean("AZERTY", azerty);
		prefs.putInt("CURRENTMAXLEVEL",currentmaxlevel);
		prefs.putInt("DIFFICULTY", difficultylevel);
		prefs.putInt("LASTLEVEL", currentlevel);
		for (Armor armor : playerarmor)
			prefs.putBoolean(armor.getID(),true);
		for (Firearm firearm : playerweapons)
			prefs.putBoolean(firearm.getID(),true);
		for (Hat hat : playerhats) {
			if (null != hat)
				prefs.putBoolean(hat.getID(),true);
		}
	}
	
	public void loadGameState() {
		azerty = prefs.getBoolean("AZERTY", true);
		currentmaxlevel = prefs.getInt("CURRENTMAXLEVEL",1);
		difficultylevel = prefs.getInt("DIFFICULTY", 1);
		currentlevel = prefs.getInt("LASTLEVEL", 1);
		boolean shotgun = prefs.getBoolean("SHOTGUN",false);
		if (shotgun) {
			Shotgun sht = new Shotgun();
			playerweapons.add(sht);
			sht.setHolderProjectiles(player.getProjectiles());
		}
		boolean smg = prefs.getBoolean("SMG",false);
		if (smg) {
			Smg sht = new Smg();
			playerweapons.add(sht);
			sht.setHolderProjectiles(player.getProjectiles());
		}
		boolean rifle = prefs.getBoolean("RIFLE",false);
		if (rifle) {
			Rifle sht = new Rifle();
			playerweapons.add(sht);
			sht.setHolderProjectiles(player.getProjectiles());
		}
		boolean flamer = prefs.getBoolean("FLAMER",false);
		if (flamer) {
			Flamer sht = new Flamer();
			playerweapons.add(sht);
			sht.setHolderProjectiles(player.getProjectiles());
		}
		boolean rocket = prefs.getBoolean("ROCKET",false);
		if (rocket) {
			Rocket sht = new Rocket();
			playerweapons.add(sht);
			sht.setHolderProjectiles(player.getProjectiles());
		}
		boolean cheesearmor = prefs.getBoolean("CHEESEARMOR",false);
		if (cheesearmor) {
			playerarmor.add(new CheeseArmor());
		}
		boolean hawaiiarmor = prefs.getBoolean("HAWAIIARMOR",false);
		if (hawaiiarmor) {
			playerarmor.add(new HawaiiArmor());
		}
		boolean chicagoarmor = prefs.getBoolean("CHICAGOARMOR",false);
		if (chicagoarmor) {
			playerarmor.add(new ChicagoArmor());
		}
		boolean margheritaarmor = prefs.getBoolean("MARGHERITAARMOR",false);
		if (margheritaarmor) {
			playerarmor.add(new MargheritaArmor());
		}
		boolean hatbaseball = prefs.getBoolean("HATBASEBALL",false);
		if (hatbaseball) {
			playerhats.add(new HatBaseball());
		}
		boolean hatbowler = prefs.getBoolean("HATBOWLER",false);
		if (hatbowler) {
			playerhats.add(new HatBowler());
		}
		boolean hatfedora = prefs.getBoolean("HATFEDORA",false);
		if (hatfedora) {
			playerhats.add(new HatFedora());
		}
		boolean hatpanama = prefs.getBoolean("HATPANAMA",false);
		if (hatpanama) {
			playerhats.add(new HatPanama());
		}
		boolean hatsherlock = prefs.getBoolean("HATSHERLOCK",false);
		if (hatsherlock) {
			playerhats.add(new HatSherlock());
		}
		boolean hattop = prefs.getBoolean("HATTOP",false);
		if (hattop) {
			playerhats.add(new HatTop());
		}
	}

	public void loadResources() {
		setSize(1280, 800);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		if (fullscreenmode) {
			 setUndecorated(true);
			 setAlwaysOnTop(true);
		} else {
			setTitle("Pizza Tales");
		}
		
		

		// Image Setups
		
		Player.hurtSound = getClass().getResource("/data/ugh.wav");

		CheeseArmor.staysprite1 = new ImageIcon(getClass().getResource("/data/cheese1.png")).getImage();
		CheeseArmor.staysprite2 = new ImageIcon(getClass().getResource("/data/cheese2.png")).getImage();
		CheeseArmor.movespriteLeft1 = new ImageIcon(getClass().getResource("/data/cheeseLeft1.png")).getImage();
		CheeseArmor.movespriteLeft2 = new ImageIcon(getClass().getResource("/data/cheeseLeft2.png")).getImage();
		CheeseArmor.movespriteRight1 = new ImageIcon(getClass().getResource("/data/cheeseRight1.png")).getImage();
		CheeseArmor.movespriteRight2 = new ImageIcon(getClass().getResource("/data/cheeseRight2.png")).getImage();
		CheeseArmor.deathSprite = new ImageIcon(getClass().getResource("/data/cheeseDead.png")).getImage();
		ChicagoArmor.staysprite1 = new ImageIcon(getClass().getResource("/data/chicago1.png")).getImage();
		ChicagoArmor.staysprite2 = new ImageIcon(getClass().getResource("/data/chicago2.png")).getImage();
		ChicagoArmor.movespriteLeft1 = new ImageIcon(getClass().getResource("/data/chicagoLeft1.png")).getImage();
		ChicagoArmor.movespriteLeft2 = new ImageIcon(getClass().getResource("/data/chicagoLeft2.png")).getImage();
		ChicagoArmor.movespriteRight1 = new ImageIcon(getClass().getResource("/data/chicagoRight1.png")).getImage();
		ChicagoArmor.movespriteRight2 = new ImageIcon(getClass().getResource("/data/chicagoRight2.png")).getImage();
		ChicagoArmor.deathSprite = new ImageIcon(getClass().getResource("/data/chicagoDead.png")).getImage();
		HawaiiArmor.staysprite1 = new ImageIcon(getClass().getResource("/data/hawaii1.png")).getImage();
		HawaiiArmor.staysprite2 = new ImageIcon(getClass().getResource("/data/hawaii2.png")).getImage();
		HawaiiArmor.movespriteLeft1 = new ImageIcon(getClass().getResource("/data/hawaiiLeft1.png")).getImage();
		HawaiiArmor.movespriteLeft2 = new ImageIcon(getClass().getResource("/data/hawaiiLeft2.png")).getImage();
		HawaiiArmor.movespriteRight1 = new ImageIcon(getClass().getResource("/data/hawaiiRight1.png")).getImage();
		HawaiiArmor.movespriteRight2 = new ImageIcon(getClass().getResource("/data/hawaiiRight2.png")).getImage();
		HawaiiArmor.deathSprite = new ImageIcon(getClass().getResource("/data/hawaiiDead.png")).getImage();
		MargheritaArmor.staysprite1 = new ImageIcon(getClass().getResource("/data/margherita1.png")).getImage();
		MargheritaArmor.staysprite2 = new ImageIcon(getClass().getResource("/data/margherita2.png")).getImage();
		MargheritaArmor.movespriteLeft1 = new ImageIcon(getClass().getResource("/data/margheritaLeft1.png")).getImage();
		MargheritaArmor.movespriteLeft2 = new ImageIcon(getClass().getResource("/data/margheritaLeft2.png")).getImage();
		MargheritaArmor.movespriteRight1 = new ImageIcon(getClass().getResource("/data/margheritaRight1.png")).getImage();
		MargheritaArmor.movespriteRight2 = new ImageIcon(getClass().getResource("/data/margheritaRight2.png")).getImage();
		MargheritaArmor.deathSprite = new ImageIcon(getClass().getResource("/data/margheritaDead.png")).getImage();
		PepperoniArmor.staysprite1 = new ImageIcon(getClass().getResource("/data/pepperoni1.png")).getImage();
		PepperoniArmor.staysprite2 = new ImageIcon(getClass().getResource("/data/pepperoni2.png")).getImage();
		PepperoniArmor.movespriteLeft1 = new ImageIcon(getClass().getResource("/data/pepperoniLeft1.png")).getImage();
		PepperoniArmor.movespriteLeft2 = new ImageIcon(getClass().getResource("/data/pepperoniLeft2.png")).getImage();
		PepperoniArmor.movespriteRight1 = new ImageIcon(getClass().getResource("/data/pepperoniRight1.png")).getImage();
		PepperoniArmor.movespriteRight2 = new ImageIcon(getClass().getResource("/data/pepperoniRight2.png")).getImage();
		PepperoniArmor.deathSprite = new ImageIcon(getClass().getResource("/data/pepperoniDead.png")).getImage();

		tileTree = new ImageIcon(getClass().getResource("/data/tree.png")).getImage();
		//tileGrass = new ImageIcon(getClass().getResource("/data/grass.png")).getImage();
		tileWall = new ImageIcon(getClass().getResource("/data/wall.png")).getImage();
		tileCave = new ImageIcon(getClass().getResource("/data/cave.png")).getImage();
		tileStalag = new ImageIcon(getClass().getResource("/data/stalagmites.png")).getImage();
		tileCaveRock = new ImageIcon(getClass().getResource("/data/caverock.png")).getImage();
		tileGate = new ImageIcon(getClass().getResource("/data/gate.png")).getImage();
		tileCaveExit = new ImageIcon(getClass().getResource("/data/caveexit.png")).getImage();
		tileRock = new ImageIcon(getClass().getResource("/data/rock.png")).getImage();
		tileDecoy = new ImageIcon(getClass().getResource("/data/decoy.png")).getImage();
		tilePikes = new ImageIcon(getClass().getResource("/data/pikes.png")).getImage();
		tileFlag = new ImageIcon(getClass().getResource("/data/flag.png")).getImage();
		tileBarrel = new ImageIcon(getClass().getResource("/data/barrel.png")).getImage();
		tileCandelabrum = new ImageIcon(getClass().getResource("/data/candelabrum.png")).getImage();
		tileCrate = new ImageIcon(getClass().getResource("/data/crate.png")).getImage();
		tileChest = new ImageIcon(getClass().getResource("/data/chest.png")).getImage();
		tileBlack = new ImageIcon(getClass().getResource("/data/blacktile.png")).getImage();
		tileChestOpen = new ImageIcon(getClass().getResource("/data/chestopen.png")).getImage();
		tilePineTree = new ImageIcon(getClass().getResource("/data/pinetree.png")).getImage();
		tileSnowRock = new ImageIcon(getClass().getResource("/data/snowrock.png")).getImage();
		tileMudWall = new ImageIcon(getClass().getResource("/data/mudwall.png")).getImage();
		tileSky = new ImageIcon(getClass().getResource("/data/sky.png")).getImage();
		
		BackgroundFactory.grass = new ImageIcon(getClass().getResource("/data/grass.png")).getImage();
		BackgroundFactory.cave = new ImageIcon(getClass().getResource("/data/cavefloor.png")).getImage();
		BackgroundFactory.dirt = new ImageIcon(getClass().getResource("/data/dirt.png")).getImage();
		BackgroundFactory.brick = new ImageIcon(getClass().getResource("/data/bricktile.png")).getImage();
		BackgroundFactory.mountain = new ImageIcon(getClass().getResource("/data/mountainfloor.png")).getImage();
		BackgroundFactory.lava = new ImageIcon(getClass().getResource("/data/puddlelava.png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.dirtset[i] = new ImageIcon(StartingClass.class.getResource("/data/dirt"+i+".png")).getImage();
		for (int i = 1; i < 16; i++)
			Level.dirtiset[i] = new ImageIcon(StartingClass.class.getResource("/data/dirti"+i+".png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.mountainset[i] = new ImageIcon(StartingClass.class.getResource("/data/mountainfloor"+i+".png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.iceset[i] = new ImageIcon(StartingClass.class.getResource("/data/ice"+i+".png")).getImage();
		for (int i = 1; i < 16; i++)
			Level.icesetm[i] = new ImageIcon(StartingClass.class.getResource("/data/icem"+i+".png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.caveset[i] = new ImageIcon(StartingClass.class.getResource("/data/cave"+i+".png")).getImage();
		
		for (int i = 1; i < 16; i++)
			Level.brickset[i] = new ImageIcon(StartingClass.class.getResource("/data/brick"+i+".png")).getImage();
		
		blooddrop = new ImageIcon(getClass().getResource("/data/blooddrop.png")).getImage();
		Gun.leftSprite = new ImageIcon(getClass().getResource("/data/pistol1.png")).getImage();
		Gun.rightSprite = new ImageIcon(getClass().getResource("/data/pistol2.png")).getImage();
		Gun.upSprite = new ImageIcon(getClass().getResource("/data/pistol4.png")).getImage();
		Gun.downSprite = new ImageIcon(getClass().getResource("/data/pistol3.png")).getImage();
		Gun.leftDownSprite = new ImageIcon(getClass().getResource("/data/pistol7.png")).getImage();
		Gun.leftUpSprite = new ImageIcon(getClass().getResource("/data/pistol8.png")).getImage();
		Gun.rightDownSprite = new ImageIcon(getClass().getResource("/data/pistol6.png")).getImage();
		Gun.rightUpSprite = new ImageIcon(getClass().getResource("/data/pistol5.png")).getImage();
		Gun.url = getClass().getResource("/data/pistol.wav");
		Bullet.bulletsprite = new ImageIcon(getClass().getResource("/data/pistolprojectile.png")).getImage();
		Shotgun.leftSprite = new ImageIcon(getClass().getResource("/data/shotgun1.png")).getImage();
		Shotgun.rightSprite = new ImageIcon(getClass().getResource("/data/shotgun2.png")).getImage();
		Shotgun.upSprite = new ImageIcon(getClass().getResource("/data/shotgun4.png")).getImage();
		Shotgun.downSprite = new ImageIcon(getClass().getResource("/data/shotgun3.png")).getImage();
		Shotgun.leftDownSprite = new ImageIcon(getClass().getResource("/data/shotgun7.png")).getImage();
		Shotgun.leftUpSprite = new ImageIcon(getClass().getResource("/data/shotgun8.png")).getImage();
		Shotgun.rightDownSprite = new ImageIcon(getClass().getResource("/data/shotgun6.png")).getImage();
		Shotgun.rightUpSprite = new ImageIcon(getClass().getResource("/data/shotgun5.png")).getImage();
		Shotgun.url = getClass().getResource("/data/shotgun.wav");
		ShotgunBullet.bulletsprite = new ImageIcon(getClass().getResource("/data/shotgunprojectile.png")).getImage();
		Rifle.leftSprite = new ImageIcon(getClass().getResource("/data/rifle1.png")).getImage();
		Rifle.rightSprite = new ImageIcon(getClass().getResource("/data/rifle2.png")).getImage();
		Rifle.upSprite = new ImageIcon(getClass().getResource("/data/rifle4.png")).getImage();
		Rifle.leftDownSprite = new ImageIcon(getClass().getResource("/data/rifle7.png")).getImage();
		Rifle.leftUpSprite = new ImageIcon(getClass().getResource("/data/rifle8.png")).getImage();
		Rifle.rightDownSprite = new ImageIcon(getClass().getResource("/data/rifle6.png")).getImage();
		Rifle.rightUpSprite = new ImageIcon(getClass().getResource("/data/rifle5.png")).getImage();
		Rifle.url = getClass().getResource("/data/rifle.wav");
		Rifle.downSprite = new ImageIcon(getClass().getResource("/data/rifle3.png")).getImage();
		RifleBullet.bulletsprite = new ImageIcon(getClass().getResource("/data/rifleprojectile.png")).getImage();
		Flamer.leftSprite = new ImageIcon(getClass().getResource("/data/flamer1.png")).getImage();
		Flamer.rightSprite = new ImageIcon(getClass().getResource("/data/flamer2.png")).getImage();
		Flamer.downSprite = new ImageIcon(getClass().getResource("/data/flamer3.png")).getImage();
		Flamer.upSprite = new ImageIcon(getClass().getResource("/data/flamer4.png")).getImage();
		Flamer.leftDownSprite = new ImageIcon(getClass().getResource("/data/flamer7.png")).getImage();
		Flamer.leftUpSprite = new ImageIcon(getClass().getResource("/data/flamer8.png")).getImage();
		Flamer.rightDownSprite = new ImageIcon(getClass().getResource("/data/flamer6.png")).getImage();
		Flamer.rightUpSprite = new ImageIcon(getClass().getResource("/data/flamer5.png")).getImage();
		Flamer.url = getClass().getResource("/data/flamer.wav");
		FlamerFlame.bulletsprite = new ImageIcon(getClass().getResource("/data/flamerprojectile.png")).getImage();
		Rocket.leftSprite = new ImageIcon(getClass().getResource("/data/rocket1.png")).getImage();
		Rocket.rightSprite = new ImageIcon(getClass().getResource("/data/rocket2.png")).getImage();
		Rocket.downSprite = new ImageIcon(getClass().getResource("/data/rocket3.png")).getImage();
		Rocket.upSprite = new ImageIcon(getClass().getResource("/data/rocket4.png")).getImage();
		Rocket.url = getClass().getResource("/data/rocket.wav");
		BazookaBullet.bulletspriteLeft = new ImageIcon(getClass().getResource("/data/rocketprojectileleft.png"))
				.getImage();
		BazookaBullet.bulletspriteRight = new ImageIcon(getClass().getResource("/data/rocketprojectileright.png"))
				.getImage();
		BazookaBullet.bulletspriteUp = new ImageIcon(getClass().getResource("/data/rocketprojectileup.png")).getImage();
		BazookaBullet.bulletspriteDown = new ImageIcon(getClass().getResource("/data/rocketprojectiledown.png"))
				.getImage();
		Smg.leftSprite = new ImageIcon(getClass().getResource("/data/smg1.png")).getImage();
		Smg.rightSprite = new ImageIcon(getClass().getResource("/data/smg2.png")).getImage();
		Smg.downSprite = new ImageIcon(getClass().getResource("/data/smg3.png")).getImage();
		Smg.upSprite = new ImageIcon(getClass().getResource("/data/smg4.png")).getImage();
		Smg.leftDownSprite = new ImageIcon(getClass().getResource("/data/smg7.png")).getImage();
		Smg.leftUpSprite = new ImageIcon(getClass().getResource("/data/smg8.png")).getImage();
		Smg.rightDownSprite = new ImageIcon(getClass().getResource("/data/smg6.png")).getImage();
		Smg.rightUpSprite = new ImageIcon(getClass().getResource("/data/smg5.png")).getImage();
		Smg.url = getClass().getResource("/data/smg.wav");
		SmgBullet.bulletsprite = new ImageIcon(getClass().getResource("/data/smgprojectile.png")).getImage();
		TomatoProjectile.tomatoprojectilesprite = new ImageIcon(getClass().getResource("/data/sirtomatoprojectile.png"))
				.getImage();
		MushroomWizardBall.greenball = new ImageIcon(getClass().getResource("/data/mushroomwizardgreenball.png"))
				.getImage();
		MushroomWizardBall.yellowball = new ImageIcon(getClass().getResource("/data/mushroomwizardyellowball.png"))
				.getImage();
		MushroomWizardBall.redball = new ImageIcon(getClass().getResource("/data/mushroomwizardredball.png"))
				.getImage();
		MushroomWizardBall.blueball = new ImageIcon(getClass().getResource("/data/mushroomwizardblueball.png"))
				.getImage();
		ReaperBarrelProjectile.sprite = new ImageIcon(getClass().getResource("/data/barrelprojectile.png")).getImage();
		OnioughProjectile.sprite = new ImageIcon(getClass().getResource("/data/onioughProjectile.png")).getImage();
		
		for (int i = 0; i < 32; i++)
			IceBolt.boltset[i] = new ImageIcon(getClass().getResource("/data/icebolt"+i+".png")).getImage();
		KaleKingBall.sprite = new ImageIcon(getClass().getResource("/data/kalekingBall.png")).getImage();
		KaleKingFlame.sprite = new ImageIcon(getClass().getResource("/data/darkflame.png")).getImage();

		Tato.staySprite = new ImageIcon(getClass().getResource("/data/tato1.png")).getImage();
		Tato.move1Sprite = new ImageIcon(getClass().getResource("/data/tato2.png")).getImage();
		Tato.move2Sprite = new ImageIcon(getClass().getResource("/data/tato3.png")).getImage();
		Tato.dieSprite = new ImageIcon(getClass().getResource("/data/tatoDie.png")).getImage();
		Tato.gibsSprite = new ImageIcon(getClass().getResource("/data/tatogibs.png")).getImage();
		Aubergine.staySprite = new ImageIcon(getClass().getResource("/data/aubergine1.png")).getImage();
		Aubergine.move1Sprite = new ImageIcon(getClass().getResource("/data/aubergine2.png")).getImage();
		Aubergine.move2Sprite = new ImageIcon(getClass().getResource("/data/aubergine3.png")).getImage();
		Aubergine.dieSprite = new ImageIcon(getClass().getResource("/data/auberginedead.png")).getImage();
		Aubergine.gibsSprite = new ImageIcon(getClass().getResource("/data/auberginegibs.png")).getImage();
		Broccoli.staySprite = new ImageIcon(getClass().getResource("/data/broccoli1.png")).getImage();
		Broccoli.move1Sprite = new ImageIcon(getClass().getResource("/data/broccoli2.png")).getImage();
		Broccoli.move2Sprite = new ImageIcon(getClass().getResource("/data/broccoli3.png")).getImage();
		Broccoli.dieSprite = new ImageIcon(getClass().getResource("/data/broccolidead.png")).getImage();
		Broccoli.gibsSprite = new ImageIcon(getClass().getResource("/data/broccoligibs.png")).getImage();
		Pepper.staySprite = new ImageIcon(getClass().getResource("/data/pepperLeft1.png")).getImage();
		Pepper.move1Sprite = new ImageIcon(getClass().getResource("/data/pepperLeft2.png")).getImage();
		Pepper.move2Sprite = new ImageIcon(getClass().getResource("/data/pepperLeft3.png")).getImage();
		Pepper.staySpriteRight = new ImageIcon(getClass().getResource("/data/pepperRight1.png")).getImage();
		Pepper.move1SpriteRight = new ImageIcon(getClass().getResource("/data/pepperRight2.png")).getImage();
		Pepper.move2SpriteRight = new ImageIcon(getClass().getResource("/data/pepperRight3.png")).getImage();
		Pepper.dieSprite = new ImageIcon(getClass().getResource("/data/pepperdead.png")).getImage();
		Pepper.gibsSprite = new ImageIcon(getClass().getResource("/data/peppergibs.png")).getImage();
		Mushroom.staySprite = new ImageIcon(getClass().getResource("/data/shroom1.png")).getImage();
		Mushroom.move1Sprite = new ImageIcon(getClass().getResource("/data/shroom2.png")).getImage();
		Mushroom.move2Sprite = new ImageIcon(getClass().getResource("/data/shroom3.png")).getImage();
		Mushroom.dieSprite = new ImageIcon(getClass().getResource("/data/shroomdead.png")).getImage();
		Mushroom.gibsSprite = new ImageIcon(getClass().getResource("/data/shroomgibs.png")).getImage();
		SirTomato.staySprite = new ImageIcon(getClass().getResource("/data/sirtomatoleft1.png")).getImage();
		SirTomato.move1Sprite = new ImageIcon(getClass().getResource("/data/sirtomatoleft2.png")).getImage();
		SirTomato.move2Sprite = new ImageIcon(getClass().getResource("/data/sirtomatoleft3.png")).getImage();
		SirTomato.staySpriteRight = new ImageIcon(getClass().getResource("/data/sirtomatoright1.png")).getImage();
		SirTomato.move1SpriteRight = new ImageIcon(getClass().getResource("/data/sirtomatoright2.png")).getImage();
		SirTomato.move2SpriteRight = new ImageIcon(getClass().getResource("/data/sirtomatoright3.png")).getImage();
		SirTomato.dieSprite = new ImageIcon(getClass().getResource("/data/sirtomatodead.png")).getImage();
		SirTomato.sirtomatothrowleft = new ImageIcon(getClass().getResource("/data/sirtomatothrowleft.png")).getImage();
		SirTomato.sirtomatothrowright = new ImageIcon(getClass().getResource("/data/sirtomatothrowright.png"))
				.getImage();
		SirTomato.dashSpriteLeft = new ImageIcon(getClass().getResource("/data/sirtomatodashleft.png")).getImage();
		SirTomato.dashSpriteRight = new ImageIcon(getClass().getResource("/data/sirtomatodashright.png")).getImage();
		SirTomato.slashSpriteLeft = new ImageIcon(getClass().getResource("/data/sirtomatoswipeleft.png")).getImage();
		SirTomato.slashSpriteRight = new ImageIcon(getClass().getResource("/data/sirtomatoswiperight.png")).getImage();
		SirTomato.dashLeftWindup = new ImageIcon(getClass().getResource("/data/sirtomatodashleftwindup.png")).getImage();
		SirTomato.dashRightWindup = new ImageIcon(getClass().getResource("/data/sirtomatodashrightwindup.png")).getImage();
		SirTomato.swipeLeftWindup = new ImageIcon(getClass().getResource("/data/sirtomatoswipeleftwindup.png")).getImage();
		SirTomato.swipeRightWindup = new ImageIcon(getClass().getResource("/data/sirtomatoswiperightwindup.png")).getImage();
		SirTomato.intermediateDieSprite = new ImageIcon(getClass().getResource("/data/sirtomatodying.png")).getImage();
		MushroomWizard.staySprite = new ImageIcon(getClass().getResource("/data/mushroomwizardleft1.png")).getImage();
		MushroomWizard.move1Sprite = new ImageIcon(getClass().getResource("/data/mushroomwizardleft2.png")).getImage();
		MushroomWizard.move2Sprite = new ImageIcon(getClass().getResource("/data/mushroomwizardleft3.png")).getImage();
		MushroomWizard.staySpriteRight = new ImageIcon(getClass().getResource("/data/mushroomwizardright1.png"))
				.getImage();
		MushroomWizard.move1SpriteRight = new ImageIcon(getClass().getResource("/data/mushroomwizardright2.png"))
				.getImage();
		MushroomWizard.move2SpriteRight = new ImageIcon(getClass().getResource("/data/mushroomwizardright3.png"))
				.getImage();
		MushroomWizard.dieSprite = new ImageIcon(getClass().getResource("/data/mushroomwizarddead.png")).getImage();
		MushroomWizard.swipeLeft = new ImageIcon(getClass().getResource("/data/mushroomwizardswipeleft.png"))
				.getImage();
		MushroomWizard.swipeRight = new ImageIcon(getClass().getResource("/data/mushroomwizardswiperight.png"))
				.getImage();
		MushroomWizard.swipeDown = new ImageIcon(getClass().getResource("/data/mushroomwizardswipedown.png"))
				.getImage();
		MushroomWizard.swipeUp = new ImageIcon(getClass().getResource("/data/mushroomwizardswipeup.png")).getImage();
		MushroomWizard.shooting = new ImageIcon(getClass().getResource("/data/mushroomwizardshooting.png")).getImage();
		MushroomWizard.summoning = new ImageIcon(getClass().getResource("/data/mushroomwizardlavasummon.png"))
				.getImage();
		MushroomWizard.intermediateDieSprite = new ImageIcon(getClass().getResource("/data/mushroomwizarddying.png")).getImage();
		CarolinaReaper.staySprite = new ImageIcon(getClass().getResource("/data/reaper.png")).getImage();
		CarolinaReaper.move1Sprite = new ImageIcon(getClass().getResource("/data/reaperwalk1.png")).getImage();
		CarolinaReaper.move2Sprite = new ImageIcon(getClass().getResource("/data/reaperwalk2.png")).getImage();
		CarolinaReaper.staySpriteOnFire = new ImageIcon(getClass().getResource("/data/reaperwalkfire1.png")).getImage();
		CarolinaReaper.move1SpriteOnFire = new ImageIcon(getClass().getResource("/data/reaperwalkfire2.png")).getImage();
		CarolinaReaper.move2SpriteOnFire = new ImageIcon(getClass().getResource("/data/reaperwalkfire3.png")).getImage();
		CarolinaReaper.firering = new ImageIcon(getClass().getResource("/data/reaperfirering.png")).getImage();
		CarolinaReaper.fireringOnFire = new ImageIcon(getClass().getResource("/data/reaperfirestreamwindup.png")).getImage();
		CarolinaReaper.streamleft = new ImageIcon(getClass().getResource("/data/reaperfirestreamleft.png")).getImage();
		CarolinaReaper.streamright = new ImageIcon(getClass().getResource("/data/reaperfirestreamright.png"))
				.getImage();
		CarolinaReaper.streamup = new ImageIcon(getClass().getResource("/data/reaperfirestreamup.png")).getImage();
		CarolinaReaper.streamdown = new ImageIcon(getClass().getResource("/data/reaperfirestreamdown.png")).getImage();
		CarolinaReaper.dieSprite = new ImageIcon(getClass().getResource("/data/reaperdead.png")).getImage();
		CarolinaReaper.intermediateDieSprite = new ImageIcon(getClass().getResource("/data/reaperdead1.png")).getImage();
		Oniough.staySprite = new ImageIcon(getClass().getResource("/data/oniough1.png")).getImage();
		Oniough.move1Sprite = new ImageIcon(getClass().getResource("/data/onioughWalk1.png")).getImage();
		Oniough.move2Sprite = new ImageIcon(getClass().getResource("/data/onioughWalk2.png")).getImage();
		Oniough.onioughStomp1 = new ImageIcon(getClass().getResource("/data/onioughStomp1.png")).getImage();
		Oniough.onioughStomp2 = new ImageIcon(getClass().getResource("/data/onioughStomp2.png")).getImage();
		Oniough.dieSprite = new ImageIcon(getClass().getResource("/data/onioughdead.png")).getImage();
		Oniough.intermediateDieSprite = new ImageIcon(getClass().getResource("/data/onioughdying.png")).getImage();
		Garlnstein.staySprite = new ImageIcon(getClass().getResource("/data/garlnstein.png")).getImage();
		Garlnstein.move1Sprite = new ImageIcon(getClass().getResource("/data/garlnsteinWalk1.png")).getImage();
		Garlnstein.move2Sprite = new ImageIcon(getClass().getResource("/data/garlnsteinWalk2.png")).getImage();
		Garlnstein.dieSprite = new ImageIcon(getClass().getResource("/data/garlnsteinDead.png")).getImage();
		Garlnstein.slashDown = new ImageIcon(getClass().getResource("/data/garlnsteinSwipeDown.png")).getImage();
		Garlnstein.slashUp = new ImageIcon(getClass().getResource("/data/garlnsteinSwipeUp.png")).getImage();
		Garlnstein.slashRight = new ImageIcon(getClass().getResource("/data/garlnsteinSwipeRight.png")).getImage();
		Garlnstein.slashLeft = new ImageIcon(getClass().getResource("/data/garlnsteinDashLeft.png")).getImage();
		Garlnstein.dashDown = new ImageIcon(getClass().getResource("/data/garlnsteinDashDown.png")).getImage();
		Garlnstein.dashUp = new ImageIcon(getClass().getResource("/data/garlnsteinDashUp.png")).getImage();
		Garlnstein.dashRight = new ImageIcon(getClass().getResource("/data/garlnsteinDashRight.png")).getImage();
		Garlnstein.dashLeft = new ImageIcon(getClass().getResource("/data/garlnsteinDashLeft.png")).getImage();
		Garlnstein.dashBlinking = new ImageIcon(getClass().getResource("/data/garlnsteinReadyToDash.png")).getImage();
		Garlnstein.cloning1 = new ImageIcon(getClass().getResource("/data/garlnsteinCloning1.png")).getImage();
		Garlnstein.cloning2 = new ImageIcon(getClass().getResource("/data/garlnsteinCloning2.png")).getImage();
		Garlnstein.cloning3 = new ImageIcon(getClass().getResource("/data/garlnsteinCloning3.png")).getImage();
		Garlnstein.intermediateDieSprite = new ImageIcon(getClass().getResource("/data/garlnsteinDying.png")).getImage();
		KaleKing.staySprite = new ImageIcon(getClass().getResource("/data/kaleking.png")).getImage();
		KaleKing.move1Sprite = new ImageIcon(getClass().getResource("/data/kalekingWalk1.png")).getImage();
		KaleKing.move2Sprite = new ImageIcon(getClass().getResource("/data/kalekingWalk2.png")).getImage();
		KaleKing.dieSprite = new ImageIcon(getClass().getResource("/data/kalekingDead.png")).getImage();
		KaleKing.swipeDown = new ImageIcon(getClass().getResource("/data/kalekingSwipeDown.png")).getImage();
		KaleKing.swipeLeft = new ImageIcon(getClass().getResource("/data/kalekingSwipeLeft.png")).getImage();
		KaleKing.swipeRight = new ImageIcon(getClass().getResource("/data/kalekingSwipeRight.png")).getImage();
		KaleKing.swipeUp = new ImageIcon(getClass().getResource("/data/kalekingSwipeUp.png")).getImage();
		KaleKing.boltsfiringsprite = new ImageIcon(getClass().getResource("/data/kalekingMagicCryoBeam.png")).getImage();
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase1.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase2.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase3.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase4.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase5.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase6.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase7.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase8.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase9.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase10.png")).getImage());
		KaleKing.phaseanim.add(new ImageIcon(getClass().getResource("/data/kalekingPhase11.png")).getImage());
		KaleKing.dashSpriteLeft = new ImageIcon(getClass().getResource("/data/kalekingThrustLeft.png")).getImage();
		KaleKing.dashSpriteRight = new ImageIcon(getClass().getResource("/data/kalekingThrustRight.png")).getImage();
		KaleKing.hulkSprite = new ImageIcon(getClass().getResource("/data/hulk.png")).getImage();
		KaleKing.hulkMove1 = new ImageIcon(getClass().getResource("/data/hulkWalk1.png")).getImage();
		KaleKing.hulkMove2 = new ImageIcon(getClass().getResource("/data/hulkWalk2.png")).getImage();
		KaleKing.hulkSwipeDown = new ImageIcon(getClass().getResource("/data/hulkSwipeDown.png")).getImage();
		KaleKing.hulkSwipeLeft = new ImageIcon(getClass().getResource("/data/hulkSwipeLeft.png")).getImage();
		KaleKing.hulkSwipeRight = new ImageIcon(getClass().getResource("/data/hulkSwipeRight.png")).getImage();
		KaleKing.hulkSwipeUp = new ImageIcon(getClass().getResource("/data/hulkSwipeUp.png")).getImage();
		KaleKing.blinkingSprite = new ImageIcon(getClass().getResource("/data/kalekingMagicAoE.png")).getImage();
		KaleKing.intermediateDieSprite = new ImageIcon(getClass().getResource("/data/kalekingDying.png")).getImage();

		BazookaBulletExplosion.bazookaexplosionsprite = new ImageIcon(
				getClass().getResource("/data/bazookaexplosion.png")).getImage();
		BazookaBulletExplosion.sound = getClass().getResource("/data/explosion.wav");
		TomatoProjectileExplosion.tomatoexplosionsprite = new ImageIcon(
				getClass().getResource("/data/sirtomatoprojectileexplosion.png")).getImage();
		BarrelExplosion.explosionsprite = new ImageIcon(getClass().getResource("/data/barrelexplosion.png")).getImage();
		BarrelExplosion.sound = getClass().getResource("/data/explosion.wav");
		
		ArmorPotion.armorpotionsprite = new ImageIcon(getClass().getResource("/data/armor.png")).getImage();
		ArmorPotion.armorpotioneffectsprite = new ImageIcon(getClass().getResource("/data/armoreffect.png")).getImage();
		ArmorPotion.armorpotioneffectsprite2 = new ImageIcon(getClass().getResource("/data/armoreffect_2.png")).getImage();
		ArmorPotion.armorpotioneffectsprite3 = new ImageIcon(getClass().getResource("/data/armoreffect_3.png")).getImage();
		ArmorPotion.armorpotioneffectsprite4 = new ImageIcon(getClass().getResource("/data/armoreffect_4.png")).getImage();
		HealthPotion.healthpotionsprite = new ImageIcon(getClass().getResource("/data/health.png")).getImage();
		HealthPotion.healthpotioneffectsprite = new ImageIcon(getClass().getResource("/data/healtheffect.png")).getImage();
		HealthPotion.healthpotioneffectsprite2 = new ImageIcon(getClass().getResource("/data/healtheffect_2.png")).getImage();
		HealthPotion.healthpotioneffectsprite3 = new ImageIcon(getClass().getResource("/data/healtheffect_3.png")).getImage();
		HealthPotion.healthpotioneffectsprite4 = new ImageIcon(getClass().getResource("/data/healtheffect_4.png")).getImage();
		Lava.lavaeffectsprite = new ImageIcon(getClass().getResource("/data/lavaeffect.png")).getImage();
		BackgroundFactory.waterflow = new ImageIcon(getClass().getResource("/data/waterflow.png")).getImage();
		WaterFlow.watereffectsprite = new ImageIcon(getClass().getResource("/data/watereffect.png")).getImage();
		WaterPuddle.sprite = new ImageIcon(getClass().getResource("/data/puddle.png")).getImage();
		WoodBridge.sprite = new ImageIcon(getClass().getResource("/data/woodbridge.png")).getImage();
		PizzaBox.pizzaboxsprite = new ImageIcon(getClass().getResource("/data/pizzabox.png")).getImage();
		Carpet.sprite = new ImageIcon(getClass().getResource("/data/carpet.png")).getImage();
		CrateOpen.sprite = new ImageIcon(getClass().getResource("/data/crateopen.png")).getImage();
		ReaperBlinkingItem.sprite = new ImageIcon(getClass().getResource("/data/reaperblinkingpos.png")).getImage();
		ReaperTrap.pizzatrap = new ImageIcon(getClass().getResource("/data/pizzatrap.png")).getImage();
		ReaperTrap.potiontrap = new ImageIcon(getClass().getResource("/data/potiontrap.png")).getImage();
		ReaperTrap.potiontrap1 = new ImageIcon(getClass().getResource("/data/potiontrap1.png")).getImage();
		BackgroundFactory.snow = new ImageIcon(getClass().getResource("/data/snowbank.png")).getImage();
		SnowBank.snoweffectsprite = new ImageIcon(getClass().getResource("/data/snowbankeffect.png")).getImage();
		BackgroundFactory.ice = new ImageIcon(getClass().getResource("/data/ice.png")).getImage();
		BoostCheese.boostsprite = new ImageIcon(getClass().getResource("/data/cheese.png")).getImage();
		BoostCheese.boosteffectsprite = new ImageIcon(getClass().getResource("/data/cheeseeffect.png")).getImage();
		BoostBacon.boostsprite = new ImageIcon(getClass().getResource("/data/bacon.png")).getImage();
		BoostBacon.boosteffectsprite = new ImageIcon(getClass().getResource("/data/baconeffect.png")).getImage();
		BoostBasil.boostsprite = new ImageIcon(getClass().getResource("/data/basil.png")).getImage();
		BoostBasil.boosteffectsprite = new ImageIcon(getClass().getResource("/data/basileffect.png")).getImage();
		BoostGarlic.boostsprite = new ImageIcon(getClass().getResource("/data/garlicbread.png")).getImage();
		BoostGarlic.boosteffectsprite = new ImageIcon(getClass().getResource("/data/garlicbreadeffect.png")).getImage();
		LevelExit.sprite = new ImageIcon(getClass().getResource("/data/finishline.png")).getImage();
		SummonedIce.healingice = new ImageIcon(getClass().getResource("/data/iceheal.png")).getImage();
		FakeItemForFrozen.frozen = new ImageIcon(getClass().getResource("/data/frozeneffect.png")).getImage();
		DarkIce.ready  = new ImageIcon(getClass().getResource("/data/darkiceready.png")).getImage();
		DarkIce.strike  = new ImageIcon(getClass().getResource("/data/darkice.png")).getImage();
		KaleKingBlinkingItem.sprite = new ImageIcon(getClass().getResource("/data/kingkaleblinking.png")).getImage();
		
		HatBaseball.hatsprite = new ImageIcon(getClass().getResource("/data/hatbaseball.png")).getImage();
		HatBowler.hatsprite = new ImageIcon(getClass().getResource("/data/hatbowler.png")).getImage();
		HatFedora.hatsprite = new ImageIcon(getClass().getResource("/data/hatfedora.png")).getImage();
		HatPanama.hatsprite = new ImageIcon(getClass().getResource("/data/hatpanama.png")).getImage();
		HatSherlock.hatsprite = new ImageIcon(getClass().getResource("/data/hatsherlock.png")).getImage();
		HatTop.hatsprite = new ImageIcon(getClass().getResource("/data/hattop.png")).getImage();

		grinningsprite = new ImageIcon(getClass().getResource("/data/grin.png")).getImage();
		
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

		playerhats = new ArrayList<Hat>();
		playerhats.add(null);
		if (TESTMODE) {
			playerhats.add(new HatBaseball());
			playerhats.add(new HatBowler());
			playerhats.add(new HatFedora());
			playerhats.add(new HatPanama());
			playerhats.add(new HatSherlock());
			playerhats.add(new HatTop());
		}

		bg = new Background(0, 0);
		player.setBackground(bg);
		bginitx = bg.getCenterX();
		bginity = bg.getCenterY() - 15;

		player.currentSprite = player.getArmor().getStaySprite();
		/*
		 * anim = new Animation(); anim.addFrame(character1, 1250);
		 * anim.addFrame(character2, 50); currentSprite = anim.getImage();
		 */
	}
	
	private void settingsUI(){
		if (null == iconScreen) {
			iconScreen = new ImageIcon(getClass().getResource("/data/icon3.png")).getImage();
			this.setIconImage(iconScreen);
		
			setLayout(new BorderLayout());
			setContentPane(new JLabel(new ImageIcon(getClass().getResource("/data/menuScreen.png"))));
			
			setLayout(null);
		}
		
		JButton azertyButton = new JButton(new ImageIcon((azerty)?(getClass().getResource("/data/buttonAzertyRed.png")):(getClass().getResource("/data/buttonQwertyRed.png"))));
		azertyButton.setRolloverIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonAzertyWhite.png")):(getClass().getResource("/data/buttonQwertyWhite.png"))));
		

		azertyButton.setBorderPainted(false);
		azertyButton.setContentAreaFilled(false);
		
		add(azertyButton);
		
		JButton mainMenuButton = new JButton(new ImageIcon((getClass().getResource("/data/buttonMainMenuRed.png"))));
		mainMenuButton.setRolloverIcon(new ImageIcon((getClass().getResource("/data/buttonMainMenuWhite.png"))));

		mainMenuButton.setBorderPainted(false);
		mainMenuButton.setContentAreaFilled(false);
		
		add(mainMenuButton);
		
		JButton musicButton = new JButton(new ImageIcon((playMusic)?(getClass().getResource("/data/buttonMusicOnRed.png")):(getClass().getResource("/data/buttonMusicOffRed.png"))));
		musicButton.setRolloverIcon(new ImageIcon((playMusic)?(getClass().getResource("/data/buttonMusicOnWhite.png")):(getClass().getResource("/data/buttonMusicOffWhite.png"))));
		
		musicButton.setBorderPainted(false);
		musicButton.setContentAreaFilled(false);
		
		add(musicButton);
		
		azertyButton.setBounds(500, 15, 150, 75);
		mainMenuButton.setBounds(150, 15, 150, 75);
		musicButton.setBounds(800, 15, 150, 75);
		
		JLabel moveUpButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsZ.png")):(getClass().getResource("/data/buttonSettingsW.png"))));
		JLabel moveDownButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsS.png")):(getClass().getResource("/data/buttonSettingsS.png"))));
		JLabel moveLeftButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsQ.png")):(getClass().getResource("/data/buttonSettingsA.png"))));
		JLabel moveRightButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsD.png")):(getClass().getResource("/data/buttonSettingsD.png"))));
		JLabel shootUpButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsArrowUp.png")):(getClass().getResource("/data/buttonSettingsW.png"))));
		JLabel shootDownButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsArrowDown.png")):(getClass().getResource("/data/buttonSettingsW.png"))));
		JLabel shootLeftButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsArrowLeft.png")):(getClass().getResource("/data/buttonSettingsW.png"))));
		JLabel shootRightButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsArrowRight.png")):(getClass().getResource("/data/buttonSettingsW.png"))));
		JLabel weaponsButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsE.png")):(getClass().getResource("/data/buttonSettingsE.png"))));
		JLabel armorsButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsA.png")):(getClass().getResource("/data/buttonSettingsQ.png"))));
		JLabel hatsButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsH.png")):(getClass().getResource("/data/buttonSettingsH.png"))));
		JLabel pauseButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsSpace.png")):(getClass().getResource("/data/buttonSettingsSpace.png"))));
		JLabel quitButton = new JLabel(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsEsc.png")):(getClass().getResource("/data/buttonSettingsEsc.png"))));
		add(moveUpButton);
		add(moveDownButton);
		add(moveLeftButton);
		add(moveRightButton);
		add(shootUpButton);
		add(shootDownButton);
		add(shootLeftButton);
		add(shootRightButton);
		add(weaponsButton);
		add(armorsButton);
		add(hatsButton);
		add(pauseButton);
		add(quitButton);
		moveUpButton.setBounds(800, 125, 200, 25);
		moveDownButton.setBounds(800, 175, 200, 25);
		moveLeftButton.setBounds(800, 225, 200, 25);
		moveRightButton.setBounds(800, 275, 200, 25);
		shootUpButton.setBounds(800, 325, 200, 25);
		shootDownButton.setBounds(800, 375, 200, 25);
		shootLeftButton.setBounds(800, 425, 200, 25);
		shootRightButton.setBounds(800, 475, 200, 25);
		weaponsButton.setBounds(800, 525, 200, 25);
		armorsButton.setBounds(800, 575, 200, 25);
		hatsButton.setBounds(800, 625, 200, 25);
		pauseButton.setBounds(800, 675, 200, 25);
		quitButton.setBounds(800, 725, 200, 25);
		
		azertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (azerty == false) {
					azertyButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonAzertyRed.png")));
					azertyButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonAzertyWhite.png")));
					azerty = true;
				} else {
					azertyButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonQwertyRed.png")));
					azertyButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonQwertyWhite.png")));
					azerty = false;
				}
				moveUpButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsZ.png")):(getClass().getResource("/data/buttonSettingsW.png"))));
				moveDownButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsS.png")):(getClass().getResource("/data/buttonSettingsS.png"))));
				moveLeftButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsQ.png")):(getClass().getResource("/data/buttonSettingsA.png"))));
				moveRightButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsD.png")):(getClass().getResource("/data/buttonSettingsD.png"))));
				shootUpButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsArrowUp.png")):(getClass().getResource("/data/buttonSettingsArrowUp.png"))));
				shootDownButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsArrowDown.png")):(getClass().getResource("/data/buttonSettingsArrowDown.png"))));
				shootLeftButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsArrowLeft.png")):(getClass().getResource("/data/buttonSettingsArrowLeft.png"))));
				shootRightButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsArrowRight.png")):(getClass().getResource("/data/buttonSettingsArrowRight.png"))));
				weaponsButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsE.png")):(getClass().getResource("/data/buttonSettingsE.png"))));
				armorsButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsA.png")):(getClass().getResource("/data/buttonSettingsQ.png"))));
				hatsButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsH.png")):(getClass().getResource("/data/buttonSettingsH.png"))));
				pauseButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsSpace.png")):(getClass().getResource("/data/buttonSettingsSpace.png"))));
				quitButton.setIcon(new ImageIcon((azerty)?(getClass().getResource("/data/buttonSettingsEsc.png")):(getClass().getResource("/data/buttonSettingsEsc.png"))));
			}
		});
		
		mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	contentPane = getContentPane();
    			contentPane.removeAll();
    			contentPane.invalidate();
            	initUI();
				me.validate();
				me.repaint();
            	//state = GameState.Running;
            }
        });
		
		musicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(playMusic){
					musicButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonMusicOffRed.png")));
					musicButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonMusicOffWhite.png")));
					playMusic = false;
				} else {
					musicButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonMusicOnRed.png")));
					musicButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonMusicOnWhite.png")));
					playMusic = true;
				}
			}
		});
		
		JLabel moveUpLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsMoveUp.png"))));
		JLabel moveDownLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsMoveDown.png"))));
		JLabel moveLeftLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsMoveLeft.png"))));
		JLabel moveRightLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsMoveRight.png"))));
		JLabel shootUpLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsShootUp.png"))));
		JLabel shootDownLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsShootDown.png"))));
		JLabel shootLeftLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsShootLeft.png"))));
		JLabel shootRightLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsShootRight.png"))));
		JLabel weaponsLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsWeapons.png"))));
		JLabel armorsLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsArmors.png"))));
		JLabel hatsLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsHats.png"))));
		JLabel pauseLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsPause.png"))));
		JLabel quitLabel = new JLabel(new ImageIcon((getClass().getResource("/data/buttonSettingsQuit.png"))));
		add(moveUpLabel);
		add(moveDownLabel);
		add(moveLeftLabel);
		add(moveRightLabel);
		add(shootUpLabel);
		add(shootDownLabel);
		add(shootLeftLabel);
		add(shootRightLabel);
		add(weaponsLabel);
		add(armorsLabel);
		add(hatsLabel);
		add(pauseLabel);
		add(quitLabel);
		moveUpLabel.setBounds(650, 125, 200, 25);
		moveDownLabel.setBounds(650, 175, 200, 25);
		moveLeftLabel.setBounds(650, 225, 200, 25);
		moveRightLabel.setBounds(650, 275, 200, 25);
		shootUpLabel.setBounds(650, 325, 200, 25);
		shootDownLabel.setBounds(650, 375, 200, 25);
		shootLeftLabel.setBounds(650, 425, 200, 25);
		shootRightLabel.setBounds(650, 475, 200, 25);
		weaponsLabel.setBounds(650, 525, 200, 25);
		armorsLabel.setBounds(650, 575, 200, 25);
		hatsLabel.setBounds(650, 625, 200, 25);
		pauseLabel.setBounds(650, 675, 200, 25);
		quitLabel.setBounds(650, 725, 200, 25);
		
		
		
		
	}

	private void initUI() {
		if (null == iconScreen) {
			iconScreen = new ImageIcon(getClass().getResource("/data/icon3.png")).getImage();
			this.setIconImage(iconScreen);
		
			setLayout(new BorderLayout());
			setContentPane(new JLabel(new ImageIcon(getClass().getResource("/data/menuScreen.png"))));
			
			setLayout(null);
		}
		
		JButton startButton = new JButton(new ImageIcon(getClass().getResource("/data/buttonStartRed.png")));
		startButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonStartWhite.png")));

		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		
		add(startButton);
		
		JButton settingsButton = new JButton(new ImageIcon(getClass().getResource("/data/buttonSettingsRed.png")));
		settingsButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonSettingsWhite.png")));

		settingsButton.setBorderPainted(false);
		settingsButton.setContentAreaFilled(false);
		
		add(settingsButton);
		
		JButton quitButton = new JButton(new ImageIcon(getClass().getResource("/data/buttonQuitRed.png")));
		quitButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonQuitWhite.png")));

		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		
		add(quitButton);
		
		final JButton levelButton = new JButton(new ImageIcon(getClass().getResource("/data/buttonLevel1Red.png")));
		levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel1White.png")));

		levelButton.setBorderPainted(false);
		levelButton.setContentAreaFilled(false);
		
		switch(currentlevel) {
		case 1:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel1Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel1White.png")));
			break;
		case 2:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel2Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel2White.png")));
			break;
		case 3:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel3Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel3White.png")));
			break;
		case 4:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel4Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel4White.png")));
			break;
		case 5:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel5Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel5White.png")));
			break;
		case 6:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel6Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel6White.png")));
			break;
		case 7:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel7Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel7White.png")));
			break;
		case 8:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel8Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel8White.png")));
			break;
		case 9:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel9Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel9White.png")));
			break;
		case 10:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel10Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel10White.png")));
			break;
		case 11:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel11Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel11White.png")));
			break;
		case 12:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel12Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel12White.png")));
			break;
		case 13:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel13Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel13White.png")));
			break;
		case 14:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel14Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel14White.png")));
			break;
		case 15:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel15Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel15White.png")));
			break;
		case 16:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel16Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel16White.png")));
			break;
		case 17:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel17Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel17White.png")));
			break;
		case 18:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel18Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel18White.png")));
			break;
		case 19:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel19Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel19White.png")));
			break;
		case 20:
			levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel20Red.png")));
			levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel20White.png")));
			break;
		}
		
		add(levelButton);
		
		final JButton diffButton = new JButton(new ImageIcon(getClass().getResource("/data/buttonDiffEasyRed.png")));
		diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffEasyWhite.png")));

		diffButton.setBorderPainted(false);
		diffButton.setContentAreaFilled(false);
		
		switch(difficultylevel) {
		case 1:
			diffButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonDiffEasyRed.png")));
			diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffEasyWhite.png")));
			break;
		case 2:
			diffButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonDiffNormalRed.png")));
			diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffNormalWhite.png")));
			break;
		case 3:
			diffButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonDiffHardRed.png")));
			diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffHardWhite.png")));
			break;
		case 4:
			diffButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonDiffExtremeRed.png")));
			diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffExtremeWhite.png")));
			break;
		}
		
		add(diffButton);
		
		startButton.setBounds(670, 100, 150, 75);
		settingsButton.setBounds(670, 210, 150, 75);
		quitButton.setBounds(670, 320, 150, 75);
		
		levelButton.setBounds(1100, 15, 150, 75);
		diffButton.setBounds(1100, 110, 150, 75);
		
		levelButton.addMouseListener(new MouseAdapter(){
            boolean pressed;

            @Override
            public void mousePressed(MouseEvent e) {
            	levelButton.getModel().setArmed(true);
            	levelButton.getModel().setPressed(true);
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //if(isRightButtonPressed) {underlyingButton.getModel().setPressed(true));
            	levelButton.getModel().setArmed(false);
            	levelButton.getModel().setPressed(false);

                if (pressed) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                    	currentlevel--;
        				if (currentlevel < 1) {
        					currentlevel = currentmaxlevel;
        				}
                    }
                    else {
                    	currentlevel++;
        				if (currentlevel > currentmaxlevel) {
        					currentlevel = 1;
        				}
                    }
                }
				
				switch(currentlevel) {
				case 1:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel1Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel1White.png")));
					break;
				case 2:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel2Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel2White.png")));
					break;
				case 3:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel3Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel3White.png")));
					break;
				case 4:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel4Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel4White.png")));
					break;
				case 5:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel5Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel5White.png")));
					break;
				case 6:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel6Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel6White.png")));
					break;
				case 7:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel7Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel7White.png")));
					break;
				case 8:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel8Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel8White.png")));
					break;
				case 9:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel9Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel9White.png")));
					break;
				case 10:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel10Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel10White.png")));
					break;
				case 11:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel11Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel11White.png")));
					break;
				case 12:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel12Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel12White.png")));
					break;
				case 13:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel13Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel13White.png")));
					break;
				case 14:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel14Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel14White.png")));
					break;
				case 15:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel15Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel15White.png")));
					break;
				case 16:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel16Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel16White.png")));
					break;
				case 17:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel17Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel17White.png")));
					break;
				case 18:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel18Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel18White.png")));
					break;
				case 19:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel19Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel19White.png")));
					break;
				case 20:
					levelButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonLevel20Red.png")));
					levelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonLevel20White.png")));
					break;
				}
				//levelButton.setText("Level: " + currentlevel);

                pressed = false;

            }

            @Override
            public void mouseExited(MouseEvent e) {
                pressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pressed = true;
            }                    
        });

		diffButton.addMouseListener(new MouseAdapter(){
            boolean pressed;

            @Override
            public void mousePressed(MouseEvent e) {
            	levelButton.getModel().setArmed(true);
            	levelButton.getModel().setPressed(true);
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //if(isRightButtonPressed) {underlyingButton.getModel().setPressed(true));
            	levelButton.getModel().setArmed(false);
            	levelButton.getModel().setPressed(false);

                if (pressed) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                    	if (difficultylevel == 1) {
        					difficultylevel = 4;
        				} else {
        					difficultylevel--;
        				}
                    }
                    else {
                    	if (difficultylevel == 4) {
        					difficultylevel = 1;
        				} else {
        					difficultylevel++;
        				}
                    }
                    switch(difficultylevel) {
    				case 1:
    					diffButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonDiffEasyRed.png")));
    					diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffEasyWhite.png")));
    					break;
    				case 2:
    					diffButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonDiffNormalRed.png")));
    					diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffNormalWhite.png")));
    					break;
    				case 3:
    					diffButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonDiffHardRed.png")));
    					diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffHardWhite.png")));
    					break;
    				case 4:
    					diffButton.setIcon(new ImageIcon(getClass().getResource("/data/buttonDiffExtremeRed.png")));
    					diffButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonDiffExtremeWhite.png")));
    					break;
    				}
                    pressed = false;
                }
            }
		});

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				endlevelmenuloaded = false;
				clean();
				// Initialize Tiles
				bg.setCenterX(0);
				bg.setCenterY(0);
				bginity = bg.getCenterY() - 15;
				try {
					loadMap("/data/" + Level.getMapName(currentlevel));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (!threadstarted) {
					start();
				} else {
					player.setHealth(player.getMaxHealth());
					player.getArmor().setDefense(player.getArmor().MAXDEF);
				}
				state = GameState.Running;
				deathCountdown = 180;
				contentPane = getContentPane();
				contentPane.removeAll();
				contentPane.invalidate();
				me.validate();
				me.repaint();
			}
		});

		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				state = GameState.Exit;
				System.exit(0);
			}
		});
		
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				contentPane = getContentPane();
    			contentPane.removeAll();
    			contentPane.invalidate();
				settingsUI();
				me.validate();
				me.repaint();
			}
		});

		/*createLayout(startButton);
		createLayout(settingsButton);
		createLayout(quitButton);
		createLayout(levelButton);
		createLayout(diffButton);
		createLayout(azertyButton);*/

		setTitle("Pizza Tales");
		setSize(1280, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent... arg) {

		contentPane = getContentPane();
		GroupLayout gl = new GroupLayout(contentPane);
		contentPane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));

		gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]));
	}
	
	public void teststart() {
		bg.setCenterX(0);
		bg.setCenterY(0);
		bginity = bg.getCenterY() - 15;
		try {
			loadMap("/data/" + Level.getMapName(currentlevel));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
		state = GameState.Running;
		deathCountdown = 180;
		me.validate();
	}

	// @Override
	public void start() {
		threadstarted = true;
		Thread thread = new Thread(this);
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}

	private void loadMap(String filename) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		blockmaxheight = 0;
		
		maskminx = -1;
		maskmaxx = -1;
		maskminy = -1;
		maskmaxy = -1;
		maskphase = 1;
		/*if (clip != null && clip.isOpen())
			clip.close();*/
		if (playMusic){
			if (clip != null)
				clip.close();
			URL url = getClass().getResource("/data/"+Level.getClip(currentlevel));
			try {
				clip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(url);
				clip.open(ais);
			} catch (Exception e) {
				e.printStackTrace();
			}
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-15.0f);
	
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));
		String line;
		width = Integer.MAX_VALUE;
		HashMap<Integer,Integer> exits = new HashMap<Integer,Integer>();
		while (null != (line = reader.readLine())) {
			// no more lines to read
			if (line.startsWith("#")) {
				if (line.contains("Exit")) {
					String[] specifiedexit = line.split("\r");
					
				}
			} else {
				if (!line.startsWith("!")) {
					lines.add(line);
					if (!line.contains("["))
						width = Math.min(width, line.length());
				}
			}
		}
		reader.close();
		height = lines.size();
		levelwithxscrolling = false;
		levelwithyscrolling = false;
		if (height > 16)
			levelwithyscrolling = true;
		if (width > 26)
			levelwithxscrolling = true;

		map = new BlockingStuff[width][height];
		char[][] charmap = new char[width][height];
		heightitemmap = new int[width][height];
		for (int i = 0; i < width; i++)
			Arrays.fill(heightitemmap[i], -1);
		backgroundmap = new Image[width][height];
		maskmap = new Image[width][height][3];
		Image background = new ImageIcon(getClass().getResource("/data/"+Level.getBackground(currentlevel))).getImage();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				backgroundmap[i][j] = background;
			}
		}
		HashMap<Integer, EntryDoor> mentrydoors = new HashMap<Integer, EntryDoor>();
		HashMap<Integer, Tile> doors = new HashMap<Integer, Tile>();
		HashMap<Integer, HiddenTrigger> hiddentriggers = new HashMap<Integer, HiddenTrigger>();

		int posplayery = 0;
		int posplayerx = 0;

		boolean inBlock = false;
		for (int j = 0; j < height; j++) {
			line = lines.get(j);
			int widthl = width;
			inBlock = false;
			int k = 0;
			int itemheight = 0;
			for (int i = 0; i < widthl; i++) {
				char c = line.charAt(i);
				if (c != '[' && c != ']')
					charmap[k][j] = line.charAt(i);
				if (charmap[k][j] == 'U') {
					posplayerx = k;
					posplayery = j;
				}
				if (c == '[') {
					inBlock = true;
					itemheight = -1;
				}
				if (c == ']') {
					inBlock = false;
					itemheight = 0;
				}
				if (inBlock) {
					if (itemheight > blockmaxheight)
						blockmaxheight = itemheight;
					itemheight++;
					widthl++;
				} else
					k++;
			}
		}
		items = new Item[width][height][blockmaxheight+10];
		int deltapx = 0;
		int deltapy = 0;
		if (levelwithxscrolling)
			deltapx = 13 - posplayerx;
		if (levelwithyscrolling)
			deltapy = 8 - posplayery;
		if (levelwithxscrolling)
			bginitx = -700;
		else
			bginitx = 0;
		if (levelwithyscrolling)
			bginity = -400;
		else
			bginity = 0;
		bg.setCenterX(bginitx + 50 * deltapx);
		bg.setCenterY(bginity + 50 * deltapy);
		bginity -= 15;

		for (int j = 0; j < height; j++) {
			line = lines.get(j);
			int widthl = width;
			inBlock = false;
			int k = 0;
			int itemheight = 0;
			for (int i = 0; i < widthl; i++) {
				char ch = line.charAt(i);
				if (ch == '[') {
					inBlock = true;
					itemheight = 0;
				}
				if (ch == ']') {
					heightitemmap[k][j] = itemheight - 1;
					inBlock = false;
					itemheight = 0;
				}
				if (ch == 'U') {
					player.setCenterX(50 * (k + deltapx) + 25);
					player.setCenterY(50 * (j + deltapy) + 40);
					map[player.posx][player.posy] = player;
				}
				if (ItemFactory.isItemSupported(ch)) {
					Item it = ItemFactory.getItem(k, j, deltapx, deltapy, ch, itemheight);
					items[k][j][itemheight] = it;
					if (ch == 'i') {
						mentrydoors.put(height * k + j, (EntryDoor) it);
					}
					if (ch == 'm') {
						hiddentriggers.put(height * k + j, (HiddenTrigger) it);
					}
					if (inBlock)
						itemheight++;
					else
						heightitemmap[k][j] = 0;
				}
				if (BackgroundFactory.isBackgroundImage(ch)) {
					backgroundmap[k][j] = BackgroundFactory.getBackground(ch);
				}
				
				if (TileFactory.isTileTypeSupported(ch)) {
					Tile t = TileFactory.getTile(k + deltapx, j + deltapy, ch);
					tilearray.add(t);
					if (ch == 'd')
						doors.put(height * k + j, t);
					if (DestroyableTile.class.isInstance(t))
						destroyabletiles.add((DestroyableTile) t);
				}
				if (EnemyFactory.isTileTypeSupported(ch)) {
					getEnemyarray().add(EnemyFactory.getEnemy(k + deltapx, j + deltapy, ch, this));
				}
				if (inBlock) {
					widthl++;
				} else
					k++;
			}
		}
		Level.bitmask(currentlevel, backgroundmap, maskmap);
		int k = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (charmap[i][j] == 'f') {
					ArrayList<Integer> nonobstacles = new ArrayList<Integer>();
					MapUtil.getAccessibleArea(i, j, 100, charmap, nonobstacles, mentrydoors, doors, k);
					if (!nonobstacles.isEmpty()) {
						int l = 0;
						ArrayList<Enemy> lenemies = new ArrayList<Enemy>();
						while (l < enemyarray.size()) {
							if (nonobstacles.contains(height * enemyarray.get(l).posx + enemyarray.get(l).posy)) {
								lenemies.add(enemyarray.get(l));
								enemyarray.get(l).sleep();
								enemyarray.get(l).setIsInArena(true);
							}
							l++;
						}
						l = 0;
						arenaenemies.add(lenemies);
						//arenacenters.add(new SimpleEntry<Integer, Integer>(i, j));
						arenainsidearea.add(nonobstacles);
						k++;
					}
				}
			}
		}
		for (EntryDoor e : mentrydoors.values()) {
			if (e.isGoingIn() < 0)
				removeItem(e);
			else
				entrydoors.add(e);
		}
		k = 0;
		HashMap<Integer, Character> area = new HashMap<Integer, Character>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (charmap[i][j] == 'z') {
					MapUtil.getHiddenAccesibleArea(i, j, 100, charmap, area, hiddentriggers, k);
					if (!area.isEmpty()) {
						int l = 0;
						ArrayList<Enemy> lenemies = new ArrayList<Enemy>();
						while (l < enemyarray.size()) {
							if (area.containsKey(height * enemyarray.get(l).posx + enemyarray.get(l).posy)) {
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
							int postx = (tilearray.get(l).getCenterX() - bg.getCenterX() + bginitx) / 50;
							int posty = (tilearray.get(l).getCenterY() - bg.getCenterY() + bginity) / 50;
							if (area.containsKey(height * postx + posty)) {
								tilearray.get(l).hideImage(Level.getHidingImage(currentlevel));
								ltiles.add(tilearray.get(l));
								tilearray.remove(l);
							} else
								l++;
						}
						for (Entry<Integer, Character> entry : area.entrySet()) {
							if (!TileFactory.isTileTypeSupported(entry.getValue())) {
								Tile t = TileFactory.getTile(entry.getKey() / height + deltapx,
										entry.getKey() % height + deltapy, entry.getValue());
								t.hideImage(Level.getHidingImage(currentlevel));
								tilearray.add(t);
								ltiles.add(t);
							}
						}
						l = 0;
						for (int m = 0; m < width; m++) {
							for (int n = 0; n < height; n++) {
								if (area.containsKey(height * m + n)) {
									for (int h = 0; h <= heightitemmap[m][n]; h++) {
										litems.add(items[m][n][h]);
										items[m][n][h] = null;
									}
									heightitemmap[m][n] = -1;
								}
							}
						}
						hiddentiles.add(ltiles);
						hiddenitems.add(litems);
						k++;
					}
				}
			}
		}
		for (Enemy e : enemyarray) {
			e.initWaitingPattern();
		}
		if (true) {
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

	/*
	 * @Override public void stop() { super.stop(); }
	 */

	/*
	 * @Override public void destroy() { super.destroy(); }
	 */

	@Override
	public void run() {
		if (state == GameState.Running) {
			// TODO soundtrack.loop();
			while (state != GameState.Exit) {
				while (state == GameState.Running) {
					// computationtime += System.nanoTime() - nanoclock;
					try {
						Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// nanoclock = System.nanoTime();
					clock = System.currentTimeMillis();
					if (TESTMODE) {
						if (clock > fpsclock + 1000) {
							fpsclock = clock;
							fps = fpscount;
							fpscount = 0;
							/*
							 * cmptime = computationtime / fps / 1000;
							 * computationtime = 0;
							 */
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
					player.initState();
					for (Enemy e : enemyarray) {
						e.canmovedown = true;
						e.canmoveleft = true;
						e.canmoveright = true;
						e.canmoveup = true;
						e.sliding = false;
						e.initSpeed();
					}
					checkEnemiesCollision();
					player.checkCollisionsWithBlockingStuff();
					checkItemsCollision();
					map[player.posx][player.posy] = null;
					updatePlayer();
					updateExplosions();
					callEnemiesAIs();
					for (Enemy e : getEnemyarray()) {
						if (e.alive)
							map[e.posx][e.posy] = null;
					}
					updateEnemies();
					int exi = 0;
					int exsize = explosions.size();
					while (exi < exsize) {
						Explosion e = explosions.get(exi);
						int dt = 0;
						while (dt < destroyabletiles.size()) {
							if (e.isProcing() && e.getR().intersects(destroyabletiles.get(dt).R)) {
								if (!destroyabletiles.get(dt).damage(e.damage))
									dt++;
							} else
								dt++;
						}
						exi++;
					}
					bg.update();
					// animate();
					updateTiles();
					updateItems();
					repaint(); // this calls paint

					if (isInArena < 0 && activatedentry != null) {
						int playerposx = (player.getCenterX() - bg.getCenterX() + bginitx) / 50;
						int playerposy = (player.getCenterY() - bg.getCenterY() + bginity) / 50;
						for (Tile t : activatedentry.getDoors()) {
							map[t.posx][t.posy] = null;
						}
						int dirplace = 0;
						int difPX = 50*player.posx+25+bg.getCenterX()-StartingClass.bginitx - player.getCenterX();
						int difPY = 50*player.posy+25+bg.getCenterY()-StartingClass.bginity - player.getCenterY();
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
						if (pf.getDirection(playerposx, playerposy, activatedentry.getOut().getPosX(),
								activatedentry.getOut().getPosY(), 10, player.canmoveleft, player.canmoveup,
								player.canmoveright, player.canmovedown, dirplace, true) > 0) {
							for (Enemy e : enemyarray) {
								e.sleep();
							}
							int l = 0;
							while (l < entrydoors.size()) {
								if (entrydoors.get(l).isGoingIn() == activatedentry.isGoingIn()) {
									removeItem(entrydoors.get(l));
								}
								l++;
							}
							for (Tile t : activatedentry.getDoors()) {
								tilearray.remove(t);
							}
							isInArena = activatedentry.isGoingIn();

							/*int arenacentery = arenacenters.get(isInArena).getValue() * 50 + 25 + bg.getCenterY()
									- bginity;
							int arenacenterx = arenacenters.get(isInArena).getKey() * 50 + 25 + bg.getCenterX()
									- bginitx;*/
							long sumx = 0;
							long sumy = 0;
							for (int ii : arenainsidearea.get(isInArena)) {
								sumx += (ii / height);
								sumy += (ii % height);
							}
							int cix = (int)(sumx/((float)arenainsidearea.get(isInArena).size())* 50 + 25)  + bg.getCenterX() - bginitx;
							int ciy = (int)(sumy/((float)arenainsidearea.get(isInArena).size())* 50 + 25)  + bg.getCenterY() - bginity;
							int arenacenterx = cix;
							int arenacentery = ciy;
							int bgaix = bg.getCenterX();
							int bgaiy = bg.getCenterY();
							player.setScrollingSpeedX(0);
							player.setScrollingSpeedY(0);
							if (levelwithyscrolling) {
								if (arenacentery > 400)
									player.setScrollingSpeedY(player.getMOVESPEED());
								else
									player.setScrollingSpeedY(-player.getMOVESPEED());
							}
							if (levelwithxscrolling) {
								if (arenacenterx > 640)
									player.setScrollingSpeedX(player.getMOVESPEED());
								else
									player.setScrollingSpeedX(-player.getMOVESPEED());
							}
							/*System.out.println("sums: " + sumx+ "-" + sumy);
							System.out.println("sums: " + sumx+ "-" + sumy);
							System.out.println("arenacenter: " + cix+ "-" + ciy);*/
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
							while ((levelwithyscrolling && Math.abs(arenacentery - 400) > 6)
									|| (levelwithxscrolling && Math.abs(arenacenterx - 640) > 6) || !foundposition) {
								// computationtime += System.nanoTime() -
								// nanoclock;
								try {
									Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								// nanoclock = System.nanoTime();
								clock = System.currentTimeMillis();
								if (TESTMODE) {
									if (clock > fpsclock + 1000) {
										fpsclock = clock;
										fps = fpscount;
										fpscount = 0;
										/*
										 * cmptime = computationtime / fps /
										 * 1000; computationtime = 0;
										 */
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
								player.initState();
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
													e.damage(p.damage);
													if (!e.alive) {
														if (player.getHealth() + 1 < player.getMaxHealth())
															player.setHealth(player.getHealth() + 1);
														else
															player.setHealth(player.getMaxHealth());
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
											if (DestroyableTile.class.isInstance(t))
												((DestroyableTile) t).damage(p.damage);
										}
										i++;
									} else {
										projectiles.remove(i);
									}
								}
								for (Explosion e : explosions) {
									if (e.isProcing() && player.R.intersects(e.getR())) {
										player.damage(e.damage);
									}
								}
								playerposx = (player.getCenterX() - bg.getCenterX() + bginitx + deltapx) / 50;
								playerposy = (player.getCenterY() - bg.getCenterY() + bginity + deltapy) / 50;
								map[player.posx][player.posy] = null;
								if (!foundposition) {
									dirplace = 0;
									difPX = 50*player.posx+25+bg.getCenterX()-StartingClass.bginitx - player.getCenterX();
									difPY = 50*player.posy+25+bg.getCenterY()-StartingClass.bginity - player.getCenterY();
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
									switch (pf.getDirection(playerposx, playerposy, activatedentry.getOut().getPosX(),
											activatedentry.getOut().getPosY(), 10, player.canmoveleft, player.canmoveup,
											player.canmoveright, player.canmovedown, dirplace, true)) {
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
								updateExplosions();
								for (Enemy e : getEnemyarray()) {
									if (e.alive)
										map[e.posx][e.posy] = null;
								}
								updateEnemies();
								exi = 0;
								exsize = 0;
								while (exi < exsize) {
									Explosion e = explosions.get(exi);
									int dt = 0;
									while (dt < destroyabletiles.size()) {
										if (e.isProcing() && e.getR().intersects(destroyabletiles.get(dt).R)) {
											if (!destroyabletiles.get(dt).damage(e.damage))
												dt++;
										} else
											dt++;
									}
									exi++;
								}
								bg.update();
								updateTiles();
								for (Tile t : activatedentry.getDoors())
									t.update();
								updateItems();
								repaint();
								/*
								arenacentery = arenacenters.get(isInArena).getValue() * 50 + 25 + bg.getCenterY() - bginity;
								arenacenterx = arenacenters.get(isInArena).getKey() * 50 + 25 + bg.getCenterX() - bginitx;*/
								arenacenterx = cix + bg.getCenterX() - bgaix;
								arenacentery = ciy + bg.getCenterY() - bgaiy;
								if (Math.abs(arenacentery - 400) < 6)
									player.setScrollingSpeedY(0);
								if (Math.abs(arenacenterx - 640) < 6)
									player.setScrollingSpeedX(0);

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
						i = 0;
						while (i < leavingitems.size()) {
							if (Boost.class.isInstance(leavingitems.get(i)))
								leavingitems.remove(i);
							else
								i++;
						}
						player.isGrinning = 0;
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
					if (ScrollingMode > 0 && (levelwithxscrolling || levelwithyscrolling) && centeringOnPlayerRequest) {
						int deltax = 0;
						int deltay = 0;
						int finaldeltax = 0;
						int finaldeltay = 0;
						if (levelwithxscrolling) {
							deltax = (640 - player.getCenterX()) / 10;
							finaldeltax = 640 - player.getCenterX() - deltax * 9;
						}
						if (levelwithyscrolling) {
							deltay = (400 - player.getCenterY()) / 10;
							finaldeltay = 400 - player.getCenterY() - deltay * 9;
						}
						for (int l = 0; l < 9; l++) {
							bg.setCenterX(bg.getCenterX() + deltax);
							bg.setCenterY(bg.getCenterY() + deltay);
							for (Tile t : tilearray) {
								t.setCenterX(t.getCenterX() + deltax);
								t.setCenterY(t.getCenterY() + deltay);
							}
							for (Enemy e : enemyarray) {
								e.setCenterX(e.getCenterX() + deltax);
								e.setCenterY(e.getCenterY() + deltay);
								for (Projectile p : e.getProjectiles()) {
									p.setCenterX(p.getCenterX() + deltax);
									p.setCenterY(p.getCenterY() + deltay);
								}
							}
							for (Projectile p : player.getProjectiles()) {
								p.setCenterX(p.getCenterX() + deltax);
								p.setCenterY(p.getCenterY() + deltay);
							}
							for (int itx = 0; itx < width; itx++) {
								for (int ity = 0; ity < height; ity++) {
									for (int ith = 0; ith <= heightitemmap[itx][ity]; ith++) {
										items[itx][ity][ith].setCenterX(items[itx][ity][ith].getCenterX() + deltax);
										items[itx][ity][ith].setCenterY(items[itx][ity][ith].getCenterY() + deltay);
									}
								}
							}
							for (Item it : leavingitems) {
								it.setCenterX(it.getCenterX() + deltax);
								it.setCenterY(it.getCenterY() + deltay);
							}
							for (Explosion e : explosions) {
								e.setCenterX(e.getCenterX() + deltax);
								e.setCenterY(e.getCenterY() + deltay);
							}
							player.setCenterX(player.getCenterX() + deltax);
							player.setCenterY(player.getCenterY() + deltay);
							repaint();
							// computationtime += System.nanoTime() - nanoclock;
							try {
								Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							// nanoclock = System.nanoTime();
							clock = System.currentTimeMillis();
							if (TESTMODE) {
								if (clock > fpsclock + 1000) {
									fpsclock = clock;
									fps = fpscount;
									fpscount = 0;
									/*
									 * cmptime = computationtime / fps / 1000;
									 * computationtime = 0;
									 */
								} else {
									fpscount++;
								}
							}
						}
						bg.setCenterX(bg.getCenterX() + finaldeltax);
						bg.setCenterY(bg.getCenterY() + finaldeltay);
						for (Tile t : tilearray) {
							t.setCenterX(t.getCenterX() + finaldeltax);
							t.setCenterY(t.getCenterY() + finaldeltay);
						}
						for (Enemy e : enemyarray) {
							e.setCenterX(e.getCenterX() + finaldeltax);
							e.setCenterY(e.getCenterY() + finaldeltay);
							for (Projectile p : e.getProjectiles()) {
								p.setCenterX(p.getCenterX() + finaldeltax);
								p.setCenterY(p.getCenterY() + finaldeltay);
							}
						}
						for (Projectile p : player.getProjectiles()) {
							p.setCenterX(p.getCenterX() + finaldeltax);
							p.setCenterY(p.getCenterY() + finaldeltay);
						}
						for (int itx = 0; itx < width; itx++) {
							for (int ity = 0; ity < height; ity++) {
								for (int ith = 0; ith <= heightitemmap[itx][ity]; ith++) {
									items[itx][ity][ith].setCenterX(items[itx][ity][ith].getCenterX() + finaldeltax);
									items[itx][ity][ith].setCenterY(items[itx][ity][ith].getCenterY() + finaldeltay);
								}
							}
						}
						for (Item it : leavingitems) {
							it.setCenterX(it.getCenterX() + finaldeltax);
							it.setCenterY(it.getCenterY() + finaldeltay);
						}
						for (Explosion e : explosions) {
							e.setCenterX(e.getCenterX() + finaldeltax);
							e.setCenterY(e.getCenterY() + finaldeltay);
						}
						player.setCenterX(player.getCenterX() + finaldeltax);
						player.setCenterY(player.getCenterY() + finaldeltay);
						centeringOnPlayerRequest = false;
					}
					if (ScrollingMode > 0 && toggleScrollingModeRequest) {
						ScrollingMode = 3 - ScrollingMode;
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
							it.setCenterX(50 * it.posx + bg.getCenterX() - bginitx);
							it.setCenterY(50 * it.posy + bg.getCenterY() - bginity);
							items[it.posx][it.posy][it.height] = it;
							heightitemmap[it.posx][it.posy] = Math.max(heightitemmap[it.posx][it.posy], it.height);
						}
						for (Enemy e : hiddenenemies.get(revealHidden)) {
							e.setCenterX(50 * e.posx + bg.getCenterX() - bginitx);
							e.setCenterY(50 * e.posy + bg.getCenterY() - bginity);
							enemyarray.add(e);
						}
						hiddentiles.get(revealHidden).clear();
						hiddenitems.get(revealHidden).clear();
						hiddenenemies.get(revealHidden).clear();
						revealHidden = -1;
					}
					if (player.getHealth() <= 0) {
						state = GameState.Dead;
						player.controlledstopMoving();
						player.setScrollingSpeedY(0);
						player.setScrollingSpeedX(0);
						clip.stop();
					}
				}
				if (!endlevelmenuloaded && state == GameState.Menu) {
					this.clean();
					clip.stop();
					if (currentlevel == currentmaxlevel) {
						currentmaxlevel = Math.min(maxlevel, currentmaxlevel+1);
					}
					saveGameState();
					initEndLevelScreen();
					endlevelmenuloaded = true;
				}
				if (state == GameState.Dead) {

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
					for (Enemy e : enemyarray) {
						e.canmovedown = true;
						e.canmoveleft = true;
						e.canmoveright = true;
						e.canmoveup = true;
						e.sliding = false;
						e.initSpeed();
					}
					checkItemsCollision();
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
					updateExplosions();
					callEnemiesAIs();
					for (Enemy e : getEnemyarray()) {
						if (e.alive)
							map[e.posx][e.posy] = null;
					}
					updateEnemies();
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
					
					if(deathCountdown == 0){
						state = GameState.Menu;
						endlevelmenuloaded = true;
						clean();
						initUI();
					}
					deathCountdown--;
				}
				
				
				repaint();
				// computationtime += System.nanoTime() - nanoclock;
				try {
					Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// nanoclock = System.nanoTime();
				clock = System.currentTimeMillis();
				if (TESTMODE) {
					if (clock > fpsclock + 1000) {
						fpsclock = clock;
						fps = fpscount;
						fpscount = 0;
						/*
						 * cmptime = computationtime / fps / 1000;
						 * computationtime = 0;
						 */
					} else {
						fpscount++;
					}
				}
			}
		}
	}
	
	public void initEndLevelScreen(){
		
		final JButton quitButton = new JButton(new ImageIcon(getClass().getResource("/data/buttonQuitRed.png")));
		quitButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonQuitWhite.png")));

		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		
		add(quitButton);
		
		final JButton menuButton = new JButton(new ImageIcon(getClass().getResource("/data/buttonMainMenuRed.png")));
		menuButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonMainMenuWhite.png")));

		menuButton.setBorderPainted(false);
		menuButton.setContentAreaFilled(false);
		
		add(menuButton);
        
        
        menuButton.setBounds(300, 15, 150, 75);
        quitButton.setBounds(670, 320, 150, 75);
        
        if(currentlevel < maxlevel){
        	
        	final JButton nextLevelButton = new JButton(new ImageIcon(getClass().getResource("/data/buttonNextLevelRed.png")));
    		nextLevelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/data/buttonNextLevelWhite.png")));

    		nextLevelButton.setBorderPainted(false);
    		nextLevelButton.setContentAreaFilled(false);
    		
    		add(nextLevelButton);
        	
        	nextLevelButton.setBounds(670, 210, 150, 75);
        	
        	nextLevelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                	if(currentlevel < maxlevel){
    	            	currentlevel++;
    					bg.setCenterX(0);
    					bg.setCenterY(0);
    					bginity = bg.getCenterY() - 15;
    					deathCountdown = 180;
    					try {
    						loadMap("/data/"+Level.getMapName(currentlevel));
    					} catch (IOException e) {
    						e.printStackTrace();
    					}
    					player.setHealth(player.getMaxHealth());
    					player.getArmor().setDefense(player.getArmor().MAXDEF);
    	            	state = GameState.Running;
    	            	endlevelmenuloaded = false;
    	            	
    	            	contentPane = getContentPane();
    					contentPane.removeAll();
    					contentPane.invalidate();
    					me.validate();
    					me.repaint();
                	}
                }
            });
        }
        
        
        
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	contentPane = getContentPane();
    			contentPane.removeAll();
    			contentPane.invalidate();
            	initUI();
            	//state = GameState.Running;
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	state = GameState.Exit;
                System.exit(0);
            }
        });

	}
	
	public void initDeathScreen(){
		final JButton replayButton = new JButton("Replay Level");
        final JButton quitButton = new JButton("Quit");
        final JButton menuButton = new JButton("Main Menu");
        
        replayButton.setBounds(590, 100, 100, 50);
        menuButton.setBounds(590, 200, 100, 50);
        quitButton.setBounds(590, 300, 100, 50);
        
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	//currentlevel++;
				bg.setCenterX(0);
				bg.setCenterY(0);
				bginity = bg.getCenterY() - 15;
				try {
					loadMap("/data/"+Level.getMapName(currentlevel));
				} catch (IOException e) {
					e.printStackTrace();
				}
				player.setHealth(player.getMaxHealth());
				player.getArmor().setDefense(player.getArmor().MAXDEF);
            	state = GameState.Running;
            	endlevelmenuloaded = false;
            	
            	contentPane = getContentPane();
				contentPane.removeAll();
				contentPane.invalidate();
				me.validate();
            }
        });
        
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	contentPane = getContentPane();
    			contentPane.removeAll();
    			contentPane.invalidate();
            	initUI();
            	//state = GameState.Running;
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        createLayout(replayButton);
        createLayout(menuButton);
        createLayout(quitButton);
	}

	/*
	 * public void update(Graphics g) { if (image == null) { image =
	 * createImage(this.getWidth(), this.getHeight()); second =
	 * image.getGraphics(); } second.setColor(getBackground());
	 * second.fillRect(0, 0, getWidth(), getHeight());
	 * second.setColor(getForeground()); paint(second);
	 * 
	 * g.drawImage(image, 0, 0, this); }
	 */

	private void paintOffScreen(Graphics g) {
		int stx = Math.max(0, (-50 - bg.getCenterX() + StartingClass.bginitx) / 50);
		int sty = Math.max(0, (-50 - bg.getCenterY() + StartingClass.bginity) / 50);
		int fx = Math.min(width, (1330 - bg.getCenterX() + StartingClass.bginitx) / 50);
		int fy = Math.min(height, (850 - bg.getCenterY() + StartingClass.bginity) / 50);
		int bbx = bg.getCenterX() - bginitx;
		int bby = bg.getCenterY() - bginity;
		if (remask) {
			remask = false;
			if (maskminx != -1)
				Level.bitmask(currentlevel, backgroundmap, maskmap, maskminx, maskmaxx, maskminy, maskmaxy, maskphase);
			else
				Level.bitmask(currentlevel, backgroundmap, maskmap);
		}
		//boolean test = false;
		for (int y = sty; y < fy; y++) {
			for (int x = stx; x < fx; x++) {
				g.drawImage(backgroundmap[x][y], 50*x+bbx, 50*y+bby, this);
				if (map[x][y] != null) {
					//System.out.println(map[x][y].getCenterX()+"-"+map[x][y].getCenterY()+" "+Integer.toString(50*x+bbx)+"-"+Integer.toString(50*y+bby));
					//test = true;
				}
				int h = 0;
				while (h < 3) {
					g.drawImage(maskmap[x][y][h], 50*x+bbx, 50*y+bby, this);
					h++;
				}
			}
		}
		//g.drawImage(background, bg.getCenterX(), bg.getCenterY(), this);
		paintItems(g,stx,fx,sty,fy);
		paintItemEffectBelow(g,stx,fx,sty,fy);
		for (Enemy e : getEnemyarray()) {
			if (!e.alive || e.paintoverride) {
				g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex, e.getCenterY() - e.halfsizey, this);
			}
			for (int j = 0; j < e.getProjectiles().size(); j++) {
				Projectile p = e.getProjectiles().get(j);
				g.drawImage(p.getSprite(), p.getCenterX() - p.halfsize, p.getCenterY() - p.halfsize, this);
			}
		}
		for (int y = sty; y < fy; y++) {
			for (int x = stx; x < fx; x++) {
				if (null != map[x][y]) {
					if (Enemy.class.isInstance(map[x][y])) {
						Enemy e = (Enemy) map[x][y];
						if (null != e.getWeapon()) {
							if (e.isAimingUp()) {
								g.drawImage(e.getWeapon().currentSprite, e.getCenterX() + e.getWeapon().deltapx,
										e.getCenterY() + e.getWeapon().deltapy, this);
								g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex,
										e.getCenterY() - e.halfsizey, this);
							} else {
								g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex,
										e.getCenterY() - e.halfsizey, this);
								g.drawImage(e.getWeapon().currentSprite, e.getCenterX() + e.getWeapon().deltapx,
										e.getCenterY() + e.getWeapon().deltapy, this);
							}
						} else {
							g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex, e.getCenterY() - e.halfsizey,
									this);
						}
						if (e.showHealthBar) {
							g.setColor(Color.GREEN);
							int lifetaken = (int) ((e.getMaxHealth() - e.getHealth()) * e.halfbarx * 2)
									/ e.getMaxHealth();
							g.fillRect(e.getCenterX() - e.halfbarx, e.getCenterY() - e.halfbary,
									2 * e.halfbarx - lifetaken, 2);
							g.setColor(Color.RED);
							g.fillRect(e.getCenterX() + e.halfbarx - lifetaken, e.getCenterY() - e.halfbary,
									lifetaken, 2);
						}
					} else if (Player.class.isInstance(map[x][y])) {
						if (player.isAimingUp()) {
							g.drawImage(player.getWeapon().currentSprite, player.getCenterX() - player.halfsizex,
									player.getCenterY() - player.halfsizey, this);
							g.drawImage(player.currentSprite, player.getCenterX() - player.halfsizex,
									player.getCenterY() - player.halfsizey, this);
							if (player.isGrinning > 0)
								g.drawImage(grinningsprite, player.getCenterX() - player.halfsizex,
										player.getCenterY() - player.halfsizey, this);
							if (player.getHat() != null)
								g.drawImage(player.getHat().getSprite(), player.getCenterX() - player.halfsizex,
										player.getCenterY() - player.halfsizey + player.getHat().deltay, this);
						} else {
							g.drawImage(player.currentSprite, player.getCenterX() - player.halfsizex,
									player.getCenterY() - player.halfsizey, this);
							if (player.isGrinning > 0)
								g.drawImage(grinningsprite, player.getCenterX() - player.halfsizex,
										player.getCenterY() - player.halfsizey, this);
							if (player.getHat() != null)
								g.drawImage(player.getHat().getSprite(), player.getCenterX() - player.halfsizex,
										player.getCenterY() - player.halfsizey + player.getHat().deltay, this);
							g.drawImage(player.getWeapon().currentSprite, player.getCenterX() - player.halfsizex,
									player.getCenterY() - player.halfsizey, this);
						}
					} else
						g.drawImage(map[x][y].getSprite(), map[x][y].getCenterX() - map[x][y].halfrsizex,
								map[x][y].getCenterY() - map[x][y].halfsizey, this);
				}
			}
		} /*
			 * for (int i = 0; i < getEnemyarray().size(); i++) { Enemy e =
			 * getEnemyarray().get(i); if (e.alive) { if (null !=
			 * e.getWeapon()) { if (e.isAimingUp()) {
			 * g.drawImage(e.getWeapon().currentSprite, e.getCenterX() - 31,
			 * e.getCenterY() - 31, this); g.drawImage(e.currentSprite,
			 * e.getCenterX() - e.halfsizex, e.getCenterY() - e.halfsizey,
			 * this); } else { g.drawImage(e.currentSprite, e.getCenterX() -
			 * e.halfsizex, e.getCenterY() - e.halfsizey, this);
			 * g.drawImage(e.getWeapon().currentSprite, e.getCenterX() - 31,
			 * e.getCenterY() - 31, this); } } else {
			 * g.drawImage(e.currentSprite, e.getCenterX() - e.halfsizex,
			 * e.getCenterY() - e.halfsizey, this); } if (e.showHealthBar) {
			 * g.setColor(Color.GREEN); int lifetaken =
			 * ((e.getMaxHealth()-e.getHealth())*e.halfbar*2)/e.getMaxHealth
			 * (); g.fillRect(e.getCenterX()-e.halfbar,
			 * e.getCenterY()-e.halfsizey, 2*e.halfbar-lifetaken, 2);
			 * g.setColor(Color.RED);
			 * g.fillRect(e.getCenterX()+e.halfbar-lifetaken,
			 * e.getCenterY()-e.halfsizey, lifetaken, 2); } } }
			 */
		for (Explosion e : explosions) {
			g.drawImage(e.getSprite(), e.getR().x, e.getR().y, this);
		}
		ArrayList<Projectile> projectiles = player.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			// g.setColor(Color.YELLOW);
			// g.fillRect(p.getR().x, p.getR().y, p.getR().width,
			// p.getR().height);
			g.drawImage(p.getSprite(), p.getCenterX() - p.halfsize, p.getCenterY() - p.halfsize, this);
		}
		if (showPlayerHealthBar) {
			g.setColor(Color.GREEN);
			int lifetaken = (int) ((20 + player.getArmor().MAXDEF - player.getHealth()
					- player.getArmor().getDefense()) * 44) / (20 + player.getArmor().MAXDEF);
			int armorp = (int) (player.getArmor().getDefense() * 44) / (20 + player.getArmor().MAXDEF);
			g.fillRect(player.getCenterX() - 22, player.getCenterY() - 31, 44 - lifetaken - armorp, 2);
			g.setColor(Color.BLUE);
			g.fillRect(player.getCenterX() + 22 - lifetaken - armorp, player.getCenterY() - 31, armorp, 2);
			g.setColor(Color.RED);
			g.fillRect(player.getCenterX() + 22 - lifetaken, player.getCenterY() - 31, lifetaken, 2);
		}
		for (int i = 0; i < hitpoints.size(); i++) {
			g.drawImage(blooddrop, hitpoints.get(i).getCenterX() - 7, hitpoints.get(i).getCenterY() - 7, this);
		}
		paintItemsEffectAbove(g,stx,fx,sty,fy);
		
		g.setFont(new Font ("AR DESTINE", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(32, 37, 200, 20);
		g.setColor(Color.RED);
		g.fillRect(32, 37, ((int) player.getHealth())*10 , 20);
		g.setColor(Color.WHITE);
		g.drawString("HP: "+Integer.toString((int) player.getHealth()), 35, 51);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(32, 57, ((int) player.getArmor().MAXDEF)*10, 20);
		g.setColor(Color.BLUE);
		g.fillRect(32, 57, ((int) player.getArmor().defense)*10, 20);
		g.setColor(Color.WHITE);
		g.drawString("Armor: "+Integer.toString((int) player.getArmor().defense), 35, 71);
		
		g.setFont(new Font ("AR DESTINE", Font.BOLD, 20));
		g.setColor(Color.YELLOW);
		g.drawString(""+Math.round(player.getWeapon().getProjectiledmg()*100.0)/100.0, 260, 75);
		g.drawString(Integer.toString((int) player.getWeapon().getFireRate()), 300, 75);
		g.drawString(Integer.toString((int) player.getWeapon().getRange()), 340, 75);
		
		if (TESTMODE) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(1200, 37, 20, 20);
			g.fillRect(1230, 37, 45, 20);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(fps), 1203, 51);
			// g.drawString(Integer.toString(cmptime), 1233, 51);
		}
		if (state == GameState.Paused) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 385, 1280, 20);
			//g.fillRect(1230, 37, 45, 20);
			g.setColor(Color.WHITE);
			g.drawString("PAUSE", 620, 400);
		}
		if (state == GameState.Dead) {
			Graphics2D g2d = (Graphics2D) g;
			Composite c = g2d.getComposite();
			g2d.setColor(Color.GRAY);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			g2d.fillRect(0, 0, 1280, 800);
			//g.fillRect(1230, 37, 45, 20);
			int style = Font.BOLD | Font.ITALIC;
			
			/*
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 300, 1280, 200);*/

			Font font = new Font ("AR DESTINE", style , 60);
			Font pfont = g2d.getFont();
			g2d.setFont(font);
			g2d.setColor(Color.RED);
			g2d.drawString("LEVEL FAILED", 640-g2d.getFontMetrics().stringWidth("LEVEL FAILED")/2, 400);
			g2d.setComposite(c);
			g2d.setFont(pfont);
		}
	}

	@Override
	public void paint(Graphics g) {
		if (state != GameState.Menu) {
			if (image == null) {
				image = createImage(this.getWidth(), this.getHeight());
				second = image.getGraphics();
			}
			second.setColor(getBackground());
			second.fillRect(0, 0, getWidth(), getHeight());
			second.setColor(getForeground());
		
			paintOffScreen(second);

			g.drawImage(image, 0, 0, this);
		} else
			super.paint(g);
	}

	private void callEnemiesAIs() {
		for (int i = 0; i < enemyarray.size(); i++)
			enemyarray.get(i).launchAI();
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
		for (Enemy e : enemyarray) {
			if (e.alive)
				e.checkCollisionsWithItems();
		}
		player.checkCollisionsWithItems();
		for (int i = 0; i < leavingitems.size(); i++) {
			leavingitems.get(i).doLeavingEffect();
		}
	}
	/*
	 * private void checkTileCollisions() { for (int i = 0; i <
	 * tilearray.size(); i++) { Tile t = tilearray.get(i); t.checkCollisions();
	 * } }
	 */

	public static void updateExplosions() {
		int i = 0;
		while (i < explosions.size()) {
			Explosion e = explosions.get(i);
			e.update();
			/*TODO if(!e.soundPlayed){
				try {
					Clip explosiveSound = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(e.sound);
					explosiveSound.open(ais);
					ais.close();
					FloatControl gainControl = (FloatControl) explosiveSound.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-12.0f);
					explosiveSound.loop(0);
					synchronized(gunclips) {
						gunclips.add(explosiveSound);
					}
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				e.soundPlayed = true;
			}*/
			if (e.isVisible()) {
				i++;
			} else {
				explosions.remove(i);
			}
		}
	}

	private void updatePlayer() {
		
		/* TODO
		if (player.isHurt){
			player.isHurt = false;
			try {
				Clip hurtSound = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(Player.hurtSound);
				hurtSound.open(ais);
				ais.close();
				FloatControl gainControl = (FloatControl) hurtSound.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(0.0f);
				hurtSound.loop(0);
				synchronized (gunclips) {
					gunclips.add(hurtSound);
				}
				
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (Throwable e3) {
				e3.printStackTrace();
			}
		}*/
		
		//}
		
		/* TODO
		if (player.playSound){
			player.playSound = false;
			try {
				Clip gunSound = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(player.getWeapon().getAudioURL());
				gunSound.open(ais);
				ais.close();
				FloatControl gainControl = (FloatControl) gunSound.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-15.0f);
				gunSoundLoaded = true;
				gunSound.loop(0);
				synchronized(gunclips) {
					gunclips.add(gunSound);
				}
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/
		
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
							e.damage(p.damage);
							if (!e.alive) {
								if (player.getHealth() + 1 < player.getMaxHealth())
									player.setHealth(player.getHealth() + 1);
								else
									player.setHealth(player.getMaxHealth());
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
				if (p.canbedestroyed) {
					for (Projectile pe : player.getProjectiles()) {
						if (!p.equals(pe) && pe.isVisible() && p.checkCollision(pe)) {
							p.doOnCollision(pe);
							pe.doOnCollision(p);
						}
					}
				}
				Tile t = p.checkCollisionTile();
				if (null != t) {
					p.doOnCollision(t);
					if (DestroyableTile.class.isInstance(t))
						((DestroyableTile) t).damage(p.damage);
				}
				i++;
			} else {
				projectiles.remove(i);
			}
		}
		for (Explosion e : explosions) {
			if (e.isProcing() && player.R.intersects(e.getR())) {
				player.damage(e.damage);
			}
		}
		player.update();
	}

	public static void updateEnemies() {
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
						player.damage(p.damage);
					}
					Tile t = p.checkCollisionTile();
					if (null != t) {
						p.doOnCollision(t);
						if (DestroyableTile.class.isInstance(t))
							((DestroyableTile) t).damage(p.damage);
					}
					if (p.canbedestroyed) {
						for (Projectile pe : player.getProjectiles()) {
							if (pe.isVisible() && p.checkCollision(pe)) {
								p.doOnCollision(pe);
								pe.doOnCollision(p);
							}
						}
						for (Enemy e2 : getEnemyarray()) {
							for (Projectile pe : e2.getProjectiles()) {
								if (!pe.equals(p) && pe.isVisible() && p.checkCollision(pe)) {
									p.doOnCollision(pe);
									pe.doOnCollision(p);
								}
							}
						}
					}
					i++;
				} else {
					e.getProjectiles().remove(i);
				}
			}
			for (int exi = 0; exi < explosions.size(); exi++) {
				Explosion ex = explosions.get(exi);
				if (ex.isProcing() && e.alive && e.R.intersects(ex.getR())) {
					e.damage(ex.damage);
					if (!e.alive)
						e.setGibsSprite();
				}
			}
		}
	}

	public static void updateTiles() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			t.update();
		}
	}

	public static void updateItems() {
		int h;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				h = 0;
				while (h<=heightitemmap[i][j]) {
					items[i][j][h].update();
					if (items[i][j][h].pleaseremove) {
						if (SummonedIce.class.isInstance(items[i][j][h])) {
							backgroundmap[i][j] = ((SummonedIce)items[i][j][h]).previousbackground;
							remask = true;
						}
						removeItem(items[i][j][h]);
					} else
						h++;
				}
			}
		}
		for (Item i : leavingitems)
			i.update();
	}
	/*
	 * private void paintBelowTiles(Graphics g) { for (int i = 0; i <
	 * tilearray.size(); i++) { Tile t = tilearray.get(i); if (t.getCenterY() <=
	 * player.getCenterY() || !Tile.isTileBlocking(t.getType()))
	 * g.drawImage(t.getSprite(), t.getCenterX() - 31, t.getCenterY() - 31,
	 * this); } }
	 * 
	 * private void paintAboveTiles(Graphics g) { for (int i = 0; i <
	 * tilearray.size(); i++) { Tile t = tilearray.get(i); if (t.getCenterY() >
	 * player.getCenterY() && Tile.isTileBlocking(t.getType()))
	 * g.drawImage(t.getSprite(), t.getCenterX() - 31, t.getCenterY() - 31,
	 * this); } }
	 */

	private void paintItems(Graphics g,int stx,int fx,int sty,int fy) {
		for (int h = 0; h <= blockmaxheight; h++) {
			for (int j = sty; j < fy; j++) {
				for (int i = stx; i < fx; i++) {
					if (h <= heightitemmap[i][j]) {
						if (BackgroundItem.class.isInstance(items[i][j][h])) {
							if (h == heightitemmap[i][j] || !BackgroundItem.class.isInstance(items[i][j][h+1]))
							g.drawImage(items[i][j][h].getSprite(), items[i][j][h].getCenterX() - 31, items[i][j][h].getCenterY() - 31, this);
					} else if (StartingClass.map[i][j] == null || !Tile.class.isInstance(StartingClass.map[i][j]))
							g.drawImage(items[i][j][h].getSprite(), items[i][j][h].getCenterX() - 31, items[i][j][h].getCenterY() - 31, this);
					}
				}
			}
		}
	}
	
	private void paintItemsEffectAbove(Graphics g,int stx,int fx,int sty,int fy) {
		for (int j = sty; j < fy; j++) {
			for (int i = stx; i < fx; i++) {
				for (int h = 0; h <= heightitemmap[i][j]; h++) {
					if (items[i][j][h].effectactive == true && items[i][j][h].isEffectAbove()) {
						g.drawImage(items[i][j][h].getEffectSprite(), items[i][j][h].getEffectCenterX() - 31, items[i][j][h].getEffectCenterY() - 31, this);
					}
				}
			}
		}
		int i = 0;
		while (i < leavingitems.size()) {
			if (leavingitems.get(i).effectactive) {
				if (leavingitems.get(i).isEffectAbove())
					g.drawImage(leavingitems.get(i).getEffectSprite(), leavingitems.get(i).getEffectCenterX() - 31, leavingitems.get(i).getEffectCenterY() - 31,
						this);
				i++;
			} else
				leavingitems.remove(i);
		}
	}

	private void paintItemEffectBelow(Graphics g,int stx,int fx,int sty,int fy) {
		for (int j = sty; j < fy; j++) {
			for (int i = stx; i < fx; i++) {
				for (int h = 0; h <= heightitemmap[i][j]; h++) {
					if (items[i][j][h].effectactive == true && !items[i][j][h].isEffectAbove()) {
						g.drawImage(items[i][j][h].getEffectSprite(), items[i][j][h].getEffectCenterX() - 31, items[i][j][h].getEffectCenterY() - 31, this);
					}
				}
			}
		}
		int i = 0;
		while (i < leavingitems.size()) {
			if (leavingitems.get(i).effectactive) {
				if (!leavingitems.get(i).isEffectAbove())
					g.drawImage(leavingitems.get(i).getEffectSprite(), leavingitems.get(i).getEffectCenterX() - 31, leavingitems.get(i).getEffectCenterY() - 31,
						this);
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
			if(azerty)
				player.moveUp();
			break;
			
		case KeyEvent.VK_W:
			if(!azerty)
				player.moveUp();
			break;

		case KeyEvent.VK_S:
			player.moveDown();
			break;

		case KeyEvent.VK_Q:
			if(azerty){
				player.moveLeft();
			}
			
			break;
			
		case KeyEvent.VK_A:
			if(!azerty){
				player.moveLeft();
			}
			
			break;

		case KeyEvent.VK_D:
			player.moveRight();
			break;

		case KeyEvent.VK_UP:
			player.wannashootup = true;
			break;

		case KeyEvent.VK_DOWN:
			player.wannashootdown = true;
			break;

		case KeyEvent.VK_LEFT:
			player.wannashootleft = true;
			break;

		case KeyEvent.VK_RIGHT:	
			player.wannashootright = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_Z:
			if(azerty)
				player.stopMovingUp();
			break;
		case KeyEvent.VK_W:
			if(!azerty)
				player.stopMovingUp();
			break;
		case KeyEvent.VK_S:
			player.stopMovingDown();
			break;
		case KeyEvent.VK_Q:
			if(azerty)
				player.stopMovingLeft();
			else{
				armorindex++;
				if (armorindex == playerarmor.size())
					armorindex = 0;
				player.setArmor(playerarmor.get(armorindex));
			}
			break;
		case KeyEvent.VK_D:
			player.stopMovingRight();
			break;
		case KeyEvent.VK_UP:
			player.wannashootup = false;
			break;
		case KeyEvent.VK_DOWN:
			player.wannashootdown = false;
			break;
		case KeyEvent.VK_LEFT:
			player.wannashootleft = false;
			break;
		case KeyEvent.VK_RIGHT:
			player.wannashootright = false;
			break;
		case KeyEvent.VK_E:
			gunSoundLoaded = false;
			weaponindex++;
			if (weaponindex == playerweapons.size())
				weaponindex = 0;
			player.setWeapon(playerweapons.get(weaponindex));
			break;
		case KeyEvent.VK_R:
			gunSoundLoaded = false;
			weaponindex--;
			if (weaponindex == -1)
				weaponindex = playerweapons.size() - 1;
			player.setWeapon(playerweapons.get(weaponindex));
			break;
		case KeyEvent.VK_A:
			if(azerty){
				armorindex++;
				if (armorindex == playerarmor.size())
					armorindex = 0;
				player.setArmor(playerarmor.get(armorindex));
			}else{
				player.stopMovingLeft();
			}
			break;
		case KeyEvent.VK_H:
			hatindex++;
			if (hatindex == playerhats.size())
				hatindex = 0;
			player.setHat(playerhats.get(hatindex));
			break;
		case KeyEvent.VK_ESCAPE:
			state = GameState.Exit;
			System.exit(0);
			break;
		case KeyEvent.VK_SPACE:
			if (state == GameState.Paused) {
				state = GameState.Running;
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else if (state == GameState.Running) {
				state = GameState.Paused;
				clip.stop();
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
	}

	public static Background getBg() {
		return bg;
	}

	public static void setBg(Background bg) {
		StartingClass.bg = bg;
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

	public static ArrayList<Tile> getTilearray() {
		return tilearray;
	}

	public static void setTilearray(ArrayList<Tile> tilearray_) {
		tilearray = tilearray_;
	}

	public void clean() {
		explosions.clear();
		tilearray.clear();
		//items.clear();
		leavingitems.clear();
		enemyarray.clear();
		arenaenemies.clear();
		arenacenters.clear();
		hitpoints.clear();
		entrydoors.clear();
		destroyabletiles.clear();
		isInArena = -1;
		ScrollingMode = 1;
		player.projectiles.clear();
		/*playerweapons.clear();
		weaponindex = 0;
		playerarmor.clear();
		armorindex = 0;
		playerhats.clear();*/
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				DisplayMode dm = new DisplayMode(1280, 800, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
				me = new StartingClass();
				GraphicsEnvironment ge = GraphicsEnvironment.
						   getLocalGraphicsEnvironment();
				GraphicsDevice gd = ge.getDefaultScreenDevice();
				if (gd.isFullScreenSupported()) {
				     //gd.setFullScreenWindow(me);
				 }
				if(dm != null && gd.isDisplayChangeSupported()) {
		            try{
		            	gd.setDisplayMode(dm); 
		            }catch(Exception ex){}
		        }
				if (TESTMODE)
					me.teststart();
					
				me.setVisible(true);
				/*
				 * sc.init(); sc.start();
				 */
			}
		});
	}
	
	public static void removeItem(Item i) {
		//System.out.println("Removing item: " + i.getClass().getName());
		for (int h = i.height+1; h <= heightitemmap[i.posx][i.posy]; h++) {
			items[i.posx][i.posy][h-1] = items[i.posx][i.posy][h];
			items[i.posx][i.posy][h-1].height--;
		}	
		heightitemmap[i.posx][i.posy]--;
		//TODO
	}
}