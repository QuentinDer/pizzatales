package pizzatales;

import java.awt.Image;

public class SnowBank extends BackgroundItem {

	public static Image sprite;
	public static Image snoweffectsprite;
	
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
		effectTimer = 120;
		effectactive = true;
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
			return false;
		} else
			return false;
	}

}
