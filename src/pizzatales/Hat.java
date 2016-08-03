package pizzatales;

import java.awt.Image;

public abstract class Hat {

	public int deltay;
	
	public abstract Image getSprite();
	public abstract Image getAddSprite();
	
	public abstract void effect();
	
	public abstract String getID();
}
