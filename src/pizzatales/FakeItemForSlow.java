package pizzatales;

import java.awt.Image;

public class FakeItemForSlow extends Item {
	
	public FakeItemForSlow(int slowDuration) {
		super(0, 0, 0, 0, true, 0);
		effectTimer = slowDuration;
		effectactive = true;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doEffect(Player p) {
		
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doEffect(Enemy e) {
		// TODO Auto-generated method stub
		
	}
}
