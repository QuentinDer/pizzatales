package pizzatales;

import java.awt.Image;

public class Lava extends BackgroundItem {

	int timer = 0;
	int freq = 30;
	
	public Lava(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image lavasprite;
	public static Image lavaeffectsprite;

	@Override
	protected void doEffect() {
		if(timer % freq == 0){
			player.setHealth((int)player.getHealth()-1);
		}
		timer++;
		effectactive = true;
		effectTimer = 30;
	}

	@Override
	protected Image getSprite() {
		return lavasprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		return lavaeffectsprite;
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doLeavingEffect() {
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
		return true;
	}
}