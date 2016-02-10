package pizzatales;

import java.awt.Image;

public class Bullet extends Projectile {
	
	public static Image bulletsprite;
	
	public Bullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 300;
		this.width = 5;
		this.height = 5;
		this.damage = 1;
	}
	
	@Override
	public Image getSprite(){
		return bulletsprite;
	}
}
