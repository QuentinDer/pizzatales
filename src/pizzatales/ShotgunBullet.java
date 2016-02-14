package pizzatales;

import java.awt.Image;

public class ShotgunBullet extends Projectile {
	
	public static Image bulletsprite;

	public ShotgunBullet(int startX, int startY, float vectorX, float vectorY) {
		super(startX, startY, vectorX, vectorY, 10, 1, 15, 7, 250);
	}
	
	@Override
	public Image getSprite(){
		return bulletsprite;
	}
	
	public void setSprite (Image image){
		bulletsprite = image;
	}

	@Override
	public void doOnCollision(Player p) {
		visible = false;
	}

	@Override
	public void doOnCollision(Enemy e) {
		visible = false;
	}

	@Override
	public void doOnCollision(Tile t) {
		visible = false;
	}

}
