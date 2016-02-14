package pizzatales;

import java.awt.Rectangle;

public class HealthPotion extends Stuff {

	public Rectangle r;

	public HealthPotion(int centerX, int centerY) {
		super(centerX, centerY);
	}

	public void checkHorizontalCollision(Player player) {
		if (player.R.intersects(r) && Math.abs(player.getCenterY() - getCenterY()) < 50) {
			if (player.getHealth() < 20) {
				if (player.getCenterX() <= this.getCenterX()) {
					player.setHealth(player.getHealth() + 5);
				} else {
					player.setHealth(player.getHealth() + 5);
				}
			}
		}
	}

	public void checkVerticalCollision(Player player) {
		if (player.R.intersects(r) && Math.abs(player.getCenterX() - getCenterX()) < 50) {
			if (player.getHealth() < 20) {
				if (player.getCenterY() <= this.getCenterY()) {
					player.setHealth(player.getHealth() + 5);
				} else {
					player.setHealth(player.getHealth() + 5);
				}
			}
		}
	}

}
