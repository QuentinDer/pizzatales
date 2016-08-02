package pizzatales;

import java.awt.Image;

public class ArmorPotion extends Item {

	public ArmorPotion(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image armorpotionsprite;
	public static Image armorpotioneffectsprite, armorpotioneffectsprite2, armorpotioneffectsprite3, armorpotioneffectsprite4;

	@Override
	protected void doEffect(Player p) {
		if (p.getArmor().defense + 5 < p.getArmor().MAXDEF) {
			p.getArmor().setDefense(p.getArmor().getDefense()+5);
		} else
			p.getArmor().setDefense(p.getArmor().MAXDEF);
		effectactive = true;
		effectTimer = 60;
	}

	@Override
	protected Image getSprite() {
		return armorpotionsprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		switch(effectTimer/8 % 4) {
		case 0:
			return armorpotioneffectsprite;
		case 1:
			return armorpotioneffectsprite2;
		case 2:
			return armorpotioneffectsprite3;
		default:
			return armorpotioneffectsprite4;
		}
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return p.getArmor().defense != p.getArmor().MAXDEF;
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