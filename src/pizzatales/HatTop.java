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
		player.getWeapon().projectiledmg *= 1.3f;
	}

	@Override
	public String getID() {
		return "HATTOP";
	}
	
}
