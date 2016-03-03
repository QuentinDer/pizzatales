package pizzatales;

import java.awt.Image;

public class WaterPuddle extends Item {

	public static Image sprite;
	
	public WaterPuddle(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
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
