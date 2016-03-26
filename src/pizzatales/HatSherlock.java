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
		player.getWeapon().range *= 1.3f;
	}
	
}
