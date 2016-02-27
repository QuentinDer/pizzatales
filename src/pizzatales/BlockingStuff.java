package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class BlockingStuff extends Stuff {

	protected int posx;
	protected int posy;
	protected Background bg = StartingClass.getBg1();
	public int halfsizex;
	public int halfsizey;
	protected int halfrsizex;
	protected int halfrsizey;
	public Rectangle R;
	
	public BlockingStuff(int centerX, int centerY, int halfsizex, int halfsizey, int halfrsizex, int halfrsizey) {
		super(centerX, centerY);
		if (bg != null) {
			posx = (centerX - bg.getCenterX() + StartingClass.bginitx) / 50;
			posy = (centerY - bg.getCenterY() + StartingClass.bginity) / 50;
			StartingClass.map[posx][posy] = this;
		}
		this.halfsizex = halfsizex;
		this.halfsizey = halfsizey;
		this.halfrsizex = halfrsizex;
		this.halfrsizey = halfrsizey;
		R = new Rectangle(getCenterX() - halfrsizex, getCenterY() - halfrsizey, 2 * halfrsizex, 2 * halfrsizey);
	}

	@Override
	public void update() {
		super.update();
		R.setBounds(getCenterX() - halfrsizex + speedX, getCenterY() - halfrsizey + speedY, 2*halfrsizex, 2*halfrsizey);
	}
	
	public abstract Image getSprite();
}
