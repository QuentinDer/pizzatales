package pizzatales;

import java.awt.Image;

public class BoostBasil extends BackgroundItem {

	int timer = 0;
	int freq = 30;
	boolean taken = false;
	private int previousfirerate = player.getWeapon().getFireRate();
	private boolean effectStarted=false;
	
	public BoostBasil(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image boostsprite;
	public static Image boosteffectsprite;
	
	@Override
	public void update(){
		super.update();	
		r.setBounds(getCenterX() - 25, getCenterY() - 25, 50, 50);
		if(effectTimer > 0){
			effectTimer--;
		}
		if(effectTimer == 0){
			effectactive = false;
			if(effectStarted){
				undoEffect(player);
				effectStarted = false;
			}
		}
	}

	@Override
	protected void doEffect(Player p) {
		timer++;
		for(Firearm firearm : StartingClass.playerweapons){
			firearm.setFireRate((int) (firearm.getBaseFirerate()*0.5));
		}
		//player.getWeapon().setFireRate((int)(p.getWeapon().getFireRate()*0.5f));
		effectactive = true;
		effectTimer = 1800;
		StartingClass.isGrinning = 1800;
		effectStarted = true;
		taken = true;
	}

	@Override
	protected Image getSprite() {
		if(!taken)
			return boostsprite;
		else
			return null;
	}
	
	@Override
	protected Image getEffectSprite(){
		return boosteffectsprite;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return !taken;
	}
	
	@Override
	protected void undoEffect(Player p){
		for(Firearm firearm : StartingClass.playerweapons){
			firearm.setFireRate(firearm.getBaseFirerate());
		}
		//player.getWeapon().setFireRate(player.getWeapon().getBaseFirerate());
	}

	@Override
	protected void doLeavingEffect() {
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
		return true;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doEffect(Enemy e) {
		// TODO Auto-generated method stub
		
	}
}