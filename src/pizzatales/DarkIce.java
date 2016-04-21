package pizzatales;

import java.awt.Image;

public class DarkIce extends Item {

	public static Image ready;
	public static Image strike;
	
	private int timer;
	private float dmg;
	
	private boolean hasHitPlayer;
	private int frozenduration;
	
	public DarkIce(int x, int y, int timerstrike, int timerdmg, float dmg, int frozenduration) {
		super(x, y, 0, 0, false, 0);
		this.timer = timerstrike;
		this.effectactive = true;
		this.effectTimer = timerstrike+timerdmg;
		this.dmg = dmg;
		this.frozenduration = frozenduration;
	}
	
	@Override
	public boolean checkCollisionPlayer(Player p) {
		if (r.intersects(p.getCenterX()-6,p.getCenterY()-6,12,12)) {
			doEffect(p);
			return false;
		} else
			return false;
	}
	

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		return false;
	}

	@Override
	protected void doEffect(Player p) {
		
	}

	@Override
	protected void doEffect(Enemy e) {
	}

	@Override
	protected void doLeavingEffect() {
		if (timer > 0)
			timer--;
		if (timer == 0 && !hasHitPlayer && player.R.intersects(r)) {
			hasHitPlayer = true;
			StartingClass.leavingitems.add(new FakeItemForFrozen(player,frozenduration));
			player.damage(dmg);
		}
		/*if (hasHitPlayer) {
			player.setMOVESPEED(Math.max(1, player.getArmor().speed/2.f));
		}*/
		/*if (hasHitPlayer) {
			player.setMOVESPEED(0);
			player.setSpeedX(0.f);
			player.setSpeedY(0.f);
		}*/
	}

	@Override
	protected Image getSprite() {
		return null;
	}

	@Override
	protected Image getEffectSprite() {
		if (timer > 0)
			return ready;
		else
			return strike;
	}

	@Override
	protected int getEffectCenterX() {
		return centerX;
	}

	@Override
	protected int getEffectCenterY() {
		return centerY;
	}

	@Override
	protected boolean isEffectAbove() {
		return false;
	}

}
