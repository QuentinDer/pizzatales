package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Item extends Stuff {

	protected Rectangle r;
	private boolean onetimeeffect;
	public boolean removable;
	
	public Item(int x, int y, boolean onetimeeffect) {
		super((x * 50) + 25,(y * 50) + 40);
		this.onetimeeffect = onetimeeffect;
		r = new Rectangle(getCenterX() - 22, getCenterY() - 22, 45, 45);
	}
	
	@Override
	public void update() {
		super.update();	
		r.setBounds(getCenterX() - 22, getCenterY() - 22, 45, 45);
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
	
	protected abstract Image getSprite();
}
