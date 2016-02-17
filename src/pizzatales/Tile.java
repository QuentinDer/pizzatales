package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile extends Stuff {

	private char type;
	public Image tileImage;

	// private Background bg = StartingClass.getBg1();

	private Rectangle r;

	private final static String acceptedTileTypes = "twcusrde";
	private final static String blockingTileTypes = "twcsrd";

	public static boolean isTileTypeSupported(char type) {
		String test = "";
		test += type;
		return acceptedTileTypes.contains(test);
	}
	
	public static boolean isTileBlocking(char type) {
		String test = "";
		test += type;
		return blockingTileTypes.contains(test);
	}

	public Tile(int x, int y, char typeInt) {
		super((x * 50) + 25, (y * 50) + 40);

		type = typeInt;

		r = new Rectangle(getCenterX() - 25, getCenterY() - 25, 50, 50);

		if (type == 't') {
			tileImage = StartingClass.tileTree;
		} else if (type == 'w') {
			tileImage = StartingClass.tileWall;
		} else if (type == 'c') {
			tileImage = StartingClass.tileCave;
		} else if (type == 'u') {
			tileImage = StartingClass.tilePuddle;
		} else if (type == 's') {
			tileImage = StartingClass.tileStalag;
		} else if (type == 'r') {
			tileImage = StartingClass.tileCaveRock;
		} else if (type == 'e') {
			tileImage = StartingClass.tileCaveExit;
		}
	}

	public void checkCollision(Player player) {
		int diffX = Math.abs(getCenterX() - player.getCenterX());
		int diffY = Math.abs(getCenterY() - player.getCenterY());
		if (isTileBlocking(type)) {
			if (diffX < 50 && diffY < 50) {
				if (diffX > diffY && diffY < 45) {
					if (player.getCenterX() <= this.getCenterX()) {
						player.canmoveright = false;
						;
					} else {
						player.canmoveleft = false;
					}
				} else if (diffX < diffY && diffX < 45) {
					if (player.getCenterY() <= this.getCenterY()) {
						player.canmovedown = false;
					} else {
						player.canmoveup = false;
					}
				}
			}
		}
	}

	public void checkCollision(Enemy enemy) {
		if (isTileBlocking(type)) {
			if (enemy.alive == true && r.intersects(enemy.R)) {
				if (Math.abs(enemy.getCenterX() - getCenterX()) > Math.abs(enemy.getCenterY() - getCenterY())) {
					if (enemy.getCenterX() - getCenterX() > 0)
						enemy.canmoveleft = false;
					else
						enemy.canmoveright = false;
				} else {
					if (enemy.getCenterY() - getCenterY() > 0)
						enemy.canmoveup = false;
					else
						enemy.canmovedown = false;
				}
			}
		}
	}
	/*
	 * public void checkHorizontalCollision(Enemy enemy) { if (enemy.alive ==
	 * true && enemy.R.intersects(r) &&
	 * Math.abs(enemy.getCenterY()-getCenterY())<50) { if (enemy.getCenterX() <=
	 * this.getCenterX()) { enemy.setCenterX(enemy.getCenterX() - 2);
	 * enemy.setSpeedX(0); } else if (enemy.getCenterX() >= this.getCenterX()) {
	 * enemy.setCenterX(enemy.getCenterX() + 2); enemy.setSpeedX(0); } } }
	 * public void checkVerticalCollision(Enemy enemy) { if (enemy.alive == true
	 * && enemy.R.intersects(r) && Math.abs(enemy.getCenterX()-getCenterX())<50)
	 * { if (enemy.getCenterY() <= this.getCenterY()) {
	 * enemy.setCenterY(enemy.getCenterY() - 2); enemy.setSpeedY(0); } else if
	 * (enemy.getCenterY() >= this.getCenterY()) {
	 * enemy.setCenterY(enemy.getCenterY() + 2); enemy.setSpeedY(0); } } }
	 */

	public void checkCollisions() {
		checkCollision(player);
		for (int i = 0; i < StartingClass.getEnemyarray().size(); i++) {
			Enemy e = StartingClass.getEnemyarray().get(i);
			checkCollision(e);
			/*
			 * checkHorizontalCollision(e); checkVerticalCollision(e);
			 */
		}
	}

	@Override
	public void update() {
		super.update();
		r.setBounds(getCenterX() - 25, getCenterY() - 25, 50, 50);
	}

	public Rectangle getR() {
		return r;
	}

	public void setR(Rectangle r) {
		this.r = r;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
}