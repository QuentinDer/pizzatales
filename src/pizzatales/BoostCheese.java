package pizzatales;

import java.awt.Image;

public class BoostCheese extends BackgroundItem {
	
	public BoostCheese(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image boostsprite;
	public static Image boosteffectsprite;

	@Override
	protected void doEffect(Player p) {
		effectactive = true;
		effectTimer = 1800;
		player.isGrinning = 1800;
	}

	@Override
	protected Image getSprite() {
		return boostsprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		return boosteffectsprite;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doLeavingEffect() {
		player.getWeapon().setProjectiledmg(player.getWeapon().getProjectiledmg()*2);
	}

	@Override
	protected int getEffectCenterX() {
		return player.getCenterX();
	}

	@Override
	protected int getEffectCenterY() {
		return player.getCenterY();
	}

	@Override
	protected boolean isEffectAbove() {
		return false;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		return false;
	}

	@Override
	protected void doEffect(Enemy e) {
	}
}