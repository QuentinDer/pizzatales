package pizzatales;

import java.awt.Image;

public class IceBolt extends Projectile {

	public static Image boltleft, boltright, boltup, boltdown, boltleftup, boltrightup, boltleftdown, boltrightdown;
	
	private float speed;
	private int frozenduration;
	private int style;
	private KaleKing king;
	
	public IceBolt(int startX, int startY, float vectorX, float vectorY,
			int speed, float damage, int range, int frozenduration, int style, KaleKing king) {
		super(startX, startY, vectorX, vectorY, speed, damage, 30, 15, range);
		this.speed = speed;
		this.frozenduration = frozenduration;
		this.style = style;
		this.king = king;
	}
	
	@Override
	public void update() {
		if (style == 3) {
			float diffX = player.centerX - fcenterX;
			float diffY = player.centerY - fcenterY;
			speedX = speed * diffX / (Math.abs(diffX)+Math.abs(diffY));
			speedY = speed * diffY / (Math.abs(diffX)+Math.abs(diffY));
			super.update();
		} else
			super.update();
	}

	@Override
	public Image getSprite() {
		if (Math.abs(speedX)>Math.abs(speedY)) {
			if (speedX > 0) {
				if (Math.abs(speedY)/speed > 0.38f) {
					if (speedY > 0)
						return boltrightdown;
					else
						return boltrightup;
				} else
					return boltright;
			} else {
				if (Math.abs(speedY)/speed > 0.38f) {
					if (speedY > 0)
						return boltleftdown;
					else
						return boltleftup;
				} else
					return boltleft;
			}
		} else {
			if (speedY > 0) {
				if (Math.abs(speedX)/speed > 0.38f) {
					if (speedX > 0)
						return boltrightdown;
					else
						return boltleftdown;
				} else
					return boltdown;
			} else {
				if (Math.abs(speedX)/speed > 0.38f) {
					if (speedX > 0)
						return boltrightup;
					else
						return boltleftup;
				} else
					return boltup;
			}
		}
	}

	@Override
	public void doOnCollision(Player p) {
		if (visible) {
			visible = false;
			StartingClass.leavingitems.add(new FakeItemForFrozen(p,frozenduration));
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
			if (style == 3) {
				king.swapdemand = true;
			}
		}
	}

	@Override
	public void doOnCollision(Enemy e) {
		if (visible) {
			visible = false;
			StartingClass.leavingitems.add(new FakeItemForFrozen(e,frozenduration));
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Tile t) {
		if (visible) {
			visible = false;
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnLimitRange() {
		if (visible) {
			visible = false;
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnLimitScreen() {
		if (visible) {
			visible = false;
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Projectile p) {
		if (visible) {
			visible = false;
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

}
