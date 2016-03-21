package pizzatales;

import java.awt.Image;

public class CrateOpen extends Item {

	public static Image sprite;
	
	public CrateOpen(int x, int y, int deltapx, int deltapy,
			boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
		effectactive = true;
		effectTimer = 300;
	}
	
	@Override
	public boolean checkCollisionPlayer(Player p) {
		return false;
	}
	
	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doEffect(Player p) {
	}

	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected Image getSprite() {
		return null;
	}

	@Override
	protected Image getEffectSprite() {
		return sprite;
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
