package pizzatales;

import java.awt.Image;

public class HatTop extends Hat {

	private Player player = StartingClass.getPlayer();

	public HatTop() {
		deltay = -7;
	}
	
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
		player.getWeapon().projectiledmg *= 1.3f;
	}

	@Override
	public void undoEffectWeapon() {
		player.getWeapon().projectiledmg = player.getWeapon().projectiledmg/1.3f;
	}

	@Override
	public void effectArmor() {
	}

	@Override
	public void undoEffectArmor() {
	}
	
}
