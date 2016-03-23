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
		effectTimer = 20;
		effectactive = true;
	}
	
	@Override
	protected void undoEffect(Player p){
	}
	
	@Override
	public boolean checkCollisionPlayer(Player p) {
		if (r.contains(p.getCenterX(), p.getCenterY())) {
			doEffect(p);
			return false;
		} else
			return false;
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
		player.setMOVESPEED(Math.max(1, player.getArmor().speed/2));
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