package pizzatales;

import java.awt.Image;

public class BoostBacon extends BackgroundItem {

	int timer = 0;
	int freq = 30;
	boolean taken=false;
	
	public BoostBacon(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image boostsprite;
	public static Image boosteffectsprite;

	@Override
	protected void doEffect(Player p) {
		if(timer % freq == 0){
			//player.setHealth((int)player.getHealth()-1);
		}
		timer++;
		effectactive = true;
		effectTimer = 1800;
		taken = true;
	}

	@Override
	protected Image getSprite() {
		if(!taken)
			return boostsprite;
		else
			return null;
	}
	
	@Override
	protected Image getEffectSprite(){
		return boosteffectsprite;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return !taken;
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