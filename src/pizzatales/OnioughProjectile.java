package pizzatales;

import java.awt.Image;

public class OnioughProjectile extends Projectile {

	public static Image sprite;
	private int type;
	private float fccX;
	private float fccY;
	private float fscX;
	private float fscY;
	private int angle;
	private int dist;
	
	public OnioughProjectile(int startX, int startY, float vectorX, float vectorY, int speed, int range) {
		super(startX, startY, vectorX, vectorY, speed, 1.5f, 15, 7, range);
		type = 0;
	}
	
	public OnioughProjectile(int startX, int startY, float vectorX, float vectorY, int cspeed, int angle, int dist) {
		super(startX, startY, vectorX, vectorY, 10, 0.7f, 15, 7, 10000);
		this.angle = angle;
		this.dist = dist;
		type = 1;
		fccX = startX;
		fccY = startY;
		fscX = vectorX*cspeed;
		fscY = vectorY*cspeed;
	}
	
	
	@Override
	public void update() {
		if (type == 1) {
			angle = (angle +5) % 360;
			fccX += fscX;
			fccY += fscY;
			int togox = (int)(fccX + dist*Math.cos(Math.toRadians(angle)));
			int togoy = (int)(fccY + dist*Math.sin(Math.toRadians(angle)));
			fspeedX = togox - centerX;
			fspeedY = togoy - centerY;
			super.update();
		} else
			super.update();
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public void doOnCollision(Player p) {
		visible = false;
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
	public void doOnLimitScreen() {
		visible = false;
	}

	@Override
	public void doOnCollision(Projectile p) {
		visible = false;
	}

}
