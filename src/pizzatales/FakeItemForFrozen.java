package pizzatales;

import java.awt.Image;

public class FakeItemForFrozen extends Item {

	BlockingStuff frozenguy;
	
	public static Image frozen;
	
	public FakeItemForFrozen(BlockingStuff frozenguy, int frozenduration) {
		super(0, 0, 0, 0, false, 0);
		this.frozenguy = frozenguy;
		effectTimer = frozenduration;
		effectactive = true;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return false;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		return false;
	}

	@Override
	protected void doEffect(Player p) {
	}

	@Override
	protected void doEffect(Enemy e) {
	}

	@Override
	protected void doLeavingEffect() {
		if (Player.class.isInstance(frozenguy))
			((Player)frozenguy).setMOVESPEED(0.f);
		else
			((Enemy)frozenguy).setSpeed(0.f);
	}

	@Override
	protected Image getSprite() {
		return null;
	}

	@Override
	protected Image getEffectSprite() {
		return frozen;
	}

	@Override
	protected int getEffectCenterX() {
		return frozenguy.getCenterX();
	}

	@Override
	protected int getEffectCenterY() {
		return frozenguy.getCenterY();
	}

	@Override
	protected boolean isEffectAbove() {
		return true;
	}

	
	
}
