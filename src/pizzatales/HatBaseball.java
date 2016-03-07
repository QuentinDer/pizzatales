package pizzatales;

import java.awt.Image;

public class HatBaseball extends Hat {
	
	private Player player = StartingClass.getPlayer();
	
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
		player.getArmor().setSpeed(player.getArmor().getSpeed()+1);
	}

	@Override
	public void undoEffectArmor() {
		player.getArmor().setSpeed(player.getArmor().getSpeed()-1);
	}

}
