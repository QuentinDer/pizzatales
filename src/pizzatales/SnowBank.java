package pizzatales;

import java.awt.Image;

public class SnowBank extends BackgroundItem {

	public static Image snoweffectsprite;
	private boolean wasActive;
	private int icY;
	private int icX;
	private int ieY;
	private int ieX;
	
	public SnowBank(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doEffect(Player p) {
		//player.setMOVESPEED(player.getArmor().speed-1);
		player.setMOVESPEED(Math.max(1, player.getArmor().speed/2.f));
	}

	@Override
	protected Image getSprite() {
		return null;
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
		if (r.contains(p.getCenterX(), p.getCenterY())) {
			doEffect(p);
			wasActive = true;
			return false;
		} else {
			if (wasActive) {
				effectTimer = 120;
				effectactive = true;
				icX = centerX;
				icY = centerY;
				ieX = Math.max(centerX-15, Math.min(centerX+15, player.getCenterX()));
				ieY = Math.max(centerY-50, Math.min(centerY, player.getCenterY()));
				wasActive = false;
			}
			return false;
		}
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
	protected int getEffectCenterX() {
		return ieX + centerX - icX;
	}

	@Override
	protected int getEffectCenterY() {
		return ieY + centerY - icY;
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
		//e.setSpeed(Math.max(1, e.getDefaultSpeed() - 1));
		e.setSpeed(Math.max(1, e.getDefaultSpeed()/2.f));
	}
}
