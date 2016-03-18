package pizzatales;

import java.awt.Image;

public class OnioughProjectile extends Projectile {

	public static Image sprite;
	private int type;
	private int cX, cY;
	private OnioughProjectile pc;
	
	public OnioughProjectile(int startX, int startY, float vectorX, float vectorY) {
		super(startX, startY, vectorX, vectorY, 10, 2, 15, 7, 400);
		type = 0;
	}
	
	public OnioughProjectile(int startX, int startY, float vectorX, float vectorY, int cX, int cY) {
		super(startX, startY, vectorX, vectorY, 10, 2, 15, 7, 400);
		type = 1;
		this.cX = cX;
		this.cY = cY;
	}
	
	public OnioughProjectile(int startX, int startY, float vectorX, float vectorY, OnioughProjectile pc) {
		super(startX, startY, vectorX, vectorY, 10, 2, 15, 7, 400);
		type = 2;
		this.pc = pc;
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
