package pizzatales;

import java.awt.Rectangle;

public class ArmorPotion extends Stuff {

	public Rectangle r;

	public ArmorPotion(int centerX, int centerY) {
		super(centerX, centerY);
	}

	public void checkHorizontalCollision(Player player) {
		if (player.R.intersects(r) && Math.abs(player.getCenterY() - getCenterY()) < 50) {
			if (player.getArmor().getDefense() < player.getArmor().MAXDEF) {
				if (player.getCenterX() <= this.getCenterX()) {
					player.getArmor().setDefense((player.getArmor().getDefense() + 5));
				} else {
					player.getArmor().setDefense((player.getArmor().getDefense() + 5));
				}
			}
		}
	}

	public void checkVerticalCollision(Player player) {
		if (player.R.intersects(r) && Math.abs(player.getCenterX() - getCenterX()) < 50) {
			if (player.getArmor().getDefense() < player.getArmor().MAXDEF) {
				if (player.getCenterY() <= this.getCenterY()) {
					player.getArmor().setDefense((player.getArmor().getDefense() + 5));
				} else {
					player.getArmor().setDefense((player.getArmor().getDefense() + 5));
				}
			}
		}
	}

}