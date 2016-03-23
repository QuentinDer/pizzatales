package pizzatales;

import java.awt.Image;

public class HealthPotion extends Item {

	public HealthPotion(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image healthpotionsprite;
	public static Image healthpotioneffectsprite;


	@Override
	protected void doEffect(Player p) {
		if (p.getHealth() + 5 > p.getMaxHealth()) {
			p.setHealth(p.getMaxHealth());
		} else {
			p.setHealth(p.getHealth() + 5);
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
	protected boolean canDoEffect(Player p) {
		return p.getHealth() != 20;
	}

	@Override
	protected void undoEffect(Player p){
	}
	
	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected int getEffectCenterX() {
		return 0;
	}

	@Override
	protected int getEffectCenterY() {
		return 0;
	}
	
	@Override
	protected boolean isEffectAbove() {
		return false;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doEffect(Enemy e) {
		// TODO Auto-generated method stub
		
	}
}
