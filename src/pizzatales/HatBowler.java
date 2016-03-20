package pizzatales;

import java.awt.Image;

public class HatBowler extends Hat {
	
	private Player player = StartingClass.getPlayer();

	public HatBowler() {
		deltay = -4;
	}
	
	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		effectArmor();
	}
	
	@Override
	public void undoEffect(){
		undoEffectArmor();
	}

	@Override
	public void effectWeapon() {
	}

	@Override
	public void undoEffectWeapon() {
	}

	@Override
	public void effectArmor() {
		player.getArmor().setDefense(player.getArmor().getDefense()+8);
		player.getArmor().MAXDEF += 8;
	}

	@Override
	public void undoEffectArmor() {
		player.getArmor().setDefense(Math.max(0, player.getArmor().getDefense()-8));
		player.getArmor().MAXDEF -= 8;
	}
	
}
