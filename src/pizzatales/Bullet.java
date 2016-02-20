package pizzatales;

import java.awt.Image;

public class Bullet extends Projectile {
	
	public static Image bulletsprite;
	
	public Bullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 10, 1, 15, 7, 500);
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
