package pizzatales;

import java.awt.Image;

public class WaterFlow extends Item {
	
	public WaterFlow(int x, int y, int deltapy, boolean onetimeeffect) {
		super(x, y, deltapy, onetimeeffect);
	}

	public static Image waterflowsprite;
	public static Image watereffectsprite;

	@Override
	protected void doEffect() {
		if (player.getArmor().speed >= 4)
			player.setMOVESPEED(player.getArmor().speed/2);
		effectTimer = 3;
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