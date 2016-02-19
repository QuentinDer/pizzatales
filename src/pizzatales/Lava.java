package pizzatales;

import java.awt.Image;

public class Lava extends Item {

	int timer = 0;
	int freq = 30;
	
	public Lava(int x, int y, boolean onetimeeffect) {
		super(x, y, onetimeeffect);
	}

	public static Image lavasprite;
	public static Image lavaeffectsprite;

	@Override
	protected void doEffect() {
		if(timer % freq == 0){
			player.setHealth(player.getHealth()-1);
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

}