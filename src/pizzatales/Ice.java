package pizzatales;

import java.awt.Image;

public class Ice extends BackgroundItem {

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
		if (r.intersects(p.getCenterX()-6,p.getCenterY()-6,12,12)) {
			doEffect(p);
			return false;
		} else
			return false;
	}
	
	@Override
	public boolean checkCollisionEnemy(Enemy e) {
		if (canDoEffect(e) && r.intersects(e.getCenterX()-6,e.getCenterY()-6,12,12)) {
			doEffect(e);
			return false;
		} else
			return false;
	}

	@Override
	protected Image getSprite() {
		return null;
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
