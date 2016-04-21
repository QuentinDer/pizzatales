package pizzatales;

import java.awt.Image;

public class SummonedIce extends BackgroundItem {

	private int effectTimer;
	private boolean isHealing;
	public static Image healingice;
	public Image previousbackground;
	
	public SummonedIce(int x, int y, int deltapx, int deltapy,
			boolean onetimeeffect, int height, boolean healing) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
		initEffectTimer();
		isHealing = healing;
	}
	
	@Override
	public void update() {
		super.update();	
		r.setBounds(getCenterX() - 25, getCenterY() - 25, 50, 50);
		if(effectTimer > 0){
			effectTimer--;
		}
		if(effectTimer == 0){
			effectactive = false;
		}
		if (effectTimer > 0)
			effectTimer--;
		else
			pleaseremove = true;
	}
	
	public void initEffectTimer() {
		effectTimer = StartingClass.difficultylevel*600+600;
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
		if (!isHealing)
			p.sliding = true;
		if (isHealing && effectTimer % 15 == 0)
			p.setHealth(Math.min(p.getMaxHealth(), p.getHealth()+1));
	}
	
	@Override
	public boolean checkCollisionPlayer(Player p) {
		if (r.intersects(p.getCenterX()-10,p.getCenterY()-10,20,20)) {
			doEffect(p);
			return false;
		} else
			return false;
	}

	@Override
	protected void doEffect(Enemy e) {
	}

	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected Image getSprite() {
		return null;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
	}

	@Override
	protected int getEffectCenterX() {
		return 0;
	}

	@Override
	protected int getEffectCenterY() {
		return 0;
	}

	@Override
	protected boolean isEffectAbove() {
		return false;
	}

}
