package pizzatales;

import java.awt.Image;

public class SnowBank extends BackgroundItem {

	public static Image sprite;
	public static Image snoweffectsprite;
	private boolean wasActive;
	
	public SnowBank(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
		player.setMOVESPEED(player.getArmor().speed-1);
	}

	@Override
	protected Image getSprite() {
		return sprite;
	}

	@Override
	protected Image getEffectSprite() {
		return snoweffectsprite;
	}

	@Override
	protected void doLeavingEffect() {
	}
	
	@Override
	public boolean checkCollisionPlayer(Player p) {
		if (r.contains(p.getCenterX(), p.getCenterY()+27)) {
			doEffect();
			wasActive = true;
			return false;
		} else {
			if (wasActive) {
				effectTimer = 120;
				effectactive = true;
				wasActive = false;
			}
			return false;
		}
	}

	@Override
	protected int getEffectCenterX() {
		return centerX;
	}

	@Override
	protected int getEffectCenterY() {
		return centerY;
	}

	@Override
	protected boolean isEffectAbove() {
		return false;
	}
}
