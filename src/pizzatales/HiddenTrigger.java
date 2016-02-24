package pizzatales;

import java.awt.Image;

public class HiddenTrigger extends Item {

	private int hiddenarea = -1;
	
	public HiddenTrigger(int x, int y, int deltapy, boolean onetimeeffect) {
		super(x, y, deltapy, onetimeeffect);
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
		StartingClass.revealHidden = hiddenarea;
	}
	
	public void setHiddenAreaNumber(int hiddenareanumber) {
		hiddenarea = hiddenareanumber;
	}

	@Override
	protected Image getSprite() {
		return null;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
	}
	
}
