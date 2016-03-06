package pizzatales;

import java.awt.Image;

public class HatBowler extends Hat {

	public HatBowler() {
		deltay = -4;
	}
	
	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
}
