package pizzatales;

import java.awt.Image;

public class HatSherlock extends Hat {
	
	private Player player = StartingClass.getPlayer();

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
		player.getWeapon().range *= 1.2f;
	}

	@Override
	public void undoEffectWeapon() {
		player.getWeapon().range = (int)(player.getWeapon().range/1.2f);
	}

	@Override
	public void effectArmor() {
	}

	@Override
	public void undoEffectArmor() {
	}
	
}
