package pizzatales;

import java.awt.Image;

public class Ice extends BackgroundItem {

	public static Image sprite;
	
	public Ice(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doEffect(Player p) {
		player.sliding = true;
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
	public boolean checkCollisionEnemy(Enemy e) {
		if (canDoEffect(e) && e.R.intersects(r)) {
			doEffect(e);
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
		return true;
	}

	@Override
	protected void doEffect(Enemy e) {
		e.sliding = true;
	}
}
