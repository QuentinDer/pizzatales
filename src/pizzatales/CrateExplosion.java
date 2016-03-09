package pizzatales;

import java.awt.Image;

public class CrateExplosion extends Explosion {

	public static Image explosionsprite;
	
	public CrateExplosion(int startX, int startY) {
		super(startX+50, startY+44, 120, 75, 0, 300, 20);
	}

	@Override
	public Image getSprite() {
		return explosionsprite;
	}

}
