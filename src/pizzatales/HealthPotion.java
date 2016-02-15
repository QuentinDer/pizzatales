package pizzatales;

import java.awt.Image;

public class HealthPotion extends Item {

	public static Image healthpotionsprite;

	public HealthPotion(int centerX, int centerY) {
		super(centerX, centerY);
	}

	@Override
	protected void doEffect() {
		if (player.getHealth() + 5 > 20) {
			player.setHealth(20);
		} else {
			player.setHealth(player.getHealth() + 5);
		} 
	}

	@Override
	protected Image getSprite() {
		return healthpotionsprite;
	}

	@Override
	protected boolean canDoEffect() {
		return player.getHealth() != 20;
	}
}
