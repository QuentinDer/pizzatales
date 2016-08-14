package pizzatales;

import java.awt.Image;

public class FakeShiningItem extends Item {

	public static Image shining1, shining2, shining3, shining4;
	
	public FakeShiningItem(int centerX, int centerY) {
		super(0, 0, 0, 0, false, 0);
		effectactive = true;
		effectTimer = Integer.MAX_VALUE;
		setCenterX(centerX);
		setCenterY(centerY);
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
	}

	@Override
	protected Image getSprite() {
		return null;
	}

	@Override
	protected Image getEffectSprite() {
		switch(effectTimer/8 % 4) {
		case 0:
			return shining1;
		case 1:
			return shining2;
		case 2:
			return shining3;
		default:
			return shining4;
		}
	}

	@Override
	protected int getEffectCenterX() {
		return centerX;
	}

	@Override
	protected int getEffectCenterY() {
		return centerY;
	}

	@Override
	protected boolean isEffectAbove() {
		return true;
	}

}
