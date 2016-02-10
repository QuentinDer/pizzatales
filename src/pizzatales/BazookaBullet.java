package pizzatales;

import java.awt.Image;

public class BazookaBullet extends Projectile {

	public static Image bulletsprite;
	
	public BazookaBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 450;
		this.size = 30;
		this.halfsize = 15;
		this.damage = 12;
	}
	
	@Override
	public Image getSprite(){
		return bulletsprite;
	}
}
