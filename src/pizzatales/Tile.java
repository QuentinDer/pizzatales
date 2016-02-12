package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile extends Stuff {

	private char type;
	public Image tileImage;

	//private Background bg = StartingClass.getBg1();

	private Rectangle r;
	
	private static String acceptedTileTypes = "t";

	public static boolean isTileTypeSupported(char type) {
		String test = "";
		test += type;
		return acceptedTileTypes.contains(test);
	}
	
	public Tile(int x, int y, char typeInt) {
		super((x * 50) + 40,(y * 50) + 40);

		type = typeInt;

		r = new Rectangle(getCenterX()-25,getCenterY()-25,50,50);
		
		if (type == 't') {
			tileImage = StartingClass.tileTree;
		}
	}

	public void checkHorizontalCollision(Player player) {
		if (player.R.intersects(r) && Math.abs(player.getCenterY()-getCenterY())<50) {
			if (player.getCenterX() <= this.getCenterX()) {
				player.setCenterX(player.getCenterX() - 2);
				player.setSpeedX(0);
			} else {
				player.setCenterX(player.getCenterX() + 2);
				player.setSpeedX(0);
			}
		}
	}

	public void checkVerticalCollision(Player player) {
		if (player.R.intersects(r) && Math.abs(player.getCenterX()-getCenterX())<50) {
			if (player.getCenterY() <= this.getCenterY()) {
				player.setCenterY(player.getCenterY() - 2);
				player.setSpeedY(0);
				player.isColliding = true;
			} else {
				player.setCenterY(player.getCenterY() + 2);
				player.setSpeedY(0);
				player.isColliding = true;
			}
		}
	}
	
	public void checkCollision(Enemy enemy) {
		if (enemy.alive == true && r.intersects(enemy.R)) {
			if (enemy.getCenterX() - getCenterX() >= 0)
				enemy.setCenterX(enemy.getCenterX()+2);
			else
				enemy.setCenterX(enemy.getCenterX()-2);
			if (enemy.getCenterY() - getCenterY() >= 0)
				enemy.setCenterY(enemy.getCenterY()+2);
			else
				enemy.setCenterY(enemy.getCenterY()-2);
		}
	}
/*
	public void checkHorizontalCollision(Enemy enemy) {
		if (enemy.alive == true && enemy.R.intersects(r) && Math.abs(enemy.getCenterY()-getCenterY())<50) {
			if (enemy.getCenterX() <= this.getCenterX()) {
				enemy.setCenterX(enemy.getCenterX() - 2);
				enemy.setSpeedX(0);
			} else if (enemy.getCenterX() >= this.getCenterX()) {
				enemy.setCenterX(enemy.getCenterX() + 2);
				enemy.setSpeedX(0);
			}
		}
	}

	public void checkVerticalCollision(Enemy enemy) {
		if (enemy.alive == true && enemy.R.intersects(r) && Math.abs(enemy.getCenterX()-getCenterX())<50) {
			if (enemy.getCenterY() <= this.getCenterY()) {
				enemy.setCenterY(enemy.getCenterY() - 2);
				enemy.setSpeedY(0);
			} else if (enemy.getCenterY() >= this.getCenterY()) {
				enemy.setCenterY(enemy.getCenterY() + 2);
				enemy.setSpeedY(0);
			}
		}
	}*/
	
	public void checkCollisions() {
		checkHorizontalCollision(player);
		checkVerticalCollision(player);
		for (int i = 0; i < StartingClass.getEnemyarray().size(); i++) {
			Enemy e = StartingClass.getEnemyarray().get(i);
			checkCollision(e);
			/*checkHorizontalCollision(e);
			checkVerticalCollision(e);*/
		}
	}

	@Override
	public void update() {
		super.update();
		r.setBounds(getCenterX()-25, getCenterY()-25, 50, 50);
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
