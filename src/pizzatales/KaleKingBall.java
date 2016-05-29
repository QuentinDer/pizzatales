package pizzatales;

import java.awt.Image;

public class KaleKingBall extends Projectile {

	public static Image sprite;
	private KaleKing king;
	private float angle;
	private float dist;
	private float fccX;
	private float fccY;
	private boolean fixed;
	private int fixedcounter;
	
	public KaleKingBall(int startX, int startY, float vectorX, float vectorY, float dmg, int range, KaleKing king) {
		super(startX, startY, vectorX, vectorY, 10, dmg, 15, 7, range);
		this.king = king;
		angle = -17.47f;
		dist = 56.6f;
		fccX = king.getCenterX();
		fccY = king.getCenterY();
		fixedcounter = 0;
	}

	@Override
	public void update() {
		angle = (angle -5) % 360;
		if (!fixed && king.swapped)
			fixed = true;
		if (!fixed) {
			fccX = king.getCenterX();
			fccY = king.getCenterY();
		}
		int togox = (int)(fccX + dist*Math.cos(Math.toRadians(angle)));
		int togoy = (int)(fccY + dist*Math.sin(Math.toRadians(angle)));
		dist = fixed?(Math.max(1.f, dist - 0.5f)):(dist + 0.5f);
		if (fixed) {
			fixedcounter++;
			if (fixedcounter > 180)
				visible = false;
				
		}
		speedX = togox - centerX;
		speedY = togoy - centerY;
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
