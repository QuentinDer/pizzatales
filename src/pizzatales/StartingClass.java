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
import pizzatales.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 641656516622083167L;
	public static final int difficultylevel = 4;
	private static Player player;
	private Image image, character1, character2, characterMove1, characterMove2, currentSprite, background;
	public static Image tileTree, tileGrass, tileWall;
	private int walkCounter = 1;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;
	private static PathFinder pf;
	private Animation anim;
	private ArrayList<Firearm> playerweapons;
	private ArrayList<Armor> playerarmor;

	private int weaponindex;
	private int armorindex;
	private long clock = System.currentTimeMillis();

	enum GameState {
		Running, Dead, Paused
	}

	GameState state = GameState.Running;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	public static ArrayList<Enemy> enemyarray = new ArrayList<Enemy>();

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
		Pepper.move1SpriteRight = getImage(base, "data/pepperRight2.png");
		Pepper.move2SpriteRight = getImage(base, "data/pepperRight3.png");
		Pepper.dieSprite = getImage(base, "data/pepperdead.png");
		Mushroom.staySprite = getImage(base, "data/shroom1.png");
		Mushroom.move1Sprite = getImage(base, "data/shroom2.png");
		Mushroom.move2Sprite = getImage(base, "data/shroom3.png");
		Mushroom.dieSprite = getImage(base, "data/shroomdead.png");

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

		bg1 = new Background(0, -800);
		bg2 = new Background(0, 800);

		// Initialize Tiles
		try {
			loadMap("data/L11.txt");
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

		for (int j = 0; j < height; j++) {
			line = lines.get(j);
			for (int i = 0; i < width; i++) {
				if (i < line.length()) {
					char ch = line.charAt(i);
					if (ch == '0')
						pf.map[i][j] = true;
					if (Tile.isTileTypeSupported(ch)) {
						Tile t = new Tile(i, j, ch);
						tilearray.add(t);
					}
					if (EnemyFactory.isTileTypeSupported(ch)) {
						getEnemyarray().add(EnemyFactory.getEnemy(i, j, ch));
					}
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
			while (true) {
				try {
					Thread.sleep(Math.abs(17 - System.currentTimeMillis() + clock));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clock = System.currentTimeMillis();

				// Animation

				if (player.isMovingHor() == true || player.isMovingVer() == true) {
					if (walkCounter % 30 == 0) {
						// player.getArmor().setSpriteWalk1();
						// currentSprite = player.getArmor().currentSprite;
						currentSprite = characterMove1;
					} else if (walkCounter % 15 == 0) {
						// player.getArmor().setSpriteWalk2();
						// currentSprite = player.getArmor().currentSprite;
						currentSprite = characterMove2;
					}
				} else if (player.isMovingVer() == false && player.isMovingHor() == false) {
					currentSprite = character1;
				}

				/*
				 * for (int i = 0; i < getEnemyarray().size(); i++) { Enemy e =
				 * getEnemyarray().get(i);
				 * 
				 * if (e.alive == true) { if (e.isMoving == true) { if
				 * (walkCounter % 30 == 0) { e.currentSprite = getImage(base,
				 * e.characterMove1Path); } else if (walkCounter % 15 == 0) {
				 * e.currentSprite = getImage(base, e.characterMove2Path); } }
				 * else if (e.isMoving == false) { e.currentSprite =
				 * getImage(base, e.characterStayPath); } if (e.walkCounter >
				 * 1000) { e.walkCounter = 0; } } }
				 */
				updatePlayer();

				checkEnemiesCollision();
				checkTileCollisions();
				callEnemiesAIs();
				updateEnemies();

				bg1.update();
				bg2.update();
				// animate();
				updateTiles();
				repaint(); // this calls paint

				if (walkCounter == 1000) {
					walkCounter = 0;
				}
				walkCounter++;

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
			paintTiles(g);
			ArrayList<Projectile> projectiles = player.getProjectiles();
			for (int i = 0; i < getEnemyarray().size(); i++) {
				Enemy e = getEnemyarray().get(i);
				if (!e.alive) {
					g.drawImage(e.currentSprite, e.getCenterX() - 31, e.getCenterY() - 31, this);
				}
			}
			for (int i = 0; i < getEnemyarray().size(); i++) {
				Enemy e = getEnemyarray().get(i);
				if (e.alive) {
					if (e.isAimingUp()) {
						g.drawImage(e.getWeapon().currentSprite, e.getCenterX() - 31, e.getCenterY() - 31, this);
						g.drawImage(e.currentSprite, e.getCenterX() - 31, e.getCenterY() - 31, this);
					} else {
						g.drawImage(e.currentSprite, e.getCenterX() - 31, e.getCenterY() - 31, this);
						g.drawImage(e.getWeapon().currentSprite, e.getCenterX() - 31, e.getCenterY() - 31, this);
					}
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
			e.callAI();
		}
	}

	private void checkEnemiesCollision() {
		for (Enemy e : getEnemyarray()) {
			if (e.alive)
				e.checkEnemyCollisions();
		}
	}

	private void checkTileCollisions() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			t.checkCollisions();
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
					p.checkCollision(tilearray.get(j));
				}
				i++;
			} else {
				projectiles.remove(i);
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
						p.checkCollision(tilearray.get(k));
					}
					i++;
				} else {
					e.getProjectiles().remove(i);
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

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getCenterX() - 31, t.getCenterY() - 31, this);
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
		if (player.getSpeedX() > 0)
			player.setSpeedX(player.getArmor().getSpeed());
		if (player.getSpeedX() < 0)
			player.setSpeedX(-player.getArmor().getSpeed());
		if (player.getSpeedY() > 0)
			player.setSpeedY(player.getArmor().getSpeed());
		if (player.getSpeedY() < 0)
			player.setSpeedY(-player.getArmor().getSpeed());
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
		case KeyEvent.VK_S:
			player.stopVer();
			player.isColliding = false;
			break;
		case KeyEvent.VK_Q:
		case KeyEvent.VK_D:
			player.stopHor();
			player.isColliding = false;
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

}
