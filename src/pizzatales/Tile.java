package pizzatales;

import java.awt.Image;

public class Tile extends BlockingStuff {

	private char type;
	private Image tileImage;
	private Image hiddenImage;

	// private Background bg = StartingClass.getBg1();

	private final static String acceptedTileTypes = "twcsrdekgvy";

	public static boolean isTileTypeSupported(char type) {
		String test = "";
		test += type;
		return acceptedTileTypes.contains(test);
	}

	public Tile(int x, int y, char typeInt) {
		super((x * 50) + 25, (y * 50) + 40, 31, 31, 25, 25);

		type = typeInt;

		if (type == 't') {
			tileImage = StartingClass.tileTree;
		} else if (type == 'w') {
			tileImage = StartingClass.tileWall;
		} else if (type == 'c') {
			tileImage = StartingClass.tileCave;
		} else if (type == 's') {
			tileImage = StartingClass.tileStalag;
		} else if (type == 'r') {
			tileImage = StartingClass.tileCaveRock;
		} else if (type == 'd') {
			tileImage = StartingClass.tileGate;
		} else if (type == 'e') {
			tileImage = StartingClass.tileCaveExit;
  		} else if (type == 'k') {
			tileImage = StartingClass.tilePikes;
  		} else if (type == 'g') {
			tileImage = StartingClass.tileFlag;
  		} else if (type == 'v') {
			tileImage = StartingClass.tileRock;
  		} else if (type == 'y') {
			tileImage = StartingClass.tileDecoy;
  		}
	}
/*
	public void checkCollision(Player player) {
		if (isTileBlocking(type)) {
			if (R.intersects(player.R)) {
				int diffX = Math.abs(getCenterX() - player.getCenterX());
				int diffY = Math.abs(getCenterY() - player.getCenterY());
				if (diffX > diffY && diffY < 40) {
					if (player.getCenterX() <= this.getCenterX()) {
						player.canmoveright = false;
					} else {
						player.canmoveleft = false;
					}
				} else if (diffX < diffY && diffX < 40) {
					if (player.getCenterY() <= this.getCenterY()) {
						player.canmovedown = false;
					} else {
						player.canmoveup = false;
					}
				}
			}
		}*//*
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
		}*/
	//}
/*
	public void checkCollision(Enemy enemy) {
		if (isTileBlocking(type)) {
			if (enemy.alive == true && R.intersects(enemy.R)) {
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
	}*/
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
/*
	public void checkCollisions() {
		checkCollision(player);
		for (int i = 0; i < StartingClass.getEnemyarray().size(); i++) {
			Enemy e = StartingClass.getEnemyarray().get(i);
			checkCollision(e);
		}
	}*/

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public void hideImage(Image replacing) {
		this.hiddenImage = tileImage;
		this.tileImage = replacing;
	}
	
	public void reveal() {
		this.tileImage = this.hiddenImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	@Override
	public Image getSprite() {
		return tileImage;
	}
}