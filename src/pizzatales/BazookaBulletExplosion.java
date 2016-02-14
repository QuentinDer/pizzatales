package pizzatales;

import java.awt.Image;

public class BazookaBulletExplosion extends Explosion {

	public static Image bazookaexplosionsprite;
	
	public BazookaBulletExplosion(int startX, int startY) {
		super(startX, startY, 120, 75, 1, 70, 10);
	}

	@Override
	public Image getSprite() {
		return bazookaexplosionsprite;
	}

}
