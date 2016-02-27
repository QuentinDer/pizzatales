package pizzatales;

import java.awt.Image;

public class TomatoProjectile extends Projectile {

	public static Image tomatoprojectilesprite;
	private int life;
	
	public TomatoProjectile(int startX, int startY, int vectorX, int vectorY) {
		super(startX, startY, vectorX, vectorY, 7, 6, 62, 31, 350);
		canbedestroyed = true;
		life = 3;
	}

	@Override
	public Image getSprite() {
		return tomatoprojectilesprite;
	}

	@Override
	public void doOnCollision(Player p) {
		if (visible) {
			visible = false;
			StartingClass.getExplosions().add(new TomatoProjectileExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Enemy e) {
		if (visible) {
			visible = false;
			Tato t1 = new Tato(this.centerX,this.centerY);
			if (StartingClass.difficultylevel > 3) {
				t1.setWeapon(new Shotgun());
				t1.range = 170;
				if (StartingClass.TESTMODE)
					t1.showHealthBar = true;
			}
			StartingClass.getEnemyarray().add(t1);
		}
	}

	@Override
	public void doOnCollision(Tile t) {
		if (visible) {
			visible = false;
			Tato t1 = new Tato(this.centerX,this.centerY);
			if (StartingClass.difficultylevel > 3) {
				t1.setWeapon(new Shotgun());
				t1.range = 170;
				if (StartingClass.TESTMODE)
					t1.showHealthBar = true;
			}
			StartingClass.getEnemyarray().add(t1);
		}
	}

	@Override
	public void doOnLimitRange() {
		if (visible) {
			visible = false;
			Tato t = new Tato(this.centerX,this.centerY);
			if (StartingClass.difficultylevel > 3) {
				t.setWeapon(new Shotgun());
				t.range = 170;
				if (StartingClass.TESTMODE)
					t.showHealthBar = true;
			}
			StartingClass.getEnemyarray().add(t);
		}
	}

	@Override
	public void doOnCollision(Projectile p) {
		if (visible) {
			if (life > 0)
				life -= p.damage;
			else {
				visible = false;
				StartingClass.getExplosions().add(new TomatoProjectileExplosion(this.centerX,this.centerY));
			}
		}
	}
}
