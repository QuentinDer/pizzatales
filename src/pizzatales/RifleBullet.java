package pizzatales;

import java.awt.Image;

public class RifleBullet extends Projectile {
	
	public static Image bulletsprite;
	
	public RifleBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10, 2, 15, 7, 600);
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
		StartingClass.blooddropx.add((p.getCenterX()+getCenterX())/2);
		StartingClass.blooddropy.add((p.getCenterY()+getCenterY())/2);
		StartingClass.bloodtimer.add(10);
	}

	@Override
	public void doOnCollision(Enemy e) {
		visible = false;
		StartingClass.blooddropx.add((e.getCenterX()+getCenterX())/2);
		StartingClass.blooddropy.add((e.getCenterY()+getCenterY())/2);
		StartingClass.bloodtimer.add(10);
	}

	@Override
	public void doOnCollision(Tile t) {
		visible = false;
	}

	@Override
	public void doOnLimitRange() {
		visible = false;
	}
}