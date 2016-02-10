package pizzatales;

import java.awt.Image;

public class ShotgunBullet extends Projectile {
	
	public static Image bulletsprite;

	public ShotgunBullet(int startX, int startY, float vectorX, float vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 150;
		this.width = 5;
		this.height = 5;
		this.damage = 1;
	}
	
	@Override
	public Image getSprite(){
		return bulletsprite;
	}

}
