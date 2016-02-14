package pizzatales;

import java.awt.Image;

public class BazookaBullet extends Projectile {

	public static Image bulletsprite, bulletspriteLeft, bulletspriteRight, bulletspriteUp, bulletspriteDown;
	private int aiming;
	
	public BazookaBullet(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 5 , 4, 30, 15, 750);
		if (vectorX > 0) {
			aiming = 3;
		} else if (vectorX < 0) {
			aiming = 1;
		} else if (vectorY > 0) {
			aiming = 4;
		} else {
			aiming = 2;
		}
	}
	
	@Override
	public Image getSprite(){
		switch(aiming) {
		case 1:
			return bulletspriteLeft;
		case 2:
			return bulletspriteUp;
		case 3:
			return bulletspriteRight;
		case 4:
			return bulletspriteDown;
		default:
			return null;
		}
	}

	@Override
	public void doOnCollision(Player p) {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BazookaBulletExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Enemy e) {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BazookaBulletExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Tile t) {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BazookaBulletExplosion(this.centerX,this.centerY));
		}
	}
}
