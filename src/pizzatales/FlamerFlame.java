package pizzatales;

import java.awt.Image;

public class FlamerFlame extends Projectile {
	
	public static Image bulletsprite;
	
	public FlamerFlame(int startX, int startY, float vectorX, float vectorY, int speed, float dmg, int range) {
		super(startX, startY, vectorX, vectorY, speed, dmg, 30, 15, range);
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

	@Override
	public void doOnLimitRange() {
		visible = false;
	}

	@Override
	public void doOnCollision(Projectile p) {
		visible = false;
	}
}
