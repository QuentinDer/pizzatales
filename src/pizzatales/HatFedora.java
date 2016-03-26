package pizzatales;

import java.awt.Image;

public class HatFedora extends Hat {
	
	private Player player = StartingClass.getPlayer();

	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		player.getWeapon().setFireRate((int)(player.getWeapon().getFireRate()*0.8f));
	}
}
