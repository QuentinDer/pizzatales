package pizzatales;

import java.awt.Image;

public class ArmorPotion extends Item {

	public ArmorPotion(int x, int y, boolean onetimeeffect) {
		super(x, y, onetimeeffect);
	}

	public static Image armorpotionsprite;

	@Override
	protected void doEffect() {
		if (player.getArmor().defense + 5 < player.getArmor().MAXDEF) {
			player.getArmor().setDefense(player.getArmor().getDefense()+5);
		} else
			player.getArmor().setDefense(player.getArmor().MAXDEF);
	}

	@Override
	protected Image getSprite() {
		return armorpotionsprite;
	}

	@Override
	protected boolean canDoEffect() {
		return player.getArmor().defense != player.getArmor().MAXDEF;
	}

}