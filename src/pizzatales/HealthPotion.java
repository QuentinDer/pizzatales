package pizzatales;

import java.awt.Image;

public class HealthPotion extends Item {

	public HealthPotion(int x, int y, boolean onetimeeffect) {
		super(x, y, onetimeeffect);
	}

	public static Image healthpotionsprite;
	public static Image healthpotioneffectsprite;


	@Override
	protected void doEffect() {
		if (player.getHealth() + 5 > 20) {
			player.setHealth(20);
		} else {
			player.setHealth(player.getHealth() + 5);
		} 
		effectactive = true;
	}

	@Override
	protected Image getSprite() {
		return healthpotionsprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		return healthpotioneffectsprite;
	}

	@Override
	protected boolean canDoEffect() {
		return player.getHealth() != 20;
	}
}
