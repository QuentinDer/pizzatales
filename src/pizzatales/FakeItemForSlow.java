package pizzatales;

import java.awt.Image;

public class FakeItemForSlow extends Item {
	
	public FakeItemForSlow(int slowDuration) {
		super(0, 0, 0, 0, true, 0);
		effectTimer = slowDuration;
		effectactive = true;
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
		
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
		if (player.getArmor().speed >= 4)
			player.setMOVESPEED(player.getArmor().speed/2);
	}

}
