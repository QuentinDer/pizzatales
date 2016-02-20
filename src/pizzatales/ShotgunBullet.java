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
		StartingClass.hitpoints.add(new HitPoint(p,(getCenterX()-p.getCenterX())/2,(getCenterY()-p.getCenterY())/2));
	}

	@Override
	public void doOnCollision(Enemy e) {
		visible = false;
		StartingClass.hitpoints.add(new HitPoint(e,(getCenterX()-e.getCenterX())/2,(getCenterY()-e.getCenterY())/2));
	}

	@Override
	public void doOnCollision(Tile t) {
		visible = false;
	}

	@Override
	public void doOnLimitRange() {
		visible = false;
	}

	@Override
	public void doOnCollision(Projectile p) {
		visible = false;
	}

}
