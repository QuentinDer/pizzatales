package pizzatales;

import java.awt.Image;

public class HealthPotion extends Item {

	public HealthPotion(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
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
		effectTimer = 30;
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

	@Override
	protected void doLeavingEffect() {
	}
}
