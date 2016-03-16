package pizzatales;

import java.awt.Image;

public class Ice extends BackgroundItem {

	public static Image sprite;
	
	public Ice(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
		player.sliding = true;
	}
	
	@Override
	public boolean checkCollisionPlayer(Player p) {
		if (r.contains(p.getCenterX(), p.getCenterY())) {
			doEffect();
			return false;
		} else
			return false;
	}

	@Override
	protected Image getSprite() {
		return sprite;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
	}

	@Override
	protected void doLeavingEffect() {
	}

}
