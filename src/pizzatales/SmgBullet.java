package pizzatales;

import java.awt.Image;

public class SmgBullet extends Projectile {
	
	public static Image bulletsprite;
	
	public SmgBullet(int startX, int startY, float vectorX, float vectorY, int speed, float dmg, int range) {
		super(startX, startY, vectorX, vectorY, speed, dmg, 15, 7, range);
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
		p.isHurt = true;
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
		if (DestroyableTile.class.isInstance(t))
			StartingClass.hitpoints.add(new HitPoint(t,(getCenterX()-t.getCenterX())/2,(getCenterY()-t.getCenterY())/2));
	}

	@Override
	public void doOnLimitRange() {
		visible = false;
	}

	@Override
	public void doOnCollision(Projectile p) {
		visible = false;
	}

	@Override
	public void doOnLimitScreen() {
		visible = false;
	}
}
