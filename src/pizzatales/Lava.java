package pizzatales;

import java.awt.Image;

public class Lava extends Item {

	int timer = 0;
	int freq = 30;
	
	public Lava(int x, int y, boolean onetimeeffect) {
		super(x, y, onetimeeffect);
		removable = false;
	}

	public static Image lavasprite;

	@Override
	protected void doEffect() {
		if(timer % freq == 0){
			player.setHealth(player.getHealth()-1);
		}
		timer++;
	}

	@Override
	protected Image getSprite() {
		return lavasprite;
	}

	@Override
	protected boolean canDoEffect() {
		return player.getArmor().defense != player.getArmor().MAXDEF;
	}

}