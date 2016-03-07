package pizzatales;

import java.awt.Image;

public class HatPanama extends Hat {
	
	private Player player = StartingClass.getPlayer();

	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		player.setHealth(player.getHealth()+5);
		player.setMaxHealth(player.getMaxHealth()+5);
	}
	
	@Override
	public void undoEffect(){
		player.setHealth(Math.max(1.0f, player.getHealth()-5));
		player.setMaxHealth(player.getMaxHealth()-5);
	}

	@Override
	public void effectWeapon() {
	}

	@Override
	public void undoEffectWeapon() {
	}

	@Override
	public void effectArmor() {
	}

	@Override
	public void undoEffectArmor() {
	}

}
