package pizzatales;

import java.awt.Image;

public class BarrelExplosion extends Explosion {

	public static Image explosionsprite;
	
	public BarrelExplosion(int startX, int startY) {
		super(startX, startY, 120, 75, 1, 80, 20);
	}

	@Override
	public Image getSprite() {
		return explosionsprite;
	}

}
