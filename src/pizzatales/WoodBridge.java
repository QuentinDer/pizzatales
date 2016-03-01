package pizzatales;

import java.awt.Image;

public class WoodBridge extends Item {

	public static Image sprite;
	
	public WoodBridge(int x, int y, int deltapx, int deltapy, boolean onetimeeffect) {
		super(x, y, deltapx, deltapy, onetimeeffect);
	}

	@Override
	protected boolean canDoEffect() {
		return false;
	}

	@Override
	protected void doEffect() {
	}

	@Override
	protected Image getSprite() {
		return sprite;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
	}

}