package pizzatales;

import java.awt.Image;

public class HatTop extends Hat {

	public HatTop() {
		deltay = -7;
	}
	
	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
}
