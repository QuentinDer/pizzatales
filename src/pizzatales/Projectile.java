package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Projectile extends Stuff {

	protected boolean visible;
	protected boolean canbedestroyed;
	protected Rectangle rectP;
	private int initX;
	private int initY;
	private int initbgx;
	private int initbgy;
	protected int range;
	protected int size;
	protected int halfsize;
	public int damage = 1;
	private Background bg = StartingClass.getBg1();

	public Projectile(int startX, int startY, float vectorX, float vectorY, int speed, int damage, int size, int halfsize, int range) {
		super(startX, startY);
		speedX = (int)(vectorX * speed);
		speedY = (int)(vectorY * speed);
		initX = startX;
		initY = startY;
		initbgx = bg.getCenterX();
		initbgy = bg.getCenterY();
		visible = true;
		this.damage = damage;
		this.size = size;
		this.halfsize = halfsize;
		this.range = range;
		rectP = new Rectangle(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
	}
	
	@Override
	public void update() {
		super.update();
		rectP.setBounds(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
		if (Math.abs(centerX - bg.getCenterX() + initbgx - initX) > range) {
			doOnLimitRange();
		}
		if (Math.abs(centerY - bg.getCenterY() + initbgy - initY) > range) {
			doOnLimitRange();
		}
		if (centerX > 1240 && speedX > 0) {
			doOnLimitRange();
		}
		if (centerX < 40 && speedX < 0) {
			doOnLimitRange();
		}
	}
	
	BlockingStuff checkCollision() {
		int posx = (getCenterX() - bg.getCenterX() + StartingClass.bginitx) / 50;
		int posy = (getCenterY() - bg.getCenterY() + StartingClass.bginity) / 50;
		if (null != StartingClass.map[posx][posy] && rectP.intersects(StartingClass.map[posx][posy].R))
			return StartingClass.map[posx][posy];
		return null;
	}

	boolean checkCollision(Enemy e) {
		if(rectP.intersects(e.R)){
			return true;
		}
		return false;
	}
	
	boolean checkCollision(Tile t) {
		if(rectP.intersects(t.R)){
			return true;
		}
		return false;
	}
	
	public boolean checkCollision(Projectile p) {
		if (rectP.intersects(p.rectP))
			return true;
		else
			return false;
	}
	
	boolean checkCollision(Player p) {
		if(rectP.intersects(p.R)){
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
	
	public abstract void doOnCollision(Player p);
	public abstract void doOnCollision(Enemy e);
	public abstract void doOnCollision(Tile t);
	public abstract void doOnLimitRange();
	public abstract void doOnCollision(Projectile p);
}
