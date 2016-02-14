package pizzatales;

import java.awt.Image;

public class ArmorPotion extends Item {

	public static Image armorpotionsprite;
	
	public ArmorPotion(int centerX, int centerY) {
		super(centerX, centerY);
	}

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

}