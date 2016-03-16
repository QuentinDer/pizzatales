package pizzatales;

import java.awt.Image;

public class WaterFlow extends BackgroundItem {
	
	public WaterFlow(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image waterflowsprite;
	public static Image watereffectsprite;

	@Override
	protected void doEffect(Player p) {
		player.setMOVESPEED(Math.max(1, player.getArmor().speed/2));
		effectTimer = 3;
		effectactive = true;
	}

	@Override
	protected Image getSprite() {
		return waterflowsprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		return watereffectsprite;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
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
		return false;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		return true;
	}

	@Override
	protected void doEffect(Enemy e) {
		e.setSpeed(Math.max(1, e.getDefaultSpeed()/2));
	}
}