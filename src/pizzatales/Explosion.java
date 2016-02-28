package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Explosion extends Stuff {
	
	private boolean visible;
	private Rectangle rectP;
	protected int size;
	protected int halfsize;
	protected int timer;
	protected int time;
	protected int procfrequency;
	public int damage = 1;

	public Explosion(int startX, int startY, int size, int halfsize, int damage, int timer, int procfrequency) {
		super(startX, startY);
		visible = true;
		this.size = size;
		this.halfsize = halfsize;
		rectP = new Rectangle(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
		time = 0;
		this.timer = timer;
		this.damage = damage;
		this.procfrequency = procfrequency;
	}
	
	@Override
	public void update() {
		super.update();
		rectP.setBounds(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
		if (time >= timer) {
			visible = false;
		}
		time++;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean isProcing() {
		return time % procfrequency == 0;
	}
	
	public abstract Image getSprite();
	
	public Rectangle getR() {
		return rectP;
	}
	
	@Override
	public void setCenterY(int centerY) {
		super.setCenterY(centerY);
		rectP.setBounds(getCenterX() - halfsize, getCenterY() - halfsize, size, size);
	}
}
