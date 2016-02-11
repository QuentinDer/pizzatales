package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Projectile extends Stuff {

	private boolean visible;
	private Rectangle rectP;
	private int initX;
	private int initY;
	protected int range;
	protected int size;
	protected int halfsize;
	public int damage = 1;

	public Projectile(int startX, int startY, float vectorX, float vectorY, int speed) {
		super(startX, startY);
		speedX = (int)(vectorX * speed);
		speedY = (int)(vectorY * speed);
		initX = startX;
		initY = startY;
		visible = true;
		rectP = new Rectangle(0, 0, 0, 0);
	}

	@Override
	public void update() {
		super.update();
		rectP.setBounds(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
		if (Math.abs(this.getCenterX() - initX) > range) {
			visible = false;
		}
		if (Math.abs(this.getCenterY() - initY) > range) {
			visible = false;
		}
	}

	boolean checkCollision(Enemy e) {
		if(rectP.intersects(e.R) /*|| rectP.intersects(e.rectY)*/){
			visible = false;
			return true;
		}
		return false;
	}
	
	boolean checkCollision(Tile t) {
		if (t.getType() != '0') {
			if(rectP.intersects(t.getR())){
				visible = false;
				return true;
			}
			return false;
		}
		return false;
	}
	
	boolean checkCollision(Player p) {
		if(rectP.intersects(p.R)){
			visible = false;
			return true;
		}
		return false;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Rectangle getR() {
		return rectP;
	}
	
	public abstract Image getSprite();

	public abstract void setSprite(Image sprite);

}
