package pizzatales;

import java.awt.Image;

public class Bullet extends Projectile {
	
	public static Image bulletsprite;
	
	public Bullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10);
		this.range = 300;
		this.size = 15;
		this.halfsize = 7;
		this.damage = 1;
	}
	
	@Override
	public Image getSprite(){
		return bulletsprite;
	}
	
	public void setSprite (Image image){
		bulletsprite = image;
	}
}
