package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Item extends Stuff {

	protected Rectangle r;
	private boolean onetimeeffect;
	public boolean effectactive;
	protected int effectTimer;
	public int posx;
	public int posy;
	public int height;
	
	public Item(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height) {
		super((x + deltapx) * 50 + 25,(y + deltapy)* 50 + 40);
		this.onetimeeffect = onetimeeffect;
		r = new Rectangle(getCenterX() - 25, getCenterY() - 25, 50, 50);
		posx = x;
		posy = y;
		this.height = height;
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
	}
	
	public boolean checkCollisionPlayer(Player p) {
		if (p.R.intersects(r) && canDoEffect()) {
			doEffect();
			if (onetimeeffect)
				return true;
			else
				return false;
		} else
			return false;
	}

	protected abstract boolean canDoEffect();
	protected abstract void doEffect();
	protected abstract void doLeavingEffect();
	
	protected abstract Image getSprite();
	protected abstract Image getEffectSprite();
	protected abstract int getEffectCenterX();
	protected abstract int getEffectCenterY();
	protected abstract boolean isEffectAbove();
}
