package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Projectile extends Stuff {

	protected boolean visible;
	protected boolean canbedestroyed;
	protected Rectangle rectP;
	protected float travelleddist;
	protected int range;
	protected int size;
	protected int halfsize;
	public float damage = 1f;
	/*protected float fspeedX;
	protected float fspeedY;
	protected float fcenterX;
	protected float fcenterY;*/
	private Background bg = StartingClass.getBg();

	public Projectile(int startX, int startY, float vectorX, float vectorY, float speed, float damage, int size, int halfsize, int range) {
		super(startX, startY);
		fcenterX = centerX;
		fcenterY = centerY;
		speedX = vectorX * speed;
		speedY = vectorY * speed;
		visible = true;
		this.damage = damage;
		this.size = size;
		this.halfsize = halfsize;
		this.range = range;
		rectP = new Rectangle(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
	}
	
	@Override
	public void update() {
		fcenterX += speedX - player.getScrollingSpeedX();
		fcenterY += speedY - player.getScrollingSpeedY();
		centerX = (int)fcenterX;
		centerY = (int)fcenterY;
		rectP.setBounds(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
		travelleddist += Math.sqrt(Math.abs(speedX)*Math.abs(speedX)+Math.abs(speedY)*Math.abs(speedY));
		if (travelleddist > range) {
			doOnLimitRange();
		}
		if (centerX > 1255 && speedX > 0) {
			doOnLimitScreen();
		}
		if (centerX < 25 && speedX < 0) {
			doOnLimitScreen();
		}
		if (centerY > 775 && speedY > 0) {
			doOnLimitScreen();
		}
		if (centerY < 25 && speedY < 0) {
			doOnLimitScreen();
		}
	}
	
	BlockingStuff checkCollision() {
		int posx = (getCenterX() - bg.getCenterX() + StartingClass.bginitx) / 50;
		int posy = (getCenterY() - bg.getCenterY() + StartingClass.bginity) / 50;
		if (null != StartingClass.map[posx][posy] && rectP.intersects(StartingClass.map[posx][posy].R))
			return StartingClass.map[posx][posy];
		return null;
	}
	
	Tile checkCollisionTile() {
		int posx = (getCenterX() - bg.getCenterX() + StartingClass.bginitx) / 50;
		int posy = (getCenterY() - bg.getCenterY() + StartingClass.bginity) / 50;
		if (null != StartingClass.map[posx][posy] && Tile.class.isInstance(StartingClass.map[posx][posy]) && rectP.intersects(StartingClass.map[posx][posy].R)) {
			return (Tile)StartingClass.map[posx][posy];
		}
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
	
	@Override
	public void setCenterX(int centerX) {
		super.setCenterX(centerX);
		rectP = new Rectangle(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
	}
	
	@Override
	public void setCenterY(int centerY) {
		super.setCenterY(centerY);
		rectP = new Rectangle(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
	}
	
	public abstract Image getSprite();
	
	public abstract void doOnCollision(Player p);
	public abstract void doOnCollision(Enemy e);
	public abstract void doOnCollision(Tile t);
	public abstract void doOnLimitRange();
	public abstract void doOnLimitScreen();
	public abstract void doOnCollision(Projectile p);
}
