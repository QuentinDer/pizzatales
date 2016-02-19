package pizzatales;

import java.awt.Image;

public class HealthPotion extends Item {

	public HealthPotion(int x, int y, boolean onetimeeffect) {
		super(x, y, onetimeeffect);
		removable = true;
	}

	public static Image healthpotionsprite;


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
