package pizzatales;

import java.awt.Image;

public class BoostBacon extends Item {

	int target;
	private final static int angleincrease = 3;
	
	public BoostBacon(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
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
		
		boolean atleastoneenemyalive = false;
		for (Enemy e : StartingClass.enemyarray) {
			if (e.alive)
				atleastoneenemyalive = true;
		}
		if (atleastoneenemyalive) {
			for(int j = 0; j < player.getProjectiles().size(); j++){
				Projectile p = player.getProjectiles().get(j);
				int target = -1;
				float prevDist = Float.MAX_VALUE;
				float dist;
				float distX, distY;
				for(int i = 0; i< StartingClass.enemyarray.size(); i++){
					Enemy e = StartingClass.enemyarray.get(i);
					if(e.alive){
						distX = p.getCenterX() - e.getCenterX();
						distY = p.getCenterY() - e.getCenterY();
						dist = (float) Math.sqrt(distX*distX+distY*distY);
						if(dist < prevDist){
							target = i;
							prevDist = dist;
						}
					}
				}
				float speed = (float) Math.sqrt(p.fspeedX*p.fspeedX+p.fspeedY*p.fspeedY);
				float fdirX = StartingClass.enemyarray.get(target).getCenterX() - p.fcenterX;
				float fdirY = StartingClass.enemyarray.get(target).getCenterY() - p.fcenterY;
				dist = (float) Math.sqrt(fdirX*fdirX+fdirY*fdirY);
				float vectorx = fdirX / dist;
				float vectory = fdirY / dist;
				double angledir = Math.toDegrees(Math.acos(vectorx));
				if (vectory < 0)
					angledir = 360-angledir;
				vectorx = p.fspeedX / speed;
				vectory = p.fspeedY / speed;
				double anglespeed = Math.toDegrees(Math.acos(vectorx));
				if (vectory < 0)
					anglespeed = 360-anglespeed;
				if (anglespeed < 180) {
					if (angledir > anglespeed && angledir < anglespeed + 180)
						anglespeed = Math.min(anglespeed+angleincrease, angledir);
					else if (angledir >= anglespeed + 180)
						anglespeed = Math.max(anglespeed - angleincrease, angledir-360);
					else
						anglespeed = Math.max(anglespeed - angleincrease, angledir);
				} else {
					if (angledir < anglespeed && angledir > anglespeed - 180)
						anglespeed = Math.max(anglespeed-angleincrease, angledir);
					else if (angledir <= anglespeed - 180)
						anglespeed = Math.min(anglespeed + angleincrease, angledir+360);
					else
						anglespeed = Math.min(anglespeed + angleincrease, angledir);
				}
				p.fspeedX = (float)(speed * Math.cos(Math.toRadians(anglespeed)));
				p.fspeedY = (float)(speed * Math.sin(Math.toRadians(anglespeed)));
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