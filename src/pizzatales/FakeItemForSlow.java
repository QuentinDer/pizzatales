package pizzatales;

import java.awt.Image;

public class FakeItemForSlow extends Item {
	
	private int slow;
	
	public FakeItemForSlow(int slowDuration, int slow) {
		super(0, 0, 0, 0, true, 0);
		effectTimer = slowDuration;
		effectactive = true;
		this.slow = slow;
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
		if (slow == 0)
			player.setMOVESPEED(Math.max(1, player.getArmor().speed/2.f));
		else
			player.setMOVESPEED(Math.max(1, player.getArmor().speed-slow));
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
