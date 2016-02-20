package pizzatales;

import java.awt.Image;

public class TomatoProjectileExplosion extends Explosion {

	public static Image tomatoexplosionsprite;
	
	public TomatoProjectileExplosion(int startX, int startY) {
		super(startX, startY, 62, 31, 1, 60, 15);
	}

	@Override
	public Image getSprite() {
		return tomatoexplosionsprite;
	}
	
}
