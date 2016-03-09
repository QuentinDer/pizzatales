package pizzatales;

import java.awt.Image;

public class ChestOpen extends Explosion {

	public static Image explosionsprite;
	
	public ChestOpen(int startX, int startY) {
		super(startX+50, startY+44, 120, 75, 0, 36000, 20);
	}

	@Override
	public Image getSprite() {
		return explosionsprite;
	}

}
