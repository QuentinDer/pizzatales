package pizzatales;

import java.awt.Image;
import java.net.URL;

public class BazookaBulletExplosion extends Explosion {

	public static Image bazookaexplosionsprite;
	
	public BazookaBulletExplosion(int startX, int startY) {
		super(startX, startY, 150, 75, 1, 70, 10);
	}

	@Override
	public Image getSprite() {
		return bazookaexplosionsprite;
	}

}
