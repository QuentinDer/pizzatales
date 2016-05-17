package pizzatales;

import java.awt.Image;

public class BazookaBullet extends Projectile {

	public static Image bulletsprite, bulletspriteLeft, bulletspriteRight, bulletspriteUp, bulletspriteDown;
	private int life;
	
	public BazookaBullet(int startX, int startY, int vectorX, int vectorY, int speed, float dmg, int range) {
		super(startX, startY, vectorX, vectorY, speed, dmg, 30, 15, range);
		life = 1;
		canbedestroyed = true;
	}
	
	@Override
	public Image getSprite(){
		if (Math.abs(speedX)>Math.abs(speedY)) {
			if (speedX > 0)
				return bulletspriteRight;
			else
				return bulletspriteLeft;
		} else {
			if (speedY > 0)
				return bulletspriteDown;
			else
				return bulletspriteUp;
		}
	}

	@Override
	public void doOnCollision(Player p) {
		p.isHurt = true;
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
