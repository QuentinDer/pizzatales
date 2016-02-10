package pizzatales;

import java.awt.Image;

public class RifleBullet extends Projectile {
	
	public static Image bulletsprite;
	
	public RifleBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 480;
		this.width = 5;
		this.height = 5;
		this.damage = 2;
	}
	
	@Override
	public Image getSprite(){
		return bulletsprite;
	}
}