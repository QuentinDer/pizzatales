package pizzatales;

import java.awt.Image;

public class CrateOpen extends Item {

	public static Image sprite;
	private int counter;
	
	public CrateOpen(int x, int y, int deltapx, int deltapy,
			boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
		counter = 300;
	}
	
	@Override
	public boolean checkCollisionPlayer(Player p) {
		counter--;
		return counter <= 0;
	}
	
	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
	}

	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected Image getSprite() {
		return sprite;
	}

	@Override
	protected Image getEffectSprite() {
		// TODO Auto-generated method stub
		return null;
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
}
