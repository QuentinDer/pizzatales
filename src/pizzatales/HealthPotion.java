package pizzatales;

import java.awt.Image;

public class HealthPotion extends Item {

	public HealthPotion(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image healthpotionsprite;
	public static Image healthpotioneffectsprite, healthpotioneffectsprite2, healthpotioneffectsprite3, healthpotioneffectsprite4;


	@Override
	protected void doEffect(Player p) {
		if (p.getHealth() + 5 > p.getMaxHealth()) {
			p.setHealth(p.getMaxHealth());
		} else {
			p.setHealth(p.getHealth() + 5);
		} 
		effectactive = true;
		effectTimer = 60;
	}

	@Override
	protected Image getSprite() {
		return healthpotionsprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		switch(effectTimer/8 % 4) {
		case 0:
			return healthpotioneffectsprite;
		case 1:
			return healthpotioneffectsprite2;
		case 2:
			return healthpotioneffectsprite3;
		default:
			return healthpotioneffectsprite4;
		}
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return p.getHealth() != p.getMaxHealth();
	}
	
	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected int getEffectCenterX() {
		return player.getCenterX();
	}

	@Override
	protected int getEffectCenterY() {
		return player.getCenterY();
	}
	
	@Override
	protected boolean isEffectAbove() {
		return true;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		return false;
	}

	@Override
	protected void doEffect(Enemy e) {
	}
}
