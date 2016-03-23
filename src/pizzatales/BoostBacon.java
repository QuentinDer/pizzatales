package pizzatales;

import java.awt.Image;

public class BoostBacon extends BackgroundItem {

	int timer = 0;
	int freq = 30;
	boolean taken=false;
	private boolean effectStarted=false;
	float distX, distY, dist, prevDist;
	int target;
	int speed;
	
	public BoostBacon(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
	}

	public static Image boostsprite;
	public static Image boosteffectsprite;
	
	@Override
	public void update(){
		super.update();	
		r.setBounds(getCenterX() - 25, getCenterY() - 25, 50, 50);
		speed = player.getWeapon().getSpeed();
		if(effectTimer > 0){
			effectTimer--;
			for(int i = 0; i< StartingClass.enemyarray.size(); i++){
				Enemy e = StartingClass.enemyarray.get(i);
				if(e.alive){
					distX = player.getCenterX() - e.getCenterX();
					distY = player.getCenterY() - e.getCenterY();
					dist = (float) Math.sqrt(distX*distX+distY*distY);
					if(i == 0){
						target = i;
						prevDist = dist;
					}
					if(dist < prevDist){
						target = i;
						prevDist = dist;
					}
				}
			}
			for(int j = 0; j < player.getProjectiles().size(); j++){
				Projectile p = player.getProjectiles().get(j);
				p.fspeedX = (-1) * speed * distX / dist;
				p.fspeedY = (-1) * speed * distY / dist;
			}
			
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
		effectactive = true;
		effectTimer = 1800;
		StartingClass.isGrinning = 1800;
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