package pizzatales;

import java.awt.Image;

public class WaterFlow extends Item {
	
	public WaterFlow(int x, int y, boolean onetimeeffect) {
		super(x, y, onetimeeffect);
		removable = false;
	}

	public static Image waterflowsprite;
	public static Image watereffectsprite;

	@Override
	protected void doEffect() {
		//player.getArmor().speed = (int) player.getArmor().speed/2;
		effectactive = true;
	}

	@Override
	protected Image getSprite() {
		return waterflowsprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		return watereffectsprite;
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

}