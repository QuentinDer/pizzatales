package pizzatales;

import java.awt.Image;

public class ArmorPotion extends Item {

	public ArmorPotion(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image armorpotionsprite;
	public static Image armorpotioneffectsprite;

	@Override
	protected void doEffect(Player p) {
		if (p.getArmor().defense + 5 < p.getArmor().MAXDEF) {
			p.getArmor().setDefense(p.getArmor().getDefense()+5);
		} else
			p.getArmor().setDefense(p.getArmor().MAXDEF);
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
	protected boolean canDoEffect(Player p) {
		return p.getArmor().defense != p.getArmor().MAXDEF;
	}
	
	@Override
	protected void undoEffect(Player p){
	}

	@Override
	protected void doLeavingEffect() {
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