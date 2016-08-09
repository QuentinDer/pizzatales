package pizzatales;

import java.awt.Image;

public class BazookaBullet extends Projectile {

	public static Image bulletspriteLeft, bulletspriteRight, bulletspriteUp, bulletspriteDown;
	public static Image bulletrightup, bulletrightdown, bulletleftdown, bulletleftup;
	private int life;
	
	public BazookaBullet(int startX, int startY, float vectorX, float vectorY, int speed, float dmg, int range) {
		super(startX, startY, vectorX, vectorY, speed, dmg, 30, 15, range);
		life = 1;
		canbedestroyed = true;
	}
	
	@Override
	public Image getSprite(){
		Image ans = null;
		if (speedX == 0) {
			if (speedY > 0) {
				ans = bulletspriteDown;
			} else {
				ans = bulletspriteUp;
			}
		} else {
			if (speedY == 0) {
				if (speedX > 0) {
					ans = bulletspriteRight;
				} else {
					ans = bulletspriteLeft;
				}
			} else {
				if (speedX > 0) {
					if (speedY > 0) {
						ans = bulletrightdown;
					} else {
						ans = bulletrightup;
					}
				} else {
					if (speedY > 0) {
						ans = bulletleftdown;
					} else {
						ans = bulletleftup;
					}
				}
			}
		}
		return ans;
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
