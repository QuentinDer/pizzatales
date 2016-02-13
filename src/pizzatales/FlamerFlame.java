package pizzatales;

import java.awt.Image;

public class FlamerFlame extends Projectile {
	
	public static Image bulletsprite;
	
	public FlamerFlame(int startX, int startY, float vectorX, float vectorY) {
		super(startX, startY, vectorX, vectorY, 10, 1, 30, 15, 100);
	}
	
	@Override
	public Image getSprite(){
		return bulletsprite;
	}
	
	public void setSprite (Image image){
		bulletsprite = image;
	}

	@Override
	public boolean hasEffect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doOnCollision(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doOnCollision(Enemy e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doOnCollision(Tile t) {
		// TODO Auto-generated method stub
		
	}
}
