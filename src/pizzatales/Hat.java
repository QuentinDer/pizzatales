package pizzatales;

import java.awt.Image;

public abstract class Hat {
	
	public int index;

	public int deltay;
	
	public abstract Image getSprite();
	
	public abstract void effect();
	
	public abstract void undoEffect();
}
