package pizzatales;

import java.awt.Image;

public class BazookaBullet extends Projectile {

	public static Image bulletsprite, bulletspriteLeft, bulletspriteRight, bulletspriteUp, bulletspriteDown;
	private int aiming;
	private int life;
	
	public BazookaBullet(int startX, int startY, int vectorX, int vectorY, int speed, float dmg, int range) {
		super(startX, startY, vectorX, vectorY, speed, dmg, 30, 15, range);
		if (vectorX > 0) {
			aiming = 3;
		} else if (vectorX < 0) {
			aiming = 1;
		} else if (vectorY > 0) {
			aiming = 4;
		} else {
			aiming = 2;
		}
		life = 1;
		canbedestroyed = true;
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

	@Override
	public void doOnLimitRange() {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BazookaBulletExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Projectile p) {
		if (visible) {
			if (life > 0)
				life -= p.damage;
			else {
				visible = false;
				StartingClass.getExplosions().add(new BazookaBulletExplosion(this.centerX,this.centerY));
			}
		}
	}

	@Override
	public void doOnLimitScreen() {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BazookaBulletExplosion(this.centerX,this.centerY));
		}
	}
}
