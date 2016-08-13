package pizzatales;

import java.awt.Image;

public class ReaperBarrelProjectile extends Projectile {

	public static Image sprite;
	private int life;
	
	public ReaperBarrelProjectile(int startX, int startY, float vectorX,
			float vectorY, int speed, float damage, int size, int halfsize,
			int range) {
		super(startX, startY, vectorX, vectorY, speed, damage, size, halfsize, range);
		canbedestroyed = true;
		life = 4;
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public void doOnCollision(Player p) {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Enemy e) {
		/*if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}*/
	}

	@Override
	public void doOnCollision(Tile t) {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnLimitRange() {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnLimitScreen() {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Projectile p) {
		if (visible) {
			life -= p.damage;
			if (life <= 0) {
				visible = false;
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
			}
		}
	}

}
