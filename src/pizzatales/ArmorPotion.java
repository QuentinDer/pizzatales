package pizzatales;

import java.awt.Image;

public class ArmorPotion extends Item {

	public ArmorPotion(int x, int y, int deltapy, boolean onetimeeffect) {
		super(x, y, deltapy, onetimeeffect);
	}

	public static Image armorpotionsprite;
	public static Image armorpotioneffectsprite;

	@Override
	protected void doEffect() {
		if (player.getArmor().defense + 5 < player.getArmor().MAXDEF) {
			player.getArmor().setDefense(player.getArmor().getDefense()+5);
		} else
			player.getArmor().setDefense(player.getArmor().MAXDEF);
		effectactive = true;
		effectTimer = 30;
	}

	@Override
	protected Image getSprite() {
		return armorpotionsprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		return armorpotioneffectsprite;
	}

	@Override
	protected boolean canDoEffect() {
		return player.getArmor().defense != player.getArmor().MAXDEF;
	}

}