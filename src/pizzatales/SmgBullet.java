package pizzatales;

import java.awt.Image;

public class SmgBullet extends Projectile {
	
	public static Image bulletsprite;
	
	public SmgBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 180;
		this.width = 5;
		this.height = 5;
		this.damage = 1;
	}
	
	@Override
	public Image getSprite(){
		return bulletsprite;
	}
}
