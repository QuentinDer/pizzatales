package pizzatales;

import java.awt.Image;

public class HiddenTrigger extends Item {

	private int hiddenarea = -1;
	
	public HiddenTrigger(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doEffect(Player p) {
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

	@Override
	protected void doLeavingEffect() {
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
