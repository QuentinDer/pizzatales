package pizzatales;

import java.awt.Image;

public class HatFedora extends Hat {
	
	private Player player = StartingClass.getPlayer();
	private int previousfirerate;

	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		effectWeapon();
	}
	
	@Override
	public void undoEffect(){
		undoEffectWeapon();
	}

	@Override
	public void effectWeapon() {
		previousfirerate = player.getWeapon().getFireRate();
		player.getWeapon().setFireRate((int)(player.getWeapon().getFireRate()*0.8f));
	}

	@Override
	public void undoEffectWeapon() {
		player.getWeapon().setFireRate(previousfirerate);
	}

	@Override
	public void effectArmor() {
	}

	@Override
	public void undoEffectArmor() {
	}
}
