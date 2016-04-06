package pizzatales;

import java.awt.Image;

public class BoostGarlic extends Boost {
	
	public BoostGarlic(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image boostsprite;
	public static Image boosteffectsprite;

	@Override
	protected void doEffect(Player p) {
		effectactive = true;
		effectTimer = 1800;
		player.isGrinning = 1800;
	}

	@Override
	protected Image getSprite() {
		return boostsprite;
	}
	
	@Override
	protected Image getEffectSprite(){
		return boosteffectsprite;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected void doLeavingEffect() {
		player.setDefBoost(0.5f);
		for (Enemy e : StartingClass.enemyarray) {
			int i = 0;
			while (i < e.getProjectiles().size()) {
				Projectile p = e.getProjectiles().get(i);
				if (player.R.intersects(p.fcenterX+2*p.speedX- p.halfsize, p.fcenterY+2*p.speedY- p.halfsize, p.size, p.size)) {
					player.damage(p.damage);
					p.speedX = -p.speedX;
					p.speedY = -p.speedY;
					p.damage = p.damage / 2.f;
					p.travelleddist = 0;
					e.getProjectiles().remove(i);
					player.projectiles.add(p);
				} else
					i++;
			}
		}
	}

	@Override
	protected int getEffectCenterX() {
		return player.getCenterX();
	}

	@Override
	protected int getEffectCenterY() {
		return player.getCenterY();
	}

	@Override
	protected boolean isEffectAbove() {
		return false;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		return false;
	}

	@Override
	protected void doEffect(Enemy e) {
	}
}