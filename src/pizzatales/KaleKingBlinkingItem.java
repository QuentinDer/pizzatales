package pizzatales;

import java.awt.Image;

public class KaleKingBlinkingItem extends Item {

	public static Image sprite;
	public boolean canblink;
	
	public KaleKingBlinkingItem(int x, int y, int deltapx, int deltapy,
			boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
		canblink = true;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		return false;
	}

	@Override
	protected void doEffect(Player p) {
		canblink = false;
	}

	@Override
	protected void doEffect(Enemy e) {
	}

	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected Image getSprite() {
		return sprite;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
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

}
